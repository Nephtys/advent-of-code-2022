import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day8 {

    public static int visibleTrees(String inputData) {
        String[] treeLines = inputData.split("\n");
        return new VisibleTreeCounter(treeLines).countVisibleTrees();
    }

    public static int biggestScenicScore(String inputData) {
        String[] treeLines = inputData.split("\n");
        return new VisibleTreeCounter(treeLines).biggestScenicScore();
    }

    private record Point(int x, int y, char val) implements Comparable<Point> {
        @Override
        public int compareTo(Point o) {
            return Integer.compare(val, o.val);
        }
    }

    private record VisibleTreeCounter(String[] treeLines, int xSize, int ySize) {
        private VisibleTreeCounter(String[] lines) {
            this(lines, lines[0].length(), lines.length);
        }

        private Point point(int x, int y) {
            return new Point(x, y, treeLines[y].charAt(x));
        }

        private int countVisibleTrees() {
            int visibleOuterTrees = 2 * xSize + 2 * ySize - 4;
            int visibleInnerTrees = (int) innerTrees()
                    .filter(this::isVisible)
                    .count();
            return visibleInnerTrees + visibleOuterTrees;
        }

        private Stream<Point> innerTrees() {
            return IntStream
                    .range(1, ySize - 1)
                    .mapToObj(y -> IntStream.range(1, xSize - 1).mapToObj(x -> point(x, y)))
                    .flatMap(Function.identity());
        }

        private boolean isVisible(Point point) {
            return treesOnTheLeft(point).noneMatch(point1 -> point1.compareTo(point) >= 0)
                    || treesOnTheRight(point).noneMatch(point1 -> point1.compareTo(point) >= 0)
                    || treesOnBottom(point).noneMatch(point1 -> point1.compareTo(point) >= 0)
                    || treesOnTop(point).noneMatch(point1 -> point1.compareTo(point) >= 0);
        }

        private Stream<Point> treesOnBottom(Point thePoint) {
            return IntStream.range(thePoint.y + 1, ySize)
                    .mapToObj(yy -> point(thePoint.x, yy));
        }

        private Stream<Point> treesOnTop(Point thePoint) {
            return IntStream.iterate(thePoint.y - 1, yy -> yy >= 0, yy -> yy - 1)
                    .mapToObj(yy -> point(thePoint.x, yy));
        }

        private Stream<Point> treesOnTheRight(Point thePoint) {
            return IntStream.range(thePoint.x + 1, xSize)
                    .mapToObj(xx -> point(xx, thePoint.y));
        }

        private Stream<Point> treesOnTheLeft(Point thePoint) {
            return IntStream.iterate(thePoint.x - 1, xx -> xx >= 0, xx -> xx - 1)
                    .mapToObj(xx -> point(xx, thePoint.y));
        }

        private int biggestScenicScore() {
            return innerTrees()
                    .mapToInt(this::scenicScore)
                    .max().orElse(0);
        }

        private int scenicScore(Point thePoint) {
            return visibleTreesOnTheLeft(thePoint)
                    * visibleTreesOnTheRight(thePoint)
                    * visibleTreesOnTheTop(thePoint)
                    * visibleTreesOnTheBottom(thePoint);
        }

        private Integer visibleTreesOnTheBottom(Point thePoint) {
            return treesOnBottom(thePoint)
                    .filter(point -> point.compareTo(thePoint) >= 0)
                    .findFirst()
                    .map(point -> point.y - thePoint.y)
                    .orElse(ySize - thePoint.y - 1);
        }

        private Integer visibleTreesOnTheTop(Point thePoint) {
            return treesOnTop(thePoint)
                    .filter(point -> point.compareTo(thePoint) >= 0)
                    .findFirst()
                    .map(point -> thePoint.y - point.y)
                    .orElse(thePoint.y);
        }

        private Integer visibleTreesOnTheRight(Point thePoint) {
            return treesOnTheRight(thePoint)
                    .filter(point -> point.compareTo(thePoint) >= 0)
                    .findFirst()
                    .map(point -> point.x - thePoint.x)
                    .orElse(xSize - thePoint.x - 1);
        }

        private Integer visibleTreesOnTheLeft(Point thePoint) {
            return treesOnTheLeft(thePoint)
                    .filter(point -> point.compareTo(thePoint) >= 0)
                    .findFirst()
                    .map(point -> thePoint.x - point.x)
                    .orElse(thePoint.x);
        }

    }

}
