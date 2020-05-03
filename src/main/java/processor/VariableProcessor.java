package processor;

import core.ApplicationMain;
import core.variable.DataType;
import core.variable.DataTypeSection;
import core.variable.Variable;

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
        line = line.trim();
        if(var.getType().getTypeSection() == DataTypeSection.TEXT){
            if(line.contains("\"")){
                String[] plusSplit = line.split("\\+");
                String[] allStrings = new String[plusSplit.length];
                for(int i = 0; i < allStrings.length; i++){
                    String l = plusSplit[i];
                    String[] strings = l.split("\"");
                    if(strings.length < 2 || strings.length > 3){
                        if (!isNumber(strings[0]))
                            throw new IllegalArgumentException("String is wrongly specified.");
                        else {
                            allStrings[i] = strings[0];
                            continue;
                        }
                    }
                    allStrings[i] = strings[1];
                }
                String output = "";
                for(String s : allStrings)
                    output += s;
                var.setValue(output);
            }
        } else if(var.getType().getTypeSection() == DataTypeSection.NUMBER){
            long value = 0;
            for(String split : line.split("\\+")){
                split = split.trim();
                if(isNumber(split)){
                    value += Long.parseLong(split);
                }
            }
            var.setValue(value);
        }
    }

    private Variable processAction(String line) {
        line = line.trim();
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

    public HashMap<String, Variable> getVariables() {
        return variables;
    }

    @Override
    public void clear() {
        variables.clear();
        ApplicationMain.tLang.getVariableTable().clear();

    }
}
