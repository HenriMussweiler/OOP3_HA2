package awk.fuhrparkverwaltung.factory.impl;

import awk.fuhrparkverwaltung.factory.IFuhrparkverwaltungFactory;
import awk.fuhrparkverwaltung.usecases.IFahrzeugePflegen;
import awk.fuhrparkverwaltung.usecases.IStandortErstellen;
import awk.fuhrparkverwaltung.usecases.impl.FahrzeugePflegen;
import awk.fuhrparkverwaltung.usecases.impl.StandortErstellen;

public class FuhrparkverwaltungFactory implements IFuhrparkverwaltungFactory {
    @Override
    public IFahrzeugePflegen getFahrzeugePflegen() {
        return new FahrzeugePflegen();
    }

    @Override
    public IStandortErstellen getStandortErstellen() {
        return new StandortErstellen();
    }
}
