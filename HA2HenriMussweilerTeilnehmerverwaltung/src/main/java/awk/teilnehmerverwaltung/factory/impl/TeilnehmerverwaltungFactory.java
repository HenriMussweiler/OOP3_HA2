package awk.teilnehmerverwaltung.factory.impl;

import awk.teilnehmerverwaltung.factory.ITeilnehmerverwaltungFactory;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerPflegen;
import awk.teilnehmerverwaltung.usecases.impl.TeilnehmerPflegen;

public class TeilnehmerverwaltungFactory implements ITeilnehmerverwaltungFactory {
    @Override
    public ITeilnehmerPflegen getTeilnehmerPflegen() {
        return new TeilnehmerPflegen();
    }
}
