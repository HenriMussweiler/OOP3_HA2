package awk.teilnehmerverwaltung.entity;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;

public class TeilnehmerTO {

    private long teilnehmerId;

    private String name;

    private String vorname;

    private String strasse;

    private String hausnummer;

    private String postleitzahl;

    private String ort;

    private String iban;

    private String mail;

    private String telefon;

    public TeilnehmerTO() {
    }

    public Teilnehmer toTeilnehmer() throws AnwendungskernException {
        Teilnehmer teilnehmer = new Teilnehmer(this.teilnehmerId, this.name, this.vorname, this.strasse, this.hausnummer, this.postleitzahl, this.ort, this.iban, this.mail, this.telefon);
        teilnehmer.setTeilnehmerId(this.teilnehmerId);
        teilnehmer.setName(this.name);
        teilnehmer.setVorname(this.vorname);
        teilnehmer.setStrasse(this.strasse);
        teilnehmer.setHausnummer(this.hausnummer);
        teilnehmer.setPostleitzahl(this.postleitzahl);
        teilnehmer.setOrt(this.ort);
        teilnehmer.setIban(this.iban);
        teilnehmer.setMail(this.mail);
        teilnehmer.setTelefon(this.telefon);
        return teilnehmer;
    }

    public long getTeilnehmerId() {
        return teilnehmerId;
    }

    public void setTeilnehmerId(long teilnehmerId) {
        this.teilnehmerId = teilnehmerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
