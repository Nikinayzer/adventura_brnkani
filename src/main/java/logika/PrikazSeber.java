package logika;
/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Slouží k sbírání věcí ve hře.
 *
 *@author     Nikita Korotov
 *@version    1.0 11.6.2022
 *
 */

public class PrikazSeber implements IPrikaz{
    private static final String NAZEV = "seber";
    private HerniPlan herniPlan;
    private Batoh batoh;
    private Hrdina hrdina;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan
     * @param batoh
     * @param hrdina
     */
    public PrikazSeber(HerniPlan herniPlan, Batoh batoh, Hrdina hrdina) {
        this.herniPlan = herniPlan;
        this.batoh = batoh;
        this.hrdina = hrdina;
    }

    /**
     * Metoda provádí příkaz seber. Kontroluje délku příkazu, zda je věc ve prostoru a neumožňuje provádět příkaz v obchodech.
     * Po úspěchu přidáva věc do batohu a vytahuje ze prostoru
     *
     * @param parametry název věci.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat?";
        }

        String nazevVeci = parametry[0];

        Prostor aktualni = herniPlan.getAktualniProstor();

        if (!aktualni.getVeci().containsKey(nazevVeci)){
            return "To tu neni!";
        }
        if(aktualni.getNazev().equals("obchod_s_nastroji")||aktualni.getNazev().equals("obchod_s_potravinami")){
            return "Měl bys si něco koupit, nekrást";
        }
        Vec sbirana = aktualni.getVeci().remove(nazevVeci);

        //easter egg
        if(sbirana.isPrenositelna()&&batoh.isMisto()&&nazevVeci.equals("mince")){
            hrdina.setPenize(hrdina.getPenize()+20);
            return "Našel jsi minci! Včera jsem ji ztratil...";
        }
        if(sbirana.isPrenositelna()&&batoh.isMisto()){
            batoh.vlozVec(sbirana);
            aktualni.odeberVec(nazevVeci);
            return "Je to sebrane";

        }
        aktualni.vlozVec(sbirana); // jínak ve hře bude bug. Pokud se hráč pokusí věc vzít, bude odstraněná, i když není přenositelná.
        return "To nezvedneš";
    }


    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
