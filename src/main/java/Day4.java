import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    public static long numberOfPairsNeedingReconsideration(String inputData) {
        return Arrays.stream(eachPair(inputData))
                .filter(Day4::pairHasOneAssignmentFullyContainingOther)
                .count();
    }

    public static long numberOfOverlappingAssignments(String inputData) {
        return Arrays.stream(eachPair(inputData))
                .filter(Day4::assignmentsDoOverlap)
                .count();
    }

    private static String[] eachPair(String inputData) {
        return inputData.split("\n");
    }

    static Set<Integer> sectionAssignments(String sectionData) {
        String[] range = sectionData.split("-");
        return IntStream.rangeClosed(Integer.parseInt(range[0]), Integer.parseInt(range[1])).boxed().collect(Collectors.toSet());
    }

    static boolean pairHasOneAssignmentFullyContainingOther(String pairsData) {
        List<Set<Integer>> assignments = Arrays.stream(pairsData.split(",")).map(Day4::sectionAssignments).toList();
        return (assignments.get(0).containsAll(assignments.get(1))
                || assignments.get(1).containsAll(assignments.get(0)));
    }

    static boolean assignmentsDoOverlap(String pairsData) {
        List<Set<Integer>> assignments = Arrays.stream(pairsData.split(",")).map(Day4::sectionAssignments).toList();
        return assignments.get(0).stream().anyMatch(i -> assignments.get(1).contains(i))
                || assignments.get(1).stream().anyMatch(i -> assignments.get(0).contains(i));
    }
}
