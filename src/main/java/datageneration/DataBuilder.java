package datageneration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.github.javafaker.Faker;

import datageneration.fields.BornInItaly;
import datageneration.fields.City;
import datageneration.fields.Country;
import static datageneration.fields.Country.ITALY;
import datageneration.fields.FiscalCodeCheck;
import datageneration.fields.Gender;
import static datageneration.fields.Gender.MALE;
import datageneration.fields.Region;

public class DataBuilder {

    private final Map<Field, Object> fieldValues = new EnumMap<>(Field.class);

    private final Map<String, Integer> oddSumValues = new HashMap<>();
    private final Map<String, Integer> evenSumValues = new HashMap<>();
    private final Map<Integer, Character> controlCharValues = new HashMap<>();

    public DataBuilder() {
        oddSumValues.put("0", 1);
        oddSumValues.put("1", 0);
        oddSumValues.put("2", 5);
        oddSumValues.put("3", 7);
        oddSumValues.put("4", 9);
        oddSumValues.put("5", 13);
        oddSumValues.put("6", 15);
        oddSumValues.put("7", 17);
        oddSumValues.put("8", 19);
        oddSumValues.put("9", 21);
        oddSumValues.put("A", 1);
        oddSumValues.put("B", 0);
        oddSumValues.put("C", 5);
        oddSumValues.put("D", 7);
        oddSumValues.put("E", 9);
        oddSumValues.put("F", 13);
        oddSumValues.put("G", 15);
        oddSumValues.put("H", 17);
        oddSumValues.put("I", 19);
        oddSumValues.put("J", 21);
        oddSumValues.put("K", 2);
        oddSumValues.put("L", 4);
        oddSumValues.put("M", 18);
        oddSumValues.put("N", 20);
        oddSumValues.put("O", 11);
        oddSumValues.put("P", 3);
        oddSumValues.put("Q", 6);
        oddSumValues.put("R", 8);
        oddSumValues.put("S", 12);
        oddSumValues.put("T", 14);
        oddSumValues.put("U", 16);
        oddSumValues.put("V", 10);
        oddSumValues.put("W", 22);
        oddSumValues.put("X", 25);
        oddSumValues.put("Y", 24);
        oddSumValues.put("Z", 23);

        evenSumValues.put("0", 0);
        evenSumValues.put("1", 1);
        evenSumValues.put("2", 2);
        evenSumValues.put("3", 3);
        evenSumValues.put("4", 4);
        evenSumValues.put("5", 5);
        evenSumValues.put("6", 6);
        evenSumValues.put("7", 7);
        evenSumValues.put("8", 8);
        evenSumValues.put("9", 9);
        evenSumValues.put("A", 0);
        evenSumValues.put("B", 1);
        evenSumValues.put("C", 2);
        evenSumValues.put("D", 3);
        evenSumValues.put("E", 4);
        evenSumValues.put("F", 5);
        evenSumValues.put("G", 6);
        evenSumValues.put("H", 7);
        evenSumValues.put("I", 8);
        evenSumValues.put("J", 9);
        evenSumValues.put("K", 10);
        evenSumValues.put("L", 11);
        evenSumValues.put("M", 12);
        evenSumValues.put("N", 13);
        evenSumValues.put("O", 14);
        evenSumValues.put("P", 15);
        evenSumValues.put("Q", 16);
        evenSumValues.put("R", 17);
        evenSumValues.put("S", 18);
        evenSumValues.put("T", 19);
        evenSumValues.put("U", 20);
        evenSumValues.put("V", 21);
        evenSumValues.put("W", 22);
        evenSumValues.put("X", 23);
        evenSumValues.put("Y", 24);
        evenSumValues.put("Z", 25);

        controlCharValues.put(0, 'A');
        controlCharValues.put(1, 'B');
        controlCharValues.put(2, 'C');
        controlCharValues.put(3, 'D');
        controlCharValues.put(4, 'E');
        controlCharValues.put(5, 'F');
        controlCharValues.put(6, 'G');
        controlCharValues.put(7, 'H');
        controlCharValues.put(8, 'I');
        controlCharValues.put(9, 'J');
        controlCharValues.put(10, 'K');
        controlCharValues.put(11, 'L');
        controlCharValues.put(12, 'M');
        controlCharValues.put(13, 'N');
        controlCharValues.put(14, 'O');
        controlCharValues.put(15, 'P');
        controlCharValues.put(16, 'Q');
        controlCharValues.put(17, 'R');
        controlCharValues.put(18, 'S');
        controlCharValues.put(19, 'T');
        controlCharValues.put(20, 'U');
        controlCharValues.put(21, 'V');
        controlCharValues.put(22, 'W');
        controlCharValues.put(23, 'X');
        controlCharValues.put(24, 'Y');
        controlCharValues.put(25, 'Z');
    }

