import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EdytujKarnetDialog extends JDialog {


    private JTextField karnetIdField;
    private JTextField klientIdField;
    private JTextField dataAktywacjiField;
    private JTextField dataWaznosciField;
    private JTextField premiumField;
    private JButton zapiszButton;
    private JButton anulujButton;

    private KarnetDAO karnetDAO;

    public EdytujKarnetDialog(JFrame parent, KarnetDAO karnetDAO) {
        super(parent, "Edytuj KarnET", true);

        this.karnetDAO = karnetDAO;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);

        panel.add(new JLabel("Karnet ID: "), cs);

        karnetIdField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(karnetIdField, cs);

        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Klient ID: "), cs);

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
        panel.add(dataAktywacjiField, cs);

        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Data ważności: "), cs);

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
        panel.add(new JLabel("Premium: "), cs);

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
                    int klientId = Integer.parseInt(klientIdField.getText());
                    Date dataAktywacji = Date.valueOf(dataAktywacjiField.getText());
                    Date dataWaznosci = Date.valueOf(dataWaznosciField.getText());
                    boolean premium = Boolean.parseBoolean(premiumField.getText());
                    Karnet karnet = new Karnet(karnetId,  dataAktywacji, dataWaznosci, premium,klientId);
                    karnetDAO.updateKarnet(karnet);

                    setVisible(false);
                    dispose();

                    JOptionPane.showMessageDialog(EdytujKarnetDialog.this, "Karnet został zaktualizowany pomyślnie.", "Aktualizacja karnetu", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(EdytujKarnetDialog.this, "Błąd podczas aktualizowania karnetu: " + exc.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
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
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public void setKarnet(Karnet karnet) {
        karnetIdField.setText(Integer.toString(karnet.getKarnet_id()));
        klientIdField.setText(Integer.toString(karnet.getKlient_id()));
        dataAktywacjiField.setText(karnet.getData_aktywacji().toString());
        dataWaznosciField.setText(karnet.getData_waznosci().toString());
        premiumField.setText(Boolean.toString(karnet.isPremium()));
    }
}