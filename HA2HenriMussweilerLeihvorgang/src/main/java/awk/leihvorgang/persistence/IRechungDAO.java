package awk.leihvorgang.persistence;

import awk.leihvorgang.entity.internal.Rechnung;

public interface IRechungDAO {

    void saveRechnung(Rechnung rechnung);

    Long maxRechnungsnummer();
}
