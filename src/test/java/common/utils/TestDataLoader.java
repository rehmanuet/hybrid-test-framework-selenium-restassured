package common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class TestDataLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String, JsonNode> cache = new ConcurrentHashMap<>();

    public static synchronized <T> T getData(String fileName, String key, Class<T> clazz) throws IOException {
        JsonNode root = cache.computeIfAbsent(fileName, TestDataLoader::loadJsonFromResource);
        JsonNode node = root.get(key);
        if (node == null) {
            throw new IllegalArgumentException("Key '" + key + "' not found in file: " + fileName);
        }
        return objectMapper.treeToValue(node, clazz);
    }

    private static JsonNode loadJsonFromResource(String fileName) {
        try (InputStream input = TestDataLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalArgumentException("File not found in resources: " + fileName);
            }
            return objectMapper.readTree(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read or parse test data file: " + fileName, e);
        }
    }
}