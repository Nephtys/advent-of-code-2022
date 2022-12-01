import org.junit.Test;

import java.io.IOException;

public class Day1Test {
    @Test
    public void firstStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day1-example.txt");
        assert Day1.greaterCarriedElvesCalories(inputData) == 24000;
    }

    @Test
    public void solveFirstStar() throws IOException {
        String inputData = Utils.readInputData("day1.txt");
        System.out.println(Day1.greaterCarriedElvesCalories(inputData));
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day1-example.txt");
        assert Day1.sumOf3GreaterElvesCalories(inputData) == 45000;
    }
    @Test
    public void solveSecondStar() throws IOException {
        String inputData = Utils.readInputData("day1.txt");
        System.out.println(Day1.sumOf3GreaterElvesCalories(inputData));
    }
}
