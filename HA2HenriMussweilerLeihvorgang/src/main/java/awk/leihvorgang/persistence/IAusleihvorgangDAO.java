package awk.leihvorgang.persistence;

import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.entity.internal.Ausleihvorgang;

import java.util.List;

public interface IAusleihvorgangDAO {

    List<AusleihvorgangTO> getAllAusleihvorgaenge();

    Ausleihvorgang getAusleihvorgangById(Long id);

    void saveAusleihvorgang(Ausleihvorgang ausleihvorgang);

    void updateAusleihvorgang(Ausleihvorgang ausleihvorgang);

    void storniereAusleihvorgang(Ausleihvorgang ausleihvorgang);

    void abschliesseAusleihvorgang(Ausleihvorgang ausleihvorgang);

    Long maxAusleihvorgangsnummer();
}
