package awk.teilnehmerverwaltung.usecases.impl;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerAendern;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;

public class TeilnehmerAendern implements ITeilnehmerAendern {

    private static TeilnehmerTO selectedTeilnehmer;

    @Override
    public void teilnehmerAendern(TeilnehmerTO teilnehmerTO) throws AnwendungskernException {
        TeilnehmerTO einTeilnehmer = teilnehmerTO;
        Teilnehmer teilnehmer = teilnehmerTO.toTeilnehmer();
        TeilnehmerManager teilnehmerManager = TeilnehmerManager.getTeilnehmerManager();

        teilnehmerManager.update(teilnehmer);
    }

    @Override
    public void setSelectedTeilnehmer(TeilnehmerTO teilnehmerTO) {
        selectedTeilnehmer = teilnehmerTO;
    }

    @Override
    public TeilnehmerTO getSelectedTeilnehmer() {
        return selectedTeilnehmer;
    }
}
