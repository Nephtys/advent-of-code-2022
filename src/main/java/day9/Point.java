package day9;

public class Point {
    private int x;
    private int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point at(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return x + 31 * y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void moveLeft() {
        this.x--;
    }
    public void moveRight() {
        this.x++;
    }
    public void moveUp() {
        this.y++;
    }
    public void moveDown() {
        this.y--;
    }
    public void moveTowards(Point otherPoint) {
        if (Math.abs(this.x - otherPoint.x) <= 1 && Math.abs(this.y - otherPoint.y) <= 1) {
            return;
        }
        if (this.x == otherPoint.x) {
            this.y = this.y - Integer.compare(this.y, otherPoint.y);
            return;
        }
        if (this.y == otherPoint.y) {
            this.x = this.x - Integer.compare(this.x, otherPoint.x);
            return;
        }
        this.x = this.x - Integer.compare(this.x, otherPoint.x);
        this.y = this.y - Integer.compare(this.y, otherPoint.y);
    }

    public Point duplicate() {
        return new Point(this.x, this.y);
    }
}
