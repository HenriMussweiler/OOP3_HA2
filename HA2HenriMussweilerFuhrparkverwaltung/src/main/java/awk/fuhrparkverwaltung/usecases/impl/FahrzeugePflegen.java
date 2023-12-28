package awk.fuhrparkverwaltung.usecases.impl;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import awk.fuhrparkverwaltung.entity.internal.SharingStandort;
import awk.fuhrparkverwaltung.usecases.IFahrzeugePflegen;

public class FahrzeugePflegen implements IFahrzeugePflegen {

    public FahrzeugePflegen() {
    }

    @Override
    public void fahrzeugAnlegen(
            String hersteller, String modell, String ausstattung,
            int leistungKw, String kraftstoffart, int baujahr,
            int kilometerstand, String getriebe, int sitzplaetze,
            SharingStandort sharingStandort, boolean deleted)
            throws AnwendungskernException {

        FahrzeugManager einFahrzeugManager = FahrzeugManager.getFahrzeugManager();

        einFahrzeugManager.fahrzeugHinzufuegen(
                new Fahrzeug(hersteller, modell, ausstattung,
                        leistungKw, kraftstoffart, baujahr,
                        kilometerstand, getriebe, sitzplaetze,
                        sharingStandort, deleted));
    }

    @Override
    public void fahrzeugLoeschen(Long fahrzeugId) throws AnwendungskernException {
        FahrzeugManager einFahrzeugManager = FahrzeugManager.getFahrzeugManager();
        Fahrzeug fahrzeug = einFahrzeugManager.fahrzeugSuchenByNr(fahrzeugId);
        if (fahrzeug == null)
            throw new AnwendungskernException("Fahrzeug nicht gefunden!");
        else if (fahrzeug.isDeleted())
            throw new AnwendungskernException("Fahrzeug bereits gelöscht!");
        else {
            fahrzeug.setDeleted(true);
            einFahrzeugManager.fahrzeugAendern(fahrzeug);
        }
    }
}
