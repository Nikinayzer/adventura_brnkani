package logika;

/**
 *  Třída PrikazJist implementuje pro hru příkaz jist.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Slouží ke zvýšení HP.
 *
 *@author     Nikita Korotov
 *@version    1.0 11.6.2022
 *
 */

public class PrikazJist implements IPrikaz {
    public static final String NAZEV = "jist";
    private Hrdina hrdina;
    private Batoh batoh;

    /**
     * Konstruktor třídy
     * @param hrdina
     * @param batoh
     */
    public PrikazJist(Hrdina hrdina, Batoh batoh) {
        this.hrdina = hrdina;
        this.batoh = batoh;
    }
    /**
     * Metoda provádí zvyšení hp, ale před tím ověřuje, jestli hrač konzumuje jídlo a má-li ho v batohu.
     * @param parametry název jídla
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length < 1) {
            // pokud chybí druhé slovo (název jídla), tak ....
            return "Jíst co? Nechápu, proč jsi nezadal druhé slovo.";
        }
        String nazevJidla = parametry[0];
        if (!batoh.obsahuje(nazevJidla)) {
            //kontrola, zda je jídlo v batohu
            return "Co chceš jíst? To nemás v batohu.";
        }
        Vec vec = batoh.getBatohMap().get(nazevJidla);
        if (!vec.isJidlo()){
            //kontrola, zda je vec opravdu jídlo
            return "To není jedlé";
        }
            hrdina.setHp(hrdina.getHp() + vec.getHpJidlo(parametry[0]));
            batoh.odeberVec(parametry[0]);
            //vypíše, že hráč úspešně konzumoval jídlo a počét hp (po konzumace jídla)
            return "Dobrou chuť, ted´ máš " + hrdina.getHp() + " HP";
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
