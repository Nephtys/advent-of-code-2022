import day11.Monkey;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day11Test {

    @Test
    public void shouldThrowToRightMonkey() throws IOException {
        List<Monkey> monkeys = Day11.readInput(Utils.readInputData("day11-example.txt"), Day11.Part.ONE);
        Monkey monkey0 = monkeys.get(0);
        List<Long> monkey0Items = monkey0.items();
        long newWorryLevel = monkey0.inspectAndGetBored();
        assert newWorryLevel == 500;
        assert monkey0.whichMonkeyToThrowItem(500) == 3;
        monkey0.throwItem(monkeys, 500);
        List<Long> monkey3Items = monkeys.get(3).items();
        assert monkey3Items.get(monkey3Items.size()-1) == 500;
        assert monkey0.items().size() == monkey0Items.size()-1;
    }

    @Test
    public void doOneRound() throws IOException {
        List<Monkey> monkeys = Day11.readInput(Utils.readInputData("day11-example.txt"), Day11.Part.ONE);
        Day11.executeRounds(monkeys, 1);
        assert monkeys.get(0).items().containsAll(List.of(20L, 23L, 27L, 26L));
        assert monkeys.get(1).items().containsAll(List.of(2080L, 25L, 167L, 207L, 401L, 1046L));
        assert monkeys.get(2).items().isEmpty();
        assert monkeys.get(3).items().isEmpty();
    }

    @Test
    public void doTwentyRounds() throws IOException {
        List<Monkey> monkeys = Day11.readInput(Utils.readInputData("day11-example.txt"), Day11.Part.ONE);
        Day11.executeRounds(monkeys, 20);
        assert monkeys.get(0).items().containsAll(List.of(10L, 12L, 14L, 26L, 34L));
        assert monkeys.get(1).items().containsAll(List.of(245L, 93L, 53L, 199L, 115L));
        assert monkeys.get(2).items().isEmpty();
        assert monkeys.get(3).items().isEmpty();
    }

    @Test
    public void countHowManyTimesAMonkeyInspectsAnItem() throws IOException {
        List<Monkey> monkeys = Day11.readInput(Utils.readInputData("day11-example.txt"), Day11.Part.ONE);
        assert monkeys.get(0).itemsInspected() == 0;
        Day11.executeRounds(monkeys, 20);
        assert monkeys.get(0).itemsInspected() == 101;
        assert monkeys.get(1).itemsInspected() == 95;
        assert monkeys.get(2).itemsInspected() == 7;
        assert monkeys.get(3).itemsInspected() == 105;
    }

    @Test
    public void firstStarOnProvidedExample() throws IOException {
        List<Monkey> monkeys = Day11.readInput(Utils.readInputData("day11-example.txt"), Day11.Part.ONE);
        long result = Day11.levelOfMonkeyBusinessAfterSoMuchRounds(monkeys, 20);
        assert result == 10605;
    }

    @Test
    public void solveFirstStar() throws IOException {
        List<Monkey> monkeys = Day11.readInput(Utils.readInputData("day11.txt"), Day11.Part.ONE);
        long result = Day11.levelOfMonkeyBusinessAfterSoMuchRounds(monkeys, 20);
        System.out.println(result);
        assert result == 110888;
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        List<Monkey> monkeys = Day11.readInput(Utils.readInputData("day11-example.txt"), Day11.Part.TWO);
        long result = Day11.levelOfMonkeyBusinessAfterSoMuchRounds(monkeys, 10000);
        assert result == 2713310158L;
    }
    @Test
    public void solveSecondStar() throws IOException {
        List<Monkey> monkeys = Day11.readInput(Utils.readInputData("day11.txt"), Day11.Part.TWO);
        long result = Day11.levelOfMonkeyBusinessAfterSoMuchRounds(monkeys, 10000);
        System.out.println(result);
        assert result == 25590400731L;
    }
}
