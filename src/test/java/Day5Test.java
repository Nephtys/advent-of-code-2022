import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class Day5Test {

    @Test
    public void shouldReadStacksInitialState() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day5-example.txt");
        List<Stack<Character>> stacks = Day5.initialStacksState(inputData);
        assert stacks.size() == 3;
        Stack<Object> firstStack = new Stack<>();
        firstStack.push('Z');
        firstStack.push('N');
        assert stacks.get(0).equals(firstStack);
        Stack<Object> secondStack = new Stack<>();
        secondStack.push('M');
        secondStack.push('C');
        secondStack.push('D');
        assert stacks.get(1).equals(secondStack);
        Stack<Object> thirdStack = new Stack<>();
        thirdStack.push('P');
        assert stacks.get(2).equals(thirdStack);
    }

    @Test
    public void shouldReadAStep() {
        Day5.RearrangementStep step = Day5.rearrangementStep("move 3 from 1 to 2");
        assert step.numberOfCratesToMove == 3;
        assert step.originStack == 1;
        assert step.destinationStack == 2;
    }

    @Test
    public void shouldReadRearrangementProcedure() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day5-example.txt");
        List<Day5.RearrangementStep> rearrangementProcedure = Day5.rearrangementProcedure(inputData);
        assert rearrangementProcedure.size() == 4;
        assert rearrangementProcedure.get(0).numberOfCratesToMove == 1;
        assert rearrangementProcedure.get(0).originStack == 2;
        assert rearrangementProcedure.get(0).destinationStack == 1;
        assert rearrangementProcedure.get(3).numberOfCratesToMove == 1;
        assert rearrangementProcedure.get(3).originStack == 1;
        assert rearrangementProcedure.get(3).destinationStack == 2;
    }

    @Test
    public void shouldApplyStep() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day5-example.txt");
        List<Stack<Character>> stacks = Day5.initialStacksState(inputData);
        Day5.applyStepWithCrateMover9000(stacks, new Day5.RearrangementStep(1, 2, 1));
        Stack<Object> firstStack = new Stack<>();
        firstStack.push('Z');
        firstStack.push('N');
        firstStack.push('D');
        assert stacks.get(0).equals(firstStack);
        Stack<Object> secondStack = new Stack<>();
        secondStack.push('M');
        secondStack.push('C');
        assert stacks.get(1).equals(secondStack);
        Stack<Object> thirdStack = new Stack<>();
        thirdStack.push('P');
        assert stacks.get(2).equals(thirdStack);
    }

    @Test
    public void firstStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day5-example.txt");
        assert Day5.cratesOnTopOfStacks(inputData).equals("CMZ");
    }

    @Test
    public void solveFirstStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day5.txt");
        String result = Day5.cratesOnTopOfStacks(inputData);
        System.out.println(result);
        assert result.equals("PTWLTDSJV");
    }

    @Test
    public void shouldApplyStepWithCrateMover9001() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day5-example.txt");
        List<Stack<Character>> stacks = Day5.initialStacksState(inputData);
        Day5.applyStepWithCrateMover9001(stacks, new Day5.RearrangementStep(2, 2, 1));
        Stack<Object> firstStack = new Stack<>();
        firstStack.push('Z');
        firstStack.push('N');
        firstStack.push('C');
        firstStack.push('D');
        assert stacks.get(0).equals(firstStack);
        Stack<Object> secondStack = new Stack<>();
        secondStack.push('M');
        assert stacks.get(1).equals(secondStack);
        Stack<Object> thirdStack = new Stack<>();
        thirdStack.push('P');
        assert stacks.get(2).equals(thirdStack);
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day5-example.txt");
        assert Day5.cratesOnTopOfStacksWithCrateMover9001(inputData).equals("MCD");
    }

    @Test
    public void solveSecondStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day5.txt");
        String result = Day5.cratesOnTopOfStacksWithCrateMover9001(inputData);
        System.out.println(result);
        assert result.equals("WZMFVGGZP");
    }
}
