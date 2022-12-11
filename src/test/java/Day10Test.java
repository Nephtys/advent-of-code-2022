import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day10Test {

    @Test
    public void signalStrengthDuringFirstCycleIsOne() {
        List<String> program = List.of("noop");
        List<Integer> registerValues = Day10.registerValuesDuringProgram(program);
        assert registerValues.get(0) == 1;
    }

    @Test
    public void signalStrengthDoNotChangeOnNoopCycle() {
        List<String> program = List.of("noop", "noop");
        List<Integer> registerValues = Day10.registerValuesDuringProgram(program);
        assert registerValues.get(0) == 1;
        assert registerValues.get(1) == 1;
    }

    @Test
    public void signalStrengthChangeATheEndOfTwoCycleWithAddXCycle() {
        List<String> program = List.of("addx 1", "noop");
        List<Integer> registerValues = Day10.registerValuesDuringProgram(program);
        assert registerValues.get(0) == 1;
        assert registerValues.get(1) == 1;
        assert registerValues.get(2) == 2;
    }

    @Test
    public void signalStrengthOnSomeCycleIsValueOfRegisterMultipliedByCycleCount() {
        List<String> program = List.of("addx 1", "noop");
        List<Integer> registerValues = Day10.registerValuesDuringProgram(program);
        assert Day10.signalStrengthAtCycle(registerValues, 3) == 6;
    }

    @Test
    public void firstStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day10-example.txt");
        assert Day10.sumOfInterestingSignalStrengths(inputData) == 13140;
    }

    @Test
    public void solveFirstStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day10.txt");
        int result = Day10.sumOfInterestingSignalStrengths(inputData);
        System.out.println(result);
        assert result == 13860;
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day10-example.txt");
        String expectedScreen =
                """
                        ##..##..##..##..##..##..##..##..##..##..
                        ###...###...###...###...###...###...###.
                        ####....####....####....####....####....
                        #####.....#####.....#####.....#####.....
                        ######......######......######......####
                        #######.......#######.......#######.....
                        """;
        String result = Day10.crtScreen(inputData);
        System.out.println(result);
        assert result.equals(expectedScreen);
    }

    @Test
    public void solveSecondStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day10.txt");
        String expectedScreen =
                """
                        ###..####.#..#.####..##....##..##..###..
                        #..#....#.#..#.#....#..#....#.#..#.#..#.
                        #..#...#..####.###..#.......#.#....###..
                        ###...#...#..#.#....#.##....#.#....#..#.
                        #.#..#....#..#.#....#..#.#..#.#..#.#..#.
                        #..#.####.#..#.#.....###..##...##..###..
                        """;
        String crtScreen = Day10.crtScreen(inputData);
        System.out.println(crtScreen);
        assert crtScreen.equals(expectedScreen);
        // RZHFGJCB
    }
}
