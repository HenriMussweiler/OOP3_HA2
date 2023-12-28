package awk.fuhrparkverwaltung.usecases.impl;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.internal.SharingStandort;
import awk.fuhrparkverwaltung.usecases.IStandortErstellen;

public class StandortErstellen implements IStandortErstellen {

    public StandortErstellen() {
    };

    @Override
    public void standortErstellen(String name) throws AnwendungskernException {

        FahrzeugManager fahrzeugManager = FahrzeugManager.getFahrzeugManager();

        fahrzeugManager.standortErstellen(name);
    }
}
