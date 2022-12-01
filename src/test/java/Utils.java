import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static String readInputData(String file) throws IOException {
        return Files.readString(Paths.get("src/test/resources/", file));
    }
}