    public <T> DataBuilder setField(Field field, T value) {
        if (!field.getEditableClass().isInstance(value)) {
            throw new IllegalArgumentException("Invalid type for field: " + field);
        }
        fieldValues.put(field, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getField(Field field) {
        return (T) fieldValues.get(field);
    }

    public Data build() {
        return new Data(fieldValues);
    }

    public DataBuilder completeWithRandomValidData() {
        Country country = getField(Field.COUNTRY);
        if (country == null) {
            country = Country.getRandomValue();
            setField(Field.COUNTRY, country);
        }

        Locale locale = new Locale.Builder()
                .setLanguage(country.getValue().toLowerCase())
                .setRegion(country.getValue())
                .build();
        Faker faker = new Faker(locale);

        setFieldIfAbsent(Field.NAME, faker.name().firstName());
        setFieldIfAbsent(Field.SURNAME, faker.name().lastName());
        setFieldIfAbsent(Field.ADDRESS, faker.address().streetAddress());
        setFieldIfAbsent(Field.CITY, faker.address().city());
        setFieldIfAbsent(Field.POSTAL_CODE, faker.address().zipCode());

        if (ITALY.equals(country)) {
            setFieldIfAbsent(Field.REGION, Region.getRandomValue());
            setFieldIfAbsent(Field.FISCAL_CODE_CHECK, FiscalCodeCheck.getRandomValue());

            FiscalCodeCheck fiscalCodeCheck = getField(Field.FISCAL_CODE_CHECK);
            if (fiscalCodeCheck.asBoolean()) {
                LocalDate birthDayAsLocalDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                setFieldIfAbsent(Field.BIRTH_DATE, birthDayAsLocalDate.toString());
                setFieldIfAbsent(Field.GENDER, Gender.getRandomValue());
                setFieldIfAbsent(Field.BORN_IN_ITALY, BornInItaly.getRandomValue());

                BornInItaly bornInItaly = getField(Field.BORN_IN_ITALY);
                if (bornInItaly.asBoolean()) {
                    setFieldIfAbsent(Field.CITY_OF_BIRTH, City.getRandomValue());
                } else {
                    setFieldIfAbsent(Field.COUNTRY_OF_BIRTH, Country.getRandomNonItalyValue());
                }

                setField(Field.FISCAL_CODE, generateValidFiscalCode());
            } else {
                setFieldIfAbsent(Field.COUNTRY_OF_BIRTH, Country.getRandomNonItalyValue());
            }
        }

        return this;
    }

    private <T> void setFieldIfAbsent(Field field, T value) {
        if (!fieldValues.containsKey(field)) {
            setField(field, value);
        }
    }

    private String generateValidFiscalCode() {
        String generated = "";

        generated += getSurnameCode();
        generated += getFirstNameCode();
        generated += getBirthDateAndGenderCode();
        generated += getPlaceOfBirthCode();
        generated += getControlCharacter(generated);

        return generated;
    }

    private String[] extractConsonantsAndVowels(String name) {
        name = name.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder consonants = new StringBuilder();
        StringBuilder vowels = new StringBuilder();

        for (char c : name.toCharArray()) {
            if ("BCDFGHJKLMNPQRSTVWXYZ".indexOf(c) != -1) {
                consonants.append(c);
            } else if ("AEIOU".indexOf(c) != -1) {
                vowels.append(c);
            }
        }
        return new String[]{consonants.toString(), vowels.toString()};
    }

    private String getSurnameCode() {
        String lastName = getField(Field.SURNAME);

        String[] parts = extractConsonantsAndVowels(lastName);
        StringBuilder code = new StringBuilder(parts[0] + parts[1]);

        while (code.length() < 3) {
            code.append('X');
        }
        return code.substring(0, 3);
    }

    private String getFirstNameCode() {
        String firstName = getField(Field.NAME);

        String[] parts = extractConsonantsAndVowels(firstName);
        String consonants = parts[0];
        String vowels = parts[1];
        StringBuilder code = new StringBuilder();

        if (consonants.length() >= 4) {
            code.append(consonants.charAt(0));
            code.append(consonants.charAt(2));
            code.append(consonants.charAt(3));
        } else {
            code.append(consonants).append(vowels);
        }

        while (code.length() < 3) {
            code.append('X');
        }

        return code.substring(0, 3);
    }

    private String getBirthDateAndGenderCode() {
        String birthDate = getField(Field.BIRTH_DATE);
        Gender gender = getField(Field.GENDER);

        String birthDateCode = birthDate.substring(2, 4);

        birthDateCode += switch (birthDate.substring(5, 7)) {
            case "01" ->
                "A";
            case "02" ->
                "B";
            case "03" ->
                "C";
            case "04" ->
                "D";
            case "05" ->
                "E";
            case "06" ->
                "H";
            case "07" ->
                "L";
            case "08" ->
                "M";
            case "09" ->
                "P";
            case "10" ->
                "R";
            case "11" ->
                "S";
            case "12" ->
                "T";
            default ->
                throw new IllegalStateException("Unexpected value: " + birthDate.substring(5, 7));
        };

        String day = birthDate.substring(8, 10);
        birthDateCode += MALE.equals(gender) ? day : String.valueOf(Integer.parseInt(day) + 40);

        return birthDateCode;
    }

    private String getPlaceOfBirthCode() {
        BornInItaly bornInItaly = getField(Field.BORN_IN_ITALY);
        City cityOfBirth = getField(Field.CITY_OF_BIRTH);
        Country countryOfBirth = getField(Field.COUNTRY_OF_BIRTH);

        return bornInItaly.asBoolean() ? cityOfBirth.getIstatCode() : "Z" + countryOfBirth.getCountryCode();
    }

    private char getControlCharacter(String fiscalCodeFirst15) {
        int evenSum = 0;
        for (int i = 1; i <= 13; i += 2) {
            Integer valueToSum = evenSumValues.get(String.valueOf(fiscalCodeFirst15.charAt(i)));
            evenSum += valueToSum;
        }

        int oddSum = 0;
        for (int i = 0; i <= 14; i += 2) {
            Integer valueToSum = oddSumValues.get(String.valueOf(fiscalCodeFirst15.charAt(i)));
            oddSum += valueToSum;
        }

        int interoControllo = (evenSum + oddSum) % 26;

        return controlCharValues.get(interoControllo);
    }
}
