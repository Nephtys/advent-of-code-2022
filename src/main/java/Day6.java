import java.util.stream.IntStream;

public class Day6 {

    public static int startOfPacketMarkerPosition(String inputData) {
        int markerSize = 4;
        return markerPosition(inputData, markerSize);
    }

    public static int startOfMessageMarkerPosition(String inputData) {
        int markerSize = 14;
        return markerPosition(inputData, markerSize);
    }

    private static int markerPosition(String inputData, int markerSize) {
        for (int i = 0; i + markerSize <= inputData.length(); i++) {
            long distinctCharsCount = IntStream
                    .range(i, i + markerSize)
                    .mapToObj(inputData::charAt)
                    .distinct()
                    .count();

            if (distinctCharsCount == markerSize) {
                return i + markerSize;
            }
        }
        return 0;
    }

}
