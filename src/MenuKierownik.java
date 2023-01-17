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

public class MenuKierownik {

    public JFrame frmMenu;

    /**

     Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuKierownik window = new MenuKierownik();
                    window.frmMenu.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**

     Create the application.
     */
    public MenuKierownik() {
        initialize();
    }
    /**

     Initialize the contents of the frame.
     */
    private void initialize() {
        frmMenu = new JFrame();
        frmMenu.setTitle("Menu Kierownik");
        frmMenu.setBounds(100, 100, 450, 300);
        frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMenu.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Wyświetl wszystkich pracowników");
        btnNewButton.setBounds(100, 50, 250, 25);
        frmMenu.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Dodaj nowego pracownika");
        btnNewButton_1.setBounds(100, 100, 250, 25);
        frmMenu.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Usuń pracownika");
        btnNewButton_2.setBounds(100, 150, 250, 25);
        frmMenu.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Wyloguj");
        btnNewButton_3.setBounds(175, 200, 100, 25);
        frmMenu.getContentPane().add(btnNewButton_3);
    }

}