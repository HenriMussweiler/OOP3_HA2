package awk.teilnehmerverwaltung.usecases;

import awk.teilnehmerverwaltung.AnwendungskernException;

public interface ITeilnehmerPflegen {

    public void teilnehmerAnlegen(String name, String vorname, String strasse, String hausnummer,
                                  String postleitzahl, String ort, String iban, String mail,
                                  String telefon) throws AnwendungskernException;

    public void teilnehmerLoeschen(Long teilnehmerId) throws AnwendungskernException;
}
