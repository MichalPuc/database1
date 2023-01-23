import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WydarzenieDAO {

    public List<Wydarzenie> getAllWydarzenie() throws Exception {
        List<Wydarzenie> lista = new ArrayList<>();
        Statement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.createStatement();
            myRs = myStmt1.executeQuery("select * from wydarzenie");

            while (myRs.next()){
                Wydarzenie tempWydarzenie = convertRowToWydarzenie(myRs);
                lista.add(tempWydarzenie);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }

    public Wydarzenie searchWydarzenie(int id) throws Exception {
        List<Wydarzenie> lista = new ArrayList<>();
        PreparedStatement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.prepareStatement("Select * From wydarzenie where data = ?");
            myStmt1.setInt(1, id);
            myRs = myStmt1.executeQuery();
            if (myRs.next()) {
                return convertRowToWydarzenie(myRs);
            }
            else return null;
        } finally {
            myStmt1.close();
        }
    }

    public void addWydarzenie(Wydarzenie wydarzenie) throws Exception {
        PreparedStatement myStmt2 = null;
        try {
            myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_wydarzenie(?,?,?,?)}");
            myStmt2.setString(1, wydarzenie.getRodzaj());
            myStmt2.setString(2, wydarzenie.getData());
            myStmt2.setInt(3, wydarzenie.getTrener_id());
            myStmt2.execute();
        } finally {
            myStmt2.close();
        }
    }

    public void updateWydarzenie(Wydarzenie wydarzenie) throws Exception {
        PreparedStatement myStmt3 = null;
        try {
            myStmt3 = Logowanie.myConn.prepareStatement("update wydarzenie set rodzaj=?, data=?, trener_id=? where wydarzenie_id=?");
            myStmt3.setString(1, wydarzenie.getRodzaj());
            myStmt3.setString(2, wydarzenie.getData());
            myStmt3.setInt(3, wydarzenie.getTrener_id());
            myStmt3.setInt(4, wydarzenie.getWydarzenie_id());
            myStmt3.executeUpdate();
        } finally {
            myStmt3.close();
        }
    }

    public void deleteWydarzenie(int id) throws Exception {
        PreparedStatement myStmt4 = null;
        try {
            myStmt4 = Logowanie.myConn.prepareStatement("delete from wydarzenie where wydarzenie_id=?");
            myStmt4.setInt(1, id);
            myStmt4.executeUpdate();
        } finally {
            myStmt4.close();
        }
    }

    private Wydarzenie convertRowToWydarzenie(ResultSet myRs) throws SQLException {
        int wydarzenie_id = myRs.getInt("wydarzenie_id");
        String rodzaj = myRs.getString("rodzaj");
        String data = myRs.getString("data");
        int trener_id = myRs.getInt("trener_trener_id");

        Wydarzenie tempWydarzenie = new Wydarzenie(wydarzenie_id, rodzaj, data, trener_id);

        return tempWydarzenie;
    }
}