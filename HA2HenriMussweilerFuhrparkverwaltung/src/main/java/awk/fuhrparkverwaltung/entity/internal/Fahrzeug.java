package awk.fuhrparkverwaltung.entity.internal;

import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import jakarta.persistence.*;

@Entity
@Table(name = "fahrzeug")
@NamedQuery(name="Fahrzeug.FIND_FAHRZEUG_BY_MODELL", query="SELECT f FROM Fahrzeug f WHERE f.modell = :modell")
public class Fahrzeug {

    public static final String FIND_FAHRZEUG_BY_MODELL = "Fahrzeug.FIND_FAHRZEUG_BY_MODELL";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fahrzeug_generator")
    @Column(name = "fahrzeug_id")
    private Long fahrzeugId;

    @Column(name = "hersteller")
    private String hersteller;

    @Column(name = "modell")
    private String modell;

    @Column(name = "ausstattung")
    private String ausstattung;

    @Column(name = "leistung_kw")
    private int leistungKw;

    @Column(name = "kraftstoffart")
    private String kraftstoffart;

    @Column(name = "baujahr")
    private int baujahr;

    @Column(name = "kilometerstand")
    private int kilometerstand;

    @Column(name = "getriebe")
    private String getriebe;

    @Column(name = "sitzplaetze")
    private int sitzplaetze;

    @Column(name = "sharing_standort")
    private String sharingStandort;

    @Column(name = "deleted")
    private boolean deleted;

    public Fahrzeug() {
    }

    public Fahrzeug(String hersteller, String modell, String ausstattung, int leistungKw, String kraftstoffart, int baujahr, int kilometerstand, String getriebe, int sitzplaetze, String sharingStandort, boolean deleted) {
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

    public Fahrzeug(long fahrzeugId, String hersteller, String modell, String ausstattung, int leistungKw, String kraftstoffart, int baujahr, int kilometerstand, String getriebe, int sitzplaetze, String sharingStandort, boolean deleted) {
        this.fahrzeugId = fahrzeugId;
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

    public Fahrzeug(String hersteller, String modell, String ausstattung, int leistungKw, String kraftstoffart, int baujahr, int kilometerstand, String getriebe, int sitzplaetze, String sharingStandort) {
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
        this.deleted = false;
    }

    public FahrzeugTO toFahrzeugTO() {
        FahrzeugTO einFahrzeugTO = new FahrzeugTO();

        einFahrzeugTO.setFahrzeugId(this.fahrzeugId);
        einFahrzeugTO.setHersteller(this.hersteller);
        einFahrzeugTO.setModell(this.modell);
        einFahrzeugTO.setAusstattung(this.ausstattung);
        einFahrzeugTO.setLeistungKw(this.leistungKw);
        einFahrzeugTO.setKraftstoffart(this.kraftstoffart);
        einFahrzeugTO.setBaujahr(this.baujahr);
        einFahrzeugTO.setKilometerstand(this.kilometerstand);
        einFahrzeugTO.setGetriebe(this.getriebe);
        einFahrzeugTO.setSitzplaetze(this.sitzplaetze);
        einFahrzeugTO.setSharingStandort(this.sharingStandort);
        einFahrzeugTO.setDeleted(this.deleted);

        return einFahrzeugTO;
    }

    public Long getFahrzeugId() {
        return fahrzeugId;
    }

    public void setFahrzeugId(Long fahrzeugId) {
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
