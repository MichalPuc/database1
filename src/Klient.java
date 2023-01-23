import java.sql.Date;
public class Klient {
    int klient_id;
    String imie;
    String nazwisko;
    long pesel;
    String data_urodzenia;
    String haslo;

    public Klient(int klient_id, String imie, String nazwisko, long pesel, String data_urodzenia, String haslo) {
        this.klient_id = klient_id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.data_urodzenia = data_urodzenia;
        this.haslo = haslo;
    }
    public Klient() {
        this.klient_id = 0;
        this.imie = "";
        this.nazwisko = "";
        this.pesel = 0;
        this.data_urodzenia = null;
        this.haslo = "";
    }
    public int getKlient_id() {
        return klient_id;
    }

    public void setKlient_id(int klient_id) {
        this.klient_id = klient_id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public String getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(String data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
