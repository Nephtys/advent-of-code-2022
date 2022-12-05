import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class Day5 {

    private static final Pattern REARRANGEMENT_PATTERN = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    public static String cratesOnTopOfStacks(List<String> inputData) {
        var stacks = initialStacksState(inputData);
        rearrangementProcedure(inputData).forEach(step -> applyStepWithCrateMover9000(stacks, step));
        return collectFirstCrates(stacks);
    }

    public static String cratesOnTopOfStacksWithCrateMover9001(List<String> inputData) {
        var stacks = initialStacksState(inputData);
        rearrangementProcedure(inputData).forEach(step -> applyStepWithCrateMover9001(stacks, step));
        return collectFirstCrates(stacks);
    }

    private static String collectFirstCrates(List<Stack<Character>> stacks) {
        return stacks.stream().map(Stack::pop).map(String::valueOf).collect(Collectors.joining());
    }

    public static List<Stack<Character>> initialStacksState(List<String> inputData) {
        int stackNumbersLineIndex = findStackNumbersLine(inputData);
        String stackNumbersLine = inputData.get(stackNumbersLineIndex).strip();
        List<Stack<Character>> stacks = generateEmptyStacks(stackNumbersLine);
        List<String> stacksData = inputData.subList(0, stackNumbersLineIndex);
        fillStacks(stacksData, stacks);
        return stacks;
    }

    private static void fillStacks(List<String> stacksData, List<Stack<Character>> stacks) {
        for (int i = stacksData.size() - 1; i >= 0; i--) {
            String line = stacksData.get(i);
            for (int j = 0; (j + 1) * 4 <= line.length() + 1; j++) {
                if (line.charAt(j * 4) == '[') {
                    stacks.get(j).push(line.charAt(j * 4 + 1));
                }
            }
        }
    }

    private static List<Stack<Character>> generateEmptyStacks(String stackNumbersLine) {
        int numberOfStacks = parseInt(stackNumbersLine.substring(stackNumbersLine.lastIndexOf(" ") + 1));
        return IntStream.range(0, numberOfStacks).mapToObj(i -> new Stack<Character>()).toList();
    }

    private static int findStackNumbersLine(List<String> inputLines) {
        return IntStream.range(0, inputLines.size())
                .filter(l -> inputLines.get(l).startsWith(" 1"))
                .findFirst().orElse(0);
    }

    public static List<RearrangementStep> rearrangementProcedure(List<String> inputData) {
        int firstProcLine = findStackNumbersLine(inputData) + 2;
        return inputData.subList(firstProcLine, inputData.size())
                .stream()
                .map(Day5::rearrangementStep)
                .toList();
    }

    public static RearrangementStep rearrangementStep(String stepData) {
        Matcher matcher = REARRANGEMENT_PATTERN.matcher(stepData);
        if (matcher.find()) {
            int numberOfCratesToMove = parseInt(matcher.group(1));
            int origin = parseInt(matcher.group(2));
            int destination = parseInt(matcher.group(3));
            return new RearrangementStep(numberOfCratesToMove, origin, destination);
        }
        return null;
    }

    public static void applyStepWithCrateMover9000(List<Stack<Character>> stacks, RearrangementStep step) {
        for (int i = 0; i < step.numberOfCratesToMove; i++) {
            stacks.get(step.destinationStack - 1).push(stacks.get(step.originStack - 1).pop());
        }
    }

    public static void applyStepWithCrateMover9001(List<Stack<Character>> stacks, RearrangementStep step) {
        Stack<Character> intermediateStack = new Stack<>();
        for (int i = 0; i < step.numberOfCratesToMove; i++) {
            intermediateStack.push(stacks.get(step.originStack - 1).pop());
        }
        for (int i = 0; i < step.numberOfCratesToMove; i++) {
            stacks.get(step.destinationStack - 1).push(intermediateStack.pop());
        }
    }

    static class RearrangementStep {
        int numberOfCratesToMove;
        int originStack;
        int destinationStack;

        RearrangementStep(int numberOfCratesToMove, int originStack, int destinationStack) {
            this.numberOfCratesToMove = numberOfCratesToMove;
            this.originStack = originStack;
            this.destinationStack = destinationStack;
        }
    }
}
