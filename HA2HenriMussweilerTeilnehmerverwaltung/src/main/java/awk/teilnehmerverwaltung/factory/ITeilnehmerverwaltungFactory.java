package awk.teilnehmerverwaltung.factory;

import awk.teilnehmerverwaltung.usecases.ITeilnehmerPflegen;

public interface ITeilnehmerverwaltungFactory {

    ITeilnehmerPflegen getTeilnehmerPflegen();
}
