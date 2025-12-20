package datageneration.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String value;

    private static final Random RANDOM = new Random();

    @Override
    public String toString() {
        return this == MALE ? "Uomo" : "Donna";
    }

    public static Gender getRandomValue() {
        Gender[] values = Gender.values();
        return values[RANDOM.nextInt(values.length)];
    }
}
