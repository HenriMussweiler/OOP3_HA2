package awk.leihvorgang.usecases.impl;

import awk.leihvorgang.entity.internal.Rechnung;
import awk.leihvorgang.persistence.IRechungDAO;
import awk.leihvorgang.persistence.impl.RechnungDAO;

public class RechnungManager {

    private IRechungDAO rechungDAO = new RechnungDAO();

    private static RechnungManager rechnungManager;

    public static RechnungManager getRechnungManager() {
        if (rechnungManager == null) {
            rechnungManager = new RechnungManager();
        }
        return rechnungManager;
    }

    private RechnungManager() {
    }

    public void rechnungHinzuFuegen(Rechnung rechnung) {
        try {
            this.rechungDAO.saveRechnung(rechnung);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Long naechsteRechnungsnummerErmitteln() {
        try{
            return this.rechungDAO.maxRechnungsnummer() + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public IRechungDAO getRechungDAO() {
        return this.rechungDAO;
    }
}
