import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class klientDAO {
    public List<klient> getAllKlient() throws Exception {
        List<klient> lista = new ArrayList<>();
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
        }
        finally {
            myStmt1.close();
        }
    }

}
