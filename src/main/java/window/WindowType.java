package window;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JFrame.DO_NOTHING_ON_CLOSE;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public enum WindowType {

    EDITOR(EXIT_ON_CLOSE),
    COMPILER_CONSOLE(DISPOSE_ON_CLOSE),
    DEBUG(DO_NOTHING_ON_CLOSE);

    private int closeOperation;

    WindowType(int closeOperation){
        this.closeOperation = closeOperation;
    }

    public int getCloseOperation() {
        return closeOperation;
    }
}
