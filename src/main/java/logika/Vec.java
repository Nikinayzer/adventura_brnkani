package logika;

/**
 * Trida Vec - popisuje věcí hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * Ve hře jsou 3 typy věci: jídlo, kytary a furnitura
 *
 *
 * @author Nikita Korotov
 * @version 1.0 11.6.2022
 */

public class Vec {
    private String nazev;
    private boolean prenositelna;
    private boolean kytara;
    private int level;
    private boolean jidlo;
    private int hpJidlo;
    private int price;

    /**
     * Konstruktor třídy Vec
     *
     * Vsechny typy věci realizovány pomoci boolean (boolean kytara, boolean jidlo)
     *
     * @param nazev
     * @param prenositelna
     * @param kytara
     * @param level (level kytary, použivá se ve vzorcu v třídě PrikazNapsatPisnicku)
     * @param jidlo
     * @param hpJidlo (císlo, které se přičítá k počtu hp při konzumaci jídla.
     * @param price (cena v obchodě)
     */
    public Vec(String nazev, boolean prenositelna, boolean kytara, int level, boolean jidlo, int hpJidlo, int price) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.kytara = kytara;
        this.level = level;
        this.jidlo=jidlo;
        this.hpJidlo=hpJidlo;
        this.price = price;
    }

    /**
     * Getter na nazev
     * @return nazev
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Getter na boolean prenositelna
     * @return prenositelna
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    /**
     * Getter na price
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Getter na level
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Getter na boolean jidlo
     * @return jidlo
     */
    public boolean isJidlo() {
        return jidlo;
    }

    /**
     * Getter na hpJidlo
     * @param nazev
     * @return hpJidlo
     */
    public int getHpJidlo(String nazev) {
        this.nazev = nazev;
        return hpJidlo;
    }
}


