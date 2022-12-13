import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day12Test {
    @Test
    public void firstStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day12-example.txt");
        assert Day12.minimumStepsFromStartingPoint(inputData) == 31;
    }

    @Test
    public void solveFirstStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day12.txt");
        long minimumSteps = Day12.minimumStepsFromStartingPoint(inputData);
        System.out.println(minimumSteps);
        assert minimumSteps == 383;
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day12-example.txt");
        assert Day12.minimumStepsFromALowPoint(inputData) == 29;
    }
    @Test
    public void solveSecondStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day12.txt");
        long minimumSteps = Day12.minimumStepsFromALowPoint(inputData);
        System.out.println(minimumSteps);
        assert minimumSteps == 377;
    }
}
