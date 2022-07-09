import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
//import euler_15.classes.point_2d;

import java.util.ArrayList;

public class App {

    private static List<Point_2D> grid = new ArrayList<Point_2D>();
    private static int dimension = 2; // 20
    private static int nodesSquare = dimension + 1;

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

        // int n = 21; // 21x21 grid (the nodes of a 20x20 "squares" grid)
        // int n = 3; // 3x3 grid
        List<Integer> pointSet = new ArrayList<Integer>();

        // defineGrid(n);
        defineGridofPoints(nodesSquare);
        for (int i = 1; i <= nodesSquare; i++) {
            for (int j = 1; j <= nodesSquare; j++) {
                pointSet.add(convertXYValueToPointNumber(i, j, nodesSquare));
            }
        }

        System.out.println(pointSet);

        // int point1Number = convertXYValueToPointNumber(1, 1, nodesSquare);
        // System.out.println(point1Number);
        // Point_2D point1 = initiateStartingPoint(0);
        // System.out.println("Starting point: " + point1);
        // identifyPossiblePaths(point1);
        identifyPossiblePaths();
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

    /*
     * private static void defineGrid(int dimension) {
     * Dictionary<Integer, List<Integer>> grid = new Hashtable<>();
     * List<Integer> columns = new ArrayList<Integer>();
     * 
     * for (int j = 1; j <= dimension; j++) {
     * columns.add(j);
     * }
     * for (int i = 1; i <= dimension; i++) {
     * grid.put(i, columns);
     * }
     * System.out.println(grid);
     * }
     */
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

    private static Point_2D initiateStartingPoint(int nodesSquare) {
        return grid.get(nodesSquare);
    }

