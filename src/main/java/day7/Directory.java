package day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {
    public final String name;
    public final Directory parentDir;
    public final Map<String, Directory> directories = new HashMap<>();
    public final List<File> files = new ArrayList<>();

    public Directory(String name, Directory parentDir) {
        this.name = name;
        this.parentDir = parentDir;
    }

    public int size() {
        int sumOfSizeOfSubdirectories = directories.values().stream().mapToInt(Directory::size).sum();
        int sumOfSizeOfFiles = files.stream().mapToInt(File::size).sum();
        return sumOfSizeOfSubdirectories + sumOfSizeOfFiles;
    }
}
