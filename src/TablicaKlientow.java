import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TablicaKlientow extends AbstractTableModel {

    public static final int OBJECT_COL = -1;
    private static final int ID_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int NR_TELEFONU_COL = 3;
    private static final int EMAIL_COL = 4;
    private static final int ADRESID_COL = 5;

    private String[] nazwy_kolumn = {"Id ", "Imie", "Nazwisko","Nr Telefonu","Email","adresId"};
    private List<Klient> klienci;
    public TablicaKlientow(List<Klient> klienty) {
        klienci = klienty;
    }
    public int getColumnCount() {
        return nazwy_kolumn.length;
    }
    public int getRowCount() {
        return klienci.size();
    }
    public String getColumnName(int col) {
        return nazwy_kolumn[col];
    }

    public Object getValueAt(int row, int col) {
        Klient tempKlient = klienci.get(row);
        switch(col) {
            case ID_COL:
                return tempKlient.getKlient_id();
            case FIRST_NAME_COL:
                return tempKlient.getImie();
            case LAST_NAME_COL:
                return tempKlient.getNazwisko();
            case NR_TELEFONU_COL:
                return tempKlient.getPesel();
            case EMAIL_COL:
                return tempKlient.getData_urodzenia();
            case ADRESID_COL:
                return tempKlient.getHaslo();
            case OBJECT_COL:
                return tempKlient;
            default:
                return tempKlient.getNazwisko();
        }
    }
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}