package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.Hra;
import observer.Observable;
import observer.Observer;
import logika.Batoh;

import java.util.*;


/**
 *  Třída PanelBatohu implementuje pro hru grafický obsáh panelu batohu (inventory).
 *  This class is an observer for the class batoh.
 *
 *
 *
 *@author     Nikita Korotov
 *@version    1.0 27.11.2022
 */

public class PanelBatohu implements Observer {
    ObservableList<String> observableList = FXCollections.observableArrayList();

    ListView<String> listViewBatoh = new ListView<String>(observableList);

    Batoh batoh;

    /**
     * Metoda inicializuje logiku panelBatohu. (Gets batoh, registruje observer etc...)
     * CellFactory pridava obrazky do panelu podle jmena veci, ktera se nachazi v batohu
     *
     * @param hra
     */
    public PanelBatohu(Hra hra) {
        batoh = Hra.getHra().getBatoh();
        batoh.register(this);
        update(batoh, batoh.vypisBatoha());

        listViewBatoh.setCellFactory(stringListView -> new ListCell<String>() {
            @Override
            protected void updateItem(String vec, boolean isEmpty) {
                super.updateItem(vec, isEmpty);
                if(!isEmpty) {
                    setText(vec);
                    ImageView iw = new ImageView("/"+vec+".png");
                    iw.setPreserveRatio(true);
                    iw.setFitWidth(100);
                    setGraphic(iw);
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        })
        ;





    }

    /**
     * Getter na listViewBatoh
     *
     * @return listViewBatoh
     */
    public ListView<String> getListViewBatoh(){
        return listViewBatoh;
    }

    /**
     * Metoda, ktera updatuje observableList podle obsahu batohu. Viz observer.
     *
     * @param observable - the observable object.
     * @param arg - an argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable observable, Object arg) {
        observableList.clear();
        Set<String> obsahBatohu = batoh.getObsahKeys();
        for (String vec : obsahBatohu) {
            observableList.add(vec);






        }
    }

    @Override
    public void setSubject(Observable sub) {

    }
}
