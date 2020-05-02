package window.component;

import math.Vector2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonComponent extends Component {

    public ButtonComponent(String title) {
        super(new JButton(title));
        int fontSize = getButton().getFont().getSize();
        int width = fontSize*(title.length()+2);
        setSize(new Vector2<Integer>(width, (int)(fontSize*1.5)));
    }

    public ButtonComponent addActionListener(ActionListener e){
        getButton().addActionListener(e);
        return this;
    }

    public JButton getButton(){
        return (JButton) getComponent();
    }

}
