import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Logowanie {

    public JFrame frame;
    private JComboBox<String> accountTypeComboBox;
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    static Connection myConn;

    public Logowanie() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 330, 230);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        accountTypeComboBox = new JComboBox<>(new String[]{"Trener", "Pracownik"});
        accountTypeComboBox.setBounds(110, 20, 100, 20);
        frame.getContentPane().add(accountTypeComboBox);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 60, 30, 20);
        frame.getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(90, 60, 120, 20);
        frame.getContentPane().add(idField);
        JLabel passwordLabel = new JLabel("Hasło:");
        passwordLabel.setBounds(50, 90, 50, 20);
        frame.getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(90, 90, 120, 20);
        frame.getContentPane().add(passwordField);

        loginButton = new JButton("Zaloguj");
        loginButton.setBounds(110, 130, 80, 20);
        frame.getContentPane().add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String accountType = (String) accountTypeComboBox.getSelectedItem();
                    int id = Integer.parseInt(idField.getText());
                    String password = new String(passwordField.getPassword());
                    if (accountType.equals("Trener")) {
                        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/siłownia", "myadmin", "lab12345");
                        PreparedStatement myStmt = myConn.prepareStatement("select * from trener where trener_id=? and haslo=?");
                        myStmt.setInt(1, id);
                        myStmt.setString(2, password);
                        ResultSet myRs = myStmt.executeQuery();
                        if (myRs.next()) {
                            JOptionPane.showMessageDialog(frame, "Zalogowano jako trener");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Niepoprawny ID lub hasło");
                        }
                    } else {
                        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/siłownia", "myadmin", "lab12345");
                        PreparedStatement myStmt = myConn.prepareStatement("select * from pracownik where pracownik_id=? and haslo=?");
                        myStmt.setInt(1, id);
                        myStmt.setString(2, password);
                        ResultSet myRs = myStmt.executeQuery();
                        if (myRs.next()) {
                            JOptionPane.showMessageDialog(frame, "Zalogowano jako pracownik");
                            PracownikGUI pracownikGUI = new PracownikGUI();
                            pracownikGUI.setVisible(true);
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Niepoprawny ID lub hasło");
                        }
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(frame, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    Logowanie window = new Logowanie();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}