package day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Directory(String name, Directory parentDir, Map<String, Directory> directories, List<File> files) {

    public Directory(String name, Directory parentDir) {
        this(name, parentDir, new HashMap<>(), new ArrayList<>());
    }

    public int size() {
        int sumOfSizeOfSubdirectories = directories.values().stream().mapToInt(Directory::size).sum();
        int sumOfSizeOfFiles = files.stream().mapToInt(File::size).sum();
        return sumOfSizeOfSubdirectories + sumOfSizeOfFiles;
    }

    public Directory addDirectory(String dirName) {
        Directory dir = new Directory(dirName, this);
        this.directories.put(dir.name(), dir);
        return dir;
    }

    public void addFile(String fileName, int fileSize) {
        this.files.add(new File(fileName, fileSize));
    }
}
