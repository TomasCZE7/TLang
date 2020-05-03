package processor;

import core.ApplicationMain;
import core.variable.DataType;
import core.variable.DataTypeSection;
import core.variable.Variable;
import math.LangMath;

import java.util.Arrays;
import java.util.HashMap;

class VariableAction{
    private Variable variable;
    private boolean newVariable;

    public VariableAction(Variable variable, boolean newVariable) {
        this.variable = variable;
        this.newVariable = newVariable;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public boolean isNewVariable() {
        return newVariable;
    }

    public void setNewVariable(boolean newVariable) {
        this.newVariable = newVariable;
    }
}

public class VariableProcessor extends Processor {

    private HashMap<String, Variable> variables = new HashMap<>();

    private final String[] variableKeyWords = {  };
    private final String[] mashSymbols = { "+", "-", "*", "/" };

    public void process(String source){
        String[] sides = source.split("=");
        String endingCharacter = sides[0].substring(sides[0].length()-1);
        VariableAction variableAction = processAction(sides[0]);
        if(variableAction == null){
            return;
        }
        Variable var = variableAction.getVariable();
        if(var == null){
            return;
        }
        if(arrayContains(mashSymbols, endingCharacter)) {
            sides[1] = var.getName()+endingCharacter + sides[1];
        }
        Object value = processValue(sides[1], var);
        var.setValue(value);

        variables.put(var.getName(), var);
        if(variableAction.isNewVariable())
            ApplicationMain.tLang.getVariableTable().addRow(var.getName(), var.getType(), var.getValue());
        else
            ApplicationMain.tLang.getVariableTable().updateRow(var.getName(), var.getValue());
    }

    private Object processValue(String line, Variable var) {
        line = replaceAllSpaces(line);
        line = preprocessValue(line);
        if (var.getType().getTypeSection().equalsOrAny(DataTypeSection.TEXT) && isString(line)) {
            if (var.getType() == DataType.AUTO) {
                var.setType(DataType.TEXT);
            }
            return getString(line);
        } else if (var.getType().getTypeSection().equalsOrAny(DataTypeSection.NUMBER)) {
            long value = 0;
            for (String split : line.split("\\+")) {
                value += Long.parseLong(split);
            }
            return value;
        }
        return null;
    }

    private String preprocessValue(String line) {
        for (String l : line.split("\\+")) {
            if (!isString(l) && !LangMath.isNumber(l)) {
                Variable var = getVariableFromString(l);
                if (var != null) {
                    String value = var.getValue().toString();
                    if(!LangMath.isNumber(value)){
                        line = line.replace(var.getName(), "\""+var.getValue().toString()+"\"");
                    } else {
                        line = line.replace(var.getName(), var.getValue().toString());
                    }
                } else {
                    throw new IllegalArgumentException("Variable "+l+" wasn't found");
                }
            }
        }
        return line;
    }

    private VariableAction processAction(String line) {
        if(line.contains(":")) {
            String[] split = line.split(":");
            for (int i = 0; i < split.length; i++)
                split[i] = split[i].trim();
            int length = split.length;
            if (length > 1) {
                String name = split[length - 2];
                DataType type = DataType.getDataTypeFromString(split[length - 1].toUpperCase());
                if (type == null)
                    throw new IllegalArgumentException("Invalid data type of variable " + name);
                return new VariableAction(new Variable(type, name), true);
            } else
                throw new IllegalArgumentException("Missing type or name of the variable.");
        } else {
            for(String split : line.split(" ")){
                if(arrayContains(variableKeyWords, split))
                    continue;

                Variable var = getVariableFromString(split);
                if(var == null){
                    throw new NullPointerException("Variable "+split+" is not defined.");
                }
                return new VariableAction(var, false);
            }
        }
        return null;
    }
    @Override
    public void clear() {
        variables.clear();
        ApplicationMain.tLang.getVariableTable().clear();
    }

    public boolean removeVariable(Variable var){
        if(variables.containsValue(var)){
            variables.remove(var.getName());
            return true;
        }
        return false;
    }

    public Variable getVariable(String name) {
        return variables.getOrDefault(name, null) ;
    }
}
