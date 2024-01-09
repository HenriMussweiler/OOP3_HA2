package awk.leihvorgang.entity.internal;

import awk.leihvorgang.entity.AusleihvorgangTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Ausleihvorgang {

    private Long ausleihvorgangId;

    private Long fahrzeugId;

    private Long teilnehmerId;

    private LocalDateTime startdatum;

    private LocalDateTime enddatum;

    private String abgeschlossen;

    private String storniert;

    private int gefahreneKilometer;

    private Long rechnungId;

    public Ausleihvorgang(Long ausleihvorgangId, Long fahrzeugId, Long teilnehmerId, LocalDateTime startdatum, LocalDateTime enddatum, String abgeschlossen, String storniert, int gefahreneKilometer, Long rechnungId) {
        this.ausleihvorgangId = ausleihvorgangId;
        this.fahrzeugId = fahrzeugId;
        this.teilnehmerId = teilnehmerId;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
        this.abgeschlossen = abgeschlossen;
        this.storniert = storniert;
        this.gefahreneKilometer = gefahreneKilometer;
        this.rechnungId = rechnungId;
    }

    public Ausleihvorgang() {
    }

    public Ausleihvorgang(Long fahrzeugId, Long teilnehmerId, LocalDateTime startdatum, LocalDateTime enddatum, String abgeschlossen, String storniert, int gefahreneKilometer, Long rechnungId) {
        this.fahrzeugId = fahrzeugId;
        this.teilnehmerId = teilnehmerId;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
        this.abgeschlossen = abgeschlossen;
        this.storniert = storniert;
        this.gefahreneKilometer = gefahreneKilometer;
        this.rechnungId = rechnungId;
    }

    public Ausleihvorgang(Long fahrzeugId, Long teilnehmerId, String startdatum, String enddatum) {
        this.fahrzeugId = fahrzeugId;
        this.teilnehmerId = teilnehmerId;
        this.startdatum = LocalDateTime.parse(startdatum);
        this.enddatum = LocalDateTime.parse(enddatum);
    }

    public AusleihvorgangTO ausleihvorgangTO() {
        AusleihvorgangTO ausleihvorgangTO = new AusleihvorgangTO();

        ausleihvorgangTO.setAusleihvorgangId(this.ausleihvorgangId);
        ausleihvorgangTO.setFahrzeugId(this.fahrzeugId);
        ausleihvorgangTO.setTeilnehmerId(this.teilnehmerId);
        ausleihvorgangTO.setStartdatum(this.startdatum.toString());
        ausleihvorgangTO.setEnddatum(this.enddatum.toString());
        ausleihvorgangTO.setAbgeschlossen(this.abgeschlossen);
        ausleihvorgangTO.setStorniert(this.storniert);
        ausleihvorgangTO.setGefahreneKilometer(this.gefahreneKilometer);
        ausleihvorgangTO.setRechnungId(this.rechnungId);

        return ausleihvorgangTO;
    }

    public Long getAusleihvorgangId() {
        return ausleihvorgangId;
    }

    public void setAusleihvorgangId(Long ausleihvorgangId) {
        this.ausleihvorgangId = ausleihvorgangId;
    }

    public Long getFahrzeugId() {
        return fahrzeugId;
    }

    public void setFahrzeugId(Long fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
    }

    public Long getTeilnehmerId() {
        return teilnehmerId;
    }

    public void setTeilnehmerId(Long teilnehmerId) {
        this.teilnehmerId = teilnehmerId;
    }

    public LocalDateTime getStartdatum() {
        return startdatum;
    }

    public void setStartdatum(LocalDateTime startdatum) {
        this.startdatum = startdatum;
    }

    public LocalDateTime getEnddatum() {
        return enddatum;
    }

    public void setEnddatum(LocalDateTime enddatum) {
        this.enddatum = enddatum;
    }

    public String getAbgeschlossen() {
        return abgeschlossen;
    }

    public void setAbgeschlossen(String abgeschlossen) {
        this.abgeschlossen = abgeschlossen;
    }

    public String getStorniert() {
        return storniert;
    }

    public void setStorniert(String storniert) {
        this.storniert = storniert;
    }

    public int getGefahreneKilometer() {
        return gefahreneKilometer;
    }

    public void setGefahreneKilometer(int gefahreneKilometer) {
        this.gefahreneKilometer = gefahreneKilometer;
    }

    public Long getRechnungId() {
        return rechnungId;
    }

    public void setRechnungId(Long rechnungId) {
        this.rechnungId = rechnungId;
    }

}
