package awk.fuhrparkverwaltung.usecases.impl;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import awk.fuhrparkverwaltung.usecases.IFahrzeuglisteErstellen;

import java.util.ArrayList;
import java.util.Collection;

public class FahrzeuglisteErstellen implements IFahrzeuglisteErstellen {
    @Override
    public Collection<FahrzeugTO> fahrzeugTOListeErstellen() throws AnwendungskernException {
        FahrzeugManager fahrzeugManager = FahrzeugManager.getFahrzeugManager();
        Collection<FahrzeugTO> fahrzeugtTOliste = new ArrayList<FahrzeugTO>();
        Collection<Fahrzeug> fahrzeugeListe = fahrzeugManager.fahrzeugeLiefern();

        for (Fahrzeug einFahrzeug:fahrzeugeListe)
            fahrzeugtTOliste.add( einFahrzeug.toFahrzeugTO());

        return fahrzeugtTOliste;
    }

    @Override
    public Collection<FahrzeugTO> fahrzeugListeErstellen() throws AnwendungskernException {
            FahrzeugManager fahrzeugManager = FahrzeugManager.getFahrzeugManager();
            Collection<FahrzeugTO> fahrzeugeTOListe = new ArrayList<FahrzeugTO>();
            Collection<Fahrzeug> fahrzeugeListe = fahrzeugManager.fahrzeugeLiefern();

            for (Fahrzeug einFahrzeug:fahrzeugeListe)
                    fahrzeugeTOListe.add( einFahrzeug.toFahrzeugTO());

            return fahrzeugeTOListe;



    }
}
