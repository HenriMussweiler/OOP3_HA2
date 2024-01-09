package awk.leihvorgang.usecases;

import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.entity.internal.Ausleihvorgang;

import java.util.Collection;

public interface IAusleihvorgangSuchen {

    public Collection<AusleihvorgangTO> ausleihvorgangSuchenByTeilnehmerId (Long teilnehmerId);

    public Collection<AusleihvorgangTO> liefereAlleAusleihvorgaenge();

    public Collection<AusleihvorgangTO> ausleihvorgangSuchenByFahrzeugId (Long fahrzeugId);

    public AusleihvorgangTO ausleihvorgangSuchenByAusleihvorgangId (Long ausleihvorgangId);
}
