package main;

;
import gui.HraciPloha;
import gui.PanelBatohu;
import gui.PanelVychodu;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import logika.Hra;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.PrikazJdi;
import logika.PrikazJist;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 * Hlavni GUI trida aplikaci. Inicializuje vsechny polozky gui okna (Panely, menu etc..)
 * Je Singletonem pro aplikaci.
 *
 * @author Nikita Korotov
 * @version 1.0 27.11.2022
 *
 */
public class AdventuraZaklad extends Application {
    private final TextArea textArea = new TextArea();

    /**
     * Singleton launcher pro aplikaci.
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Metoda vytvari Pane, Stage, nastavi textArea pro text, nastavi uzivatelskyVstup pro zadani prikazu a nsatavi vsechny panely.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Hra hra = Hra.getHra();
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane,1123,800); //creating of window
        primaryStage.setScene(scene); //setting scene to stage
        primaryStage.setMinWidth(1123);
        primaryStage.setMinHeight(800);
        primaryStage.setResizable(true); //jinak mapa looks weird
        primaryStage.setTitle("Brnkani 1.1"); //setting title for window
        primaryStage.getIcons().add(new Image(AdventuraZaklad.class.getResourceAsStream("/icon.png"))); //adding an icon for window

        //pridani menu
        MenuBar menu = createMenu(primaryStage);
        VBox menuAHraVBox = new VBox(menu, borderPane);
        scene.setRoot(menuAHraVBox);



        // vytvarit full-suplex komunikaci mezi pocitacem a udzivatelem
        //smer: od pocitace k uzivateli


        textArea.setText(hra.vratUvitani());


        borderPane.setCenter(textArea);
        //smer:od uzivatele ke hre
        TextField uzivatelskyVstup = new TextField(); //je vhodnejsi nez textarea, protoze u textArea enter dela neco jineho,zde spousti onAction event.
        borderPane.setBottom(uzivatelskyVstup);

        //kdyz se zmackne enter
        uzivatelskyVstup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String prikaz = uzivatelskyVstup.getText();
                zpracujPrikaz(prikaz);
                uzivatelskyVstup.clear();

                //DU: neumoznit zadavat dalsi prikazy, pokud hra skoncila DONE
                if(Hra.getHra().konecHry()){
                   uzivatelskyVstup.setEditable(false);
                }
            }
        });

        textArea.setEditable(false);

        Label okno_prikazu = new Label("Zadej příkaz: ");
        okno_prikazu.setFont(Font.font("Arial", FontWeight.BLACK, 14.0));

        FlowPane flowPane = new FlowPane();
        //HBox flowPane = new HBox();
        borderPane.setBottom(flowPane);

        flowPane.getChildren().addAll(okno_prikazu, uzivatelskyVstup);

        uzivatelskyVstup.requestFocus();

        //panel batohu

        PanelBatohu panelBatohu = new PanelBatohu(hra);
        borderPane.setLeft(panelBatohu.getListViewBatoh());

        //makes menu clickable, pouziva prikaz jist
        panelBatohu.getListViewBatoh().setOnMouseClicked(mouseEvent -> {
            String cil = panelBatohu.getListViewBatoh().getSelectionModel().getSelectedItem();
            if(cil==null || hra.konecHry()) return;
            zpracujPrikaz(PrikazJist.NAZEV+" "+cil);
        });

        //panel vychodu

        PanelVychodu panelVychodu = new PanelVychodu(hra);
        borderPane.setRight(panelVychodu.getListViewVychody());

        //usefull thing, makes menu interactive
        panelVychodu.getListViewVychody().setOnMouseClicked(mouseEvent -> {
            String cil = panelVychodu.getListViewVychody().getSelectionModel().getSelectedItem();
            if(cil==null || hra.konecHry()) return;
            zpracujPrikaz(PrikazJdi.NAZEV+" "+cil);
        });

        flowPane.setAlignment(Pos.CENTER);

        // HraciPloha

        HraciPloha hraciPloha = new HraciPloha();
        HBox boxHracePlocha = new HBox();
        boxHracePlocha.setAlignment(Pos.CENTER);
        boxHracePlocha.getChildren().add(hraciPloha.getAnchorPane());
        borderPane.setTop(boxHracePlocha);

        primaryStage.show();
    }

    /**
     * Metoda vytvari menu a polozky menu.
     *
     * @param primaryStage
     * @return menuBar
     */
    private MenuBar createMenu(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        Menu souborMenu = new Menu("Soubor");
        Menu napovedaMenu = new Menu("Nápověda");

        //ImageView novaHraIkonka = new ImageView(new Image(AdventuraZaklad.class.getResourceAsStream("/new.gif")));
        MenuItem novaHraMenuItem = new MenuItem("Nová Hra");
        novaHraMenuItem.setOnAction(event -> {
            System.out.println( "Restarting app!" );
            Hra.getHra().restart();
            primaryStage.close();
            start(new Stage());


        });

        MenuItem konecMenuItem = new MenuItem("Konec");
        konecMenuItem.setOnAction(event -> System.exit(0));

        MenuItem oAplikaciMenuItem = new MenuItem("O Aplikaci");
        MenuItem napovedaMenuItem = new MenuItem("Nápověda");

        novaHraMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        // oddělovač
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();

        souborMenu.getItems().addAll(novaHraMenuItem, separatorMenuItem, konecMenuItem);
        napovedaMenu.getItems().addAll(oAplikaciMenuItem, napovedaMenuItem);

        oAplikaciMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("O aplikaci - Brnkani");
            alert.setHeaderText("Brnkani - Java FX Adventura");
            alert.setContentText("Verze 1.0, ZS 2022");
            alert.showAndWait();
        });

        napovedaMenuItem.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Napoveda k aplikaci");
            WebView webView = new WebView();
            webView.getEngine().load(AdventuraZaklad.class.getResource
                    ("/napoveda.html").toExternalForm());
            stage.setScene(new Scene(webView, 600, 600));

            stage.show();
        });


        menuBar.getMenus().addAll(souborMenu, napovedaMenu);
        return menuBar;
    }

    /**
     * Metoda, ktera zpracuje prikaz
     *
     * @param prikaz
     */
    private void zpracujPrikaz(String prikaz) {
        String lokalniPromennaDrziciOdpovedOdPocitace = Hra.getHra().zpracujPrikaz(prikaz);
        textArea.appendText("\n"+ lokalniPromennaDrziciOdpovedOdPocitace + "\n");
    }
}
