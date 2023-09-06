package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import logika.*;

import java.util.Collection;
import observer.Observable;
import observer.Observer;

/**
 *  Třída PanelVychodu implementuje pro hru grafický obsáh panelu vychodů.
 *  This class is an observer for the class herniPlan
 *
 *@author     Nikita Korotov
 *@version    1.0 27.11.2022
 */

public class PanelVychodu implements Observer {
    ObservableList<String> observableList = FXCollections.observableArrayList();
    ListView<String> listViewVychody = new ListView<>(observableList);
    HerniPlan herniPlan;

    public PanelVychodu(Hra hra){
        herniPlan = Hra.getHra().getHerniPlan();
        update(herniPlan, herniPlan.getAktualniProstor());
        herniPlan.register(this);

        //listview set max width
        listViewVychody.setMinWidth(200);
        listViewVychody.setMaxHeight(200);
        listViewVychody.setCellFactory(stringListView -> new ListCell<String>() {
            @Override
            /**
             * Cell-Factory. Method that sets images (icons) to panelVychodu.
             *
             * @param nazev - string, name of mistnost.
             * @param isEmpty - boolean
             *
             * @author - Nikita Korotov
             */
            protected void updateItem(String nazev, boolean isEmpty) {
                super.updateItem(nazev, isEmpty);
                if(!isEmpty) {
                    setText(nazev);
                    ImageView iw = new ImageView("/"+nazev+".png");
                    iw.setPreserveRatio(true);
                    iw.setFitWidth(30);
                    setGraphic(iw);
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        };


    /**
     * Getter na listViewVychody
     *
     * @return listViewVychody
     */
    public ListView<String> getListViewVychody() {
        return listViewVychody;
    }

    /**
     * Method that updates observable list. Víz class Observer.
     *
     * @param o - the observable object.
     * @param arg - an argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable o, Object arg) {
        observableList.clear();
        Prostor prostor = (Prostor) arg;
        Collection<Prostor> vychody = prostor.getVychody();
        for (Prostor vychod : vychody) {
            observableList.add(vychod.getNazev());
        }
    }


    @Override
    public void setSubject(Observable sub) {
    }
}
