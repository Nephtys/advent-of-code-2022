import day9.Point;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day9Test {

    @Test
    public void ifOnSamePlaceTailDontMove() {
        Point head = Point.at(5, 5);
        Point tail = Point.at(5, 5);
        tail.moveTowards(head);
        assert tail.x() == 5;
        assert tail.y() == 5;
    }

    @Test
    public void ifOnNearPlaceTailDontMove() {
        Point head = Point.at(5, 5);
        Point tail = Point.at(5, 6);
        tail.moveTowards(head);
        assert tail.x() == 5;
        assert tail.y() == 6;
    }

    @Test
    public void ifOnDiagonalNearPlaceTailDontMove() {
        Point head = Point.at(5, 5);
        Point tail = Point.at(6, 6);
        tail.moveTowards(head);
        assert tail.x() == 6;
        assert tail.y() == 6;
    }

    @Test
    public void ifOn2LinesDownTailDoMoveNextToHead() {
        Point head = Point.at(5, 5);
        Point tail = Point.at(5, 7);
        tail.moveTowards(head);
        assert tail.x() == 5;
        assert tail.y() == 6;
    }

    @Test
    public void ifOn2LinesUpTailDoMoveNextToHead() {
        Point head = Point.at(5, 7);
        Point tail = Point.at(5, 5);
        tail.moveTowards(head);
        assert tail.x() == 5;
        assert tail.y() == 6;
    }

    @Test
    public void ifOn2ColumnsRightTailDoMoveNextToHead() {
        Point head = Point.at(5, 5);
        Point tail = Point.at(7, 5);
        tail.moveTowards(head);
        assert tail.x() == 6;
        assert tail.y() == 5;
    }

    @Test
    public void ifOn2ColumnsLeftTailDoMoveNextToHead() {
        Point head = Point.at(7, 5);
        Point tail = Point.at(5, 5);
        tail.moveTowards(head);
        assert tail.x() == 6;
        assert tail.y() == 5;
    }

    @Test
    public void ifNotOnSameColumnOrLineTailDoMoveNextToHeadDiagonallyDownLeft() {
        Point head = Point.at(2, 2);
        Point tail = Point.at(3, 4);
        tail.moveTowards(head);
        assert tail.x() == 2;
        assert tail.y() == 3;
    }

    @Test
    public void ifNotOnSameColumnOrLineTailDoMoveNextToHeadDiagonallyDownRight() {
        Point head = Point.at(2, 1);
        Point tail = Point.at(1, 3);
        tail.moveTowards(head);
        assert tail.x() == 2;
        assert tail.y() == 2;
    }

    @Test
    public void ifNotOnSameColumnOrLineTailDoMoveNextToHeadDiagonallyUpLeft() {
        Point head = Point.at(1, 3);
        Point tail = Point.at(2, 1);
        tail.moveTowards(head);
        assert tail.x() == 1;
        assert tail.y() == 2;
    }

    @Test
    public void ifNotOnSameColumnOrLineTailDoMoveNextToHeadDiagonallyUpRight() {
        Point head = Point.at(1, 2);
        Point tail = Point.at(0, 0);
        tail.moveTowards(head);
        assert tail.x() == 1;
        assert tail.y() == 1;
    }

    @Test
    public void firstStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day9-example.txt");
        assert Day9.visitedPositionsByTailWith2Knots(inputData) == 13;
    }

    @Test
    public void solveFirstStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day9.txt");
        int result = Day9.visitedPositionsByTailWith2Knots(inputData);
        System.out.println(result);
        assert result == 5710;
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day9-example2.txt");
        int result = Day9.visitedPositionsByTailWith10Knots(inputData);
        System.out.println(result);
        assert result == 36;
    }
    @Test
    public void solveSecondStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day9.txt");
        int result = Day9.visitedPositionsByTailWith10Knots(inputData);
        System.out.println(result);
        assert result == 2259;
    }
}
