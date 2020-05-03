package processor;

import core.ApplicationMain;
import core.variable.Variable;
import math.LangMath;

import java.util.function.DoubleUnaryOperator;
import java.util.regex.Pattern;

public abstract class Processor {

    public abstract void process(String line);

    public abstract void clear();

    public final String getString(String s) {
        String output = "";
        String[] split = s.split("\\+");
        for (int i = 0; i < split.length; i++) {
            String[] stringSplit = split[i].split("\"");
            if (stringSplit.length < 2 || stringSplit.length > 3) {
                if (!LangMath.isNumber(stringSplit[0])) {
                    Variable variable = ApplicationMain.tLang.getCompilingProcessor().getVariableProcessor().
                            getVariableFromString(stringSplit[0]);
                    if (variable == null)
                        throw new IllegalArgumentException("String is wrongly specified.");
                    output += variable.getValue();
                    continue;
                } else {
                    output += stringSplit[0];
                    continue;
                }
            }
            output += stringSplit[1];
        }
        return output.isEmpty() ? null : output;
    }

    public Variable getVariableFromString(String name){
        return ApplicationMain.tLang.getCompilingProcessor().getVariableProcessor().getVariable(name);
    }

    public String replaceAllSpaces(String line){
        boolean inString = false;
        String output = "";
        for(char c : line.toCharArray()){
            if(c == ' ' && !inString)
                continue;
            if(c == '"')
                inString = !inString;
            output += c;
        }
        return output;
    }

    public boolean isString(String s ){
        return s.startsWith("\"") && s.startsWith("\"");
    }
    public boolean isValidString(String s){
        return s.length() - s.replaceAll("\"","").length() == 2;
    }

    public static boolean arrayContains(String[] arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue))
                return true;
        }
        return false;
    }


}
