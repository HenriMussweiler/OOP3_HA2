package awk.teilnehmerverwaltung.factory;

import awk.teilnehmerverwaltung.usecases.ITeilnehmerAendern;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerPflegen;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerSuchen;
import awk.teilnehmerverwaltung.usecases.ITeilnehmerlisteAusgeben;

public interface ITeilnehmerverwaltungFactory {

    ITeilnehmerPflegen getTeilnehmerPflegen();

    ITeilnehmerAendern getTeilnehmerAendern();

    ITeilnehmerlisteAusgeben getTeilnehmerlisteAusgeben();

    ITeilnehmerSuchen getTeilnehmerSuchen();
}
