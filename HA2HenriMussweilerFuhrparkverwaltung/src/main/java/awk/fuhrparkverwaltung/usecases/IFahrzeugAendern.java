package awk.fuhrparkverwaltung.usecases;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;

public interface IFahrzeugAendern {


    public void fahrzeugAendern(FahrzeugTO fahrzeugTO) throws AnwendungskernException;

    public void setSelectedFahrzeug(Long fahrzeugId);

    public Long getSelectedFahrzeug();
}
