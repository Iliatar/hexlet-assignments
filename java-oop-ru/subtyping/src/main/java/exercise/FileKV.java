package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{
    private final String filePath;

    public FileKV (String filePath, Map<String, String> storage) {
        this.filePath = filePath;
        writeToFile(storage);
    }
    @Override
    public void set(String key, String value) {
        Map<String, String> storage = readFromFile();
        storage.put(key, value);
        writeToFile(storage);
    }

    @Override
    public void unset(String key) {
        Map<String, String> storage = readFromFile();
        storage.remove(key);
        writeToFile(storage);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> storage = readFromFile();
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> storage = readFromFile();
        Map<String, String> result = new HashMap<>();

        for (String key : storage.keySet()) {
            result.put(key, storage.get(key));
        }

        return result;
    }

    private Map<String, String> readFromFile() {
        return Utils.unserialize(Utils.readFile(filePath));
    }

    private void writeToFile (Map<String, String> storage) {
        Utils.writeFile(filePath, Utils.serialize(storage));
    }
}
// END
