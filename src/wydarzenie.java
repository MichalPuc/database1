import java.sql.Date;
public class wydarzenie {
    int wydarzenie_id;
    boolean rodzaj;
    Date data;
    int trener_id;

    public wydarzenie(int wydarzenie_id, boolean rodzaj, Date data, int trener_id) {
        this.wydarzenie_id = wydarzenie_id;
        this.rodzaj = rodzaj;
        this.data = data;
        this.trener_id = trener_id;
    }
    public wydarzenie() {
        this.wydarzenie_id = 0;
        this.rodzaj = false;
        this.data = null;
        this.trener_id = 0;
    }

    public int getWydarzenie_id() {
        return wydarzenie_id;
    }

    public void setWydarzenie_id(int wydarzenie_id) {
        this.wydarzenie_id = wydarzenie_id;
    }

    public boolean isRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(boolean rodzaj) {
        this.rodzaj = rodzaj;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getTrener_id() {
        return trener_id;
    }

    public void setTrener_id(int trener_id) {
        this.trener_id = trener_id;
    }
}
