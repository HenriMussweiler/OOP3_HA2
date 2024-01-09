package awk.leihvorgang.entity.internal;

import awk.leihvorgang.entity.RechnungTO;

import java.time.LocalDateTime;

public class Rechnung {

    private Long rechnungId;

    private Long teilnehmerId;

    private LocalDateTime rechnungsdatum;

    private double gesamtsumme;

    public Rechnung(Long rechnungId, Long teilnehmerId, LocalDateTime rechnungsdatum, double gesamtsumme) {
        this.rechnungId = rechnungId;
        this.teilnehmerId = teilnehmerId;
        this.rechnungsdatum = rechnungsdatum;
        this.gesamtsumme = gesamtsumme;
    }

    public Rechnung(Long teilnehmerId, LocalDateTime rechnungsdatum, double gesamtsumme) {
        this.teilnehmerId = teilnehmerId;
        this.rechnungsdatum = rechnungsdatum;
        this.gesamtsumme = gesamtsumme;
    }

    public Rechnung() {
    }

    public Long getRechnungId() {
        return rechnungId;
    }

    public RechnungTO toRechnungTO() {
        RechnungTO rechnungTO = new RechnungTO(this.rechnungId, this.teilnehmerId, this.rechnungsdatum, this.gesamtsumme);
        return rechnungTO;
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
