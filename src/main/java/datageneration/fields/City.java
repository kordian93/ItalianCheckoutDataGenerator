package datageneration.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum City {
    ROMA("Rome", "H501"),
    MILANO("Milan", "F205"),
    NAPOLI("Naples", "F839"),
    TORINO("Turin", "L219"),
    PALERMO("Palermo", "G273"),
    GENOVA("Genoa", "D969"),
    BOLOGNA("Bologna", "A944"),
    FIRENZE("Florence", "D612"),
    VENEZIA("Venice", "L736"),
    BARI("Bari", "A662");

    private final String name, istatCode;

    private static final Random RANDOM = new Random();

    @Override
    public String toString() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }

    public static City getRandomValue() {
        City[] values = City.values();
        return values[RANDOM.nextInt(values.length)];
    }
}
