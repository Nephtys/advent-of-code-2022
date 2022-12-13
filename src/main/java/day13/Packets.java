package day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Packets implements Comparable<Packets> {

    public final List<Object> data = new ArrayList<>();

    private final String stringContent;

    public Packets(String content) {
        this.stringContent = content;
        parseData(content);
    }

    public static Packets forLine(String line) {
        return new Packets(line);
    }

    private void parseData(String content) {
        List<Object> currentList = this.data;
        Stack<List<Object>> parentsList = new Stack<>();
        for (int i = 1; i < content.length() - 1; i++) {
            char c = content.charAt(i);
            if (c >= '0' && c <= '9') {
                StringBuilder number = new StringBuilder();
                number.append(c);
                while (content.charAt(i + 1) >= '0' && content.charAt(i + 1) <= '9') {
                    number.append(content.charAt(i + 1));
                    i++;
                }
                currentList.add(Integer.parseInt(number.toString()));
            } else if (c == '[') {
                parentsList.push(currentList);
                List<Object> newList = new ArrayList<>();
                currentList.add(newList);
                currentList = newList;
            } else if (c == ']') {
                currentList = parentsList.pop();
            }
        }
    }

    @Override
    public int compareTo(Packets o) {
        return compare(this.data, o.data);
    }

    private int compare(List left, List right) {
        if (left.isEmpty() || right.isEmpty()) {
            return Integer.compare(left.size(), right.size());
        }
        int compare = 0;
        int maxIndexForBothLists = Math.min(left.size(), right.size());
        for (int i = 0; compare == 0 && i < maxIndexForBothLists; i++) {
            Object l = left.get(i);
            Object r = right.get(i);
            if (l instanceof Integer && r instanceof Integer) {
                compare = Integer.compare((int) l, (int) r);
            } else if (l instanceof List && r instanceof List) {
                compare = compare((List) l, (List) r);
            } else if (l instanceof List && r instanceof Integer) {
                compare = compare((List) l, List.of(r));
            } else if (l instanceof Integer && r instanceof List) {
                compare = compare(List.of(l), (List) r);
            }
        }
        if (compare == 0 && left.size() != right.size()) {
            return Integer.compare(left.size(), right.size());
        }
        return compare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packets packets = (Packets) o;
        return stringContent.equals(packets.stringContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringContent);
    }
}
