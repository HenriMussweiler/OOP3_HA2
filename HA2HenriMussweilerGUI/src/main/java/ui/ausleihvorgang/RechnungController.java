package ui.ausleihvorgang;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.entity.RechnungTO;
import awk.leihvorgang.usecases.impl.AusleihvorgangManager;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import ui.menue.ControlledScreen;
import ui.menue.Hauptmenue;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.stream.Collectors;

public class RechnungController implements Initializable, ControlledScreen {

    @FXML
    private Button generiereRechnungButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private ComboBox teilnehmerComboBox;

    @FXML
    private DatePicker startdatumPicker;

    @FXML
    private DatePicker enddatumPicker;

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
        initTeilnehmerComboBox();
    }

    private void initTeilnehmerComboBox() throws AnwendungskernException {
        try {
            // Annahme: Hier bekommst du eine Liste von Teilnehmern und ihren Ausleihvorgängen
            Collection<Teilnehmer> teilnehmerListe = HauptmenueService.getTeilnehmerlisteAusgeben().teilnehmerListeAusgeben();

            // Filtere Teilnehmer, die abgeschlossene oder stornierte Ausleihvorgänge haben
            List<Long> teilnehmerMitAbgeschlossenenOderStorniertenAusleihvorgaengen = teilnehmerListe.stream()
                    .filter(te -> hatAbgeschlosseneOderStornierteAusleihvorgaenge(te))
                    .map(Teilnehmer::getTeilnehmerId)
                    .collect(Collectors.toList());

            // Fülle die ComboBox mit den gefilterten Teilnehmern
            ObservableList<Long> teilnehmerTOList = FXCollections.observableArrayList(teilnehmerMitAbgeschlossenenOderStorniertenAusleihvorgaengen);
            teilnehmerComboBox.setItems(teilnehmerTOList);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle die Ausnahme entsprechend
        } catch (awk.teilnehmerverwaltung.AnwendungskernException e) {
            throw new RuntimeException(e);
        }
    }

    // Hilfsmethode zur Überprüfung, ob ein Teilnehmer abgeschlossene oder stornierte Ausleihvorgänge hat
    private boolean hatAbgeschlosseneOderStornierteAusleihvorgaenge(Teilnehmer teilnehmer) {
        // Annahme: Du hast Zugriff auf die Ausleihvorgänge des Teilnehmers
        Collection<AusleihvorgangTO> ausleihvorgaenge = HauptmenueService.getAusleihvorgangSuchen().ausleihvorgangSuchenByTeilnehmerId(teilnehmer.getTeilnehmerId());

        // Überprüfe, ob der Teilnehmer abgeschlossene oder stornierte Ausleihvorgänge hat
        return ausleihvorgaenge.stream().anyMatch(av -> "Y".equals(av.getAbgeschlossen()) || "Y".equals(av.getStorniert()));
    }

    @FXML
    public void generiereRechnungButtonClicked(ActionEvent actionEvent) throws Exception, awk.teilnehmerverwaltung.AnwendungskernException {
        // Retrieve the selected Teilnehmer object
        Long selectedTeilnehmerId = (Long) teilnehmerComboBox.getValue();
        TeilnehmerTO teilnehmerTO = HauptmenueService.getTeilnehmerSuchen().teilnehmerSuchenByTeilnehmerId(selectedTeilnehmerId);

        // Retrieve the start and end dates
        LocalDate startDate = startdatumPicker.getValue();
        LocalDate endDate = enddatumPicker.getValue();

        if (selectedTeilnehmerId != null && startDate != null && endDate != null) {
            // Retrieve all AusleihvorgangTO objects for the selected Teilnehmer within the specified date range
            List<AusleihvorgangTO> ausleihvorgangTOList = HauptmenueService.getAusleihvorgangSuchen().ausleihvorgangSuchenByTeilnehmerId(teilnehmerTO.getTeilnehmerId()).stream()
                    .filter(ausleihvorgangTO -> ausleihvorgangTO.getStorniert().equals("Y") || ausleihvorgangTO.getAbgeschlossen().equals("Y") && ausleihvorgangTO.getRechnungId() == null
                            && LocalDateTime.parse(ausleihvorgangTO.getStartdatum(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalDate().isAfter(startDate.minusDays(1))
                            && LocalDateTime.parse(ausleihvorgangTO.getEnddatum(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalDate().isBefore(endDate.plusDays(1)))
                    .collect(Collectors.toList());

            double cost = 0;

            // For each AusleihvorgangTO object, calculate the cost and update the object
            for (AusleihvorgangTO ausleihvorgangTO : ausleihvorgangTOList) {
                // Calculate the cost based on the number of kilometers driven
                if (ausleihvorgangTO.getStorniert().equals("Y")) {
                    cost += 50;
                } else if (ausleihvorgangTO.getAbgeschlossen().equals("Y")) {
                    cost += ausleihvorgangTO.getGefahreneKilometer() * 0.8;
                }
            }

            cost = Math.round(cost * 100.0) / 100.0;

            // Generate a unique invoice number
            Long invoiceNumber = HauptmenueService.getRechnungPflegen().maxRechnungsnummer() + 1;

            // Create a RechnungTO object
            RechnungTO rechnungTO = new RechnungTO(invoiceNumber, selectedTeilnehmerId, LocalDateTime.now(), cost);

            // Call a method from RechnungManager to create the RechnungTO object
            HauptmenueService.getRechnungPflegen().rechnungErstellen(rechnungTO);

            // Update the AusleihvorgangTO object with the RechnungTO object
            ausleihvorgangTOList.forEach(ausleihvorgangTO -> ausleihvorgangTO.setRechnungId(invoiceNumber));

            // Update the AusleihvorgangTO objects
            ausleihvorgangTOList.forEach(ausleihvorgangTO -> {
                try {
                    HauptmenueService.getAusleihvorgangPflegen().ausleihvorgangAktualisieren(ausleihvorgangTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rechnung generiert");
            alert.setHeaderText("Rechnung generiert");
            alert.setContentText("Die Rechnung wurde erfolgreich generiert. Der Preis beträgt " + cost + "€.");
            alert.showAndWait();
        }
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(Hauptmenue.AVW_SCREEN);
    }
}
