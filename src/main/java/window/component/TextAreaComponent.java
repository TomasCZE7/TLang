package window.component;

import javax.swing.*;

public class TextAreaComponent extends Component {

    public TextAreaComponent() {
        super(new JTextArea());
    }

    public TextAreaComponent addText(String text){
        getTextArea().append(text);
        return this;
    }

    public JTextArea getTextArea(){
        return (JTextArea) getComponent();
    }
}
