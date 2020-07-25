package priceobserver.controller;

import org.json.JSONObject;
import priceobserver.dto.product.ProductDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ControllerUtils {
    private ControllerUtils() {

    }

    public static Map<String, String> getPropertiesMap(ProductDto product) {
        return Optional.ofNullable(product.getProductProperties())
                .map(p -> new JSONObject(p.getProperties()).toMap())
                .map(ControllerUtils::convertMap)
                .orElse(Collections.emptyMap());
    }

    public static Map<String, String> convertMap(Map<String, Object> map) {
        Map<String, String> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof String) {
                newMap.put(entry.getKey(), (String) entry.getValue());
            }
        }

        return newMap;
    }
}
