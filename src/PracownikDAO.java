import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PracownikDAO {

    public List<pracownik> getAllPracownik() throws Exception {
        List<pracownik> lista = new ArrayList<>();
        Statement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.createStatement();
            myRs = myStmt1.executeQuery("select * from pracownik");

            while (myRs.next()){
                pracownik tempPracownik = convertRowToPracownik(myRs);
                lista.add(tempPracownik);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }


    public List<pracownik> searchPracownik(String imie) throws Exception {
        List<pracownik> lista = new ArrayList<>();
        PreparedStatement myStmt1 = null;
        ResultSet myRs = null;
        try {
            imie += "%";
            myStmt1 = Logowanie.myConn.prepareStatement("Select * From pracownik where imie like ?");
            myStmt1.setString(1, imie);
            myRs = myStmt1.executeQuery();
            while (myRs.next()) {
                pracownik tempPracownik = convertRowToPracownik(myRs);
                lista.add(tempPracownik);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }
    public void addPracownik(pracownik pracownik) throws Exception {
        PreparedStatement myStmt2 = null;
        try {
            myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_pracownika(?,?,?,?,?)}");
            myStmt2.setString(1, pracownik.getImie());
            myStmt2.setString(2, pracownik.getNazwisko());
            myStmt2.setLong(3, pracownik.getPesel());
            myStmt2.setString(4, pracownik.getHaslo());
            myStmt2.execute();
        } finally {
            myStmt2.close();
        }
    }

    public void updatePracownik(pracownik pracownik) throws Exception {
        PreparedStatement myStmt3 = null;
        try {
            myStmt3 = Logowanie.myConn.prepareStatement("update pracownik set imie=?, nazwisko=?, stanowisko=?, pensja=?, haslo=? where pracownik_id=?");
            myStmt3.setString(1, pracownik.getImie());
            myStmt3.setString(2, pracownik.getNazwisko());
            myStmt3.setLong(3, pracownik.getPesel());
            myStmt3.setString(4, pracownik.getHaslo());
            myStmt3.setInt(5, pracownik.getPracownik_id());
            myStmt3.executeUpdate();
        } finally {
            myStmt3.close();
        }
    }

    public void deletePracownik(int id) throws Exception {
        PreparedStatement myStmt4 = null;
        try {
            myStmt4 = Logowanie.myConn.prepareStatement("delete from pracownik where pracownik_id=?");
            myStmt4.setInt(1, id);
            myStmt4.executeUpdate();
        } finally {
            myStmt4.close();
        }
    }

    private pracownik convertRowToPracownik(ResultSet myRs) throws SQLException {
        int id = myRs.getInt("pracownik_id");
        String imie = myRs.getString("imie");
        String nazwisko = myRs.getString("nazwisko");
        long pesel = myRs.getLong("pesel");
        String haslo = myRs.getString("haslo");
        return new pracownik(id, imie, nazwisko, pesel, haslo);
    }
}