package awk.fuhrparkverwaltung.usecases.impl;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import awk.fuhrparkverwaltung.entity.internal.SharingStandort;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FahrzeugManager extends GenericDAO {

    private static FahrzeugManager fahrzeugmanager;

    public static FahrzeugManager getFahrzeugManager() throws AnwendungskernException {
        if (fahrzeugmanager == null) {
            fahrzeugmanager = new FahrzeugManager();
        }
        return fahrzeugmanager;
    }

    private FahrzeugManager() throws AnwendungskernException {
        super(Fahrzeug.class);
    }

    public void fahrzeugHinzufuegen (Fahrzeug fahrzeug) throws AnwendungskernException {
        super.save(fahrzeug);
    }

    public void fahrzeugEntfernen (Fahrzeug fahrzeug) throws AnwendungskernException {
        super.delete(fahrzeug.getFahrzeugId(), Fahrzeug.class);
    }

    public void fahrzeugAendern (Fahrzeug fahrzeug) throws AnwendungskernException {
        super.update(fahrzeug);
    }

    public boolean istFahrzeugvorhanden(Fahrzeug fahrzeug) throws AnwendungskernException {
        if (super.find(fahrzeug.getFahrzeugId()) != null)
            return false;
        else return true;
    }

    public Collection<Fahrzeug> fahrzeugSuchenByModell (
            String modell) throws AnwendungskernException {

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("modell", modell);
        return super.findListResult(Fahrzeug.FIND_FAHRZEUG_BY_MODELL, parameters);
    }

    public Fahrzeug fahrzeugSuchenByNr (long fahrzeugId) throws AnwendungskernException {

        return (Fahrzeug) super.find(fahrzeugId);
    }

    public Collection<Fahrzeug> fahrzeugeLiefern() throws AnwendungskernException {
    	return super.findAll();
    }

    public void standortErstellen(String standort) throws AnwendungskernException {
        super.save(new SharingStandort(standort));
    }

}
