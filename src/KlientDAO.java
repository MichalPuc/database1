import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
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

    public List<Klient> searchKlient(String nazwisko) throws Exception {
        List<Klient> lista = new ArrayList<>();
        PreparedStatement myStmt1 = null;
        ResultSet myRs = null;
        try {
            nazwisko += "%";
            myStmt1 = Logowanie.myConn.prepareStatement("Select * From klient where imie like ?");
            myStmt1.setString(2, nazwisko);
            myRs = myStmt1.executeQuery();
            while (myRs.next()) {
                Klient tempKlient = convertRowToKlient(myRs);
                lista.add(tempKlient);
            }
            return lista;
        } finally {
            myStmt1.close();
        }
    }

    Klient selectKlient(String nazwisko) throws Exception {
        final Klient[] selectedKlient = {null};
        KlientDAO klientDao = new KlientDAO();
        List<Klient> klientsWithLastName = new ArrayList<>();
        klientsWithLastName = klientDao.searchKlient(nazwisko);
        //Create a new JDialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Wybierz klienta");
        dialog.setModal(true);

        //Create a JList to display the customers
        DefaultListModel<Klient> listModel = new DefaultListModel<>();
        for (Klient k : klientsWithLastName) {
            listModel.addElement(k);
        }
        JList<Klient> klientsList = new JList<>(listModel);
        klientsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(klientsList);

        //Create a button to confirm the selection
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Klient kli = klientsList.getSelectedValue();
                selectedKlient[0] = kli;
                dialog.dispose();
            }
        });

        //Add the JList and button to the dialog
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setVisible(true);

        return selectedKlient[0];
    }

    public void addKlient(Klient klient) throws Exception {
        PreparedStatement myStmt2 = null;
        try {
            myStmt2 = Logowanie.myConn.prepareCall("\"INSERT INTO klient (imie, nazwisko, pesel, data_urodzenia, haslo) VALUES (?,?,?,?,?)\"");
            myStmt2.setInt(1, klient.getKlient_id());
            myStmt2.setString(2, klient.getImie());
            myStmt2.setString(3, klient.getNazwisko());
            myStmt2.setLong(4, klient.getPesel());
            myStmt2.setString(5, klient.getData_urodzenia());
            myStmt2.setString(6, klient.getHaslo());

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
            myStmt3.setString(4, klient.getData_urodzenia());
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
        String data_urodzenia = myRs.getString("data_urodzenia");
        String haslo = myRs.getString("haslo");
        Klient tempKlient = new Klient(id, imie, nazwisko, pesel, data_urodzenia, haslo);
        return tempKlient;
    }
}