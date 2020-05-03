package math;

import java.util.regex.Pattern;

public class LangMath {
    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumber(String s){
        if(s == null)
            return false;

        return pattern.matcher(s).matches();
    }


}
