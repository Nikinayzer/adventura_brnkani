package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Nikita Korotov, Jarmila Pavlickova
 *@version    1.0 11.6.2022
 */
public class PrikazJdi implements IPrikaz {
    public static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hrdina hrdina;

    /**
    *  Konstruktor třídy
    *
    *  @param plan herní plán, ve kterém hráč bude ve hře "chodit"
    */
    public PrikazJdi(HerniPlan plan, Hrdina hrdina) {
        this.plan = plan;
        this.hrdina = hrdina;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *  Pokud hráč jde do místnosti "metro", pohybuje se do náhodné místnosti/prostoru
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu)
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
        // pokud hráč nastupuje do metra, příkaz nastaví směr podle metody jdiRandomProstor a současně posune ho v tomto směru
        if (smer.equals("metro")&&plan.getAktualniProstor().getNazev().equals("hudebni_studio")) {
            plan.setAktualniProstor(plan.getAktualniProstor().vratSousedniProstor("metro"));
            smer = jdiRandomProstor();
        }

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        if(smer.equals("obchod_s_nastroji")||smer.equals("obchod_s_potravinami")){ //vrací dlouhyPopisObchod pokud hráč jde do obchodu
            plan.setAktualniProstor(sousedniProstor);
            hrdina.setHp(hrdina.getHp()-5); // -5hp pří úspěchu
            if(hrdina.getHp()<=0){
                Hra.getHra().setProhra();
                return "Ztratil jsi všechny body HP. Restart?";
            }
            else{
            return sousedniProstor.dlouhyPopisObchod();}
        }
        plan.setAktualniProstor(sousedniProstor);
        hrdina.setHp(hrdina.getHp()-5); // -5hp pří úspěchu
        if(hrdina.getHp()<=0){
            Hra.getHra().setProhra();
            return "Ztratil jsi všechny body HP. Restart?";}
        else{
        return sousedniProstor.dlouhyPopis();}

    }

    /**
     * Metoda, která generuje náhodný směr, aby "metro" fungovalo správně
     * @return smer
     */
    private String jdiRandomProstor() {
        double random = Math.random();
        if (random <= 0.25) {
            return "ulice";
        } else if(random <= 0.5) {
            return "hudebni_studio";
        } else if(random <=0.75) {
            return "obchod_s_potravinami";
        }
        return "obchod_s_nastroji";
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
