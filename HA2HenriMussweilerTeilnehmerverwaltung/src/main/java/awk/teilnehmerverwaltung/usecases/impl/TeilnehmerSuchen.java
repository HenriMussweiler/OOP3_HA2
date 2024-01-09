package awk.teilnehmerverwaltung.usecases.impl;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerSuchen;

import java.util.ArrayList;
import java.util.Collection;

public class TeilnehmerSuchen implements ITeilnehmerSuchen {
    @Override
    public Collection<TeilnehmerTO> teilnehmerSuchenByName(String vorname, String nachname) throws AnwendungskernException {
        TeilnehmerManager einTeilnehmerManager = TeilnehmerManager.getTeilnehmerManager();
        Collection<TeilnehmerTO> teilnehmerTOListe = new ArrayList<TeilnehmerTO>();
        Collection<Teilnehmer> teilnehmerListe = einTeilnehmerManager.teilnehmerSuchenByName(nachname, vorname);

        for (Teilnehmer einTeilnehmer : teilnehmerListe)
            teilnehmerTOListe.add(einTeilnehmer.toTeilnehmerTO());

        return teilnehmerTOListe;
    }

    @Override
    public TeilnehmerTO teilnehmerSuchenByTeilnehmerId(Long teilnehmerId) throws AnwendungskernException {
        TeilnehmerManager einTeilnehmerManager = TeilnehmerManager.getTeilnehmerManager();
        Teilnehmer einTeilnehmer = einTeilnehmerManager.teilnehmerSuchenByNr(teilnehmerId);

        if (einTeilnehmer != null) {
            return einTeilnehmer.toTeilnehmerTO();
        } else {
            // Du könntest auch eine benutzerdefinierte Exception werfen, die auftritt,
            // wenn der Teilnehmer nicht gefunden wird, anstatt null zurückzugeben.
            throw new AnwendungskernException("Teilnehmer mit ID " + teilnehmerId + " nicht gefunden.");
        }
    }

}
