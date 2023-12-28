package awk.fuhrparkverwaltung.factory;

import awk.fuhrparkverwaltung.usecases.IFahrzeugePflegen;
import awk.fuhrparkverwaltung.usecases.IStandortErstellen;

public interface IFuhrparkverwaltungFactory {

        IFahrzeugePflegen getFahrzeugePflegen();

        IStandortErstellen getStandortErstellen();
}
