package ui.menue;

import java.util.Optional;

import awk.fuhrparkverwaltung.AnwendungskernException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;


public class Hauptmenue extends Application {

    protected static Stage mainStage;

    // Screens definieren
    public static final String MAIN_SCREEN = "ui/menue/HauptmenueController";

    public static final String MAIN_SCREEN_FXML = "ui/menue/Hauptmenue.fxml";

    public static final String FVW_SCREEN = "ui/furhparkverwaltung/FahrzeugVerwaltungController";

    public static final String FVW_SCREEN_FXML = "ui/fuhrparkverwaltung/FahrzeugVerwaltung.fxml";

    public static final String TVW_SCREEN = "ui/teilnehmerverwaltung/TeilnehmerVerwaltungController";

    public static final String TVW_SCREEN_FXML = "ui/teilnehmerverwaltung/TeilnehmerVerwaltung.fxml";

    public static final String AVW_SCREEN = "ui/ausleihvorgang/AusleihvorgangverwaltungController";

    public static final String AVW_SCREEN_FXML = "ui/ausleihvorgang/Ausleihvorgangverwaltung.fxml";

    public static final String AUSLEIHVORGANG_ERSTELLEN_SCREEN = "ui/ausleihvorgang/AusleihvorgangErstellenController";

    public static final String AUSLEIHVORGANG_ERSTELLEN_SCREEN_FXML = "ui/ausleihvorgang/AusleihvorgangErstellen.fxml";

    public static final String AUSLEIHVORGANG_ABSCHLIESSEN_SCREEN = "ui/ausleihvorgang/AusleihvorgangAbschliessenController";

    public static final String AUSLEIHVORGANG_ABSCHLIESSEN_SCREEN_FXML = "ui/ausleihvorgang/AusleihvorgangAbschliessen.fxml";

    public static final String AUSLEIHVORGANG_ABSCHLIESSEN_ERWEITERUNG_SCREEN = "ui/ausleihvorgang/AusleihvorgangAbschliessenErweiterungController";

    public static final String AUSLEIHVORGANG_ABSCHLIESSEN_ERWEITERUNG_SCREEN_FXML = "ui/ausleihvorgang/AusleihvorgangAbschliessenErweiterung.fxml";

    public static final String AUSLEIHVORGANG_STORNIEREN_SCREEN = "ui/ausleihvorgang/AusleihvorgangStornierenController";

    public static final String AUSLEIHVORGANG_STORNIEREN_SCREEN_FXML = "ui/ausleihvorgang/AusleihvorgangStornieren.fxml";

    public static final String FAHRZEUG_AENDERN_SCREEN = "ui/fuhrparkverwaltung/FahrzeugAendernController";

    public static final String FAHRZEUG_AENDERN_SCREEN_FXML = "ui/fuhrparkverwaltung/FahrzeugAendern.fxml";

    public static final String FAHRZEUG_SCREEN = "ui/fuhrparkverwaltung/FahrzeugController";

    public static final String FAHRZEUG_SCREEN_FXML = "ui/fuhrparkverwaltung/Fahrzeug.fxml";

    public static final String FAHRZEUG_ERSTELLEN_SCREEN = "ui/fuhrparkverwaltung/FahrzeugErstellenController";

    public static final String FAHRZEUG_ERSTELLEN_SCREEN_FXML = "ui/fuhrparkverwaltung/FahrzeugErstellen.fxml";

    public static final String FAHRZEUG_LOESCHEN_SCREEN = "ui/fuhrparkverwaltung/FahrzeugLoeschenController";

    public static final String FAHRZEUG_LOESCHEN_SCREEN_FXML = "ui/fuhrparkverwaltung/FahrzeugLoeschen.fxml";

    public static final String FAHRZEUG_AENDERN_ERWEITERUNG_SCREEN = "ui/fuhrparkverwaltung/FahrzeugAendernErweiterungController";

    public static final String FAHRZEUG_AENDERN_ERWEITERUNG_SCREEN_FXML = "ui/fuhrparkverwaltung/FahrzeugAendernErweiterung.fxml";

    public static final String RECHNUNG_SCREEN = "ui/ausleihvorgang/RechnungController";

    public static final String RECHNUNG_SCREEN_FXML = "ui/ausleihvorgang/Rechnung.fxml";

    public static final String TEILNEHMER_ERSTELLEN_SCREEN = "ui/teilnehmerverwaltung/TeilnehmerErstellenController";

