package datageneration;

import datageneration.fields.BornInItaly;
import datageneration.fields.City;
import datageneration.fields.Country;
import datageneration.fields.FiscalCodeCheck;
import datageneration.fields.Gender;
import datageneration.fields.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Field {
    NAME("Nome:*", String.class),
    SURNAME("Cognome:*", String.class),
    ADDRESS("Indirizzo:", String.class),
    COUNTRY("Paese (dropdown):", Country.class),
    REGION("Provincia (dropdown):", Region.class),
    CITY("Città:", String.class),
    POSTAL_CODE("CAP:", String.class),
    FISCAL_CODE_CHECK("Codice fiscale (dropdown):", FiscalCodeCheck.class),
    FISCAL_CODE("Codice fiscale:", String.class),
    BIRTH_DATE("Data di nascita:*", String.class),
    GENDER("Sesso (dropdown):*", Gender.class),
    BORN_IN_ITALY("Seleziona (dropdown)", BornInItaly.class),
    CITY_OF_BIRTH("Comune di nascita:*", City.class),
    COUNTRY_OF_BIRTH("Paese di origine (dropdown):*", Country.class);

    private final String label;
    private final Class<?> editableClass;
}
