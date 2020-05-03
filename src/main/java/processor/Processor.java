package processor;

import java.util.regex.Pattern;

public abstract class Processor {

    public abstract void process(String line);

    public abstract void clear();

    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public final boolean isNumber(String s){
        if(s == null)
            return false;

        return pattern.matcher(s).matches();
    }
}
