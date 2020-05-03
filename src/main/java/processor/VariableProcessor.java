package processor;

import core.ApplicationMain;
import core.variable.DataType;
import core.variable.DataTypeSection;
import core.variable.Variable;
import math.LangMath;

import java.util.HashMap;

public class VariableProcessor extends Processor {

    private HashMap<String, Variable> variables = new HashMap<>();

    public void process(String source){
        String[] sides = source.split("=");
        Variable var = processAction(sides[0]);
        processValue(sides[1], var);
        variables.put(var.getName(), var);
        ApplicationMain.tLang.getVariableTable().addRow(var.getName(), var.getType(), var.getValue());
    }

    private void processValue(String line, Variable var) {
        line = replaceAllSpaces(line);
        if(var.getType().getTypeSection() == DataTypeSection.TEXT){
            if(line.contains("\""))
                var.setValue(getString(line));
        } else if(var.getType().getTypeSection() == DataTypeSection.NUMBER){
            long value = 0;
            for(String split : line.split("\\+")){
                split = replaceAllSpaces(split);
                if(LangMath.isNumber(split)){
                    value += Long.parseLong(split);
                }
            }
            var.setValue(value);
        }
        if(!LangMath.isNumber(line) && getVariableFromString(line) != null){
            Variable gotVar = getVariableFromString(line);
            if(gotVar == null)
                throw new IllegalArgumentException("Not found variable " +line);
            if(var.getType() == DataType.AUTO){
                var.setType(gotVar.getType());
            }
            var.setValue(gotVar.getValue());
        }
    }

    private Variable processAction(String line) {
        if(line.contains(":")){
            String[] split = line.split(":");
            for(int i = 0; i < split.length; i++)
                split[i] = split[i].trim();
            int length = split.length;
            if(length > 1){
                String name = split[length-2];
                DataType type = DataType.getDataTypeFromString(split[length-1].toUpperCase());
                if(type == null)
                    throw new IllegalArgumentException("Invalid data type of variable "+name);
                return new Variable(type, name);
            } else
                throw new IllegalArgumentException("Missing type or name of the variable.");
        }
        return null;
    }

    public Variable getVariable(String name) {
        return variables.getOrDefault(name, null);
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
}
