package day7;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TerminalOutputReader {

    public static final Pattern FILE_LINE_PATTERN = Pattern.compile("([0-9]+) ([a-z.]+)");
    private final List<String> terminalLines;
    private final FileSystem fileSystem;

    public TerminalOutputReader(List<String> terminalLines) {
        this.terminalLines = terminalLines;
        this.fileSystem = new FileSystem();
    }

    private static boolean isACommandLine(String line) {
        return line.startsWith("$");
    }

    private static boolean isADirLine(String line) {
        return line.startsWith("dir");
    }

    public FileSystem readFileStructure() {
        for (int i = 0; i < this.terminalLines.size(); i++) {
            String command = this.terminalLines.get(i);
            if (command.startsWith("$ cd")) {
                readChangeDirCommand(command);
            }
            if (command.equals("$ ls")) {
                while (i + 1 < this.terminalLines.size()) {
                    String nextLine = this.terminalLines.get(i + 1);
                    if (isACommandLine(nextLine)) {
                        break;
                    }
                    readContentLine(nextLine);
                    i++;
                }
            }
        }
        return this.fileSystem;
    }

    private void readChangeDirCommand(String line) {
        String dirName = line.substring(5);
        this.fileSystem.changeDirectory(dirName);
    }

    private void readContentLine(String line) {
        if (isADirLine(line)) {
            readDirLine(line);
        } else {
            readNewFileLine(line);
        }
    }

    private void readDirLine(String line) {
        String dir = line.substring(4);
        this.fileSystem.readNewDir(dir);
    }

    private void readNewFileLine(String line) {
        Matcher matcher = FILE_LINE_PATTERN.matcher(line);
        if (matcher.find()) {
            int fileSize = Integer.parseInt(matcher.group(1));
            String fileName = matcher.group(2);
            this.fileSystem.readNewFile(fileName, fileSize);
        }
    }
}
