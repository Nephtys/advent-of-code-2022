import day11.Monkey;
import day11.MonkeyBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day11 {
    private static final Pattern MONKEY_DESCR = Pattern.compile("Monkey (\\d+):\n" +
            "  Starting items: (.*)\n" +
            "  Operation: new = old ([+*]) (old|[0-9]+)\n" +
            "  Test: divisible by (.*)\n" +
            "    If true: throw to monkey (.?)\n" +
            "    If false: throw to monkey (.?)");

    public static List<Monkey> readInput(String inputData, Part exercisePart) {
        AtomicInteger commonModulo = new AtomicInteger(1);
        List<MonkeyBuilder> builders = MONKEY_DESCR.matcher(inputData)
                .results()
                .map(matchResult -> {
                    int testModulo = Integer.parseInt(matchResult.group(5));
                    commonModulo.getAndUpdate(c -> c * testModulo);
                    return new MonkeyBuilder(Integer.parseInt(matchResult.group(1)))
                            .withStartingItems(readStartingItems(matchResult))
                            .withOperation(readOperation(matchResult))
                            .withTest(testModulo, Integer.parseInt(matchResult.group(6)), Integer.parseInt(matchResult.group(7)));
                })
                .toList();
        return builders.stream()
                .map(monkeyBuilder -> monkeyBuilder
                        .withWorryLevelReducer(worry -> switch (exercisePart) {
                            case ONE -> (long) Math.floor(worry / 3f);
                            case TWO -> worry % (long) commonModulo.get();
                        })
                        .build())
                .toList();
    }

    private static List<Long> readStartingItems(MatchResult matchResult) {
        return Arrays.stream(matchResult.group(2).split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .toList();
    }

    private static Function<Long, Long> readOperation(MatchResult matchResult) {
        Function<Long, Long> operation;
        String operand = matchResult.group(4);
        if (matchResult.group(3).equals("+")) {
            operation = worry -> worry + Integer.parseInt(operand);
        } else {
            operation = worry -> worry * (operand.equals("old") ? worry : Integer.parseInt(operand));
        }
        return operation;
    }

    public static long levelOfMonkeyBusinessAfterSoMuchRounds(List<Monkey> monkeys, int roundCount) {
        executeRounds(monkeys, roundCount);
        return monkeys.stream()
                .mapToLong(Monkey::itemsInspected)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce(1L, (a, b) -> a * b);
    }

    public static void executeRounds(List<Monkey> monkeys, int roundCount) {
        for (int i = 0; i < roundCount; i++) {
            for (Monkey monkey : monkeys) {
                while (monkey.hasMoreItems()) {
                    long item = monkey.inspectAndGetBored();
                    monkey.throwItem(monkeys, item);
                }
            }
        }
    }

    public enum Part {
        ONE, TWO
    }
}
