import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 {

    public static int sumOfCommonItemPriorities(String inputData) {
        return Arrays.stream(eachRucksack(inputData))
                .map(Day3::asCompartments)
                .map(Day3::findErroneousItem)
                .mapToInt(Day3::computePriority)
                .sum();
    }

    public static int sumOfGroupBadgePriorities(String inputData) {
        return elvesGroups(inputData).stream()
                .map(Day3::findGroupBadge)
                .mapToInt(Day3::computePriority)
                .sum();
    }

    private static String[] eachRucksack(String inputData) {
        return inputData.split("\n");
    }

    static List<String> asCompartments(String rucksackContent) {
        int halfLength = rucksackContent.length() / 2;
        return Arrays.asList(rucksackContent.substring(0, halfLength), rucksackContent.substring(halfLength));
    }

    static char findErroneousItem(List<String> rucksackContent) {
        return (char) rucksackContent.get(0)
                .chars()
                .filter(c -> rucksackContent.get(1).indexOf(c) != -1)
                .findFirst().orElse(0);
    }

    static int computePriority(char item) {
        return item >= 'a' ? item - 'a' + 1 : item - 'A' + 27;
    }

    static List<String[]> elvesGroups(String inputData) {
        return Pattern
                .compile("([a-zA-Z]+\\n){3}")
                .matcher(inputData + "\n")
                .results()
                .map(MatchResult::group)
                .map(s -> s.split("\n"))
                .collect(Collectors.toList());
    }

    static char findGroupBadge(String[] group) {
        return (char) group[0].chars()
                .filter(value -> Arrays.stream(group)
                        .skip(1)
                        .allMatch(s -> s.contains(Character.toString(value))))
                .findFirst()
                .orElse(0);
    }
}
