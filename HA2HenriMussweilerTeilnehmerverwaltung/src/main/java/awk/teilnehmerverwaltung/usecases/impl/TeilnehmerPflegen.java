package awk.teilnehmerverwaltung.usecases.impl;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerPflegen;

public class TeilnehmerPflegen implements ITeilnehmerPflegen {

    public TeilnehmerPflegen() {
    }

    @Override
    public void teilnehmerAnlegen(String name, String vorname, String strasse, String hausnummer, String postleitzahl, String ort, String iban, String mail, String telefon) throws AnwendungskernException {

        TeilnehmerManager teilnehmerManager = TeilnehmerManager.getTeilnehmerManager();

        Teilnehmer teilnehmer = new Teilnehmer(name, vorname, strasse, hausnummer, postleitzahl, ort, iban, mail, telefon);

        teilnehmerManager.teilnehmerAnlegen(teilnehmer);
    }

    @Override
    public void teilnehmerLoeschen(Long teilnehmerId) throws AnwendungskernException {

    }
}
