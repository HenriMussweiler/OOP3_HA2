package awk.leihvorgang.usecases.impl;

import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.persistence.IAusleihvorgangDAO;
import awk.leihvorgang.entity.internal.Ausleihvorgang;
import awk.leihvorgang.persistence.impl.AusleihvorgangDAO;

import java.util.Collection;

public class AusleihvorgangManager {

    private IAusleihvorgangDAO ausleihvorgangDAO = new AusleihvorgangDAO();

    private static AusleihvorgangManager ausleihvorgangManager;

    public static AusleihvorgangManager getAusleihvorgangManager() {
        if (ausleihvorgangManager == null) {
            ausleihvorgangManager = new AusleihvorgangManager();
        }
        return ausleihvorgangManager;
    }

    private AusleihvorgangManager() {
    }

    public void ausleihvorgangHinzufuegen(Ausleihvorgang ausleihvorgang) {
        try {
            this.ausleihvorgangDAO.saveAusleihvorgang(ausleihvorgang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ausleihvorgangStornieren(Ausleihvorgang ausleihvorgang) {
        try {
            this.ausleihvorgangDAO.storniereAusleihvorgang(ausleihvorgang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ausleihvorgangAbschliessen(Ausleihvorgang ausleihvorgang) {
        try {
            this.ausleihvorgangDAO.abschliesseAusleihvorgang(ausleihvorgang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<AusleihvorgangTO> liefereAusleihvorgaenge() {
        try {
            return this.ausleihvorgangDAO.getAllAusleihvorgaenge();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public IAusleihvorgangDAO getAusleihvorgangDAO() {
        return this.ausleihvorgangDAO;
    }
}