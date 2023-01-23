import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KarnetDAO {

    public List<Karnet> getAllKarnet() throws Exception {
        List<Karnet> lista = new ArrayList<>();
        Statement myStmt1 = null;
        ResultSet myRs = null;
        try {
            myStmt1 = Logowanie.myConn.createStatement();
            myRs = myStmt1.executeQuery("select * from karnet");

            while (myRs.next()){
                Karnet tempKarnet = convertRowToKarnet(myRs);
                lista.add(tempKarnet);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }

    public Karnet searchKarnet(int id) throws Exception {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = Logowanie.myConn.prepareStatement("Select * From karnet where karnet_id = ?");
            myStmt.setInt(1, id);
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                return convertRowToKarnet(myRs);
            }
            else return null;
        } finally {
            myStmt.close();
        }
    }

    public void addKarnet(Karnet karnet) throws Exception {
        PreparedStatement myStmt2 = null;
        try {
            myStmt2 = Logowanie.myConn.prepareCall("{call dodaj_karnet(?,?,?,?)}");
            myStmt2.setString(1, karnet.getPremium());
            myStmt2.setString(3, karnet.getData_waznosci());
            myStmt2.setInt(4, karnet.getKlient_id());

            myStmt2.execute();
        } finally {
            myStmt2.close();
        }
    }

    public void updateKarnet(Karnet karnet, int id ) throws Exception {
        PreparedStatement myStmt3 = null;
        try {
            myStmt3 = Logowanie.myConn.prepareStatement("update karnet set rodzaj=?, data_waznosci=?,data_aktywacji=?, klient_klient_id=? where karnet_id=?");
            myStmt3.setString(1, karnet.getPremium());
            myStmt3.setString(3, karnet.getData_waznosci());
            myStmt3.setString(2, karnet.getData_aktywacji());
            myStmt3.setInt(4, karnet.getKlient_id());
            myStmt3.setInt(5, id);

            myStmt3.executeUpdate();
        } finally {
            myStmt3.close();
        }
    }

    public void deleteKarnet(int id) throws Exception {
        PreparedStatement myStmt4 = null;
        try {
            myStmt4 = Logowanie.myConn.prepareStatement("delete from karnet where karnet_id=?");
            myStmt4.setInt(1, id);
            myStmt4.executeUpdate();
        } finally {
            myStmt4.close();
        }
    }

    private Karnet convertRowToKarnet(ResultSet myRs) throws SQLException {
        int id = myRs.getInt("karnet_id");
        String premium = myRs.getString("rodzaj");
        String data_waznosci = myRs.getString("data_waznosci");
        String data_aktywacji = myRs.getString("data_aktywacji");
        int klient_id = myRs.getInt("klient_klient_id");

        Karnet tempKarnet = new Karnet(id, data_waznosci, data_aktywacji,premium,klient_id);
        return tempKarnet;
    }
}