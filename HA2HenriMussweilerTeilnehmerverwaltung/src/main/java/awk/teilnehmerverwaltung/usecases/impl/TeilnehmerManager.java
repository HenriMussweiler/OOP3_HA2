package awk.teilnehmerverwaltung.usecases.impl;

import awk.teilnehmerverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TeilnehmerManager extends GenericDAO {

    private static TeilnehmerManager teilnehmerManager;

    public static TeilnehmerManager getTeilnehmerManager() throws AnwendungskernException {
        if (teilnehmerManager == null) {
            teilnehmerManager = new TeilnehmerManager();
        }
        return teilnehmerManager;
    }

    private TeilnehmerManager() throws AnwendungskernException {
        super(Teilnehmer.class);
    }

    public void teilnehmerAnlegen(Teilnehmer teilnehmer) throws AnwendungskernException {
        this.save(teilnehmer);
    }

    public void teilnehmerEntfernen(Teilnehmer teilnehmer) throws AnwendungskernException {
        this.delete(teilnehmer.getTeilnehmerId(), Teilnehmer.class);
    }

    public void teilnehmerAendern(Teilnehmer teilnehmer) throws AnwendungskernException {
        this.update(teilnehmer);
    }

    public boolean istTeilnehmervorhanden(Teilnehmer teilnehmer) throws AnwendungskernException {
        if (this.find(teilnehmer.getTeilnehmerId()) != null) {
            return false;
        } else {
            return true;
        }
    }

    public Collection<Teilnehmer> teilnehmerSuchenByName(String name, String vorname) throws AnwendungskernException {

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        parameters.put("vorname", vorname);
        return this.findListResult(Teilnehmer.FIND_TEILNEHMER_BY_NAME, parameters);
    }

    public Teilnehmer teilnehmerSuchenByNr(Long teilnehmerId) throws AnwendungskernException {
        return (Teilnehmer) this.find(teilnehmerId);
    }

    public Collection<Teilnehmer> teilnehmerLiefern() throws AnwendungskernException {
        return super.findAll();
    }

}
