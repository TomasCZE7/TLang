package core.variable;

public enum DataTypeSection {

    NUMBER, TEXT, OBJECT, ANY;

    public boolean equalsOrAny(DataTypeSection section){
        return (this == section || this == ANY);
    }

}