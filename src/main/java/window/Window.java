package window;

import math.Vector2;
import window.component.Component;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class Window {

    private final JFrame frame;
    private List<window.component.Component> componentList = new ArrayList<>();

    public Window(String title, WindowType type){
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(type.getCloseOperation());
    }

    public Window setSize(Vector2<Integer> size){
        return setSize(size.getV1(), size.getV2());
    }

    public Window setSize(int width, int height){
        frame.setSize(width, height);
        return this;
    }

    public Window open(){
        for (window.component.Component component : componentList)
            component.show();
        frame.setLayout(null);
        frame.setVisible(true);
        return this;
    }

    public Window addComponent(Component component){
        frame.add(component.getComponent());
        componentList.add(component);
        return this;
    }

    public JFrame getFrame() {
        return frame;
    }
}
