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

public class MenuRecepcjonista {
    public JFrame frmMenu;
    private JTextField textField;
    private JPasswordField passwordField;
    public static Connection myConn;
    public static String username;
    public static String role;
    public static int id;
    public static boolean isLoggedIn;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuRecepcjonista window = new MenuRecepcjonista();
                    window.frmMenu.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuRecepcjonista() {
        initialize();
    }

    private void initialize() {
        frmMenu = new JFrame();
        frmMenu.setTitle("Menu Recepcjonista");
        frmMenu.setBounds(100, 100, 450, 300);
        frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMenu.getContentPane().setLayout(null);
    }
}