public class ZapisyNaWydarzenie {
    int klient_id;
    int wydarzenie_id;

    public ZapisyNaWydarzenie(int klient_id, int wydarzenie_id) {
        this.klient_id = klient_id;
        this.wydarzenie_id = wydarzenie_id;
    }
    public ZapisyNaWydarzenie() {
        this.klient_id = 0;
        this.wydarzenie_id = 0;
    }
    public int getKlient_id() {
        return klient_id;
    }

    public void setKlient_id(int klient_id) {
        this.klient_id = klient_id;
    }

    public int getWydarzenie_id() {
        return wydarzenie_id;
    }

    public void setWydarzenie_id(int wydarzenie_id) {
        this.wydarzenie_id = wydarzenie_id;
    }
}
