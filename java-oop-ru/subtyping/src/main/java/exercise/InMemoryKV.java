package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private final HashMap<String, String> storage;

    public InMemoryKV (Map<String, String> storage) {
        this.storage = new HashMap<>();

        this.storage.putAll(storage);
    }
    @Override
    public void set(String key, String value) {
        storage.put(key, value);
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> result = new HashMap<>();

        for (String key : storage.keySet()) {
            result.put(key, storage.get(key));
        }

        return result;
    }
}
// END
