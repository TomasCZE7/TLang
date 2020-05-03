package window.component;

import javax.swing.*;
import java.awt.*;

public class ScrollPanelComponent extends Component {

    JScrollPane scrollPane;

    public ScrollPanelComponent(JComponent component) {
        super(null);
        scrollPane = new JScrollPane(component);
        setComponent(scrollPane);
    }

    public ScrollPanelComponent addComponent(Component component){
        getScrollPane().add(component.getComponent());
        return this;
    }

    public JScrollPane getScrollPane(){
        return (JScrollPane) getComponent();
    }
}
