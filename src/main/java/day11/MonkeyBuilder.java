package day11;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class MonkeyBuilder {

    private final Monkey monkey;

    public MonkeyBuilder(int number) {
        this.monkey = new Monkey();
        this.monkey.number = number;
    }

    public MonkeyBuilder withStartingItems(List<Long> startingItems) {
        this.monkey.items = new LinkedList<>(startingItems);
        return this;
    }

    public MonkeyBuilder withOperation(Function<Long, Long> operation) {
        this.monkey.operation = operation;
        return this;
    }

    public MonkeyBuilder withTest(int testModulo, int monkeyToThrowIfTrue, int monkeyToThrowIfFalse) {
        this.monkey.testModulo = testModulo;
        this.monkey.monkeyToThrowIfTrue = monkeyToThrowIfTrue;
        this.monkey.monkeyToThrowIfFalse = monkeyToThrowIfFalse;
        return this;
    }

    public MonkeyBuilder withCommonTestModulo(int commonModulo) {
        this.monkey.commonModulo = commonModulo;
        return this;
    }

    public MonkeyBuilder withWorryLevelReducer(Function<Long, Long> worryLevelReducer) {
        this.monkey.worryLevelReducer = worryLevelReducer;
        return this;
    }

    public Monkey build() {
        return this.monkey;
    }
}
