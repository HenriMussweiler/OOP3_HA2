package awk.leihvorgang.factory;

import awk.leihvorgang.usecases.IAusleihvorgangPflegen;
import awk.leihvorgang.usecases.IAusleihvorgangSuchen;
import awk.leihvorgang.usecases.IRechnungPflegen;

public interface ILeihvorgangFactory {

    IAusleihvorgangSuchen getAusleihvorgangSuchen();

    IAusleihvorgangPflegen getAusleihvorgangPflegen();

    IRechnungPflegen getRechnungPflegen();
}
