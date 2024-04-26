package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        var entrySet = storage.toMap().entrySet();

        for (var entry : entrySet) {
            storage.unset(entry.getKey());
        }

        for (var entry : entrySet) {
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
