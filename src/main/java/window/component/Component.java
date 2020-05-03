package window.component;

import math.Vector2;
import math.Vector4;

import javax.swing.*;

public class Component {

    private JComponent component;

    Vector2<Integer> size, position;

    public Component(JComponent component){
        this.component = component;
        if(getComponent() == null)
            return;
        getComponent().setLayout(null);
    }

    public Component setSize(Vector2<Integer> size){
        this.size = size;
        component.setSize(size.getV1(), size.getV2());
        return this;
    }

    public Component setPosition(Vector2<Integer> position){
        this.position = position;
        component.setLocation(position.getV1(), position.getV2());
        return this;
    }

    public Component setPositionAndSize(Vector4<Integer> positionAndSize){
        Vector2<Vector2<Integer>> vector2 = positionAndSize.getTwoVector2();
        setPosition(vector2.getV1());
        setSize(vector2.getV2());
        return this;
    }

    public JComponent getComponent() {
        return component;
    }

    protected void setComponent(JComponent component) {
        this.component = component;
    }

    public Component show() {
        component.setVisible(true);
        return this;
    }

    public Vector2<Integer> getPosition() {
        return position;
    }

    public Vector2<Integer> getSize() {
        return size;
    }
}
