package awk.fuhrparkverwaltung.usecases;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.internal.SharingStandort;

public interface IFahrzeugePflegen {

    public void fahrzeugAnlegen(
            String hersteller, String modell, String ausstattung, int leistungKw,
            String kraftstoffart, int baujahr, int kilometerstand, String getriebe,
            int sitzplaetze, SharingStandort sharingStandort, boolean deleted)
            throws AnwendungskernException;

    public void fahrzeugLoeschen(Long fahrzeugId)
            throws AnwendungskernException;
}
