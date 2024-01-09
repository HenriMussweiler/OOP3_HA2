package awk.fuhrparkverwaltung.factory;

import awk.fuhrparkverwaltung.usecases.*;

public interface IFuhrparkverwaltungFactory {

        IFahrzeugePflegen getFahrzeugePflegen();

//        IStandortErstellen getStandortErstellen();

//        IStandortlisteErstellen getStandortlisteErstellen();

        IFahrzeuglisteErstellen getFahrzeuglisteErstellen();

        IFahrzeugAendern getFahrzeugAendern();

//        IStandortFinden getStandortFinden();

        IFahrzeugSuchen getFahrzeugSuchen();
}