    public static final String TEILNEHMER_ERSTELLEN_SCREEN_FXML = "ui/teilnehmerverwaltung/TeilnehmerErstellen.fxml";

    public static final String TEILNEHMER_AENDERN_SCREEN = "ui/teilnehmerverwaltung/TeilnehmerAendernController";

    public static final String TEILNEHMER_AENDERN_SCREEN_FXML = "ui/teilnehmerverwaltung/TeilnehmerAendern.fxml";

    public static final String TEILNEHMER_SCREEN = "ui/teilnehmerverwaltung/TeilnehmerController";

    public static final String TEILNEHMER_SCREEN_FXML = "ui/teilnehmerverwaltung/Teilnehmer.fxml";

    public static final String TEILNEHMER_AENDERN_ERWEITERUNG_SCREEN = "ui/teilnehmerverwaltung/TeilnehmerAendernErweiterungController";

    public static final String TEILNEHMER_AENDERN_ERWEITERUNG_SCREEN_FXML = "ui/teilnehmerverwaltung/TeilnehmerAendernErweiterung.fxml";


    @Override
    public void start(Stage primaryStage) {

        this.mainStage = primaryStage;

        // Screens laden
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Hauptmenue.MAIN_SCREEN, Hauptmenue.MAIN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.TVW_SCREEN, Hauptmenue.TVW_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.FVW_SCREEN, Hauptmenue.FVW_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.AVW_SCREEN, Hauptmenue.AVW_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.AUSLEIHVORGANG_ERSTELLEN_SCREEN, Hauptmenue.AUSLEIHVORGANG_ERSTELLEN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.AUSLEIHVORGANG_ABSCHLIESSEN_SCREEN, Hauptmenue.AUSLEIHVORGANG_ABSCHLIESSEN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.AUSLEIHVORGANG_ABSCHLIESSEN_ERWEITERUNG_SCREEN, Hauptmenue.AUSLEIHVORGANG_ABSCHLIESSEN_ERWEITERUNG_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.AUSLEIHVORGANG_STORNIEREN_SCREEN, Hauptmenue.AUSLEIHVORGANG_STORNIEREN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.FAHRZEUG_AENDERN_SCREEN, Hauptmenue.FAHRZEUG_AENDERN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.FAHRZEUG_AENDERN_ERWEITERUNG_SCREEN, Hauptmenue.FAHRZEUG_AENDERN_ERWEITERUNG_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.FAHRZEUG_SCREEN, Hauptmenue.FAHRZEUG_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.FAHRZEUG_ERSTELLEN_SCREEN, Hauptmenue.FAHRZEUG_ERSTELLEN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.FAHRZEUG_LOESCHEN_SCREEN, Hauptmenue.FAHRZEUG_LOESCHEN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.RECHNUNG_SCREEN, Hauptmenue.RECHNUNG_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.TEILNEHMER_ERSTELLEN_SCREEN, Hauptmenue.TEILNEHMER_ERSTELLEN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.TEILNEHMER_AENDERN_SCREEN, Hauptmenue.TEILNEHMER_AENDERN_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.TEILNEHMER_SCREEN, Hauptmenue.TEILNEHMER_SCREEN_FXML);
        mainContainer.loadScreen(Hauptmenue.TEILNEHMER_AENDERN_ERWEITERUNG_SCREEN, Hauptmenue.TEILNEHMER_AENDERN_ERWEITERUNG_SCREEN_FXML);


        mainContainer.print();

        try {
            mainContainer.setScreen(Hauptmenue.MAIN_SCREEN);
        } catch (AnwendungskernException e) {
            throw new RuntimeException(e);
        } catch (awk.teilnehmerverwaltung.AnwendungskernException e) {
            throw new RuntimeException(e);
        }
        Group root = new Group();
//		BorderPane root = new BorderPane();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(confirmCloseEventHandler);
        primaryStage.show();
    }

    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        //Quelle: http://stackoverflow.com/questions/29710492/javafx-internal-close-request
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to exit?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Confirm Exit");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(mainStage);

        // normally, you would just use the default alert positioning,
        // but for this simple sample the main stage is small,
        // so explicitly position the alert so that the main window can still be seen.
        closeConfirmation.setX(mainStage.getX() + 150);
        closeConfirmation.setY(mainStage.getY() - 300 + mainStage.getHeight());

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };


    public static void main(String[] args) {
        launch(args);
    }
}
