package awk.fuhrparkverwaltung.usecases;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;

import java.util.Collection;

public interface IFahrzeugSuchen {

    public Collection<FahrzeugTO> fahrzeugSuchenByModell (String modell) throws AnwendungskernException;

    public FahrzeugTO fahrzeugSuchenById (Long fahrzeugId) throws AnwendungskernException;
}
