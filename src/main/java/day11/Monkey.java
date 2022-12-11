package day11;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Monkey {
    protected int commonModulo;
    protected int testModulo;
    protected int number;
    protected LinkedList<Long> items;
    protected Function<Long, Long> operation;
    protected Function<Long, Long> worryLevelReducer;
    protected int monkeyToThrowIfTrue;
    protected int monkeyToThrowIfFalse;
    private int itemsInspected = 0;

    public List<Long> items() {
        return items.stream().toList();
    }

    public void throwItem(List<Monkey> monkeys, long worriedLevel) {
        monkeys.get(whichMonkeyToThrowItem(worriedLevel))
                .receiveItem(worriedLevel);
    }

    private void receiveItem(long worriedLevel) {
        items.add(worriedLevel);
    }

    public int whichMonkeyToThrowItem(long worriedLevel) {
        return worriedLevel % testModulo == 0 ? monkeyToThrowIfTrue : monkeyToThrowIfFalse;
    }

    public long inspectAndGetBored() {
        long worryLevel = items.removeFirst();
        itemsInspected++;
        worryLevel = operation.apply(worryLevel);
        return worryLevelReducer.apply(worryLevel);
    }

    public int itemsInspected() {
        return itemsInspected;
    }

    public boolean hasMoreItems() {
        return !items.isEmpty();
    }
}
