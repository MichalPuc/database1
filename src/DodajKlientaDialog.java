import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DodajKlientaDialog extends JDialog {

    private JTextField klientIdField;
    private JTextField imieField;
    private JTextField nazwiskoField;
    private JTextField peselField;
    private JTextField dataUrodzeniaField;
    private JPasswordField hasloField;
    private JButton zapiszButton;
    private JButton anulujButton;

    private KlientDAO klientDAO;

    public DodajKlientaDialog(JFrame parent, KlientDAO klientDAO) {
        super(parent, "Dodaj klienta", true);

        this.klientDAO = klientDAO;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);

        panel.add(new JLabel("Id: "), cs);

        klientIdField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(klientIdField, cs);

        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);

        panel.add(new JLabel("Imię: "), cs);

        imieField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(imieField, cs);

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Nazwisko: "), cs);

        nazwiskoField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(nazwiskoField, cs);

        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("PESEL: "), cs);

        peselField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(peselField, cs);

        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Data urodzenia: "), cs);

        dataUrodzeniaField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(dataUrodzeniaField, cs);

        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Hasło: "), cs);

        hasloField = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(hasloField, cs);

        JPanel buttonPanel = new JPanel();

        zapiszButton = new JButton("Zapisz");
        zapiszButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String imie = imieField.getText();
                    int id =Integer.parseInt(klientIdField.getText());
                    String nazwisko = nazwiskoField.getText();
                    long pesel = Long.parseLong(peselField.getText());
                    Date dataUrodzenia = Date.valueOf(dataUrodzeniaField.getText());
                    String haslo = new String(hasloField.getPassword());
                    Klient klient = new Klient(id,imie, nazwisko, pesel, dataUrodzenia, haslo);
                    klientDAO.addKlient(klient);

                    setVisible(false);
                    dispose();

                    JOptionPane.showMessageDialog(DodajKlientaDialog.this, "Klient został dodany pomyślnie.", "Dodawanie klienta", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(DodajKlientaDialog.this, "Błąd podczas dodawania klienta: " + exc.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        anulujButton = new JButton("Anuluj");
        anulujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        buttonPanel.add(zapiszButton);
        buttonPanel.add(anulujButton);
        anulujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        buttonPanel.add(zapiszButton);
        buttonPanel.add(anulujButton);

        getContentPane().add(panel, "Center");
        getContentPane().add(buttonPanel, "South");

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}
