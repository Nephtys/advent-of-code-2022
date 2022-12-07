package day7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileSystem {
    public final Directory rootDir;
    private final List<Directory> allDirs = new ArrayList<>();
    private Directory currentDir;

    public FileSystem() {
        this.rootDir = new Directory("/", null);
        this.currentDir = this.rootDir;
    }

    public int usedSpace() {
        return this.rootDir.size();
    }

    public void changeDirectory(String dir) {
        this.currentDir = switch (dir) {
            case "/" -> this.rootDir;
            case ".." -> this.currentDir.parentDir();
            default -> this.currentDir.directories().get(dir);
        };
    }

    public void readNewFile(String fileName, int fileSize) {
        this.currentDir.addFile(fileName, fileSize);
    }

    public void readNewDir(String dirName) {
        Directory dir = this.currentDir.addDirectory(dirName);
        this.allDirs.add(dir);
    }

    public Stream<Directory> allDirsStream() {
        return allDirs.stream();
    }
}
