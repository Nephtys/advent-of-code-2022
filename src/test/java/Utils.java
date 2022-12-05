import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Utils {
    public static String readInputData(String file) throws IOException {
        return Files.readString(Paths.get("src/test/resources/", file));
    }

    public static List<String> readInputDataToLines(String file) throws IOException {
        return Files.readAllLines(Paths.get("src/test/resources/", file));
    }
}
