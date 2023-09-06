package logika;


/**
 *  Třída PrikazStats implementuje pro hru příkaz stats.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Slouží k výstupu statistiky hráče.
 *
 *@author     Nikita Korotov
 *@version    1.0 11.6.2022
 */
class PrikazStats implements IPrikaz {
    private static final String NAZEV = "stats";
    private Hrdina hrdina;
    private Batoh batoh;


    /**
     * Konstruktor třídy
     * @param hrdina
     * @param batoh
     */
    public PrikazStats(Hrdina hrdina, Batoh batoh){
        this.hrdina=hrdina;
        this.batoh=batoh;
    }

    /**
     * Metoda, která vypíše statistiku
     *
     *
     * @param parametry žánr písničky.
     * @return zpráva, kterou vypíše hra hráči
     *
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Máš " +hrdina.getHp()+ " HP, " +hrdina.getPenize() + " korun, a "+ hrdina.getFanousky()+ " fanoušků. Tvůj skill je "+hrdina.getSkill()+", " +"level kytary = "+batoh.levelKytary()+" ." ;
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