    private static void identifyPossiblePaths(Point_2D startingPoint) {
        System.out.println("In the process of being implemented");
        // from 1.1, can go to either 1.2 or 2.1
        // from each successive node, can do same until reach end of line either to the
        // extreme right or extreme bottom
        // need to determine a deterministic approach/pattern to make sure each overall
        // path has been covered once and only once
        // could record such path and compare to ensure no repeats
        // could also set a deterministic pattern that only goes to a new path each time
        // (but without verification)
        // so for example
        // - right first, then continue right all the way to right border, then go down
        // all the way to the bottom
        // - right first, all the way to 1 node shy of right border, then down, the
        // right again, then all the way down to the bottom
        // - rinse and repeat until only 1 right, then down 1, then right to right
        // border, then all the way down to the bottom
        // - then down first, the all the way to the right, then all the way down
        // - then repeat the one back until all but one back
        // - then ...

        // may want to think opposite, from the end backwards
        // from last point, can get there from above or from the left
        // from above, can get there from above that, or from left
        // from left, can get there from above, or from left
        // do smallest square first - 2 x 2 has 3 nodes by 3 nodes (the example)
        // last node from above or left
        // node above from above or left
        // node above that only from left
        // node left from above or left
        // node above that from above or left
        // node left of that from above only
        // node above middle, which is node left of top, far right node, from left only
        // node left of middle, which is node above bottom far left, from above only
        // first node is initial node only

        // for a 2x2:
        // 1.1 -> 1.2 -> 2.2
        // 1.1 -> 2.1 -> 2.2

        // for 3x3 building on 2x2, have 2 add'l outside paths,
        // plus 2 sets of 2x2 paths (so 4)

        // for a 3x3:
        // 1.1 -> 1.2 -> 1.3 -> 2.3 -> 3.3 (outside along right side)

        // 1.1 -> 1.2 -> 2.2 -> 2.3 -> 3.3
        // 1.1 -> 1.2 -> 2.2 -> 3.2 -> 3.3 \ these all go through the center
        // 1.1 -> 2.1 -> 2.2 -> 2.3 -> 3.3 /
        // 1.1 -> 2.1 -> 2.2 -> 3.2 -> 3.3

        // 1.1 -> 2.1 -> 3.1 -> 3.2 -> 3.3 (outside along bottom)

        // for 4x4 building on 3x3, have 2 add'l outside paths
        // plus 9 paths - 3 sets of 2x2 paths plus 3 separate outside paths
        // for both of original directions (right and down)
        // so 18 paths - 6 sets of 2x2 and 6 outside paths

        // for a 4x4:
        // 1right first (10)
        // 2then right again (4)
        // 3then right again (1)
        // 4only choice is down (1)
        // 1.1 -> 1.2 -> 1.3 -> 1.4 -> 2.4 -> 3.4 -> 4.4 (outside along right side)
        // 3then down (3)
        // 4then right (1) (only choice is down)
        // 1.1 -> 1.2 -> 1.3 -> 2.3 -> 2.4 -> 3.4 -> 4.4 (along lower right side)
        // 4then down again (2)
        // 1.1 -> 1.2 -> 1.3 -> 2.3 -> 3.3 -> 3.4 -> 4.4 (through center of lower right
        // 2x2)
        // 1.1 -> 1.2 -> 1.3 -> 2.3 -> 3.3 -> 4.3 -> 4.4 (through center of lower right
        // 2x2)
        // 2then down (6)
        // 3then right (3)
        // 4then right again (1) (only choice is down)
        // 1.1 -> 1.2 -> 2.2 -> 2.3 -> 2.4 -> 3.4 -> 4.4 (along lower right side)
        // 4then down again(2)
        // 1.1 -> 1.2 -> 2.2 -> 2.3 -> 3.3 -> 3.4 -> 4.4 (through center of lower right
        // 2x2)
        // 1.1 -> 1.2 -> 2.2 -> 2.3 -> 3.3 -> 4.3 -> 4.4 (through center of lower right
        // 2x2)
        // 3then down again (3)
        // 4then down again (2)
        // 1.1 -> 1.2 -> 2.2 -> 3.2 -> 3.3 -> 3.4 -> 4.4 (through center of lower right
        // 2x2)
        // 1.1 -> 1.2 -> 2.2 -> 3.2 -> 3.3 -> 4.3 -> 4.4 (through center of lower right
        // 2x2)
        // 4then right (1) (only choice is right)
        // 1.1 -> 1.2 -> 2.2 -> 3.2 -> 4.2 -> 4.3 -> 4.4 (along right side of bottom)
        // 1down first (10)
        // 2then right (6)
        // 3then right again (3)
        // 4then right again(1) (only choice is down)
        // 1.1 -> 2.1 -> 2.2 -> 2.3 -> 2.4 -> 3.4 -> 4.4 (along lower right side)
        // 4then down (2)
        // 1.1 -> 2.1 -> 2.2 -> 2.3 -> 3.3 -> 3.4 -> 4.4 (through center of lower right
        // 2x2)
        // 1.1 -> 2.1 -> 2.2 -> 2.3 -> 3.3 -> 4.3 -> 4.4 (through center of lower right
        // 2x2)
        // 3then down (3)
        // 4then right (2)
        // 1.1 -> 2.1 -> 2.2 -> 3.2 -> 3.3 -> 3.4 -> 4.4 (through center of lower right
        // 2x2)
        // 1.1 -> 2.1 -> 2.2 -> 3.2 -> 3.3 -> 4.3 -> 4.4 (through center of lower right
        // 2x2)
        // 4then down again (1) (only choice is right)
        // 1.1 -> 2.1 -> 2.2 -> 3.2 -> 4.2 -> 4.3 -> 4.4 (along right side of bottom)
        // 2then down again (4)
        // 3then right (3)
        // 4then right again (2)
        // 1.1 -> 2.1 -> 3.1 -> 3.2 -> 3.3 -> 3.4 -> 4.4 (through center of lower right
        // 2x2)
        // 1.1 -> 2.1 -> 3.1 -> 3.2 -> 3.3 -> 4.3 -> 4.4 (through center of lower right
        // 2x2)
        // 4then down (1) (only choice is right)
        // 1.1 -> 2.1 -> 3.1 -> 3.2 -> 4.2 -> 4.3 -> 4.4 (along right side of bottom)
        // 3then down again (1)
        // 4only choice is right (1)
        // 1.1 -> 2.1 -> 3.1 -> 4.1 -> 4.2 -> 4.3 -> 4.4 (outside along bottom)

        // analyzing the pattern:
        // build out all possibilities by adding one node at a time with all
        // possibilities (limited at right-most, and bottom nodes as special cases)

        // given starting at initial node (1.1)
        // go to both nodes possible - build out unique paths as that continues until we
        // reach end point

        // from initial point (passed in); go to both choices
        // each path can be a List<Point_2D>
        // List<List<Point_2D>> paths = new ArrayList<>();
        Dictionary<Integer, List<Point_2D>> paths = new Hashtable<>();

        /*
         * List<Point_2D> tmpPath1 = new ArrayList<>();
         * List<Point_2D> tmpPath2 = new ArrayList<>();
         * List<Point_2D> tmpPath3 = new ArrayList<>();
         * List<Point_2D> tmpPath4 = new ArrayList<>();
         * List<Point_2D> tmpPath5 = new ArrayList<>();
         */
        // go from the beginning all the way to, but not including the last column
        // (right most) and last row (bottom)
        List<Point_2D> tmpPath = new ArrayList<>();
        for (int i = 1; i < nodesSquare; i++) {
            for (int j = 1; j < nodesSquare; j++) {
                // how to dynamically create a path (List<Point_2D>) and how to create new ones
                // with each new added point?
                // create temp path
                // then add that path to a list of paths
                // tmpPath.add(startingPoint);
                Point_2D currentPoint = grid.get(convertXYValueToPointNumber(i, j, nodesSquare) - 1);
                // skip initial point (passed in)
                if (i == 1 && j == 1) {
                    tmpPath.add(grid.get(convertXYValueToPointNumber(i, j, nodesSquare) - 1));
                    continue;
                }
                /*
                 * // temp - initially just add next 2 adjacent points
                 * if (i == 1 && j == 2) {
                 * tmpPath.add(currentPoint); // on this line, the ArrayList *does* contain the
                 * content expected (2
                 * // points with correct data (1, 1) and (1, 2))
                 * paths.put(i, tmpPath); // at this step the ArrayList is added to the
                 * List<List<Point_2D>>, but says
                 * // "
                 * // Format specifier '%s' " instead of having the contents of the ArrayList
                 * continue;
                 * } else if (i == 2 && j == 1) {
                 * tmpPath.add(currentPoint); // on this line, the ArrayList *does* contain the
                 * content expected (3
                 * // points with correct data (1, 1), (1, 2) and (2, 1))
                 * paths.put(i, tmpPath);
                 * continue;
                 * } else {
                 * continue; // for now just skip all other points
                 * }
                 */
                // have points being successfully added now;
                // now to create the adjacency identification/management approach
                // should simply consist of a +1 horizontally or +1 vertically move
                // and some sort of tracking to ensure both happen when they can (until the far
                // right or bottom borders)

                // so approach should be:
                // 1. [EASY] start at beginning
                // 2. [THIS IS THE HARD PART] add points per some algorithm that makes use of
                // every point combination without repeating paths or missing any points
                // Question: do we follow a pattern, or let the system "autonavigate" and
                // track where we've been (marking points as seen = TRUE) instead
                // 3. [(fairly) EASY] when get to right side or bottom, change points navigation
                // to ONLY go
                // along that border to end
                // 4. [EASY] add path to Dictionary when reach last point (nodesSquare,
                // nodesSquare)

                // go right, then go all right, then all down
                // increase in j (y) fully first, then increase in i (x) fully
                // go right, then down, then all right, then all down
                // one step in j, then one in i, then continue in j until to right side, then
                // increase in i until end
                // go right twice, then down, then all right, then all down
                // two j, one i, all j, all i
                // continue this pattern until at the last column (which has already been done
                // as first path)
                // three j, one i, all j, all i
                // go down first, then go all right, then all down
                // rinse/repeat same
                // lastly go all down until last row, then all right
                // this doesn't account for multiple downs in a row

                // thinking from the opposite direction
                // coming in at the end, you can get there by coming down from above, or to the
                // right from the bottom
                // above or from the left, you can get there by coming from above or the left of
                // the point above the last point
                // or above or from the left to the last point on the bottom row, short of the
                // last point
            }
        }
    }

    private static void navigate(Point_2D startingPoint) {
        System.out.println("not yet implemented");
    }
}
