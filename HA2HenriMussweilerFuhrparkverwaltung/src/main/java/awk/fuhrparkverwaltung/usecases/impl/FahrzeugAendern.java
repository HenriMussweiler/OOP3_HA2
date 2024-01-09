package awk.fuhrparkverwaltung.usecases.impl;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import awk.fuhrparkverwaltung.usecases.IFahrzeugAendern;

public class FahrzeugAendern implements IFahrzeugAendern {

    private static Long selectedFahrzeug;

    @Override
    public void fahrzeugAendern(FahrzeugTO fahrzeugTO) throws AnwendungskernException {
        FahrzeugTO einFahrzeug = fahrzeugTO;
        FahrzeugManager einFahrzeugManager = FahrzeugManager.getFahrzeugManager();
        Fahrzeug fahrzeug = einFahrzeug.toFahrzeugTO();

        einFahrzeugManager.update(fahrzeug);
    }

    @Override
    public void setSelectedFahrzeug(Long fahrzeugId) {
        selectedFahrzeug = fahrzeugId;
    }

    @Override
    public Long getSelectedFahrzeug() {
        return selectedFahrzeug;
    }
}
