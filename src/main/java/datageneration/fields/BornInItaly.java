package datageneration.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum BornInItaly {
    YES("1"),
    NO("0");

    private final String value;

    private static final Random RANDOM = new Random();

    @Override
    public String toString() {
        return this == YES ? "Si" : "No";
    }

    public static BornInItaly getRandomValue() {
        BornInItaly[] values = BornInItaly.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public boolean asBoolean() {
        return this == YES;
    }
}
