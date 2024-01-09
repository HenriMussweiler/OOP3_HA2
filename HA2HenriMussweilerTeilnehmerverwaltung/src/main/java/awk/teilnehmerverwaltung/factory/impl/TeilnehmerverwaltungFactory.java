package awk.teilnehmerverwaltung.factory.impl;

import awk.teilnehmerverwaltung.factory.ITeilnehmerverwaltungFactory;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerAendern;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerPflegen;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerSuchen;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerlisteAusgeben;
import awk.teilnehmerverwaltung.usecases.impl.TeilnehmerAendern;
import awk.teilnehmerverwaltung.usecases.impl.TeilnehmerPflegen;
import awk.teilnehmerverwaltung.usecases.impl.TeilnehmerSuchen;
import awk.teilnehmerverwaltung.usecases.impl.TeilnehmerlisteAusgeben;

public class TeilnehmerverwaltungFactory implements ITeilnehmerverwaltungFactory {
    @Override
    public ITeilnehmerPflegen getTeilnehmerPflegen() {
        return new TeilnehmerPflegen();
    }

    @Override
    public ITeilnehmerAendern getTeilnehmerAendern() {
        return new TeilnehmerAendern();
    }

    @Override
    public ITeilnehmerlisteAusgeben getTeilnehmerlisteAusgeben() {
        return new TeilnehmerlisteAusgeben();
    }

    @Override
    public ITeilnehmerSuchen getTeilnehmerSuchen() {
        return new TeilnehmerSuchen();
    }
}
