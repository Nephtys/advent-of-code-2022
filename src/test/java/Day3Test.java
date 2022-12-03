import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day3Test {

    @Test
    public void shouldSplitRucksackContentInCompartments() {
        List<String> compartments = Day3.asCompartments("vJrwpWtwJgWrhcsFMMfFFhFp");
        assert compartments.size() == 2;
        assert compartments.contains("vJrwpWtwJgWr");
        assert compartments.contains("hcsFMMfFFhFp");
    }

    @Test
    public void shouldFindTheErroneousItem() {
        assert Day3.findErroneousItem(Day3.asCompartments("vJrwpWtwJgWrhcsFMMfFFhFp")) == 'p';
        assert Day3.findErroneousItem(Day3.asCompartments("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")) == 'L';
        assert Day3.findErroneousItem(Day3.asCompartments("PmmdzqPrVvPwwTWBwg")) == 'P';
    }

    @Test
    public void shouldComputeTheItemPriority() {
        assert Day3.computePriority('a') == 1;
        assert Day3.computePriority('p') == 16;
        assert Day3.computePriority('z') == 26;
        assert Day3.computePriority('A') == 27;
        assert Day3.computePriority('P') == 42;
        assert Day3.computePriority('Z') == 52;
    }

    @Test
    public void firstStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day3-example.txt");
        assert Day3.sumOfCommonItemPriorities(inputData) == 157;
    }

    @Test
    public void solveFirstStar() throws IOException {
        String inputData = Utils.readInputData("day3.txt");
        int result = Day3.sumOfCommonItemPriorities(inputData);
        System.out.println(result);
        assert result == 8349;
    }

    @Test
    public void shouldSplitInGroups() throws IOException {
        String inputData = Utils.readInputData("day3-example.txt");
        List<String[]> groups = Day3.elvesGroups(inputData);
        assert groups.size() == 2;
        assert groups.get(0)[0].equals("vJrwpWtwJgWrhcsFMMfFFhFp");
        assert groups.get(0)[1].equals("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        assert groups.get(0)[2].equals("PmmdzqPrVvPwwTWBwg");
        assert groups.get(1)[0].equals("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
        assert groups.get(1)[1].equals("ttgJtRGJQctTZtZT");
        assert groups.get(1)[2].equals("CrZsJsPPZsGzwwsLwLmpwMDw");
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day3-example.txt");
        assert Day3.sumOfGroupBadgePriorities(inputData) == 70;
    }

    @Test
    public void solveSecondStar() throws IOException {
        String inputData = Utils.readInputData("day3.txt");
        int result = Day3.sumOfGroupBadgePriorities(inputData);
        System.out.println(result);
        assert result == 2681;
    }
}
