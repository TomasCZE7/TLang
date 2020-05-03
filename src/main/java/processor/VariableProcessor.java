package processor;

import core.ApplicationMain;
import core.variable.DataType;
import core.variable.Variable;

import java.util.HashMap;

public class VariableProcessor extends Processor {

    private HashMap<String, Variable> variables = new HashMap<>();

    public VariableProcessor(){
        ApplicationMain.tLang.getVariableTable().clear();
    }

    public void process(String source){
        String[] split = source.split("=");
        String[] initialize = split[0].split(" ");
        for(int i = 0; i < initialize.length; i++)
            initialize[i] = initialize[i].replace(" ", "");
        if(initialize.length > 1){
            DataType type = DataType.getDataTypeFromString(initialize[0]);
            if(type == null){
                throw new IllegalArgumentException("Invalid data type of variable "+
                        initialize[initialize.length-1]);
            }
            Variable var = new Variable(type, initialize[initialize.length-1], split[1]);
            variables.put(var.getName(), var);
            ApplicationMain.tLang.getVariableTable().addRow(var.getName(), var.getType(), var.getValue());
        }
    }

    @Override
    public void clear() {
        variables.clear();
    }
}
