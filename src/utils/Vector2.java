package utils;

public class Vector2 {

    public float x;
    public float y;
    public float magnitude = x*x+y*y;

    public static Vector2 zero = new Vector2(0.0f, 0.0f);
    public static Vector2 up = new Vector2(-1.0f, 0f);
    public static Vector2 down = new Vector2(1.0f, 0.0f);
    public static Vector2 left = new Vector2(0.0f, -1.0f);
    public static Vector2 right = new Vector2(0.0f, 1.0f);
    public static Vector2 one = new Vector2(1.0f, 1.0f);
    public static Vector2 positiveInfinity = new Vector2(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    public static Vector2 negativeInfinity = new Vector2(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    public Vector2() {
        this.x = 0f;
        this.y = 0f;
    }

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    public static double distance(Vector2 a, Vector2 b) {
        float v0 = b.x - a.x;
        float v1 = b.y - a.y;
        return Math.sqrt(v0*v0 + v1*v1);
    }

    public void normalize() {
        double length = Math.sqrt(x*x + y*y);

        if (length != 0.0) {
            float s = 1.0f / (float)length;
            x = x*s;
            y = y*s;
        }
    }

    public void Add(Vector2 vectorToAdd){
        x += vectorToAdd.x;
        y += vectorToAdd.y;
    }

    public void Subract(Vector2 vectorToSubtract){
        x -= vectorToSubtract.x;
        y -= vectorToSubtract.y;
    }

    public String ToString(){
        return x + " " + y;
    }
}
