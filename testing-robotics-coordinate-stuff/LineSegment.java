public class LineSegment extends LineLike implements AlmostLine {
    public LineSegment(Point p1, Point p2){
        super(p1, p2);
    }

    public boolean contains(Point p) {
        return true;
    }

    public Point[] getBounds() {
        return new Point[]{this.p1, this.p2};
    }
}