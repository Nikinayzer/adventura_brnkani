package logika;

import observer.Observable;
import observer.Observer;

import java.util.*;


/**
 *
 * Třida Batoh implementuje pro hru batoh.
 *
 *
 * @author Nikita Korotov
 * @version   1.0 11.6.2022
 */
public class Batoh implements Observable {

    private Map<String,Vec> obsahBatohu;

    private static final int kapacita = 10; //kapacita batohu
    private List<Observer> listObservers;



    /**
     * Konstruktor třidy.
     *
     * Generuje HashMap.
     *
     */
    public Batoh(){
        obsahBatohu =new HashMap<>();
        listObservers = new ArrayList<>();
    }


    /**
     * Metoda vkláda předmět do batohu.
     *
     * @param vec
     * @return boolean. Pokud byla věc vložená do batohu - true, jínak false.
     */
    public boolean vlozVec(Vec vec){
            obsahBatohu.put(vec.getNazev(),vec);
            notifyObservers();
            return true;

    }

    /**
     * Metoda ověruje, obsahuje-li batoh věc podle nazvu
     *
     * @param nazevVeci
     * @return boolean. Pokud obsahuje - true, jínak false.
     */
    public boolean obsahuje(String nazevVeci){
        for(String klic : obsahBatohu.keySet()){
            if(klic.equals(nazevVeci)){
                return true;

            }
        }
        return false;
    }

    /**
     * Metoda vytahuje věc z batohu podle názvu.
     *
     * @param nazevVeci
     * @return boolean. Pokud byla věc vyloučená z batohu - true, jínak false.
     */
    public boolean odeberVec(String nazevVeci){

        if(obsahuje(nazevVeci)){
            obsahBatohu.remove(nazevVeci);
            notifyObservers();
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Getter na kapacity batohu
     *
     * @return Pokud je v batohu místo - true, jínak false
     */
    public boolean isMisto(){
        return (obsahBatohu.size()<kapacita);

    }


    /**
     *
     * Metoda vráti obsah batohu
     *
     * @return Map<String, Vec>
     */
    public Map<String, Vec> getBatohMap(){
        return obsahBatohu;
    }
    public Set<String> getObsahKeys(){
        return getBatohMap().keySet();
    }

    /**
     *
     * Metoda vráti řetězec, který obsahuje všechny věcí, které jsou v batohu.
     *
     * @return Řetězec se jmény věcí
     */
    public String vypisBatoha() {
        String vypis = "";
        for (Vec vec : obsahBatohu.values()) {
            vypis +=vec.getNazev();
        }
        return vypis;

    }

    /**
     * Metoda analyzuje všechny položky v batohu a hledá věc, která má nejvyšší level (level kytary)
     *
     * Metoda používaná v třidě PrikazNapsatPisnicku pro vzorec.
     *
     * @return levelKytary (z konstruktoru věci)
     */
    public int levelKytary() {
        int levelKytary = 0;
        for (Vec vec : obsahBatohu.values()) {
            levelKytary = Math.max(vec.getLevel(), levelKytary);
        }
        return levelKytary;
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
            observer.update(this,getBatohMap());
        }

    }

    @Override
    public Object getUpdate(Observer obj) {
        return null;
    }
}