package logika;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Třída Hrdina implementuje pro hru proměnné, které popisují hráče.
 *
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *
 *@author     Nikita Korotov
 *@version    1.0 11.6.2022
 */

public class Hrdina {
    private int fanousky = 5;
    private int hp = 100; //mame 100 od zacatku
    private double skill = 0.4;
    private int penize = 100;
    private Hra hra;
    private List<Observer> listObservers;
    public Set<String> listStats;

    /**
     * Konstruktor třídy
     *
     * @param hra
     */
    public Hrdina(Hra hra) {
        this.hra = hra;
        listObservers = new ArrayList<>();
        Set<String> listStats = new HashSet<>();
    }


    public Set<String> getListStats() {
        return listStats;
    }

    public String getHpString() {
        return "HP: " + getHp();

    }

    public String getFanouskyString() {
        return "Fanousky: " + getFanousky();

    }

    public String getSkillString() {
        return "Skill: " + getSkill();
    }

    public String getPenizeStrng() {
        return "Penize: " + getPenize();
    }


    /**
     * Getter na hp
     *
     * @return hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Getter na fanousky
     *
     * @return fanousky
     */
    public int getFanousky() {
        return fanousky;
    }


    /**
     * Getter na skill
     *
     * @return skill
     */
    public double getSkill() {
        int temp = (int) (skill * 100.0);
        double skill = ((double) temp) / 100.0;
        return skill;

    }

    /**
     * Getter na penize
     *
     * @return penize
     */
    public int getPenize() {
        return penize;
    }


    /**
     * Setter na fanousky
     *
     * @param fanousky
     */

    public void setFanousky(int fanousky) {
        this.fanousky = fanousky;
    }

    /**
     * Setter na HP
     * Metoda kontroluje, že HP nemuže byt vyšší než 100. Zároveň stanoví konec hry, pokud HP <=0
     *
     * @param hp
     */
    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp > 100) {
            this.hp = 100;
        }
        if (this.hp <= 0) {
            hra.setProhra();
        }
    }

    /**
     * Setter na skill
     *
     * @param skill
     */

    public void setSkill(double skill) {
        this.skill = skill;
    }

    /**
     * Setter na penize
     *
     * @param penize
     */
    public void setPenize(int penize) {
        this.penize = penize;
    }
}