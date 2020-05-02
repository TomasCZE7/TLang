package math;

public class Vector3<T> extends Vector2<T> {

    protected T v3;

    public Vector3(T v1, T v2, T v3) {
        super(v1, v2);
        this.v3 = v3;
    }

    public T getV3() {
        return v3;
    }

    public void setV3(T v3) {
        this.v3 = v3;
    }
}
