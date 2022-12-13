import java.util.*;
import java.util.stream.Stream;

public class Day12 {
    public static long minimumStepsFromStartingPoint(List<String> inputData) {
        Grid grid = new Grid(inputData);
        return shorterPathFrom(grid, grid.startingPoint).orElse(0);
    }

    public static long minimumStepsFromALowPoint(List<String> inputData) {
        Grid grid = new Grid(inputData);
        return grid.lowerPoints.stream()
                .map(p -> shorterPathFrom(grid, p))
                .filter(Optional::isPresent)
                .mapToInt(Optional::get)
                .min().orElse(0);
    }

    private static Optional<Integer> shorterPathFrom(Grid grid, Point startingPoint) {
        var frontier = new LinkedList<Point>();
        frontier.add(startingPoint);
        Map<Point, Point> cameFrom = new HashMap<>();
        cameFrom.put(startingPoint, null);

        while (!frontier.isEmpty()) {
            Point currentPoint = frontier.removeFirst();
            if (currentPoint.equals(grid.endPoint)) {
                break;
            }
            List<Point> neighbors = grid.neighbors(currentPoint);
            for (Point neighbor : neighbors) {
                if (!cameFrom.containsKey(neighbor)) {
                    frontier.add(neighbor);
                    cameFrom.put(neighbor, currentPoint);
                }
            }
        }
        return pathLength(grid, startingPoint, cameFrom);
    }

    private static Optional<Integer> pathLength(Grid grid, Point startingPoint, Map<Point, Point> cameFrom) {
        if (!cameFrom.containsKey(grid.endPoint)) {
            return Optional.empty();
        }
        int count = 0;
        Point point = grid.endPoint;
        while (point != null && !point.equals(startingPoint)) {
            count++;
            point = cameFrom.get(point);
        }
        return Optional.of(count);
    }

    public static class Grid {
        private final List<List<Point>> points = new ArrayList<>();
        private final List<Point> lowerPoints = new ArrayList<>();
        private final int ySize;
        private final int xSize;
        private Point startingPoint;
        private Point endPoint;

        public Grid(List<String> inputData) {
            for (int yy = 0; yy < inputData.size(); yy++) {
                List<Point> row = new ArrayList<>();
                for (int xx = 0; xx < inputData.get(0).length(); xx++) {
                    Point p = pointAt(inputData, xx, yy);
                    if (p.val == 'a') {
                        this.lowerPoints.add(p);
                    }
                    if (p.name == 'S') {
                        this.startingPoint = p;
                    } else if (p.name == 'E') {
                        this.endPoint = p;
                    }
                    row.add(p);
                }
                this.points.add(row);
            }
            xSize = this.points.get(0).size();
            ySize = this.points.size();
        }

        public static Point pointAt(List<String> inputData, int x, int y) {
            return new Point(x, y, inputData.get(y).charAt(x));
        }

        public Point pointAt(int x, int y) {
            return this.points.get(y).get(x);
        }

        public List<Point> neighbors(Point currentPoint) {
            return Stream.of(lowerNeighbor(currentPoint),
                            rightNeighbor(currentPoint),
                            upperNeighbor(currentPoint),
                            leftNeighbor(currentPoint))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(p -> p.val - currentPoint.val <= 1)
                    .toList();
        }

        private Optional<Point> lowerNeighbor(Point currentPoint) {
            return currentPoint.y < ySize - 1 ? Optional.of(this.pointAt(currentPoint.x, currentPoint.y + 1)) : Optional.empty();
        }

        private Optional<Point> upperNeighbor(Point currentPoint) {
            return currentPoint.y >= 1 ? Optional.of(this.pointAt(currentPoint.x, currentPoint.y - 1)) : Optional.empty();
        }

        private Optional<Point> rightNeighbor(Point currentPoint) {
            return currentPoint.x < xSize - 1 ? Optional.of(this.pointAt(currentPoint.x + 1, currentPoint.y)) : Optional.empty();
        }

        private Optional<Point> leftNeighbor(Point currentPoint) {
            return currentPoint.x >= 1 ? Optional.of(this.pointAt(currentPoint.x - 1, currentPoint.y)) : Optional.empty();
        }
    }

    static final class Point {
        private final int x;
        private final int y;
        private final char name;
        private final char val;

        public Point(int x, int y, char name) {
            this(x, y, name, name == 'S' ? 'a' : name == 'E' ? 'z' : name);
        }

        private Point(int x, int y, char name, char val) {
            this.x = x;
            this.y = y;
            this.name = name;
            this.val = val;
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
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return name + "(" + x + ", " + y + ')';
        }

    }

}
