//package euler_15.classes.point_2d;

import javafx.geometry.Point2D;

public class Point_2D {
    public Point2D point = new Point2D(0, 0);
    public Boolean seen = Boolean.FALSE;

    public Point_2D(int i, int j) {
        point = point.add(i, j);
    }
}