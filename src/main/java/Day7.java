import day7.Directory;
import day7.FileSystem;
import day7.TerminalOutputReader;

import java.util.List;

public class Day7 {
    public static int sumOfLessThan100000DirectorySizes(List<String> inputData) {
        FileSystem fileSystem = new TerminalOutputReader(inputData).readFileStructure();
        return fileSystem.allDirsStream()
                .mapToInt(Directory::size)
                .filter(size -> size <= 100000)
                .sum();
    }

    public static int smallestDirSizeToDeleteToSolveSpaceProblem(List<String> inputData) {
        int totalDiskSpace = 70000000;
        int neededSpace = 30000000;
        FileSystem fileSystem = new TerminalOutputReader(inputData).readFileStructure();
        int usedSpace = fileSystem.usedSpace();
        int freeSpace = totalDiskSpace - usedSpace;
        int spaceNeededToFind = neededSpace - freeSpace;
        return fileSystem.allDirsStream()
                .mapToInt(Directory::size)
                .filter(size -> size >= spaceNeededToFind)
                .sorted()
                .findFirst().orElse(0);
    }


}
