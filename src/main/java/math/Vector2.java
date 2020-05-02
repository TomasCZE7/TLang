package math;

public class Vector2<T> {

    protected T v1, v2;

    public Vector2(T v1, T v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public T getV2() {
        return v2;
    }

    public T getV1() {
        return v1;
    }

    public void setV1(T v1) {
        this.v1 = v1;
    }

    public void setV2(T v2) {
        this.v2 = v2;
    }
}
