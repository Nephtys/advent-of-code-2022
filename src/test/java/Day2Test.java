import org.junit.Test;

import java.io.IOException;

public class Day2Test {
    @Test
    public void firstStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day2-example.txt");
        assert Day2.scoreFirstStrategy(inputData) == 15;
    }

    @Test
    public void solveFirstStar() throws IOException {
        String inputData = Utils.readInputData("day2.txt");
        int result = Day2.scoreFirstStrategy(inputData);
        System.out.println(result);
        assert result == 10595;
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day2-example.txt");
        assert Day2.scoreSecondStrategy(inputData) == 12;
    }
    @Test
    public void solveSecondStar() throws IOException {
        String inputData = Utils.readInputData("day2.txt");
        int result = Day2.scoreSecondStrategy(inputData);
        System.out.println(result);
        assert result == 9541;
    }
}
