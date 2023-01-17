/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class DodajKarnetDialog extends JDialog {

    private JTextField nazwaField;
    private JTextField cenaField;
    private JTextField czasWaznosciField;
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

        panel.add(new JLabel("Nazwa: "), cs);

        nazwaField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(nazwaField, cs);

        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Cena: "), cs);

        cenaField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(cenaField, cs);

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Czas ważności: "), cs);

        czasWaznosciField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(czasWaznosciField, cs);

        JPanel buttonPanel = new JPanel();

        zapiszButton = new JButton("Zapisz");
        zapiszButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nazwa = nazwaField.getText();
                    double cena = Double.parseDouble(cenaField.getText());
                    int czasWaznosci = Integer.parseInt(czasWaznosciField.getText());
                    Karnet karnet = new Karnet(karnet_id, data_waznosci, data_aktywacji,premium,klient_id);
                    karnetDAO.addKarnet(karnet);

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
}*/