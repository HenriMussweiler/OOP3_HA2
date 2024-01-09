package awk.fuhrparkverwaltung.entity;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;

public class FahrzeugTO {

    private long fahrzeugId;

    private String hersteller;

    private String modell;

    private String ausstattung;

    private int leistungKw;

    private String kraftstoffart;

    private int baujahr;

    private int kilometerstand;

    private String getriebe;

    private int sitzplaetze;

    private String sharingStandort;

    private boolean deleted;

    public FahrzeugTO() {
    }

    public FahrzeugTO(String hersteller, String modell, String ausstattung, int leistungKw, String kraftstoffart, int baujahr, int kilometerstand, String getriebe, int sitzplaetze, String sharingStandort, boolean deleted) {
        this.hersteller = hersteller;
        this.modell = modell;
        this.ausstattung = ausstattung;
        this.leistungKw = leistungKw;
        this.kraftstoffart = kraftstoffart;
        this.baujahr = baujahr;
        this.kilometerstand = kilometerstand;
        this.getriebe = getriebe;
        this.sitzplaetze = sitzplaetze;
        this.sharingStandort = sharingStandort;
        this.deleted = deleted;
    }

    public Fahrzeug toFahrzeugTO() throws AnwendungskernException {
        Fahrzeug fahrzeug = new Fahrzeug(this.fahrzeugId, this.hersteller, this.modell, this.ausstattung, this.leistungKw, this.kraftstoffart, this.baujahr, this.kilometerstand, this.getriebe, this.sitzplaetze, this.sharingStandort, this.deleted);
        fahrzeug.setFahrzeugId(this.fahrzeugId);
        fahrzeug.setHersteller(this.hersteller);
        fahrzeug.setModell(this.modell);
        fahrzeug.setAusstattung(this.ausstattung);
        fahrzeug.setLeistungKw(this.leistungKw);
        fahrzeug.setKraftstoffart(this.kraftstoffart);
        fahrzeug.setBaujahr(this.baujahr);
        fahrzeug.setKilometerstand(this.kilometerstand);
        fahrzeug.setGetriebe(this.getriebe);
        fahrzeug.setSitzplaetze(this.sitzplaetze);
        fahrzeug.setSharingStandort(this.sharingStandort);
        fahrzeug.setDeleted(this.deleted);
        return fahrzeug;
    }

    public long getFahrzeugId() {
        return fahrzeugId;
    }

    public void setFahrzeugId(long fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getAusstattung() {
        return ausstattung;
    }

    public void setAusstattung(String ausstattung) {
        this.ausstattung = ausstattung;
    }

    public int getLeistungKw() {
        return leistungKw;
    }

    public void setLeistungKw(int leistungKw) {
        this.leistungKw = leistungKw;
    }

    public String getKraftstoffart() {
        return kraftstoffart;
    }

    public void setKraftstoffart(String kraftstoffart) {
        this.kraftstoffart = kraftstoffart;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public int getKilometerstand() {
        return kilometerstand;
    }

    public void setKilometerstand(int kilometerstand) {
        this.kilometerstand = kilometerstand;
    }

    public String getGetriebe() {
        return getriebe;
    }

    public void setGetriebe(String getriebe) {
        this.getriebe = getriebe;
    }

    public int getSitzplaetze() {
        return sitzplaetze;
    }

    public void setSitzplaetze(int sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }

    public String getSharingStandort() {
        return sharingStandort;
    }

    public void setSharingStandort(String sharingStandort) {
        this.sharingStandort = sharingStandort;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
