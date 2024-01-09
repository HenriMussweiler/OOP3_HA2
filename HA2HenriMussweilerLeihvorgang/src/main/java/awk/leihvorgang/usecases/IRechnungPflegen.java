package awk.leihvorgang.usecases;

import awk.leihvorgang.entity.RechnungTO;

public interface IRechnungPflegen {

    public void rechnungErstellen(RechnungTO rechnungTO) throws Exception;

    public Long maxRechnungsnummer() throws Exception;
}
