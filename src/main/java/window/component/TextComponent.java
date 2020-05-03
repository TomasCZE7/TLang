package window.component;

import math.Vector2;

import javax.swing.*;

public class TextComponent extends Component {

    private int width, height;


    public TextComponent(String text, Vector2<Integer> position) {
        super(null);
        JLabel label = new JLabel(text);
        setComponent(label);
        this.width = label.getFont().getSize()*(text.length()+2);
        this.height = label.getFont().getSize();
        setSize(new Vector2<>(width, height));
        setPosition(position);
    }

}
