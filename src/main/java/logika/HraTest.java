package logika;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HraTest {

    private Hra hra;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Hra.getHra();
    }


    @Test
    void testHry (){

        //vyhra

        //vložíme pár věcí pro jednoduchost testu
        Vec legenda = new Vec("gibson",true, true, 4, false, 0, 1500); //kytara
        Vec trdlo = new Vec("trdlo",true,false,0,true,100,200); //jidlo
        Vec trdlo1 = new Vec("trdlo1",true,false,0,true,100,200);
        Vec trdlo2 = new Vec("trdlo2",true,false,0,true,100,200);
        Vec trdlo3 = new Vec("trdlo3",true,false,0,true,100,200);
        hra.getHerniPlan().getAktualniProstor().vlozVec(legenda);
        hra.getHerniPlan().getAktualniProstor().vlozVec(trdlo);
        hra.getHerniPlan().getAktualniProstor().vlozVec(trdlo1);
        hra.getHerniPlan().getAktualniProstor().vlozVec(trdlo2);
        hra.getHerniPlan().getAktualniProstor().vlozVec(trdlo3);
        assertEquals ("hudebni_studio",hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("seber gibson");
        hra.zpracujPrikaz("seber trdlo");
        hra.zpracujPrikaz("seber trdlo1");
        hra.zpracujPrikaz("seber trdlo2");
        hra.zpracujPrikaz("seber trdlo3");
        hra.zpracujPrikaz("jdi ulice");
        hra.zpracujPrikaz("cvicit");
        hra.zpracujPrikaz("cvicit");
        hra.zpracujPrikaz("cvicit");
        hra.zpracujPrikaz("cvicit");
        hra.zpracujPrikaz("cvicit");
        hra.zpracujPrikaz("cvicit");
        hra.zpracujPrikaz("cvicit");
        hra.zpracujPrikaz("cvicit");
        hra.zpracujPrikaz("jdi hudebni_studio");
        hra.zpracujPrikaz("jist trdlo");
        assertEquals("Dobrý song! Ted´ máš 18 fanoušků",hra.zpracujPrikaz("napsatPisnicku rock"));
        assertFalse(hra.konecHry());
        hra.zpracujPrikaz("jist trdlo1");
        assertEquals("Dobrý song! Ted´ máš 67 fanoušků",hra.zpracujPrikaz("napsatPisnicku rock"));
        hra.zpracujPrikaz("jist trdlo2");
        assertEquals("Dobrý song! Ted´ máš 250 fanoušků",hra.zpracujPrikaz("napsatPisnicku rock"));
        hra.zpracujPrikaz("jist trdlo3");
        assertEquals("Dobrý song! Ted´ máš 936 fanoušků",hra.zpracujPrikaz("napsatPisnicku rock"));

        hra.getHerniPlan().getAktualniProstor().vlozVec(trdlo);
        hra.zpracujPrikaz("seber trdlo");
        hra.zpracujPrikaz("jist trdlo");
        hra.getHerniPlan().getAktualniProstor().vlozVec(trdlo);

        assertEquals("Dobrý song! Ted´ máš 3504 fanoušků",hra.zpracujPrikaz("napsatPisnicku rock"));
        hra.zpracujPrikaz("seber trdlo");
        hra.zpracujPrikaz("jist trdlo");
        assertEquals("Dobrý song! Ted´ máš 13118 fanoušků",hra.zpracujPrikaz("napsatPisnicku rock"));
        assertTrue(hra.konecHry());


    }
    @Test
    void testProhra(){
        hra.zpracujPrikaz("cvicit"); //90
        hra.zpracujPrikaz("cvicit"); //80
        hra.zpracujPrikaz("cvicit"); //70
        hra.zpracujPrikaz("cvicit"); //60
        hra.zpracujPrikaz("cvicit"); //50
        hra.zpracujPrikaz("cvicit"); //40
        hra.zpracujPrikaz("cvicit"); //30
        hra.zpracujPrikaz("cvicit"); //20
        hra.zpracujPrikaz("cvicit"); //10
        hra.zpracujPrikaz("cvicit"); //0
        assertTrue(hra.konecHry());

    }

    @Test
    void testProhra2(){

        //vložíme pár věcí pro jednoduchost testu
        Vec trdlo = new Vec("trdlo",true,false,0,true,100,200); //jidlo
        hra.getHerniPlan().getAktualniProstor().vlozVec(trdlo);
        hra.zpracujPrikaz("seber trdlo");

        hra.zpracujPrikaz("seber mamincina_kytara");
        assertEquals("Dobrý song! Ted´ máš 1 fanoušků",hra.zpracujPrikaz("napsatPisnicku country"));
        hra.zpracujPrikaz("jist trdlo");
        assertEquals("Dobrý song! Ted´ máš 0 fanoušků",hra.zpracujPrikaz("napsatPisnicku country"));
        assertTrue(hra.konecHry());

    }


    @Test
    void testPrikazKoupit() {
        assertEquals("Co chceš si koupít? Zadej tam druhé slovo.",hra.zpracujPrikaz("koupit"));
        assertEquals("Nejsí v obchodě",hra.zpracujPrikaz("koupit rohlik"));
        hra.zpracujPrikaz("jdi ulice");
        hra.zpracujPrikaz("jdi obchod_s_potravinami");
        assertEquals("Tu to není",hra.zpracujPrikaz("koupit gibson"));
        hra.zpracujPrikaz("jdi ulice");
        hra.zpracujPrikaz("jdi obchod_s_nastroji");
        assertEquals("Nemáš tolík peněz",hra.zpracujPrikaz("koupit gibson"));
        hra.zpracujPrikaz("jdi ulice");
        hra.zpracujPrikaz("jdi obchod_s_potravinami");
        assertEquals("Koupil jsí rohlik za 11 korun.", hra.zpracujPrikaz("koupit rohlik"));
        assertEquals("Máš v batohu: | rohlik | ",hra.zpracujPrikaz("batoh"));
    }

    @Test
    void testPrikazCvicit() {
        assertEquals("hudebni_studio",hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Cvičit co? Nechápu, proč jsi zadal druhé slovo.",hra.zpracujPrikaz("cvicit a"));
        assertEquals("Dobrá prace, zlepšil si úroveň hry o 0.10, ztratil jsi na to 10HP",hra.zpracujPrikaz("cvicit"));
       //nevím, jak testovat "cvicit" ve prostoru "ulice", protože tam je náhodné číslo
    }

    @Test
    void testJdi() {
        assertEquals("hudebni_studio",hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi ulice");
        assertEquals("Tam se odsud jít nedá!",hra.zpracujPrikaz("jdi metro"));
        assertEquals("ulice",hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi obchod_s_potravinami");
        assertEquals("Tam se odsud jít nedá!",hra.zpracujPrikaz("jdi metro"));
        assertEquals("obchod_s_potravinami",hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi ulice");
        assertEquals("ulice",hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Tam se odsud jít nedá!",hra.zpracujPrikaz("jdi metro"));
        hra.zpracujPrikaz("jdi obchod_s_nastroji");
        assertEquals("obchod_s_nastroji",hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Tam se odsud jít nedá!",hra.zpracujPrikaz("jdi metro"));
        hra.zpracujPrikaz("jdi ulice");
        assertEquals("ulice",hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi hudebni_studio");
        assertEquals("hudebni_studio",hra.getHerniPlan().getAktualniProstor().getNazev());
        //nevím, jak testovat náhodný směr u metra

    }

    @Test
    void testNapsatPisnicku() {
        assertEquals("Jakého žánru pisničky mám napsát? Máš na vyběr jen rock, blues a country.", hra.zpracujPrikaz("napsatPisnicku"));
        assertEquals("Napiš žánr písně správně", hra.zpracujPrikaz("napsatPisnicku a"));
        hra.zpracujPrikaz("jdi ulice");
        assertEquals("Písně lze psát pouze ve studiu", hra.zpracujPrikaz("napsatPisnicku rock"));
        hra.zpracujPrikaz("jdi hudebni_studio");
        assertEquals("Nemáš kytaru", hra.zpracujPrikaz("napsatPisnicku rock"));
        hra.zpracujPrikaz("seber mamincina_kytara");
        assertEquals("Dobrý song! Ted´ máš 2 fanoušků", hra.zpracujPrikaz("napsatPisnicku rock"));
    }

    @Test
    void testSeber() {
        assertEquals("hudebni_studio",hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Co mám sebrat?",hra.zpracujPrikaz("seber"));
        assertEquals("To tu neni!",hra.zpracujPrikaz("seber gibson"));
        assertEquals("Je to sebrane", hra.zpracujPrikaz("seber mamincina_kytara"));
        assertFalse(hra.getHerniPlan().getAktualniProstor().getVeci().containsKey("mamincina_kytara"));
        hra.zpracujPrikaz("jdi ulice");
        assertEquals("Našel jsi minci! Včera jsem ji ztratil...", hra.zpracujPrikaz("seber mince"));
        assertEquals("Máš 95 HP, 120 korun, a 5 fanoušků. Tvůj skill je 0.4, level kytary = 1 .",hra.zpracujPrikaz("stats"));
        hra.zpracujPrikaz("jdi obchod_s_nastroji");
        assertEquals("Měl bys si něco koupit, nekrást",hra.zpracujPrikaz("seber gibson"));


    }
    @Test
    void testStats(){
        assertEquals("hudebni_studio",hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Máš 100 HP, 100 korun, a 5 fanoušků. Tvůj skill je 0.4, level kytary = 0 .",hra.zpracujPrikaz("stats"));
        hra.zpracujPrikaz("seber mamincina_kytara");
        hra.zpracujPrikaz("cvicit");
        assertEquals("Máš 90 HP, 100 korun, a 5 fanoušků. Tvůj skill je 0.5, level kytary = 1 .",hra.zpracujPrikaz("stats"));
        //nevím jak otestovat penize, protože jejích získ je náhodný
    }
    @Test
    void testJist(){
        assertEquals("hudebni_studio",hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Jíst co? Nechápu, proč jsi nezadal druhé slovo.", hra.zpracujPrikaz("jist"));
        assertEquals("Co chceš jíst? To nemás v batohu.", hra.zpracujPrikaz("jist a"));
        hra.zpracujPrikaz("seber stara_houska");
        hra.zpracujPrikaz("seber mamincina_kytara");
        hra.zpracujPrikaz("cvicit"); //90 hp
        hra.zpracujPrikaz("cvicit"); //80 hp
        hra.zpracujPrikaz("cvicit"); //70 hp
        assertEquals("To není jedlé", hra.zpracujPrikaz("jist mamincina_kytara"));
        assertEquals("Dobrou chuť, ted´ máš 90 HP", hra.zpracujPrikaz("jist stara_houska")); //+20 hp
        hra.zpracujPrikaz("jdi ulice"); //85 hp
        hra.zpracujPrikaz("jdi obchod_s_potravinami"); //80 hp
        hra.zpracujPrikaz("koupit gulas");
        //test toho, že HP nemuže byt >100
        assertEquals("Dobrou chuť, ted´ máš 100 HP", hra.zpracujPrikaz("jist gulas")); //+70 hp
    }
    @Test
    void testKonec(){
        assertEquals("Ukončit co? Nechápu, proč jste zadal druhé slovo.",hra.zpracujPrikaz("konec a"));
        assertEquals("Hra ukončena příkazem konec, dík že jsi si zahrál",hra.zpracujPrikaz("konec"));
    }

    @Test
    void testBatoh(){
        //prazdné věcí na test kapacity
        Vec test1 = new Vec("test1" , true, false,0,false,0,0);
        Vec test2 = new Vec("test2" , true, false,0,false,0,0);
        Vec test3 = new Vec("test3" , true, false,0,false,0,0);
        Vec test4 = new Vec("test4" , true, false,0,false,0,0);
        Vec test5 = new Vec("test5" , true, false,0,false,0,0);
        Vec test6 = new Vec("test6" , true, false,0,false,0,0);
        Vec test7 = new Vec("test7" , true, false,0,false,0,0);
        Vec test8 = new Vec("test8" , true, false,0,false,0,0);
        Vec test9 = new Vec("test9" , true, false,0,false,0,0);
        assertEquals("hudebni_studio",hra.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Batoh co? Nechápu, proč jsi zadal druhé slovo.",hra.zpracujPrikaz("batoh a"));
        assertEquals("Nic v batohu nemáš",hra.zpracujPrikaz("batoh"));
        hra.zpracujPrikaz("seber mamincina_kytara");
        assertEquals("Máš v batohu: | mamincina_kytara | ", hra.zpracujPrikaz("batoh"));
        hra.getHerniPlan().getAktualniProstor().vlozVec(test1);
        hra.getHerniPlan().getAktualniProstor().vlozVec(test2);
        hra.getHerniPlan().getAktualniProstor().vlozVec(test3);
        hra.getHerniPlan().getAktualniProstor().vlozVec(test4);
        hra.getHerniPlan().getAktualniProstor().vlozVec(test5);
        hra.getHerniPlan().getAktualniProstor().vlozVec(test6);
        hra.getHerniPlan().getAktualniProstor().vlozVec(test7);
        hra.getHerniPlan().getAktualniProstor().vlozVec(test8);
        hra.getHerniPlan().getAktualniProstor().vlozVec(test9);
        hra.zpracujPrikaz("seber test1");
        hra.zpracujPrikaz("seber test2");
        hra.zpracujPrikaz("seber test3");
        hra.zpracujPrikaz("seber test4");
        hra.zpracujPrikaz("seber test5");
        hra.zpracujPrikaz("seber test6");
        hra.zpracujPrikaz("seber test7");
        hra.zpracujPrikaz("seber test8");
        hra.zpracujPrikaz("seber test9");
        assertEquals("To nezvedneš",hra.zpracujPrikaz("seber stara_houska"));
        // neprenositelna vec
        assertEquals("To nezvedneš",hra.zpracujPrikaz("seber stol"));
    }
}