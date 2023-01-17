import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZapisyNaWydarzenieDAO {

    public List<ZapisyNaWydarzenie> getAllZapisyNaWydarzenie() throws Exception {
        List<ZapisyNaWydarzenie> lista = new ArrayList<>();
        Statement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.createStatement();
            myRs = myStmt1.executeQuery("select * from zapisy_na_wydarzenie");

            while (myRs.next()){
                ZapisyNaWydarzenie tempZapisyNaWydarzenie = convertRowToZapisyNaWydarzenie(myRs);
                lista.add(tempZapisyNaWydarzenie);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }

    public List<ZapisyNaWydarzenie> searchZapisyNaWydarzenie(int wydarzenie_id) throws Exception {
        List<ZapisyNaWydarzenie> lista = new ArrayList<>();
        PreparedStatement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.prepareStatement("Select * From zapisy_na_wydarzenie where wydarzenie_id = ?");
            myStmt1.setInt(1, wydarzenie_id);
            myRs = myStmt1.executeQuery();
            while (myRs.next()) {
                ZapisyNaWydarzenie tempZapisyNaWydarzenie = convertRowToZapisyNaWydarzenie(myRs);
                lista.add(tempZapisyNaWydarzenie);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }

    public void addZapisyNaWydarzenie(ZapisyNaWydarzenie zapisyNaWydarzenie) throws Exception {
        PreparedStatement myStmt2 = null;
        try {
            myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_zapisy_na_wydarzenie(?,?,?)}");
            myStmt2.setInt(1, zapisyNaWydarzenie.getWydarzenie_id());
            myStmt2.setInt(2, zapisyNaWydarzenie.getKlient_id());
            myStmt2.execute();
        } finally {
        myStmt2.close();
    }
}

    public void updateZapisyNaWydarzenie(ZapisyNaWydarzenie zapisyNaWydarzenie) throws Exception {
        PreparedStatement myStmt3 = null;
        try {
            myStmt3 = Logowanie.myConn.prepareStatement("update zapisy_na_wydarzenie set wydarzenie_id=?, klient_id=?, ilosc_osob=? where zapisy_na_wydarzenie_id=?");
            myStmt3.setInt(1, zapisyNaWydarzenie.getWydarzenie_id());
            myStmt3.setInt(2, zapisyNaWydarzenie.getKlient_id());
            myStmt3.executeUpdate();
        } finally {
            myStmt3.close();
        }
    }

    public void deleteZapisyNaWydarzenie(int id) throws Exception {
        PreparedStatement myStmt4 = null;
        try {
            myStmt4 = Logowanie.myConn.prepareStatement("delete from zapisy_na_wydarzenie where zapisy_na_wydarzenie_id=?");
            myStmt4.setInt(1, id);
            myStmt4.executeUpdate();
        } finally {
            myStmt4.close();
        }
    }

    private ZapisyNaWydarzenie convertRowToZapisyNaWydarzenie(ResultSet myRs) throws SQLException {
        int wydarzenie_id = myRs.getInt("wydarzenie_id");
        int klient_id = myRs.getInt("klient_id");
        return new ZapisyNaWydarzenie(wydarzenie_id, klient_id);
    }
}