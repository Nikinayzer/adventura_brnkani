package gui;



import javafx.geometry.Point2D;
import logika.HerniPlan;
import logika.Hra;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.Prostor;
import observer.Observable;
import observer.Observer;

import java.util.Map;

/**
 *  Třída HraciPloha implementuje pro hru grafický obsáh mapy.
 *  This class is an observer for the class herniPlan
 *
 *
 *
 *@author     Nikita Korotov
 *@version    1.0 27.11.2022
 */

public class HraciPloha implements Observer {
    AnchorPane anchorPane=new AnchorPane();
    Image pointer_picture = new Image(HraciPloha.class.getResourceAsStream("/pointer.png"),20.0,30.0,false,false);
    ImageView pointer = new ImageView(pointer_picture);
    HerniPlan herniPlan;
    //zacatek reseni pozic hrace bez zasahu do logiky
    private Map<String, Point2D> mapaPozicHrace;

    /**
     * Metoda, ktera nastavi obrazek mapy a pointer hrace, ktereho prida do mapy.
     *
     * @author Nikita Korotov
     */

    public HraciPloha(){
        herniPlan = Hra.getHra().getHerniPlan();
        AnchorPane.setLeftAnchor(pointer,481.0);
        AnchorPane.setTopAnchor(pointer,451.0);
        herniPlan.register(this);




        //creating an image of pointer, creating an ImageView for pointer and adding it to map
        Image image = new Image(HraciPloha.class.getResourceAsStream("/map.png"), 1123,518,false,false);
        ImageView imageView = new ImageView(image);
        anchorPane.getChildren().addAll(imageView, pointer);




    }

    /** Getter na AnchorPane
     *
     * @return anchorPane
     */
    public AnchorPane getAnchorPane(){
        return this.anchorPane;
    }

    /**
     * Metoda, ktera updatuje mapu podle aktualni mistnosti. Viz observer.
     *
     * @param o - the observable object.
     * @param arg - an argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable o, Object arg) {
        Prostor prostor = (Prostor) arg;
        //updating location of pointer
        AnchorPane.setLeftAnchor(pointer, prostor.getPosLeft());
        AnchorPane.setTopAnchor(pointer, prostor.getPosTop());
    }

    @Override
    public void setSubject(Observable sub) {
    }
}