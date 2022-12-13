import day13.Packets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day13 {

    public static int sumOfPacketsInOrderIndices(List<String> inputData) {
        int sumOfPacketsInOrderIndices = 0;
        for (int i = 0; i < inputData.size(); i = i + 3) {
            Packets p1 = new Packets(inputData.get(i));
            Packets p2 = new Packets(inputData.get(i + 1));
            sumOfPacketsInOrderIndices += packetsInOrder(p1, p2) ? (i / 3) + 1 : 0;
        }
        return sumOfPacketsInOrderIndices;
    }

    public static int positionsOfDividerPackets(List<String> inputData) {
        Packets divider1 = new Packets("[[2]]");
        Packets divider2 = new Packets("[[6]]");
        List<Packets> packets = new ArrayList<>(inputData.stream()
                .filter(line -> !line.isBlank())
                .map(Packets::forLine)
                .toList());
        packets.add(divider1);
        packets.add(divider2);
        Collections.sort(packets);
        return (packets.indexOf(divider1) + 1) * (packets.indexOf(divider2) + 1);
    }

    public static boolean packetsInOrder(Packets p1, Packets p2) {
        return p1.compareTo(p2) < 0;
    }

}
