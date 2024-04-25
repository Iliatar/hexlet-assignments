package exercise;

import java.util.Arrays;

// BEGIN
public class ReversedSequence implements CharSequence{
    private final char[] chars;

    public ReversedSequence(String string) {
        chars = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            chars[i] = string.charAt(string.length() - i - 1);
        }
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt(int index) {
        return chars[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < end; i++) {
            builder.append(chars[i]);
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            builder.append(c);
        }
        return builder.toString();
    }
}
// END
