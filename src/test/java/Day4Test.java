import org.junit.Test;

import java.io.IOException;
import java.util.Set;


public class Day4Test {

    @Test
    public void shouldReadSectionAssignments() {
        assert Day4.sectionAssignments("1-1").equals(Set.of(1));
        assert Day4.sectionAssignments("1-2").equals(Set.of(1, 2));
        assert Day4.sectionAssignments("1-3").equals(Set.of(1, 2, 3));
        assert Day4.sectionAssignments("4-6").equals(Set.of(4, 5, 6));
    }

    @Test
    public void shouldFindPairsNeedingReconsideration() {
        assert Day4.pairHasOneAssignmentFullyContainingOther("2-8,3-7");
        assert !Day4.pairHasOneAssignmentFullyContainingOther("2-4,6-8");
        assert Day4.pairHasOneAssignmentFullyContainingOther("6-6,4-6");
    }

    @Test
    public void firstStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day4-example.txt");
        assert Day4.numberOfPairsNeedingReconsideration(inputData) == 2;
    }

    @Test
    public void solveFirstStar() throws IOException {
        String inputData = Utils.readInputData("day4.txt");
        long result = Day4.numberOfPairsNeedingReconsideration(inputData);
        System.out.println(result);
        assert result == 518;
    }

    @Test
    public void shouldFindOverlappingAssignments() {
        assert Day4.assignmentsDoOverlap("5-7,7-9");
        assert Day4.assignmentsDoOverlap("2-6,4-8");
        assert !Day4.assignmentsDoOverlap("2-2,4-8");
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        String inputData = Utils.readInputData("day4-example.txt");
        assert Day4.numberOfOverlappingAssignments(inputData) == 4;
    }

    @Test
    public void solveSecondStar() throws IOException {
        String inputData = Utils.readInputData("day4.txt");
        long result = Day4.numberOfOverlappingAssignments(inputData);
        System.out.println(result);
        assert result == 909;
    }
}
