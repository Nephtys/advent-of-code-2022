import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class Day1 {

    private static Stream<Integer> top3ElvesCarryingMostCalories(String inputData) {
        return Stream.of(splitByElves(inputData))
                .map(Day1::sumOfEachElfCalories)
                .sorted(Collections.reverseOrder())
                .limit(3);
    }

    private static String[] splitByElves(String inputData) {
        return inputData.split("\n\n");
    }

    private static int sumOfEachElfCalories(String linesOfCalories) {
        return Arrays.stream(linesOfCalories.split("\n")).mapToInt(Integer::valueOf).sum();
    }

    public static Integer greaterCarriedElvesCalories(String inputData) {
        return top3ElvesCarryingMostCalories(inputData).findFirst().orElse(0);
    }

    public static int sumOf3GreaterElvesCalories(String inputData) {
        return top3ElvesCarryingMostCalories(inputData).reduce(0, Integer::sum);
    }


}
