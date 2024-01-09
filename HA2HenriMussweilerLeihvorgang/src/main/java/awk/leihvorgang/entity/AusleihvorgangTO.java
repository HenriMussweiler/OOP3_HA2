package awk.leihvorgang.entity;

import awk.leihvorgang.entity.internal.Ausleihvorgang;

import java.time.LocalDateTime;

public class AusleihvorgangTO {

    private Long ausleihvorgangId;

    private Long fahrzeugId;

    private Long teilnehmerId;

    private String startdatum;

    private String enddatum;

    private String abgeschlossen;

    private String storniert;

    private int gefahreneKilometer;

    private Long rechnungId;

    public AusleihvorgangTO() {

    }

    public AusleihvorgangTO(Long ausleihvorgangId, Long fahrzeugId, Long teilnehmerId, LocalDateTime startdatum, LocalDateTime enddatum, String abgeschlossen, String storniert, int gefahreneKilometer, Long rechnungId) {
        this.ausleihvorgangId = ausleihvorgangId;
        this.fahrzeugId = fahrzeugId;
        this.teilnehmerId = teilnehmerId;
        this.startdatum = startdatum.toString();
        this.enddatum = enddatum.toString();
        this.abgeschlossen = abgeschlossen;
        this.storniert = storniert;
        this.gefahreneKilometer = gefahreneKilometer;
        this.rechnungId = rechnungId;
    }

    public Ausleihvorgang toAusleihvorgang() {
        Ausleihvorgang ausleihvorgang = new Ausleihvorgang(this.ausleihvorgangId, this.fahrzeugId, this.teilnehmerId, LocalDateTime.parse(this.startdatum), LocalDateTime.parse(this.enddatum), this.abgeschlossen, this.storniert, this.gefahreneKilometer, this.rechnungId);
        ausleihvorgang.setAusleihvorgangId(this.ausleihvorgangId);
        ausleihvorgang.setFahrzeugId(this.fahrzeugId);
        ausleihvorgang.setTeilnehmerId(this.teilnehmerId);
        ausleihvorgang.setStartdatum(LocalDateTime.parse(this.startdatum));
        ausleihvorgang.setEnddatum(LocalDateTime.parse(this.enddatum));
        ausleihvorgang.setAbgeschlossen(this.abgeschlossen);
        ausleihvorgang.setStorniert(this.storniert);
        ausleihvorgang.setGefahreneKilometer(this.gefahreneKilometer);
        ausleihvorgang.setRechnungId(this.rechnungId);
        return ausleihvorgang;
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

    public String getStartdatum() {
        return startdatum;
    }

    public void setStartdatum(String startdatum) {
        this.startdatum = startdatum;
    }

    public String getEnddatum() {
        return enddatum;
    }

    public void setEnddatum(String enddatum) {
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
