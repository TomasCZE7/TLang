package core.variable;

import core.ApplicationMain;
import math.LangMath;
import processor.VariableProcessor;

public class Variable {

    private DataType type;
    private String name;
    private Object value;

    public Variable(DataType type, String name, Object value) {
        this.type = type;
        this.name = name;
        setValue(value);
    }

    public Variable(DataType type, String name){
        this(type, name, null);
        if(type.getTypeSection() == DataTypeSection.TEXT)
            setValue("");
        else if(type.getTypeSection() == DataTypeSection.NUMBER)
             setValue(0);
    }

    public DataType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
        if(value == null)
            return;
        if(getType().getTypeSection() == DataTypeSection.NUMBER){
            if(!LangMath.isNumber(value.toString())){
                ApplicationMain.tLang.getCompilingProcessor().getVariableProcessor().removeVariable(this);
                throw new NumberFormatException(getValue()+" cannot be converted to data type "+getType());
            }
        }
    }
}
