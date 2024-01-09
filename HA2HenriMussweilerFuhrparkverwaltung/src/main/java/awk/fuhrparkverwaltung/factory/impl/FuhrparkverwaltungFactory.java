package awk.fuhrparkverwaltung.factory.impl;

import awk.fuhrparkverwaltung.factory.IFuhrparkverwaltungFactory;
import awk.fuhrparkverwaltung.usecases.*;
import awk.fuhrparkverwaltung.usecases.impl.*;

public class FuhrparkverwaltungFactory implements IFuhrparkverwaltungFactory {
    @Override
    public IFahrzeugePflegen getFahrzeugePflegen() {
        return new FahrzeugePflegen();
    }

//    @Override
//    public IStandortErstellen getStandortErstellen() {
//        return new StandortErstellen();
//    }

//    @Override
//    public IStandortlisteErstellen getStandortlisteErstellen() {
//        return new StandortlisteErstellen();
//    }

    @Override
    public IFahrzeuglisteErstellen getFahrzeuglisteErstellen() {
        return new FahrzeuglisteErstellen();
    }

    @Override
    public IFahrzeugAendern getFahrzeugAendern() {
        return new FahrzeugAendern();
    }

//    @Override
//    public IStandortFinden getStandortFinden() {
//        return new StandortFinden();
//    }

    @Override
    public IFahrzeugSuchen getFahrzeugSuchen() {
        return new FahrzeugSuchen();
    }
}
