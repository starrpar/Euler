import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
//import euler_15.classes.point_2d;

import java.util.ArrayList;

public class App {

    private static List<Point_2D> grid = new ArrayList<Point_2D>();

    /*
     * Problem statement:
     * Starting in the top left corner of a 2×2 grid, and only being able to move to
     * the right and down, there are exactly 6 routes to the bottom right corner.
     * 
     * 
     * How many such routes are there through a 20×20 grid?
     */
    public static void main(String[] args) {
        // for simple case, you can go right or down from a given point, unless it
        // becomes out-of-bounds
        // from top/left point, can go right or down
        // by going right, can still go right or down
        // by going down, can still go down or right
        // once reach limit going right, can still go down until reaching limit down
        // once reach limit going down, can still go right until reaching limit right
        // so in summary all points except right boundary can go right, and all points
        // except bottom boundary can go down
        // in computational language, that means you define the last row and column
        // separately, and limit the functionality for those points

        // each point should exist with functionality defined
        // each point can have a "visitedAlready" boolean property
        // more than that, on second thought, each point should have an "exitedRight"
        // and and "exitedDown" property
        // on third thought - that cannot be a part of the decision-making - bad design
        // uniqueness seems to be in which points touched
        // so if grid is this:
        // 0 - 1 - 2
        // 3 - 4 - 5
        // 6 - 7 - 8
        //
        // then one path can be 0-1-2-5-8
        // another path can be 0-1-4-5-8
        // yet another can be 0-1-4-7-8
        // and another ... 0-3-4-5-8
        // another... 0-3-4-7-8
        // another... 0-3-6-7-8
        //
        // how do we *know* that is exhaustive? (thinking)
        // (may need to go look at Postman algorithm descriptions)
        //
        // another way to define it...
        // a1-a2-a3
        // b1-b2-b3
        // c1-c2-c3
        //
        // i.e. rows a - b - c
        // columns 1 - 2 - 3
        // to ease navigation "down" (from a to b to c); and "right" (from 1 to 2 to 3)
        //
        // another alternative to keep everything in integers (vs. a, b & c)
        // 1.1 - 1.2 - 1.3
        // 2.1 - 2.2 - 2.3
        // 3.1 - 3.2 - 3.3

        int n = 21; // 21x21 grid (the nodes of a 20x20 "squares" grid)
        // int n = 3; // 3x3 grid
        List<Integer> pointSet = new ArrayList<Integer>();

        // defineGrid(n);
        defineGridofPoints(n);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                pointSet.add(convertXYValueToPointNumber(i, j, n));
            }
        }

        System.out.println(pointSet);

        int point1Number = convertXYValueToPointNumber(1, 1, n);
        System.out.println(point1Number);

        Point_2D point1 = initiateStartingPoint(0);
        System.out.println("Starting point: " + point1);
        identifyPossiblePaths(point1);
        navigate(point1);

        // one 2nd thought...
        // don't need to create a grid at all, just "do the math" for the answer
        // all points that are "not on the far right or bottom" have 2 choices - right
        // or down; those on the far right and on the bottom have one choice (down, or
        // right, respectively);
        // the final point at the far right, bottom has no choices - cannot go further
        // right or further down - that is the "end point" for all paths...
        // so...
        // if there are 6 paths for a square that has 12 choices (the 3x3), then there
        // are presumably x (number of choices in a 20x20) divided by 2 for a 20x20
        // the 12 in the 3x3 amounts to 2x2 of nodes with 2 choices (= 8) plus 2 (far
        // right) + 2 (bottom) with 1 choice each + 0 (end point) with 0 choices = 12
        // so for 20x20, we have 19x19 with 2 choices each (19x19=361 * 2 = 722) + 19 +
        // 19 with 1 choice each = 38 + 0 (end point) = 722 + 38 = 760
        // actually I was mistaken in my terminology - I was calling a 3x3 what a 2x2 is
        // in the problem description, so if a 2x2 has 3 "nodes" per row and column,
        // then a 20x20 has 21 nodes for each - so 20x20 = 400 x 2 = 800 + 20 + 20 =
        // 840.
    }

    private static void defineGrid(int dimension) {
        Dictionary<Integer, List<Integer>> grid = new Hashtable<>();
        List<Integer> columns = new ArrayList<Integer>();

        for (int j = 1; j <= dimension; j++) {
            columns.add(j);
        }
        for (int i = 1; i <= dimension; i++) {
            grid.put(i, columns);
        }
        System.out.println(grid);
    }

    private static void defineGridofPoints(int dimension) {

        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                Point_2D ij = new Point_2D(i, j);
                grid.add(ij);
            }
        }

        Point_2D point7 = grid.get(7);
        point7.seen = Boolean.TRUE;

        System.out.println(grid);
        System.out.println();
        System.out.println(grid.get(7));
        System.out.println(grid.get(7).seen);
        System.out.println(grid.get(6).seen);
    }

    private static int convertXYValueToPointNumber(int x, int y, int dimension) {
        int localX = (x - 1) * dimension;
        int localY = y;
        int pointNum = 0;

        pointNum = localX + localY;

        return pointNum;
    }

    private static Point_2D initiateStartingPoint(int n) {
        return grid.get(n);
    }

    private static void identifyPossiblePaths(Point_2D startingPoint) {
        System.out.println("not yet implemented");
    }

    private static void navigate(Point_2D startingPoint) {
        System.out.println("not yet implemented");
    }
}
