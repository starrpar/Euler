
public class App {
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

        defineGrid();
        initiateStartingPoint();

    }

    private static void defineGrid() {

    }

    private static void initiateStartingPoint() {

    }
}
