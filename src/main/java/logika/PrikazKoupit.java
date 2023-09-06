package logika;

import java.util.Map;

/**
 *  Třída PrikazKoupit implementuje pro hru příkaz koupit.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Slouží k nakupu věcí ve prostoru obchod_s_potravinami nebo obchod_s_nastroji .
 *
 *@author     Nikita Korotov
 *@version    1.0 11.6.2022
 *
 */
public class PrikazKoupit implements IPrikaz {
    private static final String NAZEV = "koupit";
    private Hrdina hrdina;
    private Batoh batoh;
    private HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     * @param hrdina
     * @param batoh
     * @param herniPlan
     */

    public PrikazKoupit(Hrdina hrdina, Batoh batoh, HerniPlan herniPlan) {
        this.hrdina = hrdina;
        this.batoh = batoh;
        this.herniPlan = herniPlan;

    }

    /**
     * Metoda, která provádí nákup věci.
     *
     * Ověruje, jestli hráč se nachází v spravné místností, zda-li věc v obchodu a má-li hráč dost peněz.
     * Pokud všechno ok, přidava věc do batohu
     *
     * @param parametry název věci
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length <1){
            // pokud chybí druhé slovo (název věci), tak ...
            return "Co chceš si koupít? Zadej tam druhé slovo.";}
        String nazevVeci = parametry[0];
        if(!herniPlan.getAktualniProstor().getNazev().equals("obchod_s_potravinami")&&!herniPlan.getAktualniProstor().getNazev().equals("obchod_s_nastroji")) {
            // pokud hráč není v prostoru "obchod_s_potravinami či "obchod_s_nastroji", tak ...
            return "Nejsí v obchodě";
        }
        Map<String,Vec> veciProstoru = herniPlan.getAktualniProstor().getVeci();
        if(!veciProstoru.containsKey(nazevVeci)) {
            // pokud věc neimplementovaná v prostoru v třidě HerniPlan, tak ...
            return "Tu to není";
        }

        Vec vec = veciProstoru.get(nazevVeci);

        if(hrdina.getPenize()<veciProstoru.get(nazevVeci).getPrice()){
            // pokud hráč má peněz < než cena věci, tak ...
            return "Nemáš tolík peněz";}

        hrdina.setPenize(hrdina.getPenize()-vec.getPrice());
        batoh.vlozVec(vec);



        return "Koupil jsí "+ vec.getNazev()+ " za "+vec.getPrice()+" korun.";
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
