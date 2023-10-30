package org.firstinspires.ftc.teamcode;

/**
 * A point.  The coordinate system we are using is horizontal and vertical translation in a rotated
 *  XY coordinate plane, and our unit of distance is nanoseconds.
 */
public final class Point {

    public double x;
    public double y;
    public double rotation;

    public Point(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    /**
     * Adds two points together (doesn't modify existing points)
     * @param addend the other addend
     * @return the sum of the two points
     */
    public Point add(Point addend) {
        return new Point(x + addend.x, y + addend.y, rotation + addend.rotation);
    }

    /**
     * Negates all coordinates in the point (doesn't modify the existing point)
     * @return the negated Point
     */
    public Point negate() {
        return multiply(-1);
    }

    /**
     * Subtracts a given point from this point (doesn't modify existing points)
     * @param other the point to subtract by
     * @return the resulting point
     */
    public Point subtract(Point other) {
        return add(other.negate());
    }

    /**
     * Multiplies two points together (doesn't modify existing points)
     * @param factor the other factor
     * @return the product of the two points
     */
    public Point multiply(double factor) {
        return new Point(x * factor, y * factor, rotation * factor);
    }
}
