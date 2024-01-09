package ui.menue;

import awk.leihvorgang.factory.ILeihvorgangFactory;
import awk.leihvorgang.factory.impl.LeihvorgangFactory;
import awk.fuhrparkverwaltung.factory.impl.FuhrparkverwaltungFactory;
import awk.fuhrparkverwaltung.factory.IFuhrparkverwaltungFactory;
import awk.fuhrparkverwaltung.usecases.*;
import awk.leihvorgang.entity.*;
import awk.leihvorgang.usecases.IAusleihvorgangPflegen;
import awk.leihvorgang.usecases.IAusleihvorgangSuchen;
import awk.leihvorgang.usecases.IRechnungPflegen;
import awk.teilnehmerverwaltung.factory.impl.TeilnehmerverwaltungFactory;
import awk.teilnehmerverwaltung.factory.ITeilnehmerverwaltungFactory;
import awk.teilnehmerverwaltung.usecases.*;


public class HauptmenueService {
    private static IFuhrparkverwaltungFactory fuhrparkverwaltungFactory;
    private static ITeilnehmerverwaltungFactory teilnehmerverwaltungFactory;
    private static ILeihvorgangFactory leihvorgangFactory;

    private static IFahrzeugePflegen fahrzeugePflegen;
    private static IFahrzeuglisteErstellen fahrzeuglisteErstellen;
    private static IFahrzeugAendern fahrzeugAendern;
    private static IFahrzeugSuchen fahrzeugSuchen;
//    private static IStandortErstellen standortErstellen;
//    private static IStandortlisteErstellen standortlisteErstellen;
//    private static IStandortFinden standortFinden;

    private static ITeilnehmerPflegen teilnehmerPflegen;
    private static ITeilnehmerAendern teilnehmerAendern;
    private static ITeilnehmerlisteAusgeben teilnehmerlisteAusgeben;
    private static ITeilnehmerSuchen teilnehmerSuchen;

    private static IAusleihvorgangSuchen ausleihvorgangSuchen;
    private static IAusleihvorgangPflegen ausleihvorgangPflegen;

    private static IRechnungPflegen rechnungPflegen;

    public HauptmenueService() {
        // 1. Implementierung der Komponenten des Anwendungskerns aus Factory beziehen
        fuhrparkverwaltungFactory = new FuhrparkverwaltungFactory();
        teilnehmerverwaltungFactory = new TeilnehmerverwaltungFactory();
        leihvorgangFactory = new LeihvorgangFactory();
    }

    public static ITeilnehmerPflegen getTeilnehmerPflegen() {
        if (teilnehmerPflegen == null) {
            teilnehmerPflegen = teilnehmerverwaltungFactory.getTeilnehmerPflegen();
        }
        return teilnehmerPflegen;
    }

    public static IFahrzeugePflegen getFahrzeugePflegen() {
        if (fahrzeugePflegen == null) {
            fahrzeugePflegen = fuhrparkverwaltungFactory.getFahrzeugePflegen();
        }
        return fahrzeugePflegen;
    }

//    public static IStandortErstellen getStandortErstellen() {
//        if (standortErstellen == null) {
//            standortErstellen = fuhrparkverwaltungFactory.getStandortErstellen();
//        }
//        return standortErstellen;
//    }

//    public static IStandortlisteErstellen getStandortlisteErstellen() {
//        if (standortlisteErstellen == null) {
//            standortlisteErstellen = fuhrparkverwaltungFactory.getStandortlisteErstellen();
//        }
//        return standortlisteErstellen;
//    }

    public static IFahrzeuglisteErstellen getFahrzeuglisteErstellen() {
        if (fahrzeuglisteErstellen == null) {
            fahrzeuglisteErstellen = fuhrparkverwaltungFactory.getFahrzeuglisteErstellen();
        }
        return fahrzeuglisteErstellen;
    }

    public static IFahrzeugAendern getFahrzeugAendern() {
        if (fahrzeugAendern == null) {
            fahrzeugAendern = fuhrparkverwaltungFactory.getFahrzeugAendern();
        }
        return fahrzeugAendern;
    }

    public static ITeilnehmerAendern getTeilnehmerAendern() {
        if (teilnehmerAendern == null) {
            teilnehmerAendern = teilnehmerverwaltungFactory.getTeilnehmerAendern();
        }
        return teilnehmerAendern;
    }

    public static ITeilnehmerlisteAusgeben getTeilnehmerlisteAusgeben() {
        if (teilnehmerlisteAusgeben == null) {
            teilnehmerlisteAusgeben = teilnehmerverwaltungFactory.getTeilnehmerlisteAusgeben();
        }
        return teilnehmerlisteAusgeben;
    }

    public static ITeilnehmerSuchen getTeilnehmerSuchen() {
        if (teilnehmerSuchen == null) {
            teilnehmerSuchen = teilnehmerverwaltungFactory.getTeilnehmerSuchen();
        }
        return teilnehmerSuchen;
    }

//    public static IStandortFinden getStandortFinden() {
//        if (standortFinden == null) {
//            standortFinden = fuhrparkverwaltungFactory.getStandortFinden();
//        }
//        return standortFinden;
//    }

    public static IFahrzeugSuchen getFahrzeugSuchen() {
        if (fahrzeugSuchen == null) {
            fahrzeugSuchen = fuhrparkverwaltungFactory.getFahrzeugSuchen();
        }
        return fahrzeugSuchen;
    }

    public static IAusleihvorgangSuchen getAusleihvorgangSuchen() {
        if (ausleihvorgangSuchen == null) {
            ausleihvorgangSuchen = leihvorgangFactory.getAusleihvorgangSuchen();
        }
        return ausleihvorgangSuchen;
    }

    public static IAusleihvorgangPflegen getAusleihvorgangPflegen() {
        if (ausleihvorgangPflegen == null) {
            ausleihvorgangPflegen = leihvorgangFactory.getAusleihvorgangPflegen();
        }
        return ausleihvorgangPflegen;
    }

    public static IRechnungPflegen getRechnungPflegen() {
        if (rechnungPflegen == null) {
            rechnungPflegen = leihvorgangFactory.getRechnungPflegen();
        }
        return rechnungPflegen;
    }
}
