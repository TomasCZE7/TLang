package core.variable;

public enum DataType {

    NUMBER8(Byte.class, DataTypeSection.NUMBER), NUMBER16(Short.class, DataTypeSection.NUMBER),
    NUMBER32(Integer.class, DataTypeSection.NUMBER), TEXT(String.class, DataTypeSection.TEXT);

    private Class javaType;
    private DataTypeSection typeSection;

    DataType(Class javaType, DataTypeSection typeSection){
        this.javaType = javaType;
        this.typeSection = typeSection;
    }

    public Class getJavaType() {
        return javaType;
    }

    public DataTypeSection getTypeSection() {
        return typeSection;
    }

    public static DataType getDataTypeFromString(String dataTypeString){
        for(DataType dataType : DataType.values()){
            if(dataType.name().equals(dataTypeString.toUpperCase())){
                return dataType;
            }
        }
        return null;
    }

}
