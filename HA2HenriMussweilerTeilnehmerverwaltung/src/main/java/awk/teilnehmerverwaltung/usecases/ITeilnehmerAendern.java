package awk.teilnehmerverwaltung.usecases;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;

public interface ITeilnehmerAendern {

    public void teilnehmerAendern(TeilnehmerTO teilnehmerTO) throws AnwendungskernException;

    public void setSelectedTeilnehmer(TeilnehmerTO teilnehmerTO);

    public TeilnehmerTO getSelectedTeilnehmer();
}
