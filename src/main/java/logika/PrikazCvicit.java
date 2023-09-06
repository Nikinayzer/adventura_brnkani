package logika;

import java.util.concurrent.ThreadLocalRandom;

/**
 *  Třída PrikazCvicit implementuje pro hru příkaz cvicit.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Slouží ke zvýšení dovedností a vydělávání peněz ve hře.
 *
 *@author     Nikita Korotov
 *@version    1.0 11.6.2022
 */

public class PrikazCvicit implements IPrikaz {

    private static final String NAZEV = "cvicit";
    private Hrdina hrdina;
    private HerniPlan herniPlan;

    /**
     * private int pro generace náhodného čísla (limity generace)
     */
    private int min=1;
    private int max=100;

    /**
     * Konstruktor třídy
     * @param hrdina
     * @param herniPlan
     */
    public PrikazCvicit(Hrdina hrdina, HerniPlan herniPlan) {
        this.hrdina = hrdina;
        this.herniPlan = herniPlan;
    }

    /**
     * Metoda provadi zvyseni double skill (koeficient dovednosti) a udela vycitani hp o 10.
     * @param -
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {

        double plusSkillHudebniStudio = 0.10; //local double, přidává se k aktuální dovednosti (skill), pokud hráč hraje doma
        double plusSkillUlice = 0.04; //local double, přidává se k aktuální dovednosti (skill), pokud hráč hraje ve prostoru ulice
        if (parametry.length > 0) {
            // pokud je druhé slovo v příkazu, tak ....
            return "Cvičit co? Nechápu, proč jsi zadal druhé slovo.";
        }
            else{
                if (herniPlan.getAktualniProstor().getNazev().equals("ulice")) {
                    hrdina.setSkill(hrdina.getSkill() + plusSkillUlice);
                    hrdina.setHp(hrdina.getHp() - 10);
                    if(hrdina.getHp()<=0){
                        Hra.getHra().setProhra();
                        return "Ztratil jsi všechny body HP. Restart?";}


                    int randomPenize = ThreadLocalRandom.current().nextInt(min, max + 1); //generace náhodného počtu peněz pomocí náhodného čísla https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
                    hrdina.setPenize(hrdina.getPenize() + randomPenize);
                    //Pokud příkaz probehl uspešně a hráč cvičil ve prostoru "ulice", tak obdrží peníze a zvýší svou dovednost
                    return "Dobrá prace, zlepšil si úroveň hry o 0.04, ztratil jsi na to 10HP a zároveň jsi vydělal " + randomPenize + " korun";
                } else {
                    hrdina.setSkill(hrdina.getSkill() + plusSkillHudebniStudio);
                    hrdina.setHp(hrdina.getHp() - 10);
                    if(hrdina.getHp()<=0){
                        Hra.getHra().setProhra();
                        return "Ztratil jsi všechny body HP. Restart?";}
                    //Pokud příkaz probehl uspešně, hráč zvýší svou dovednost
                    return "Dobrá prace, zlepšil si úroveň hry o 0.10, ztratil jsi na to 10HP";
                }
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



