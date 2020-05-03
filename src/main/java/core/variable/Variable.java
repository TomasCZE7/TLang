package core.variable;

public class Variable {

    private DataType type;
    private String name;
    private Object value;

    public Variable(DataType type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public Variable(DataType type, String name){
        this(type, name, null);
        if(type.getTypeSection() == DataTypeSection.TEXT)
            this.value = "";
         else if(type.getTypeSection() == DataTypeSection.NUMBER)
            this.value = 0;
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
    }
}
