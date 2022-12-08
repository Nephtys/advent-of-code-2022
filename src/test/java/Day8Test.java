import org.junit.Test;

import java.io.IOException;

public class Day8Test {
    @Test
    public void firstStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day8-example.txt");
        assert Day8.visibleTrees(inputData) == 21;
    }

    @Test
    public void solveFirstStar() throws IOException {
        String inputData = Utils.readInputData("day8.txt");
        int result = Day8.visibleTrees(inputData);
        System.out.println(result);
        assert result == 1533;
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day8-example.txt");
        assert Day8.biggestScenicScore(inputData) == 8;
    }
    @Test
    public void solveSecondStar() throws IOException {
        String inputData = Utils.readInputData("day8.txt");
        int result = Day8.biggestScenicScore(inputData);
        System.out.println(result);
        assert result == 345744;
    }
}
