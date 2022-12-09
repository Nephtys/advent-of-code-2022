import day9.Point;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class Day9 {

    public static int visitedPositionsByTailWith2Knots(List<String> inputData) {
        return visitedPositionsByTail(inputData, 2);
    }

    public static int visitedPositionsByTailWith10Knots(List<String> inputData) {
        return visitedPositionsByTail(inputData, 10);
    }

    private static int visitedPositionsByTail(List<String> inputData, int ropeSize) {
        List<Point> rope = newRope(ropeSize);
        return (int) inputData.stream()
                .map(Day9::readCommand)
                .map(command -> command.applyTo(rope))
                .flatMap(Collection::stream)
                .distinct()
                .count();
    }

    private static List<Point> newRope(int ropeSize) {
        return IntStream.range(0, ropeSize)
                .mapToObj(i -> Point.at(0, 0))
                .toList();
    }

    private static Command readCommand(String data) {
        String[] command = data.split(" ");
        return new Command(command[0], Integer.parseInt(command[1]));
    }


    private record Command(String direction, int times) {
        public List<Point> applyTo(List<Point> ropeKnots) {
            return IntStream.
                    range(0, times)
                    .mapToObj(i -> {
                        moveHead(ropeKnots.get(0));
                        moveTheRestOfTheRope(ropeKnots);
                        return tailPosition(ropeKnots);
                    })
                    .toList();
        }

        private void moveHead(Point head) {
            switch (direction) {
                case "R" -> head.moveRight();
                case "L" -> head.moveLeft();
                case "U" -> head.moveUp();
                case "D" -> head.moveDown();
            }
        }

        private void moveTheRestOfTheRope(List<Point> ropeKnots) {
            IntStream.range(1, ropeKnots.size())
                    .forEach(i -> ropeKnots.get(i).moveTowards(ropeKnots.get(i - 1)));
        }

        private Point tailPosition(List<Point> ropeKnots) {
            return ropeKnots.get(ropeKnots.size() - 1).duplicate();
        }
    }
}
