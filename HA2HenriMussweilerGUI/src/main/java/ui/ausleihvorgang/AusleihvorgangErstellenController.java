package ui.ausleihvorgang;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.entity.internal.Ausleihvorgang;
import awk.leihvorgang.usecases.impl.AusleihvorgangManager;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import jfxtras.scene.control.LocalDateTimeTextField;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AusleihvorgangErstellenController implements Initializable, ControlledScreen {

    @FXML
    private Button reservierenButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private TableView<AusleihvorgangTO> ausleihvorgangTableView;

    @FXML
    private TableColumn<AusleihvorgangTO, Long> ausleihvorgangIdColumn;

    @FXML
    private TableColumn<AusleihvorgangTO, Long> teilnehmerColumn;

    @FXML
    private TableColumn<AusleihvorgangTO, Long> fahrzeugColumn;

    @FXML
    private TableColumn<AusleihvorgangTO, LocalDateTime> startdatumColumn;

    @FXML
    private TableColumn<AusleihvorgangTO, LocalDateTime> enddatumColumn;

    @FXML
    private ComboBox teilnehmerComboBox;

    @FXML
    private ComboBox fahrzeugComboBox;

    @FXML
    private LocalDateTimeTextField startdatumPicker;

    @FXML
    private LocalDateTimeTextField enddatumPicker;

    private ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initData() throws awk.teilnehmerverwaltung.AnwendungskernException, AnwendungskernException {
        initTableView();
        initComboBoxes();

    }

    private void initComboBoxes() throws awk.teilnehmerverwaltung.AnwendungskernException, AnwendungskernException {
        initTeilnehmerComboBox();
        initFahrzeugComboBox();
    }

    private void initFahrzeugComboBox() throws AnwendungskernException {
        // Prüfen ob Fahrzeuge vorhanden sind
        if (HauptmenueService.getFahrzeuglisteErstellen().fahrzeugListeErstellen().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Keine Fahrzeuge vorhanden");
            alert.setContentText("Es sind keine Fahrzeuge vorhanden. Bitte legen Sie zuerst ein Fahrzeug an.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Fahrzeug auswählen");
            alert.setContentText("Bitte wählen Sie ein Fahrzeug aus.");
            alert.showAndWait();

            // Alle Fahrzeuge laden und in die Combobox einfügen
            ObservableList<Long> fahrzeuge = FXCollections.observableArrayList(HauptmenueService.getFahrzeuglisteErstellen().fahrzeugListeErstellen()).stream()
                    .filter(fahrzeugTO -> !fahrzeugTO.isDeleted())
                    .map(fahrzeugTO -> fahrzeugTO.getFahrzeugId())
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            fahrzeugComboBox.setItems(fahrzeuge);
        }
    }

    private void initTeilnehmerComboBox() throws awk.teilnehmerverwaltung.AnwendungskernException {
        ObservableList<Long> teilnehmerTOList = FXCollections.observableArrayList(HauptmenueService.getTeilnehmerlisteAusgeben().teilnehmerListeAusgeben().stream()
                .map(Teilnehmer::getTeilnehmerId)
                .collect(Collectors.toList())
        );

        teilnehmerComboBox.setItems(teilnehmerTOList);
    }

    private void initTableView() {
        //TableView leeren
        ausleihvorgangTableView.getItems().clear();

        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();

        Collection<AusleihvorgangTO> ausleihvorgangCollection = ausleihvorgangManager.liefereAusleihvorgaenge();
        if (ausleihvorgangCollection != null) {
            ausleihvorgangCollection = ausleihvorgangCollection.stream()
                    .filter(ausleihvorgangTO -> !ausleihvorgangTO.getStorniert().equals("Y") && !ausleihvorgangTO.getAbgeschlossen().equals("Y"))
                    .collect(Collectors.toList());
        } else {
            ausleihvorgangCollection = new ArrayList<>();
        }

        //Collection in ObservableList umwandeln
        ObservableList<AusleihvorgangTO> ausleihvorgangObservableList = FXCollections.observableArrayList(ausleihvorgangCollection);

        //TableView mit ObservableList befüllen
        ausleihvorgangTableView.setItems(ausleihvorgangObservableList);

        //TableView Spalten mit Attributen der Klasse AusleihvorgangTO verbinden
        ausleihvorgangIdColumn.setCellValueFactory(new PropertyValueFactory<AusleihvorgangTO, Long>("ausleihvorgangId"));
        teilnehmerColumn.setCellValueFactory(new PropertyValueFactory<AusleihvorgangTO, Long>("teilnehmerId"));
        fahrzeugColumn.setCellValueFactory(new PropertyValueFactory<AusleihvorgangTO, Long>("fahrzeugId"));
        startdatumColumn.setCellValueFactory(new PropertyValueFactory<AusleihvorgangTO, LocalDateTime>("startdatum"));
        enddatumColumn.setCellValueFactory(new PropertyValueFactory<AusleihvorgangTO, LocalDateTime>("enddatum"));
    }

    @FXML
    public void reservierenButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        try {
            // Prüfen ob ein Teilnehmer ausgewählt wurde
            if (teilnehmerComboBox.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fehler");
                alert.setHeaderText("Kein Teilnehmer ausgewählt");
                alert.setContentText("Bitte wählen Sie einen Teilnehmer aus.");
                alert.showAndWait();
            } else {
                // Prüfen ob ein Fahrzeug ausgewählt wurde
                if (fahrzeugComboBox.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fehler");
                    alert.setHeaderText("Kein Fahrzeug ausgewählt");
                    alert.setContentText("Bitte wählen Sie ein Fahrzeug aus.");
                    alert.showAndWait();
                } else {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");


                    LocalDateTime startDatum = startdatumPicker.getLocalDateTime();
                    LocalDateTime endDatum = enddatumPicker.getLocalDateTime();

                    // Prüfen ob das Fahrzeug bereits in diesem Zeitraum ausgeliehen ist
                    if (istFahrzeugVerfuegbar(Long.parseLong(fahrzeugComboBox.getSelectionModel().getSelectedItem().toString()), startDatum, endDatum)) {
                        // Prüfen ob der Teilnehmer bereits ein Fahrzeug in diesem Zeitraum ausgeliehen hat
                        if (istTeilnehmerVerfuegbar(Long.parseLong(teilnehmerComboBox.getSelectionModel().getSelectedItem().toString()), startDatum, endDatum)) {
                            // AusleihvorgangTO erstellen
                            AusleihvorgangTO ausleihvorgangTO = new AusleihvorgangTO();
                            ausleihvorgangTO.setFahrzeugId(Long.parseLong(fahrzeugComboBox.getSelectionModel().getSelectedItem().toString()));
                            ausleihvorgangTO.setTeilnehmerId(Long.parseLong(teilnehmerComboBox.getSelectionModel().getSelectedItem().toString()));

                            ausleihvorgangTO.setStartdatum(startdatumPicker.getLocalDateTime().format(formatter));
                            ausleihvorgangTO.setEnddatum(enddatumPicker.getLocalDateTime().format(formatter));

                            ausleihvorgangTO.setAbgeschlossen("N");
                            ausleihvorgangTO.setStorniert("N");
                            ausleihvorgangTO.setGefahreneKilometer(0);
                            ausleihvorgangTO.setRechnungId(0L);
                            ausleihvorgangTO.setAusleihvorgangId(HauptmenueService.getAusleihvorgangPflegen().maxAusleihvorgangId() + 1);

                            // AusleihvorgangTO in der Datenbank speichern
                            HauptmenueService.getAusleihvorgangPflegen().ausleihvorgangErstellen(ausleihvorgangTO);

                            // Erfolgsmeldung ausgeben
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Erfolg");
                            alert.setHeaderText("Ausleihvorgang erfolgreich erstellt");
                            alert.setContentText("Der Ausleihvorgang wurde erfolgreich erstellt!");
                            alert.showAndWait();

                            // TableView aktualisieren
                            initTableView();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Fehler");
                            alert.setHeaderText("Teilnehmer hat bereits ein Fahrzeug ausgeliehen");
                            alert.setContentText("Der Teilnehmer hat bereits ein Fahrzeug ausgeliehen.");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Fehler");
                        alert.setHeaderText("Fahrzeug bereits ausgeliehen");
                        alert.setContentText("Das Fahrzeug ist bereits ausgeliehen.");
                        alert.showAndWait();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean istTeilnehmerVerfuegbar(long teilnehmerId, LocalDateTime startDatum, LocalDateTime endDatum) {
        try {
            // Create a DateTimeFormatter with your date pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            // Get all rental processes for the participant
            Collection<AusleihvorgangTO> ausleihvorgangTOList = HauptmenueService.getAusleihvorgangSuchen().ausleihvorgangSuchenByTeilnehmerId(teilnehmerId);

            // Filter the rental processes that overlap with the desired period
            boolean isVerfuegbar = ausleihvorgangTOList.stream()
                    .noneMatch(av -> {
                        LocalDateTime avStartDatum = LocalDateTime.parse(av.getStartdatum(), formatter);
                        LocalDateTime avEndDatum = LocalDateTime.parse(av.getEnddatum(), formatter);
                        return (avEndDatum.isAfter(startDatum) && avStartDatum.isBefore(endDatum))
                                || (avStartDatum.isBefore(endDatum) && avEndDatum.isAfter(startDatum));
                    });

            return isVerfuegbar;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception accordingly
            return false;
        }
    }

    private boolean istFahrzeugVerfuegbar(Long fahrzeugId, LocalDateTime startDatum, LocalDateTime endDatum) {
        try {
            // Create a DateTimeFormatter with your date pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            // Get all rental processes for the vehicle
            Collection<AusleihvorgangTO> ausleihvorgangTOList = HauptmenueService.getAusleihvorgangSuchen().ausleihvorgangSuchenByFahrzeugId(fahrzeugId);

            // Filter the rental processes that overlap with the desired period
            boolean isVerfuegbar = ausleihvorgangTOList.stream()
                    .noneMatch(av -> {
                        LocalDateTime avStartDatum = LocalDateTime.parse(av.getStartdatum(), formatter);
                        LocalDateTime avEndDatum = LocalDateTime.parse(av.getEnddatum(), formatter);
                        return (avEndDatum.isAfter(startDatum) && avStartDatum.isBefore(endDatum))
                                || (avStartDatum.isBefore(endDatum) && avEndDatum.isAfter(startDatum));
                    });

            return isVerfuegbar;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception accordingly
            return false;
        }
    }


    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.AVW_SCREEN);
    }
}
