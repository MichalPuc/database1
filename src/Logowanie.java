import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.sql.*;
public class Logowanie {
    public JFrame frame;
    public JTextField textField;
    public JPasswordField passwordField;
    public static Connection myConn;
    public static String username;
    public static String role;
    public static int id;
    public static boolean isLoggedIn;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Logowanie window = new Logowanie();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Logowanie() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 330, 230);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 54, 314, 2);
        frame.getContentPane().add(separator);

        JLabel lblLogowanie = new JLabel("LOGOWANIE");
        lblLogowanie.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblLogowanie.setBounds(115, 11, 100, 38);
        frame.getContentPane().add(lblLogowanie);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(20, 80, 46, 14);
        frame.getContentPane().add(lblLogin);

        JLabel lblHaso = new JLabel("Has\u0142o");
        lblHaso.setBounds(20, 110, 46, 14);
        frame.getContentPane().add(lblHaso);

        textField = new JTextField();
        textField.setBounds(60, 77, 244, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(60, 107, 244, 20);
        frame.getContentPane().add(passwordField);

        JButton btnNewButton = new JButton("Zaloguj");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                try {
                    String pass = String.valueOf(passwordField.getPassword());
                    myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza?useSSL=false&noAccessToProcedureBodies=true", textField.getText(), pass);
                    String login = textField.getText();
                    isLoggedIn = true;
                    Statement stmt = myConn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM uzytkownicy WHERE login='" + login + "'");
                    if (rs.next()) {
                        role = rs.getString("rola");
                        username = rs.getString("imie") + " " + rs.getString("nazwisko");
                        id = rs.getInt("id_uzytkownika");
                    }
                    if (role.equals("Recepcjonista")) {
                        MenuRecepcjonista okno = new MenuRecepcjonista();
                        okno.frmMenu.setVisible(true);
                        frame.dispose();
                    } else if (role.equals("Kierownik")) {
                        MenuKierownik okno = new MenuKierownik();
                        okno.frmMenu.setVisible(true);
                        frame.dispose();
                    } else if (role.equals("Admin")) {
                        MenuAdmin okno = new MenuAdmin();
                        okno.frmMenu.setVisible(true);
                        frame.dispose();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Błędne dane logowania");
                    isLoggedIn = false;
                }
            }
        });
        btnNewButton.setBounds(215, 140, 89, 23);
        frame.getContentPane().add(btnNewButton);
    }
}