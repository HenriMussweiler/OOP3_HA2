package awk.fuhrparkverwaltung.usecases.impl;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import awk.fuhrparkverwaltung.usecases.IFahrzeugSuchen;

import java.util.ArrayList;
import java.util.Collection;

public class FahrzeugSuchen implements IFahrzeugSuchen {
    @Override
    public Collection<FahrzeugTO> fahrzeugSuchenByModell(String modell) throws AnwendungskernException {
        FahrzeugManager einFahrzeugManager = FahrzeugManager.getFahrzeugManager();
        Collection<FahrzeugTO> fahrzeugTOListe = new ArrayList<FahrzeugTO>();
        Collection<Fahrzeug> fahrzeugListe = einFahrzeugManager.fahrzeugSuchenByModell(modell);

        for (Fahrzeug einFahrzeug : fahrzeugListe)
            fahrzeugTOListe.add(einFahrzeug.toFahrzeugTO());

        return fahrzeugTOListe;
    }

    @Override
    public FahrzeugTO fahrzeugSuchenById(Long fahrzeugId) throws AnwendungskernException {
        FahrzeugManager einFahrzeugManager = FahrzeugManager.getFahrzeugManager();
        Fahrzeug einFahrzeug = einFahrzeugManager.fahrzeugSuchenById(fahrzeugId);

        return einFahrzeug.toFahrzeugTO();
    }
}
