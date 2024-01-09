package awk.teilnehmerverwaltung.usecases.impl;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerlisteAusgeben;

import java.util.ArrayList;
import java.util.Collection;

public class TeilnehmerlisteAusgeben implements ITeilnehmerlisteAusgeben {
    @Override
    public Collection<TeilnehmerTO> teilnehmerTOListeAusgeben() throws AnwendungskernException {
        TeilnehmerManager einTeilnehmerManager = TeilnehmerManager.getTeilnehmerManager();
        Collection<TeilnehmerTO> teilnehmerTOListe = new ArrayList<TeilnehmerTO>();
        Collection<Teilnehmer> teilnehmerListe = einTeilnehmerManager.teilnehmerLiefern();

        for (Teilnehmer einTeilnehmer : teilnehmerListe)
            teilnehmerTOListe.add(einTeilnehmer.toTeilnehmerTO());

        return teilnehmerTOListe;
    }

    @Override
    public Collection<Teilnehmer> teilnehmerListeAusgeben() throws AnwendungskernException {
        TeilnehmerManager einTeilnehmerManager = TeilnehmerManager.getTeilnehmerManager();
        Collection<Teilnehmer> teilnehmerListe = einTeilnehmerManager.teilnehmerLiefern();

        return teilnehmerListe;
    }
}
