import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MenuAdmin {
    public JFrame frmMenu;
    private JTable table;
    private JTextField textFieldImie;
    private JTextField textFieldNazwisko;
    private JTextField textFieldLogin;
    private JTextField textFieldHaslo;
    private JTextField textFieldRola;
    private JTextField textFieldId;
    private JButton btnDodaj;
    private JButton btnUsun;
    private JButton btnAktualizuj;
    private JTextField textField;
    private JPasswordField passField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuAdmin window = new MenuAdmin();
                    window.frmMenu.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuAdmin() {
        initialize();
    }

    private void initialize() {
        frmMenu = new JFrame();
        frmMenu.setTitle("Menu Admina");
        frmMenu.setBounds(100, 100, 800, 600);
        frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMenu.getContentPane().setLayout(null);

        JLabel lblImie = new JLabel("Imię:");
        lblImie.setBounds(10, 11, 46, 14);
        frmMenu.getContentPane().add(lblImie);

        JLabel lblNazwisko = new JLabel("Nazwisko:");
        lblNazwisko.setBounds(10, 36, 66, 14);
        frmMenu.getContentPane().add(lblNazwisko);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(10, 61, 46, 14);
        frmMenu.getContentPane().add(lblLogin);

        JLabel lblHaslo = new JLabel("Hasło:");
        lblHaslo.setBounds(10, 86, 46, 14);
        frmMenu.getContentPane().add(lblHaslo);

        JLabel lblRola = new JLabel("Rola:");
        lblRola.setBounds(10, 111, 46, 14);
        frmMenu.getContentPane().add(lblRola);

        textFieldImie = new JTextField();
        textFieldImie.setBounds(86, 8, 86, 20);
        frmMenu.getContentPane().add(textFieldImie);
        textFieldImie.setColumns(10);

        textFieldNazwisko = new JTextField();
        textFieldNazwisko.setBounds(86, 33, 86, 20);
        frmMenu.getContentPane().add(textFieldNazwisko);
        textFieldNazwisko.setColumns(10);

        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(86, 58, 86, 20);
        frmMenu.getContentPane().add(textFieldLogin);
        textFieldLogin.setColumns(10);

        textFieldHaslo = new JTextField();
        textFieldHaslo.setBounds(86, 83, 86, 20);
        frmMenu.getContentPane().add(textFieldHaslo);
        textFieldHaslo.setColumns(10);

        textFieldRola = new JTextField();
        textFieldRola.setBounds(86, 108, 86, 20);
        frmMenu.getContentPane().add(textFieldRola);
        textFieldRola.setColumns(10);

        textFieldId = new JTextField();
        textFieldId.setBounds(256, 8, 86, 20);
        frmMenu.getContentPane().add(textFieldId);
        textFieldId.setColumns(10);

        JLabel lblId = new JLabel("Id:");
        lblId.setBounds(202, 11, 46, 14);
        frmMenu.getContentPane().add(lblId);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 142, 764, 2);
        frmMenu.getContentPane().add(separator);

        btnDodaj = new JButton("Dodaj");
        btnDodaj.setBounds(10, 155, 89, 23);
        frmMenu.getContentPane().add(btnDodaj);

        btnUsun = new JButton("Usuń");
        btnUsun.setBounds(109, 155, 89, 23);
        frmMenu.getContentPane().add(btnUsun);

        btnAktualizuj = new JButton("Aktualizuj");
        btnAktualizuj.setBounds(208, 155, 89, 23);
        frmMenu.getContentPane().add(btnAktualizuj);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 189, 764, 361);
        frmMenu.getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Id", "Imię", "Nazwisko", "Login", "Hasło", "Rola"
                }
        ));
        scrollPane.setViewportView(table);

        JButton btnWyloguj = new JButton("Wyloguj");
        btnWyloguj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmMenu.dispose();
                Logowanie logowanie = new Logowanie();
                logowanie.frame.setVisible(true);
            }
        });
        btnWyloguj.setBounds(307, 155, 89, 23);
        frmMenu.getContentPane().add(btnWyloguj);

        textField = new JTextField();
        textField.setBounds(352, 8, 86, 20);
        frmMenu.getContentPane().add(textField);
        textField.setColumns(10);

        passField = new JPasswordField();
        passField.setBounds(352, 33, 86, 20);
        frmMenu.getContentPane().add(passField);

        JLabel lblLogin_1 = new JLabel("Login:");
        lblLogin_1.setBounds(306, 11, 46, 14);
        frmMenu.getContentPane().add(lblLogin_1);

        JLabel lblHaso = new JLabel("Hasło:");
        lblHaso.setBounds(306, 36, 46, 14);
        frmMenu.getContentPane().add(lblHaso);

    }
}