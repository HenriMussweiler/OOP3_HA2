package ui.teilnehmerverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TeilnehmerAendernController implements Initializable, ControlledScreen {

    @FXML
    private Button zurueckButton;

    @FXML
    private Button aendernButton;

    @FXML
    private ComboBox teilnehmerComboBox;

    @FXML
    private TableView teilnehmerTableView;

    @FXML
    private TableColumn teilnehmerIdColumn;

    @FXML
    private TableColumn vornameColumn;

    @FXML
    private TableColumn nameColumn;

    @FXML
    private TableColumn strasseColumn;

    @FXML
    private TableColumn hausnummerColumn;

    @FXML
    private TableColumn plzColumn;

    @FXML
    private TableColumn ortColumn;

    @FXML
    private TableColumn ibanColumn;

    @FXML
    private TableColumn mailColumn;

    @FXML
    private TableColumn telefonColumn;

    private ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initData() throws awk.teilnehmerverwaltung.AnwendungskernException {
        initTeilnehmerComboBox();
        initTeilnehmerTableView();
    }

    private void initTeilnehmerTableView() throws awk.teilnehmerverwaltung.AnwendungskernException {

        //Table View leeren
        teilnehmerTableView.getItems().clear();

        //Leere Liste erstellen
        ObservableList<Teilnehmer> teilnehmerList = FXCollections.observableArrayList();

        //Teilnehmer aus Datenbank laden
        teilnehmerList.addAll(HauptmenueService.getTeilnehmerlisteAusgeben().teilnehmerListeAusgeben());

        //Liste in TableView laden
        teilnehmerTableView.setItems(teilnehmerList);

        //Spalten mit Attributen verbinden
        teilnehmerIdColumn.setCellValueFactory(new PropertyValueFactory<>("teilnehmerId"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        strasseColumn.setCellValueFactory(new PropertyValueFactory<>("strasse"));
        hausnummerColumn.setCellValueFactory(new PropertyValueFactory<>("hausnummer"));
        plzColumn.setCellValueFactory(new PropertyValueFactory<>("postleitzahl"));
        ortColumn.setCellValueFactory(new PropertyValueFactory<>("ort"));
        ibanColumn.setCellValueFactory(new PropertyValueFactory<>("iban"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
    }

    private void initTeilnehmerComboBox() throws awk.teilnehmerverwaltung.AnwendungskernException {
        ObservableList<Long> teilnehmerTOList = FXCollections.observableArrayList(HauptmenueService.getTeilnehmerlisteAusgeben().teilnehmerListeAusgeben().stream()
                .map(Teilnehmer::getTeilnehmerId)
                .collect(Collectors.toList())
        );

        teilnehmerComboBox.setItems(teilnehmerTOList);
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.TVW_SCREEN);
    }

    @FXML
    public void aendernButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {

        TeilnehmerTO teilnehmerTO;

        //Teilnehmer auswählen
        if (teilnehmerComboBox.getValue() == null) {
            return;
        } else {
            teilnehmerComboBox.getValue();
            teilnehmerTO = HauptmenueService.getTeilnehmerSuchen().teilnehmerSuchenByTeilnehmerId((Long) teilnehmerComboBox.getValue());
        }

        HauptmenueService.getTeilnehmerAendern().setSelectedTeilnehmer(teilnehmerTO);

        myController.setScreen(ui.menue.Hauptmenue.TEILNEHMER_AENDERN_ERWEITERUNG_SCREEN);
    }
}
