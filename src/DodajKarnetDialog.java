import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class DodajKarnetDialog extends JDialog {

    private JTextField dataWaznosciField;
    private JTextField karnetIdField;
    private JTextField dataAktywacjiField;
    private JTextField premiumField;
    private JTextField klientIdField;
    private JButton zapiszButton;
    private JButton anulujButton;

    private KarnetDAO karnetDAO;

    public DodajKarnetDialog(JFrame parent, KarnetDAO karnetDAO) {
        super(parent, "Dodaj KarnET", true);

        this.karnetDAO = karnetDAO;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();

        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Karnet id: "), cs);

        karnetIdField = new JTextField(20);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(karnetIdField, cs);

        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Klient id: "), cs);

        klientIdField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(klientIdField, cs);

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Data aktywacji: "), cs);

        dataAktywacjiField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(dataWaznosciField, cs);

        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Data waznosci: "), cs);

        dataWaznosciField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(dataWaznosciField, cs);

        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Czy premium?: "), cs);

        premiumField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(premiumField, cs);

        JPanel buttonPanel = new JPanel();

        zapiszButton = new JButton("Zapisz");
        zapiszButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int karnetId = Integer.parseInt(karnetIdField.getText());
                    String dataWaznosci = String.valueOf(dataWaznosciField.getText());
                    String dataAktywacji = String.valueOf(dataAktywacjiField.getText());
                    String premium = String.valueOf(premiumField.getText());
                    int klientId = Integer.parseInt(klientIdField.getText());

                    Karnet karnet = new Karnet(karnetId, dataWaznosci, dataAktywacji,premium,klientId);

                    setVisible(false);
                    dispose();

                    JOptionPane.showMessageDialog(DodajKarnetDialog.this, "Karnet został dodany pomyślnie.", "Dodawanie karnetu", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(DodajKarnetDialog.this, "Błąd podczas dodawania karnetu: " + exc.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
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

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}