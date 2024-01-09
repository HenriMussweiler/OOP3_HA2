package awk.teilnehmerverwaltung.usecases;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;

import java.util.Collection;

public interface ITeilnehmerlisteAusgeben {

    public Collection<Teilnehmer> teilnehmerListeAusgeben()
            throws AnwendungskernException;

    public Collection<TeilnehmerTO> teilnehmerTOListeAusgeben() throws AnwendungskernException;
}
