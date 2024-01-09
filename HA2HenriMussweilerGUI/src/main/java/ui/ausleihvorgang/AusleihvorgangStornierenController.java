package ui.ausleihvorgang;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.entity.internal.Ausleihvorgang;
import awk.leihvorgang.usecases.impl.AusleihvorgangManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AusleihvorgangStornierenController implements Initializable, ControlledScreen {

    @FXML
    private Button zurueckButton;

    @FXML
    private Button stornierenButton;

    @FXML
    private TableView ausleihvorgangTableView;

    @FXML
    private TableColumn ausleihvorgangIdColumn;

    @FXML
    private TableColumn teilnehmerColumn;

    @FXML
    private TableColumn fahrzeugColumn;

    @FXML
    private TableColumn startdatumColumn;

    @FXML
    private TableColumn enddatumColumn;

    @FXML
    private ComboBox<Long> ausleihvorgangComboBox;

    private ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initData() {
        initAusleihvorgangComboBox();
        initTableView();
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

    private void initAusleihvorgangComboBox() {
        ObservableList<Long> ausleihvorgaengeTOList = FXCollections.observableArrayList(HauptmenueService.getAusleihvorgangSuchen().liefereAlleAusleihvorgaenge().stream()
                .map(AusleihvorgangTO::getAusleihvorgangId)
                .collect(Collectors.toList())
        );

        ausleihvorgangComboBox.setItems(ausleihvorgaengeTOList);
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.AVW_SCREEN);
    }

    @FXML
    public void stornierenButtonClicked(ActionEvent actionEvent) {
        // Retrieve the selected AusleihvorgangTO object
        Long selectedAusleihvorgangId = ausleihvorgangComboBox.getValue();
        AusleihvorgangTO selectedAusleihvorgangTO = HauptmenueService.getAusleihvorgangSuchen().ausleihvorgangSuchenByAusleihvorgangId(selectedAusleihvorgangId);

        if (selectedAusleihvorgangTO != null) {
            // Set the storniert status of the AusleihvorgangTO object to true
            selectedAusleihvorgangTO.setStorniert("Y");

            Ausleihvorgang ausleihvorgang = selectedAusleihvorgangTO.toAusleihvorgang();

            // Call a method from AusleihvorgangManager to update the AusleihvorgangTO object
            AusleihvorgangManager.getAusleihvorgangManager().ausleihvorgangStornieren(ausleihvorgang);

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ausleihvorgang stornieren");
            alert.setHeaderText("Ausleihvorgang erfolgreich storniert!");
            alert.showAndWait();

            // Initialize the TableView
            initTableView();
        }
    }
}
