package logika;

/**
 *  Třída PrikazNapsatPisnicku implementuje pro hru příkaz napsatPisnicku.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Slouží ke získaní fanoušků.
 *
 *@author     Nikita Korotov
 *@version    1.0 11.6.2022
 *
 */
public class PrikazNapsatPisnicku implements IPrikaz {
    private static final String NAZEV="napsatPisnicku";
    private Hrdina hrdina;
    private Batoh batoh;
    private HerniPlan herniPlan;
    private Hra hra;

    /**
     * Konstruktor třídy
     * @param hrdina
     * @param batoh
     * @param herniPlan
     * @parama hra
     */
    public PrikazNapsatPisnicku(Hrdina hrdina, Batoh batoh, HerniPlan herniPlan, Hra hra) {
        this.hrdina = hrdina;
        this.batoh=batoh;
        this.herniPlan = herniPlan;
        this.hra= hra;

    }

    /**
     * Metoda, která zvýšuje počet fanoušků podle vzorcu, v kterem použitý konstanty zanr; koef 1.5 a proměnné skill, level kytary a aktualní počet fanoušků
     *
     * Písničku je možně psát pouze ve prostoru "hudebni_studio" a jen pokud hráč má kytaru
     *
     *
     * Vzorec: fanousky = (skill*koefZanru*levelKytary*fanousky)/pocetPisnicek
     *
     * Metoda nastaví vyhru, pokud hráč má >= 10k fanousku nebo prohru, pokud <= 0.
     *
     *
     * @param parametry žánr písničky.
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        int pocetPisnicek = 0;

        if (parametry.length == 0) {
            // pokud chybí druhé slovo (žánr), tak ....
            return "Jakého žánru pisničky mám napsát?" +" Máš na vyběr jen rock, blues a country.";
        }

        double koefZanru = getKoefZanru(parametry[0]);
        if (koefZanru == 0) {
            // pokud druhé slovo(žánr) zadáno špatně, tak ...
            return "Napiš žánr písně správně";
        }
        if(!herniPlan.getAktualniProstor().getNazev().equals("hudebni_studio")){
            // pokud aktualní prostor není hudebni_studio, tak ...
            return "Písně lze psát pouze ve studiu";
        }
        if (batoh.levelKytary() == 0) {
            // pokud hráč nemá kytaru v batohu, tak ...
            return "Nemáš kytaru";
        }
        else {
            pocetPisnicek++;
            hrdina.setFanousky((int) (hrdina.getSkill()
                    *koefZanru
                    *batoh.levelKytary()
                    *hrdina.getFanousky()
                    /pocetPisnicek));
            if(hrdina.getFanousky()>=10000){
                hra.setVitezstvi();
                return "Ty jsi nejlepsí hudebník, gratuluju z vyhrou";
            }
            if(hrdina.getFanousky()<=0){
                hra.setProhra2();
                return "Bohužel, ztratil jsi všechny svoje fanoušky. Zkus příště více cvíčit";
            }
            hrdina.setHp(hrdina.getHp()-60); // -60 hp pří úspěchu
            if(hrdina.getHp()<=0){
                Hra.getHra().setProhra();
                return "Ztratil jsi všechny body HP. Restart?";}
        }
        // pokud všechné podmínky splněné, tak hráč získavá fanoušky
        return "Dobrý song! Ted´ máš " +hrdina.getFanousky()+ " fanoušků";
    }

    /**
     * Metoda analyzuje String param, použítý užívatelem v metodě proved Prikaz a priřádí podle nazvu double číslo koefZanr
     *
     * @param param Zánr (rock, blues, or country)
     * @return double koef. žánru
     */
    private double getKoefZanru(String param) {
        switch (param) {
            case ("rock"): return  1.3;
            case ("blues"): return  1.1;
            case ("country"): return  0.8;
            default: return 0;
        }
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
