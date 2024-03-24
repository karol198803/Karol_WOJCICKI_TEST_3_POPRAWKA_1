package zadanie2;

import java.util.Date;

public class Visits {
    private int idLekarza;
    private int idPacjenta;
    private Date dataWizyty;

    public Visits(int idLekarza, int idPacjenta, Date dataWizyty) {
        this.idLekarza = idLekarza;
        this.idPacjenta = idPacjenta;
        this.dataWizyty = dataWizyty;
    }

    public int getIdLekarza() {
        return idLekarza;
    }

    public int getIdPacjenta() {
        return idPacjenta;
    }

    public Date getDataWizyty() {
        return dataWizyty;
    }
}
