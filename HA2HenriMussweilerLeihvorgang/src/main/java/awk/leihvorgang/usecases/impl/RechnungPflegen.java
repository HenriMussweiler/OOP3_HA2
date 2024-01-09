package awk.leihvorgang.usecases.impl;

import awk.leihvorgang.entity.RechnungTO;
import awk.leihvorgang.entity.internal.Rechnung;
import awk.leihvorgang.usecases.IRechnungPflegen;

public class RechnungPflegen implements IRechnungPflegen {
    @Override
    public void rechnungErstellen(RechnungTO rechnungTO) throws Exception {
        RechnungManager rechnungManager = RechnungManager.getRechnungManager();

        Rechnung rechnung = new Rechnung();
        rechnungTO.setRechnungId(rechnungManager.getRechungDAO().maxRechnungsnummer() + 1);
        rechnung.setTeilnehmerId(rechnungTO.getTeilnehmerId());
        rechnung.setRechnungsdatum(rechnungTO.getRechnungsdatum());
        rechnung.setGesamtsumme(rechnungTO.getGesamtsumme());


        rechnungManager.getRechungDAO().saveRechnung(rechnung);
    }

    @Override
    public Long maxRechnungsnummer() throws Exception {
        RechnungManager rechnungManager = RechnungManager.getRechnungManager();
        Long maxRechnungsnummer = rechnungManager.getRechungDAO().maxRechnungsnummer();
        if (maxRechnungsnummer == null) {
            return 0L;
        }
        return maxRechnungsnummer;
    }
}
