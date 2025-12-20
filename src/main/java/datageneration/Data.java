package datageneration;

import java.util.EnumMap;
import java.util.Map;

public class Data {
    private final Map<Field, Object> fieldValues = new EnumMap<>(Field.class);

    Data(Map<Field, Object> fieldValues) {
        this.fieldValues.putAll(fieldValues);
    }

    @SuppressWarnings("unchecked")
    public <T> T getField(Field field) {
        return (T) fieldValues.get(field);
    }

    public boolean hasField(Field field) {
        return fieldValues.containsKey(field);
    }
}