package awk.leihvorgang.factory.impl;

import awk.leihvorgang.factory.ILeihvorgangFactory;
import awk.leihvorgang.usecases.IAusleihvorgangPflegen;
import awk.leihvorgang.usecases.IAusleihvorgangSuchen;
import awk.leihvorgang.usecases.IRechnungPflegen;
import awk.leihvorgang.usecases.impl.AusleihvorgangPflegen;
import awk.leihvorgang.usecases.impl.AusleihvorgangSuchen;
import awk.leihvorgang.usecases.impl.RechnungPflegen;

public class LeihvorgangFactory implements ILeihvorgangFactory {
    @Override
    public IAusleihvorgangSuchen getAusleihvorgangSuchen() {
        return new AusleihvorgangSuchen();
    }

    @Override
    public IAusleihvorgangPflegen getAusleihvorgangPflegen() {
        return new AusleihvorgangPflegen();
    }

    @Override
    public IRechnungPflegen getRechnungPflegen() {
        return new RechnungPflegen();
    }
}
