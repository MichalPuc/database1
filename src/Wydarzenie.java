import java.sql.Date;
public class Wydarzenie {
    int wydarzenie_id;
    String rodzaj;
    String data;
    int trener_id;

    public Wydarzenie(int wydarzenie_id, String rodzaj, String data, int trener_id) {
        this.wydarzenie_id = wydarzenie_id;
        this.rodzaj = rodzaj;
        this.data = data;
        this.trener_id = trener_id;
    }
    public Wydarzenie() {
        this.wydarzenie_id = 0;
        this.rodzaj = null;
        this.data = null;
        this.trener_id = 0;
    }

    public int getWydarzenie_id() {
        return wydarzenie_id;
    }

    public void setWydarzenie_id(int wydarzenie_id) {
        this.wydarzenie_id = wydarzenie_id;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTrener_id() {
        return trener_id;
    }

    public void setTrener_id(int trener_id) {
        this.trener_id = trener_id;
    }
}
