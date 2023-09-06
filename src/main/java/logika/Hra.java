package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 *
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Nikita Korotov, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    1.0 11.6.2022
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Hrdina hrdina;
    private Batoh batoh;

    private static Hra SINGLETON = new Hra();
    public static Hra getHra(){
        return SINGLETON;
    }
    public void restart() {
        SINGLETON = new Hra();
    }

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        hrdina = new Hrdina(this);
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        batoh = new Batoh();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, hrdina));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, batoh,hrdina));
        platnePrikazy.vlozPrikaz(new PrikazCvicit(hrdina, herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazJist(hrdina, batoh));
        platnePrikazy.vlozPrikaz(new PrikazKoupit(hrdina, batoh, herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazStats(hrdina, batoh));
        platnePrikazy.vlozPrikaz(new PrikazNapsatPisnicku(hrdina, batoh, herniPlan, this));

    }


    /**
     * Vrátí úvodní zprávu pro hráče.
     */

    public String vratUvitani() {
        return "Vítejte!\n" +
                "Toto je příběh o tobě, skvělém hudebníkovi.\n" +
                "Napiš 'napoveda', pokud si nevíš rady, jak hrát dál.\n" +
                "\n" +
                herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dík, že jsi si zahrál. Ahoj.";
    }

    /**
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }


    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *
     * @param konecHry hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Metoda ukončuje hru v případě získaní 10,000 fanoušků
     *
     * @return
     */
    String setVitezstvi() {
        setKonecHry(true);
        return "Ty jsi nejlepsí hudebník, gratuluju z vyhrou";
    }

    /**
     * Metoda ukončuje hru v případě ztráty HP
     */
    String setProhra(){
        setKonecHry(true);
        return "Ztratil jsi všechny body HP. Restart?";
    }

    /**
     * Metoda ukončuje hru v případě ztráty fanoušků
     */
    String setProhra2(){
        setKonecHry(true);
        return "Bohužel, ztratil jsi všechny svoje fanoušky. Zkus příště více cvíčit";
    }

        /**
         *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
         *  kde se jejím prostřednictvím získává aktualní místnost hry.
         *
         *  @return odkaz na herní plán
         */
        public HerniPlan getHerniPlan () {
            return herniPlan;
        }
        public Batoh getBatoh(){
            return batoh;
        }
        public Hrdina getHrdina(){
            return hrdina;
        }
}


