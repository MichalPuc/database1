public class trener {
    int trener_id;
    String imie;
    String nazwisko;
    long pesel;
    String haslo;

    public trener(int trener_id, String imie, String nazwisko, long pesel, String haslo) {
        this.trener_id = trener_id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.haslo = haslo;
    }
    public trener() {
        this.trener_id = 0;
        this.imie = "";
        this.nazwisko = "";
        this.pesel = 0;
        this.haslo = "";
    }

    public int getTrener_id() {
        return trener_id;
    }

    public void setTrener_id(int trener_id) {
        this.trener_id = trener_id;
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

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
