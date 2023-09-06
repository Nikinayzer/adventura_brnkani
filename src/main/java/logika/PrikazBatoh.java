package logika;

/**
 *  Třída PrikazBatoh implementuje pro hru příkaz batoh.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Slouží k vypisu obsahu batohu pro hráče
 *
 *@author     Nikita Korotov
 *@version    1.0 11.6.2022
 */
public class PrikazBatoh implements IPrikaz {
    private static final String NAZEV = "batoh";
    private Batoh batoh;

    /**
     * Konstruktor třídy
     * @param batoh
     */
    public PrikazBatoh(Batoh batoh) {
        this.batoh = batoh;
    }

    /**
     * Metoda provadi příkaz batoh, který vypíše řetězec s obsahem batohu.
     * @param parametry
     * @return zpráva, kterou vypíše hra hráči (obsah batohu)
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            // pokud je druhé slovo v příkazu, tak ....
            return "Batoh co? Nechápu, proč jsi zadal druhé slovo.";}
            if (batoh.getBatohMap().isEmpty()) {
                return "Nic v batohu nemáš";
            } else {
                return "Máš v batohu: " + batoh.vypisBatoha();
            }
        }


    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
        @Override
        public String getNazev () {
            return NAZEV;
        }


}
