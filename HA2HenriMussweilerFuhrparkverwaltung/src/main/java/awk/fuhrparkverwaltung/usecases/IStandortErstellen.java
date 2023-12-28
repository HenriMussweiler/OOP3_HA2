package awk.fuhrparkverwaltung.usecases;

import awk.fuhrparkverwaltung.AnwendungskernException;

public interface IStandortErstellen {

    public void standortErstellen(String name) throws AnwendungskernException;
}
