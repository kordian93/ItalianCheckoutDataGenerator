package datageneration;

import java.util.LinkedHashMap;
import java.util.Map;

import datageneration.fields.BornInItaly;
import datageneration.fields.Country;
import datageneration.fields.FiscalCodeCheck;
import datageneration.fields.Gender;
import datageneration.fields.Region;

public class JsScript {

    private static final String INPUT_SELECTOR = "div.billing-address-form input[name$=\"%s\"]";
    private static final String SELECT_SELECTOR = "div.billing-address-form select[name$=\"%s\"]";
    private static final String UPDATE_FIELD = """
                                               inputElement = document.querySelector('%s');
                                               inputElement.value = '%s';
                                               event = new Event('change', { bubbles: true });
                                               inputElement.dispatchEvent(event);
                                               """;
    private static final String WAIT = "await new Promise(r => setTimeout(r, 1500));\n";

    private static final Map<Field, String> FIELD_SELECTORS = new LinkedHashMap<>();

    static {
        FIELD_SELECTORS.put(Field.NAME, String.format(INPUT_SELECTOR, "firstname"));
        FIELD_SELECTORS.put(Field.SURNAME, String.format(INPUT_SELECTOR, "lastname"));
        FIELD_SELECTORS.put(Field.ADDRESS, String.format(INPUT_SELECTOR, "street[0]"));
        FIELD_SELECTORS.put(Field.COUNTRY, String.format(SELECT_SELECTOR, "country_id"));
        FIELD_SELECTORS.put(Field.REGION, String.format(SELECT_SELECTOR, "region_id"));
        FIELD_SELECTORS.put(Field.CITY, String.format(INPUT_SELECTOR, "city"));
        FIELD_SELECTORS.put(Field.POSTAL_CODE, String.format(INPUT_SELECTOR, "postcode"));
        FIELD_SELECTORS.put(Field.FISCAL_CODE_CHECK, String.format(SELECT_SELECTOR, "custom_attributes[fiscal_code_check]"));
        FIELD_SELECTORS.put(Field.FISCAL_CODE, String.format(INPUT_SELECTOR, "custom_attributes[fiscal_code]"));
        FIELD_SELECTORS.put(Field.BIRTH_DATE, String.format(INPUT_SELECTOR, "custom_attributes[birth_date]"));
        FIELD_SELECTORS.put(Field.GENDER, String.format(SELECT_SELECTOR, "custom_attributes[gender]"));
        FIELD_SELECTORS.put(Field.BORN_IN_ITALY, String.format(SELECT_SELECTOR, "custom_attributes[born_in_italy]"));
        FIELD_SELECTORS.put(Field.CITY_OF_BIRTH, String.format(INPUT_SELECTOR, "custom_attributes[birth_place]"));
        FIELD_SELECTORS.put(Field.COUNTRY_OF_BIRTH, String.format(SELECT_SELECTOR, "custom_attributes[birth_country]"));
    }

    public String generate(Data data) {
        StringBuilder script = new StringBuilder("let inputElement;\nlet event;\n");

        for (Map.Entry<Field, String> entry : FIELD_SELECTORS.entrySet()) {
            Field field = entry.getKey();
            String selector = entry.getValue();

            if (data.hasField(field)) {
                Object fieldValue = data.getField(field);
                String value = switch (field) {
                    case COUNTRY ->
                        ((Country) fieldValue).getValue();
                    case REGION ->
                        ((Region) fieldValue).getValue();
                    case FISCAL_CODE_CHECK ->
                        ((FiscalCodeCheck) fieldValue).getValue();
                    case GENDER ->
                        ((Gender) fieldValue).getValue();
                    case BORN_IN_ITALY ->
                        ((BornInItaly) fieldValue).getValue();
                    default ->
                        fieldValue.toString();
                };

                value = escapeJsString(value);

                script.append(String.format(UPDATE_FIELD, selector, value));

                if (field == Field.FISCAL_CODE_CHECK || field == Field.BORN_IN_ITALY) {
                    script.append(WAIT);
                }
            }
        }

        return script.toString();
    }

    private String escapeJsString(String value) {
        return value.replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                .replace("`", "\\`");
    }
}
