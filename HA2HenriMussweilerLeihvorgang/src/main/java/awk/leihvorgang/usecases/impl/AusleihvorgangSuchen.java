package awk.leihvorgang.usecases.impl;

import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.entity.internal.Ausleihvorgang;
import awk.leihvorgang.usecases.IAusleihvorgangSuchen;

import java.util.ArrayList;
import java.util.Collection;

public class AusleihvorgangSuchen implements IAusleihvorgangSuchen {
    @Override
    public Collection<AusleihvorgangTO> ausleihvorgangSuchenByTeilnehmerId(Long teilnehmerId) {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        Collection<AusleihvorgangTO> ausleihvorgaengeTO = ausleihvorgangManager.liefereAusleihvorgaenge();
        if (ausleihvorgaengeTO == null || ausleihvorgaengeTO.isEmpty()) {
            return new ArrayList<AusleihvorgangTO>();
        } else {
            Collection<AusleihvorgangTO> ausleihvorgaengeByTeilnehmerId = new ArrayList<AusleihvorgangTO>();

            for (AusleihvorgangTO ausleihvorgang : ausleihvorgaengeTO) {
                if (ausleihvorgang.getTeilnehmerId().equals(teilnehmerId)) {
                    ausleihvorgaengeByTeilnehmerId.add(ausleihvorgang);
                }
            }

            return ausleihvorgaengeByTeilnehmerId;
        }
    }

    @Override
    public Collection<AusleihvorgangTO> liefereAlleAusleihvorgaenge() {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        Collection<AusleihvorgangTO> ausleihvorgaenge = ausleihvorgangManager.liefereAusleihvorgaenge();

        if (ausleihvorgaenge == null || ausleihvorgaenge.isEmpty()) {
            return new ArrayList<AusleihvorgangTO>();
        } else {
            return ausleihvorgaenge;
        }
    }

    @Override
    public Collection<AusleihvorgangTO> ausleihvorgangSuchenByFahrzeugId(Long fahrzeugId) {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        Collection<AusleihvorgangTO> ausleihvorgaengeTO = ausleihvorgangManager.liefereAusleihvorgaenge();
        if (ausleihvorgaengeTO == null || ausleihvorgaengeTO.isEmpty()) {
            return new ArrayList<AusleihvorgangTO>();
        } else {
            Collection<AusleihvorgangTO> ausleihvorgaengeByFahrzeugId = new ArrayList<AusleihvorgangTO>();

            for (AusleihvorgangTO ausleihvorgang : ausleihvorgaengeTO) {
                if (ausleihvorgang.getFahrzeugId().equals(fahrzeugId)) {
                    ausleihvorgaengeByFahrzeugId.add(ausleihvorgang);
                }
            }

            return ausleihvorgaengeByFahrzeugId;
        }
    }

    @Override
    public AusleihvorgangTO ausleihvorgangSuchenByAusleihvorgangId(Long ausleihvorgangId) {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        Collection<AusleihvorgangTO> ausleihvorgaengeTO = ausleihvorgangManager.liefereAusleihvorgaenge();
        if (ausleihvorgaengeTO.isEmpty()) {
            return new AusleihvorgangTO();
        } else {
            for (AusleihvorgangTO ausleihvorgang : ausleihvorgaengeTO) {
                if (ausleihvorgang.getAusleihvorgangId().equals(ausleihvorgangId)) {
                    return ausleihvorgang;
                }
            }
            return null;
        }
    }
}
