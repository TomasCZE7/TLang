package math;

public class Vector4<T> extends Vector3<T> {

    protected T v4;

    public Vector4(T v1, T v2, T v3, T v4) {
        super(v1, v2, v3);
        this.v4 = v4;
    }

    public T getV4() {
        return v4;
    }

    public void setV4(T v4) {
        this.v4 = v4;
    }

    public Vector2<Vector2<T>> getTwoVector2(){
        return new Vector2<Vector2<T>>(new Vector2<T>(v1, v2), new Vector2<T>(v3, v4));
    }
}
