package datageneration.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum Region {
    AGRIGENTO("825", "Agrigento"),
    ALESSANDRIA("826", "Alessandria"),
    ANCONA("827", "Ancona"),
    AOSTA("828", "Aosta"),
    AREZZO("830", "Arezzo"),
    ASCOLI_PICENO("831", "Ascoli-Piceno"),
    ASTI("832", "Asti"),
    AVELLINO("833", "Avellino"),
    BARI("834", "Bari"),
    BARLETTA_ANDRIA_TRANI("835", "Barletta-Andria-Trani"),
    BELLUNO("836", "Belluno"),
    BENEVENTO("837", "Benevento"),
    BERGAMO("838", "Bergamo"),
    BIELLA("839", "Biella"),
    BOLOGNA("840", "Bologna"),
    BOLZANO("841", "Bolzano"),
    BRESCIA("842", "Brescia"),
    BRINDISI("843", "Brindisi"),
    CAGLIARI("844", "Cagliari"),
    CALTANISSETTA("845", "Caltanissetta"),
    CAMPOBASSO("846", "Campobasso"),
    CARBONIA_IGLESIAS("847", "Carbonia Iglesias"),
    CASERTA("848", "Caserta"),
    CATANIA("849", "Catania"),
    CATANZARO("850", "Catanzaro"),
    CHIETI("851", "Chieti"),
    COMO("852", "Como"),
    COSENZA("853", "Cosenza"),
    CREMONA("854", "Cremona"),
    CROTONE("855", "Crotone"),
    CUNEO("856", "Cuneo"),
    ENNA("857", "Enna"),
    FERMO("858", "Fermo"),
    FERRARA("859", "Ferrara"),
    FIRENZE("860", "Firenze"),
    FOGGIA("861", "Foggia"),
    FORLI_CESENA("862", "Forli-Cesena"),
    FROSINONE("863", "Frosinone"),
    GENOVA("864", "Genova"),
    GORIZIA("865", "Gorizia"),
    GROSSETO("866", "Grosseto"),
    IMPERIA("867", "Imperia"),
    ISERNIA("868", "Isernia"),
    L_AQUILA("829", "L'Aquila"),
    LA_SPEZIA("869", "La-Spezia"),
    LATINA("870", "Latina"),
    LECCE("871", "Lecce"),
    LECCO("872", "Lecco"),
    LIVORNO("873", "Livorno"),
    LODI("874", "Lodi"),
    LUCCA("875", "Lucca"),
    MACERATA("876", "Macerata"),
    MANTOVA("877", "Mantova"),
    MASSA_CARRARA("878", "Massa-Carrara"),
    MATERA("879", "Matera"),
    MEDIO_CAMPIDANO("880", "Medio Campidano"),
    MESSINA("881", "Messina"),
    MILANO("882", "Milano"),
    MODENA("883", "Modena"),
    MONZA_BRIANZA("884", "Monza-Brianza"),
    NAPOLI("885", "Napoli"),
    NOVARA("886", "Novara"),
    NUORO("887", "Nuoro"),
    OGLIASTRA("888", "Ogliastra"),
    OLBIA_TEMPIO("889", "Olbia Tempio"),
    ORISTANO("890", "Oristano"),
    PADOVA("891", "Padova"),
    PALERMO("892", "Palermo"),
    PARMA("893", "Parma"),
    PAVIA("894", "Pavia"),
    PERUGIA("895", "Perugia"),
    PESARO_URBINO("896", "Pesaro-Urbino"),
    PESCARA("897", "Pescara"),
    PIACENZA("898", "Piacenza"),
    PISA("899", "Pisa"),
    PISTOIA("900", "Pistoia"),
    PORDENONE("901", "Pordenone"),
    POTENZA("902", "Potenza"),
    PRATO("903", "Prato"),
    RAGUSA("904", "Ragusa"),
    RAVENNA("905", "Ravenna"),
    REGGIO_CALABRIA("906", "Reggio-Calabria"),
    REGGIO_EMILIA("907", "Reggio-Emilia"),
    RIETI("908", "Rieti"),
    RIMINI("909", "Rimini"),
    ROMA("910", "Roma"),
    ROVIGO("911", "Rovigo"),
    SALERNO("912", "Salerno"),
    SASSARI("913", "Sassari"),
    SAVONA("914", "Savona"),
    SIENA("915", "Siena"),
    SIRACUSA("916", "Siracusa"),
    SONDRIO("917", "Sondrio"),
    TARANTO("918", "Taranto"),
    TERAMO("919", "Teramo"),
    TERNI("920", "Terni"),
    TORINO("921", "Torino"),
    TRAPANI("922", "Trapani"),
    TRENTO("923", "Trento"),
    TREVISO("924", "Treviso"),
    TRIESTE("925", "Trieste"),
    UDINE("926", "Udine"),
    VARESE("927", "Varese"),
    VENEZIA("928", "Venezia"),
    VERBANIA("929", "Verbania"),
    VERCELLI("930", "Vercelli"),
    VERONA("931", "Verona"),
    VIBO_VALENTIA("932", "Vibo-Valentia"),
    VICENZA("933", "Vicenza"),
    VITERBO("934", "Viterbo");

    private final String value, text;

    private static final Random RANDOM = new Random();

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(text);
    }


    public static Region getRandomValue() {
        Region[] values = Region.values();
        return values[RANDOM.nextInt(values.length)];
    }
}
