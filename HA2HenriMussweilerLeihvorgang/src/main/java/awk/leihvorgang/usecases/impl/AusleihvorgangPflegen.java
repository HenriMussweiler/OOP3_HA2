package awk.leihvorgang.usecases.impl;

import awk.leihvorgang.entity.AusleihvorgangTO;
import awk.leihvorgang.entity.internal.Ausleihvorgang;
import awk.leihvorgang.usecases.IAusleihvorgangPflegen;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AusleihvorgangPflegen implements IAusleihvorgangPflegen {

    private static AusleihvorgangTO selectedAusleihvorgang;

    @Override
    public void ausleihvorgangErstellen(AusleihvorgangTO ausleihvorgangTO) throws Exception {

        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        Ausleihvorgang ausleihvorgang = new Ausleihvorgang();
        ausleihvorgang.setAusleihvorgangId(ausleihvorgangTO.getAusleihvorgangId());
        ausleihvorgang.setTeilnehmerId(ausleihvorgangTO.getTeilnehmerId());
        ausleihvorgang.setFahrzeugId(ausleihvorgangTO.getFahrzeugId());
        ausleihvorgang.setStartdatum(LocalDateTime.parse(ausleihvorgangTO.getStartdatum()));
        ausleihvorgang.setEnddatum(LocalDateTime.parse(ausleihvorgangTO.getEnddatum()));
        ausleihvorgang.setAbgeschlossen(ausleihvorgangTO.getAbgeschlossen());
        ausleihvorgang.setStorniert(ausleihvorgangTO.getStorniert());
        ausleihvorgang.setGefahreneKilometer(ausleihvorgangTO.getGefahreneKilometer());
        ausleihvorgang.setRechnungId(ausleihvorgangTO.getRechnungId());

        ausleihvorgangManager.ausleihvorgangHinzufuegen(ausleihvorgang);
    }

    @Override
    public void ausleihvorgangAbschliessen(AusleihvorgangTO ausleihvorgangTO) throws Exception {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        Ausleihvorgang ausleihvorgang = ausleihvorgangManager.getAusleihvorgangDAO().getAusleihvorgangById(ausleihvorgangTO.getAusleihvorgangId());

        if (ausleihvorgang != null) {
            try {
                // Update the Ausleihvorgang object's properties with the values from the AusleihvorgangTO object
                ausleihvorgang.setTeilnehmerId(ausleihvorgangTO.getTeilnehmerId());
                ausleihvorgang.setFahrzeugId(ausleihvorgangTO.getFahrzeugId());

                ausleihvorgang.setStartdatum(LocalDateTime.parse(ausleihvorgangTO.getStartdatum()));
                ausleihvorgang.setEnddatum(LocalDateTime.parse(ausleihvorgangTO.getEnddatum()));

                ausleihvorgang.setGefahreneKilometer(ausleihvorgangTO.getGefahreneKilometer());

                // Set the abgeschlossen status of the Ausleihvorgang object to true
                ausleihvorgang.setAbgeschlossen("Y");

                // Call a method from AusleihvorgangManager to update the Ausleihvorgang object
                ausleihvorgangManager.getAusleihvorgangDAO().updateAusleihvorgang(ausleihvorgang);
            } catch (DateTimeParseException e) {
                // Handle das Parsing-Problem
                e.printStackTrace();
                throw new Exception("Fehler beim Parsen des Datums");
            }
        }
    }



    @Override
    public void ausleihvorgangStornieren(AusleihvorgangTO ausleihvorgangTO) throws Exception {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();

        Ausleihvorgang ausleihvorgang = ausleihvorgangManager.getAusleihvorgangDAO().getAusleihvorgangById(ausleihvorgangTO.getAusleihvorgangId());

        if (ausleihvorgang != null) {
            // Update the Ausleihvorgang object's properties with the values from the AusleihvorgangTO object
            ausleihvorgang.setTeilnehmerId(ausleihvorgangTO.getTeilnehmerId());
            ausleihvorgang.setFahrzeugId(ausleihvorgangTO.getFahrzeugId());
            ausleihvorgang.setStartdatum(LocalDateTime.parse(ausleihvorgangTO.getStartdatum()));
            ausleihvorgang.setEnddatum(LocalDateTime.parse(ausleihvorgangTO.getEnddatum()));
            ausleihvorgang.setGefahreneKilometer(ausleihvorgangTO.getGefahreneKilometer());

            // Set the storniert status of the Ausleihvorgang object to true
            ausleihvorgang.setStorniert("Y");

            // Call a method from AusleihvorgangManager to update the Ausleihvorgang object
            ausleihvorgangManager.getAusleihvorgangDAO().updateAusleihvorgang(ausleihvorgang);
        }
    }

    @Override
    public void setSelectedAusleihvorgang(AusleihvorgangTO ausleihvorgangTO) {
        selectedAusleihvorgang = ausleihvorgangTO;
    }

    @Override
    public AusleihvorgangTO getSelectedAusleihvorgang() {
        return selectedAusleihvorgang;
    }

    @Override
    public void updateAusleihvorgang(AusleihvorgangTO ausleihvorgangTO) {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        Ausleihvorgang ausleihvorgang = ausleihvorgangManager.getAusleihvorgangDAO().getAusleihvorgangById(ausleihvorgangTO.getAusleihvorgangId());

        if (ausleihvorgang != null) {
            // Update the Ausleihvorgang object's properties with the values from the AusleihvorgangTO object
            ausleihvorgang.setTeilnehmerId(ausleihvorgangTO.getTeilnehmerId());
            ausleihvorgang.setFahrzeugId(ausleihvorgangTO.getFahrzeugId());
            ausleihvorgang.setStartdatum(LocalDateTime.parse(ausleihvorgangTO.getStartdatum()));
            ausleihvorgang.setEnddatum(LocalDateTime.parse(ausleihvorgangTO.getEnddatum()));
            ausleihvorgang.setGefahreneKilometer(ausleihvorgangTO.getGefahreneKilometer());

            // Call a method from AusleihvorgangManager to update the Ausleihvorgang object
            ausleihvorgangManager.getAusleihvorgangDAO().updateAusleihvorgang(ausleihvorgang);
        }
    }

    @Override
    public Long maxAusleihvorgangId() throws Exception {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        return ausleihvorgangManager.getAusleihvorgangDAO().maxAusleihvorgangsnummer();
    }

    @Override
    public void ausleihvorgangAktualisieren(AusleihvorgangTO ausleihvorgangTO) throws Exception {
        AusleihvorgangManager ausleihvorgangManager = AusleihvorgangManager.getAusleihvorgangManager();
        Ausleihvorgang ausleihvorgang = ausleihvorgangManager.getAusleihvorgangDAO().getAusleihvorgangById(ausleihvorgangTO.getAusleihvorgangId());

        if (ausleihvorgang != null) {
            // Update the Ausleihvorgang object's properties with the values from the AusleihvorgangTO object
            ausleihvorgang.setTeilnehmerId(ausleihvorgangTO.getTeilnehmerId());
            ausleihvorgang.setFahrzeugId(ausleihvorgangTO.getFahrzeugId());
            ausleihvorgang.setStartdatum(LocalDateTime.parse(ausleihvorgangTO.getStartdatum()));
            ausleihvorgang.setEnddatum(LocalDateTime.parse(ausleihvorgangTO.getEnddatum()));
            ausleihvorgang.setGefahreneKilometer(ausleihvorgangTO.getGefahreneKilometer());
            ausleihvorgang.setRechnungId(ausleihvorgangTO.getRechnungId());

            // Call a method from AusleihvorgangManager to update the Ausleihvorgang object
            ausleihvorgangManager.getAusleihvorgangDAO().updateAusleihvorgang(ausleihvorgang);
        }
    }
}