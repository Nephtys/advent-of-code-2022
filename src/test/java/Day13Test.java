import day13.Packets;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day13Test {

    @Test
    public void shouldParseASimpleList() {
        Packets packets = Packets.forLine("[10,2,3,4]");
        assert packets.data.size() == 4;
        assert packets.data.containsAll(List.of(10, 2, 3, 4));
    }

    @Test
    public void shouldParseAListOfList() {
        Packets packets = Packets.forLine("[[1,2,3,4]]");
        assert packets.data.size() == 1;
        assert packets.data.get(0) instanceof List;
        assert ((List)packets.data.get(0)).size() == 4;
        assert ((List)packets.data.get(0)).containsAll(List.of(1, 2, 3, 4));
    }

    @Test
    public void shouldParseAListOfListAndUnitaryElements() {
        Packets packets = Packets.forLine("[[1,2,3,4],4]");
        assert packets.data.size() == 2;
        assert packets.data.get(0) instanceof List;
        assert ((List)packets.data.get(0)).size() == 4;
        assert ((List)packets.data.get(0)).containsAll(List.of(1, 2, 3, 4));
        assert (int) packets.data.get(1) == 4;
    }

    @Test
    public void shouldParseAListOfListOfEmptyList() {
        Packets packets = Packets.forLine("[[[]]]");
        assert packets.data.size() == 1;
        assert packets.data.get(0) instanceof List;
        assert ((List)packets.data.get(0)).size() == 1;
        assert ((List)((List)packets.data.get(0)).get(0)).size() == 0;
    }

    @Test
    public void shouldFindIfPacketsAreInOrderByComparingInts() {
        assert Day13.packetsInOrder(new Packets("[1]"), new Packets("[2]"));
        assert !Day13.packetsInOrder(new Packets("[3]"), new Packets("[2]"));
    }

    @Test
    public void shouldFindIfPacketsAreInOrderByComparingLists() {
        assert Day13.packetsInOrder(new Packets("[1,2,3]"), new Packets("[2,3,4]"));
        assert !Day13.packetsInOrder(new Packets("[1,3,4]"), new Packets("[1,2,3]"));
    }

    @Test
    public void shouldFindIfPacketsAreInOrderByComparingListsWithOneShorter() {
        assert Day13.packetsInOrder(new Packets("[7,7,7]"), new Packets("[7,7,7,7]"));
        assert !Day13.packetsInOrder(new Packets("[7,7,7,7]"), new Packets("[7,7,7]"));
    }

    @Test
    public void shouldFindIfPacketsAreInOrderByComparingEmptyLists() {
        assert !Day13.packetsInOrder(new Packets("[[[]]]"), new Packets("[[]]"));
        assert Day13.packetsInOrder(new Packets("[[]]"), new Packets("[[[]]]"));
    }

    @Test
    public void shouldFindIfPacketsAreInOrderByComparingImbricatedLists() {
        assert !Day13.packetsInOrder(new Packets("[1,[2,[3,[4,[5,6,7]]]],8,9]"), new Packets("[1,[2,[3,[4,[5,6,0]]]],8,9]"));
        assert Day13.packetsInOrder(new Packets("[1,[2,[3,[4,[5,6,7]]]],8,9]"), new Packets("[1,[2,[3,[4,[5,6,7]]]],8,10]"));
    }

    @Test
    public void firstStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day13-example.txt");
        int result = Day13.sumOfPacketsInOrderIndices(inputData);
        System.out.println(result);
        assert result == 13;
    }

    @Test
    public void solveFirstStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day13.txt");
        System.out.println(Day13.sumOfPacketsInOrderIndices(inputData));
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day13-example.txt");
        assert Day13.positionsOfDividerPackets(inputData) == 140;
    }
    @Test
    public void solveSecondStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day13.txt");
        System.out.println(Day13.positionsOfDividerPackets(inputData));
    }
}
