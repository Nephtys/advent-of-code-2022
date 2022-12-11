import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day10 {
    public static int sumOfInterestingSignalStrengths(List<String> program) {
        List<Integer> registerValues = registerValuesDuringProgram(program);
        return IntStream.iterate(20, i -> i <= 220, i -> i + 40)
                .map(cycle -> signalStrengthAtCycle(registerValues, cycle))
                .sum();
    }

    static int signalStrengthAtCycle(List<Integer> registerValues, int cycle) {
        return cycle * getCycleRegisterValue(registerValues, cycle);
    }

    private static Integer getCycleRegisterValue(List<Integer> registerValues, int cycle) {
        return registerValues.get(cycle - 1);
    }

    static List<Integer> registerValuesDuringProgram(List<String> program) {
        int register = 1;
        List<Integer> registerValues = new ArrayList<>();
        for (String instruction : program) {
            registerValues.add(register);
            if (instruction.startsWith("addx")) {
                registerValues.add(register);
                register += parseAddXValue(instruction);
            }
        }
        return registerValues;
    }

    private static int parseAddXValue(String instruction) {
        return Integer.parseInt(instruction.substring(5));
    }

    public static String crtScreen(List<String> program) {
        List<Integer> registerValues = registerValuesDuringProgram(program);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 240; i++) {
            int spritePosition = registerValues.get(i);
            int pixelPosition = i % 40;
            String pixelToDraw = Math.abs(pixelPosition - spritePosition) <=1 ? "#" : ".";
            result.append(pixelToDraw);
            if ((i + 1) % 40 == 0) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
