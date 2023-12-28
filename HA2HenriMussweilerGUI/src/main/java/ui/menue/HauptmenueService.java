package ui.menue;

import awk.fuhrparkverwaltung.factory.impl.FuhrparkverwaltungFactory;
import awk.fuhrparkverwaltung.factory.IFuhrparkverwaltungFactory;
import awk.fuhrparkverwaltung.usecases.*;

import awk.teilnehmerverwaltung.factory.impl.TeilnehmerverwaltungFactory;
import awk.teilnehmerverwaltung.factory.ITeilnehmerverwaltungFactory;
import awk.teilnehmerverwaltung.usecases.*;


public class HauptmenueService {
    private static IFuhrparkverwaltungFactory fuhrparkverwaltungFactory;
    private static ITeilnehmerverwaltungFactory teilnehmerverwaltungFactory;

    private static IFahrzeugePflegen fahrzeugePflegen;
    private static IStandortErstellen standortErstellen;

    private static ITeilnehmerPflegen teilnehmerPflegen;

    public HauptmenueService() {
        // 1. Implementierung der Komponenten des Anwendungskerns aus Factory beziehen
        fuhrparkverwaltungFactory = new FuhrparkverwaltungFactory();
        teilnehmerverwaltungFactory = new TeilnehmerverwaltungFactory();

        // 2. Implementierung der benoetigten Use Cases beziehen
        fahrzeugePflegen = fuhrparkverwaltungFactory.getFahrzeugePflegen();
        standortErstellen = fuhrparkverwaltungFactory.getStandortErstellen();


        teilnehmerPflegen = teilnehmerverwaltungFactory.getTeilnehmerPflegen();
    }

    public static ITeilnehmerPflegen getTeilnehmerPflegen() {
        return teilnehmerPflegen;
    }

    public static IFahrzeugePflegen getFahrzeugePflegen() {
        return fahrzeugePflegen;
    }

    public static IStandortErstellen getStandortErstellen() {
        return standortErstellen;
    }
}
