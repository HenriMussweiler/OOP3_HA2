package awk.leihvorgang.entity;

import awk.leihvorgang.entity.internal.Rechnung;

import java.time.LocalDateTime;

public class RechnungTO {

    private Long rechnungId;

    private Long teilnehmerId;

    private LocalDateTime rechnungsdatum;

    private double gesamtsumme;

    public RechnungTO(Long rechnungId, Long teilnehmerId, LocalDateTime rechnungsdatum, double gesamtsumme) {
        this.rechnungId = rechnungId;
        this.teilnehmerId = teilnehmerId;
        this.rechnungsdatum = rechnungsdatum;
        this.gesamtsumme = gesamtsumme;
    }

    public RechnungTO() {

    }

    public RechnungTO(Long teilnehmerId, LocalDateTime rechnungsdatum, double gesamtsumme) {
        this.teilnehmerId = teilnehmerId;
        this.rechnungsdatum = rechnungsdatum;
        this.gesamtsumme = gesamtsumme;
    }

    public Rechnung toRechnung() {
        Rechnung rechnung = new Rechnung(this.rechnungId, this.teilnehmerId, this.rechnungsdatum, this.gesamtsumme);
        rechnung.setRechnungId(this.rechnungId);
        rechnung.setTeilnehmerId(this.teilnehmerId);
        rechnung.setRechnungsdatum(this.rechnungsdatum);
        rechnung.setGesamtsumme(this.gesamtsumme);
        return rechnung;
    }

    public Long getRechnungId() {
        return rechnungId;
    }

    public void setRechnungId(Long rechnungId) {
        this.rechnungId = rechnungId;
    }

    public Long getTeilnehmerId() {
        return teilnehmerId;
    }

    public void setTeilnehmerId(Long teilnehmerId) {
        this.teilnehmerId = teilnehmerId;
    }

    public LocalDateTime getRechnungsdatum() {
        return rechnungsdatum;
    }

    public void setRechnungsdatum(LocalDateTime rechnungsdatum) {
        this.rechnungsdatum = rechnungsdatum;
    }

    public double getGesamtsumme() {
        return gesamtsumme;
    }

    public void setGesamtsumme(double gesamtsumme) {
        this.gesamtsumme = gesamtsumme;
    }
}
