package awk.fuhrparkverwaltung.usecases;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;

import java.util.Collection;

public interface IFahrzeuglisteErstellen {

    public Collection<FahrzeugTO> fahrzeugTOListeErstellen()
            throws AnwendungskernException;

    public Collection<FahrzeugTO> fahrzeugListeErstellen() throws AnwendungskernException;
}
