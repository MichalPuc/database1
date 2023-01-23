import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KlientDAO {

    public List<Klient> getAllKlient() throws Exception {
        List<Klient> lista = new ArrayList<>();
        Statement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.createStatement();
            myRs = myStmt1.executeQuery("select * from klient");

            while (myRs.next()){
                Klient tempKlient = convertRowToKlient(myRs);
                lista.add(tempKlient);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }

    public Klient searchKlient(int id) throws Exception {
        PreparedStatement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.prepareStatement("Select * From klient where imie like ?");
            myStmt1.setInt(1, id);
            myRs = myStmt1.executeQuery();
            if (myRs.next()) {
                return convertRowToKlient(myRs);
            }
            else return null;
        } finally {
            myStmt1.close();
        }
    }

    public void addKlient(Klient klient) throws Exception {
        PreparedStatement myStmt2 = null;
        try {
            myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_klient(?,?,?,?,?,?)}");
            myStmt2.setString(1, klient.getImie());
            myStmt2.setString(2, klient.getNazwisko());
            myStmt2.setLong(3, klient.getPesel());
            myStmt2.setDate(4, klient.getData_urodzenia());
            myStmt2.setString(5, klient.getHaslo());

            myStmt2.execute();
        } finally {
            myStmt2.close();
        }
    }

    public void updateKlient(Klient klient) throws Exception {
        PreparedStatement myStmt3 = null;
        try {
            myStmt3 = Logowanie.myConn.prepareStatement("update klient set imie=?, nazwisko=?, pesel=?, data_urodzenia=?, haslo=? where klient_id=?");
            myStmt3.setString(1, klient.getImie());
            myStmt3.setString(2, klient.getNazwisko());
            myStmt3.setLong(3, klient.getPesel());
            myStmt3.setDate(4, klient.getData_urodzenia());
            myStmt3.setString(5, klient.getHaslo());
            myStmt3.setInt(6, klient.getKlient_id());

            myStmt3.executeUpdate();
        } finally {
            myStmt3.close();
        }
    }

    public void deleteKlient(int id) throws Exception {
        PreparedStatement myStmt4 = null;
        try {
            myStmt4 = Logowanie.myConn.prepareStatement("delete from klient where klient_id=?");
            myStmt4.setInt(1, id);
            myStmt4.executeUpdate();
        } finally {
            myStmt4.close();
        }
    }

    private Klient convertRowToKlient(ResultSet myRs) throws SQLException {
        int id = myRs.getInt("klient_id");
        String imie = myRs.getString("imie");
        String nazwisko = myRs.getString("nazwisko");
        long pesel =myRs.getLong("pesel");
        Date data_urodzenia = myRs.getDate("data_urodzenia");
        String haslo = myRs.getString("haslo");
        Klient tempKlient = new Klient(id, imie, nazwisko, pesel, data_urodzenia, haslo);
        return tempKlient;
    }
}