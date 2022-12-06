import org.junit.Test;

import java.io.IOException;

public class Day6Test {
    @Test
    public void firstStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day6-example.txt");
        assert Day6.startOfPacketMarkerPosition(inputData) == 10;
    }

    @Test
    public void solveFirstStar() throws IOException {
        String inputData = Utils.readInputData("day6.txt");
        int result = Day6.startOfPacketMarkerPosition(inputData);
        System.out.println(result);
        assert result == 1804;
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day6-example.txt");
        assert Day6.startOfMessageMarkerPosition(inputData) == 29;
    }
    @Test
    public void solveSecondStar() throws IOException {
        String inputData = Utils.readInputData("day6.txt");
        int result = Day6.startOfMessageMarkerPosition(inputData);
        System.out.println(result);
        assert result == 2508;
    }
}
