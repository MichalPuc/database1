import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrenerDAO {

    public List<Trener> getAllTrener() throws Exception {
        List<Trener> lista = new ArrayList<>();
        Statement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.createStatement();
            myRs = myStmt1.executeQuery("select * from trener");

            while (myRs.next()){
                Trener tempTrener = convertRowToTrener(myRs);
                lista.add(tempTrener);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }

    public List<Trener> searchTrener(String nazwisko) throws Exception {
        List<Trener> lista = new ArrayList<>();
        PreparedStatement myStmt1 = null;
        ResultSet myRs = null;
        try {
            nazwisko += "%";
            myStmt1 = Logowanie.myConn.prepareStatement("Select * From trener where nazwisko like ?");
            myStmt1.setString(1, nazwisko);
            myRs = myStmt1.executeQuery();
            while (myRs.next()) {
                Trener tempTrener = convertRowToTrener(myRs);
                lista.add(tempTrener);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }

    public void addTrener(Trener trener) throws Exception {
        PreparedStatement myStmt2 = null;
        try {
            myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_trenera(?,?,?,?,?)}");
            myStmt2.setString(1, trener.getImie());
            myStmt2.setString(2, trener.getNazwisko());
            myStmt2.setLong(3, trener.getPesel());
            myStmt2.setString(4, trener.getHaslo());
            myStmt2.execute();
        } finally {
            myStmt2.close();
        }
    }

    public void updateTrener(Trener trener) throws Exception {
        PreparedStatement myStmt3 = null;
        try {
            myStmt3 = Logowanie.myConn.prepareStatement("update trener set imie=?, nazwisko=?, specjalizacja=?, pensja=?, haslo=? where trener_id=?");
            myStmt3.setString(1, trener.getImie());
            myStmt3.setString(2, trener.getNazwisko());
            myStmt3.setLong(3, trener.getPesel());
            myStmt3.setString(4, trener.getHaslo());
            myStmt3.setInt(5, trener.getTrener_id());
            myStmt3.executeUpdate();
        } finally {
            myStmt3.close();
        }
    }

    public void deleteTrener(int id) throws Exception {
        PreparedStatement myStmt4 = null;
        try {
            myStmt4 = Logowanie.myConn.prepareStatement("delete from trener where trener_id=?");
            myStmt4.setInt(1, id);
            myStmt4.executeUpdate();
        } finally {
            myStmt4.close();
        }
    }

    private Trener convertRowToTrener(ResultSet myRs) throws SQLException {
        int trener_id = myRs.getInt("trener_id");
        String imie = myRs.getString("imie");
        String nazwisko = myRs.getString("nazwisko");
        long pesel = myRs.getLong("pesel");
        String haslo = myRs.getString("haslo");

        return new Trener(trener_id, imie, nazwisko, pesel, haslo);
    }
}