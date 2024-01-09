package awk.teilnehmerverwaltung.usecases;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;

import java.util.Collection;

public interface ITeilnehmerSuchen {

    public Collection<TeilnehmerTO> teilnehmerSuchenByName (String vorname, String nachname) throws AnwendungskernException;

    public TeilnehmerTO teilnehmerSuchenByTeilnehmerId (Long teilnehmerId) throws AnwendungskernException;
}
