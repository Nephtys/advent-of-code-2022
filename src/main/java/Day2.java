import java.util.Arrays;
import java.util.function.Function;

public class Day2 {

    public static int scoreFirstStrategy(String inputData) {
        return scoreWithStrategy(inputData, turnData -> new TurnBuilder(turnData).executeFirstStrategy());
    }

    public static int scoreSecondStrategy(String inputData) {
        return scoreWithStrategy(inputData, turnData -> new TurnBuilder(turnData).executeSecondStrategy());
    }

    private static int scoreWithStrategy(String inputData, Function<String, Turn> strategy) {
        return Arrays.stream(asTurns(inputData))
                .map(strategy)
                .mapToInt(Day2::computeScore)
                .sum();
    }

    private static String[] asTurns(String inputData) {
        return inputData.split("\n");
    }

    private static int computeScore(Turn result) {
        return result.result.ordinal() * 3 + result.myChoice.ordinal() + 1;
    }

    private enum Shape {
        ROCK,
        PAPER,
        SCISSOR
    }

    private enum Result {
        LOSE,
        DRAW,
        WIN,
    }

    private static class Turn {
        Shape opponentChoice;
        Shape myChoice;
        Result result;

        private Turn(Shape opponentChoice, Shape myChoice, Result result) {
            this.opponentChoice = opponentChoice;
            this.myChoice = myChoice;
            this.result = result;
        }
    }

    private static class TurnBuilder {
        private Shape opponentChoice;
        private Shape myChoice;
        private Result result;

        private final String turnData;

        public TurnBuilder(String turnData) {
            this.turnData = turnData;
        }

        public Turn executeFirstStrategy() {
            return this.withOpponentShape(this.turnData.charAt(0))
                    .withMyShape(this.turnData.charAt(2))
                    .evaluateResult();
        }

        public Turn executeSecondStrategy() {
            return this.withOpponentShape(this.turnData.charAt(0))
                    .withResult(this.turnData.charAt(2))
                    .evaluateMyShape();
        }

        private TurnBuilder withOpponentShape(char opponentShape) {
            this.opponentChoice = Shape.values()[opponentShape - 'A'];
            return this;
        }

        private TurnBuilder withMyShape(char myShape) {
            this.myChoice = Shape.values()[myShape - 'X'];
            return this;
        }

        private Turn evaluateResult() {
            this.result = Result.values()[positiveModulus(this.myChoice.ordinal() - this.opponentChoice.ordinal() + 1)];
            return new Turn(opponentChoice, myChoice, result);
        }

        private TurnBuilder withResult(char result) {
            this.result = Result.values()[result - 'X'];
            return this;
        }

        private Turn evaluateMyShape() {
            this.myChoice = Shape.values()[positiveModulus(this.opponentChoice.ordinal() + this.result.ordinal() - 1)];
            return new Turn(opponentChoice, myChoice, result);
        }

        private static int positiveModulus(int i) {
            return ((i % 3) + 3) % 3;
        }
    }

}
