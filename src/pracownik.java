public class pracownik {
    int pracownik_id;
    String imie;
    String nazwisko;
    long pesel;
    String haslo;

    public pracownik(int pracownik_id, String imie, String nazwisko, long pesel, String haslo) {
        this.pracownik_id = pracownik_id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.haslo = haslo;
    }
    public pracownik() {
        this.pracownik_id = 1;
        this.imie = "";
        this.nazwisko = "";
        this.pesel = 123456789101L;
        this.haslo = "haslo";
    }

    public int getPracownik_id() {
        return pracownik_id;
    }

    public void setPracownik_id(int pracownik_id) {
        this.pracownik_id = pracownik_id;
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
