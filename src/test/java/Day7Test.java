import day7.Directory;
import day7.TerminalOutputReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Day7Test {

    @Test
    public void shouldBuildTheFileStructure() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day7-example.txt");
        Directory rootDirectory = new TerminalOutputReader(inputData).readFileStructure().rootDir;
        assert rootDirectory.name().equals("/");
        assert rootDirectory.directories().size() == 2;
        assert rootDirectory.files().size() == 2;
        assert rootDirectory.directories().get("a").name().equals("a");
        assert rootDirectory.directories().get("a").directories().get("e").name().equals("e");
        assert rootDirectory.directories().get("a").files().get(0).name().equals("f");
        assert rootDirectory.directories().get("a").files().get(0).size() == 29116;
    }

    @Test
    public void shouldReadDirectorySize() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day7-example.txt");
        Directory rootDirectory = new TerminalOutputReader(inputData).readFileStructure().rootDir;
        assert rootDirectory.size() == 48381165;
        assert rootDirectory.directories().get("a").size() == 94853;
    }

    @Test
    public void firstStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day7-example.txt");
        assert Day7.sumOfLessThan100000DirectorySizes(inputData) == 95437;
    }

    @Test
    public void solveFirstStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day7.txt");
        int result = Day7.sumOfLessThan100000DirectorySizes(inputData);
        System.out.println(result);
        assert result == 1182909;
    }

    @Test
    public void secondStarOnProvidedExample() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day7-example.txt");
        assert Day7.smallestDirSizeToDeleteToSolveSpaceProblem(inputData) == 24933642;
    }

    @Test
    public void solveSecondStar() throws IOException {
        List<String> inputData = Utils.readInputDataToLines("day7.txt");
        int result = Day7.smallestDirSizeToDeleteToSolveSpaceProblem(inputData);
        System.out.println(result);
        assert result == 2832508;
    }
}
