package awk.leihvorgang.usecases;

import awk.leihvorgang.entity.AusleihvorgangTO;


public interface IAusleihvorgangPflegen {

    public void ausleihvorgangErstellen(AusleihvorgangTO ausleihvorgangTO) throws Exception;

    public void ausleihvorgangAbschliessen(AusleihvorgangTO ausleihvorgangTO) throws Exception;

    public void ausleihvorgangStornieren(AusleihvorgangTO ausleihvorgangTO) throws Exception;

    public void setSelectedAusleihvorgang(AusleihvorgangTO ausleihvorgangTO);

    public AusleihvorgangTO getSelectedAusleihvorgang();

    public void updateAusleihvorgang(AusleihvorgangTO ausleihvorgangTO) throws Exception;

    public Long maxAusleihvorgangId() throws Exception;

    public void ausleihvorgangAktualisieren(AusleihvorgangTO ausleihvorgangTO) throws Exception;
}
