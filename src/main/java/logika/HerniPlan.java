package logika;


import gui.HraciPloha;
import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 *
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory a věcí,
 *  propojuje je vzájemně pomocí východů
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author    Nikita Korotov, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova.
 *@version    1.0 11.6.2022
 */
public class HerniPlan implements Observable {

    private Prostor aktualniProstor;
    private List<Observer> listObservers;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        listObservers = new ArrayList<>();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů. Vytváří také věcí a přídava je do prostorů
     *  Jako výchozí aktuální prostor nastaví hudebni_studio.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor ulice = new Prostor("ulice",", kde je možné získat peníze pomocí hry na kytaru.", 383.0, 232.0);
        Prostor obchodSnastroji = new Prostor("obchod_s_nastroji", ", kde je možné koupit nový hudební nastroj.", 191.0,247.0);
        Prostor obchodSpotravinami = new Prostor("obchod_s_potravinami",", kde je možné koupit jídlo.", 601.0,207.0);
        Prostor hudebniStudio = new Prostor("hudebni_studio",", kde si můžeš psát písničky a cvičit.",481.0,451.0);
        Prostor metro = new Prostor("metro",", premisťuje tě do náhodného místa.",911.0, 417.0);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        ulice.setVychod(obchodSnastroji);
        ulice.setVychod(obchodSpotravinami);
        ulice.setVychod(hudebniStudio);
        obchodSnastroji.setVychod(ulice);
        obchodSpotravinami.setVychod(ulice);
        hudebniStudio.setVychod(ulice);
        hudebniStudio.setVychod(metro);
        metro.setVychod(hudebniStudio);
        metro.setVychod(ulice);
        metro.setVychod(obchodSnastroji);
        metro.setVychod(obchodSpotravinami);

        aktualniProstor = hudebniStudio;  // hra začíná ve studiu

        //generace věcí
        //(String nazev, boolean prenositelna, boolean kytara, int level, boolean jidlo, int hp, int price)
        Vec prvniKytara = new Vec("mamincina_kytara", true, true, 1, false, 0, 0);
        Vec fa125 = new Vec("fender_fa",true, true,2, false,0, 400);
        Vec bullet = new Vec("fender_squier", true, true,3, false, 0, 1000);
        Vec legenda = new Vec("gibson",true, true, 4, false, 0, 2000); //kytara
        Vec cokoloda = new Vec("studentska_cokolada", true, false,0,true,25, 20);
        Vec noodles = new Vec("cinska_polevka", true, false,0,true, 50, 70);
        Vec rohlik = new Vec("rohlik", true, false, 0, true, 10,11);
        Vec houska = new Vec("stara_houska", true,false,0, true, 20, 0);
        Vec svickova = new Vec("svickova", true, false,0,true,50, 50);
        Vec gulas = new Vec("gulas", true,false,0,true,70, 100);
        Vec trdelnik = new Vec("trdlo",true,false,0,true,99,200);

        Vec pocitac = new Vec("pocitac", false,false,0,false,0,0);
        Vec stol =  new Vec("stol", false, false, 0, false, 0, 0);
        Vec sloup = new Vec("sloup",false,false,0,false,0,0);
        Vec mince = new Vec("mince",true,false,0,false,0,20);

        //vložení věcí do prostorů
        hudebniStudio.vlozVec(prvniKytara);
        hudebniStudio.vlozVec(houska);
        hudebniStudio.vlozVec(pocitac);
        hudebniStudio.vlozVec(stol);

        ulice.vlozVec(sloup);
        ulice.vlozVec(mince);

        obchodSpotravinami.vlozVec(rohlik);
        obchodSpotravinami.vlozVec(gulas);
        obchodSpotravinami.vlozVec(noodles);
        obchodSpotravinami.vlozVec(trdelnik);
        obchodSpotravinami.vlozVec(svickova);
        obchodSpotravinami.vlozVec(cokoloda);

        obchodSnastroji.vlozVec(fa125);
        obchodSnastroji.vlozVec(bullet);
        obchodSnastroji.vlozVec(legenda);

    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }


    @Override
    public void register(Observer obj) {
        listObservers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {

    }

    @Override
    public void notifyObservers() {
        for (Observer observer:listObservers){
            observer.update(this,aktualniProstor);
        }

    }

    @Override
    public Object getUpdate(Observer obj) {
        return null;
    }
}
