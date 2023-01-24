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
import javax.swing.JTextField;

public class DodajKarnetDialog extends JDialog {


    private JTextField karnetIdField;
    private JTextField klientIdField;
    private JTextField dataAktywacjiField;
    private JTextField dataWaznosciField;
    private JTextField premiumField;
    private JButton zapiszButton;
    private JButton anulujButton;

    private KarnetDAO karnetDAO;

    public DodajKarnetDialog(KarnetDAO karnetDAO){

        this.karnetDAO = karnetDAO;

        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 330, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel karnetIdLabel = new JLabel("Karnet ID:");
        karnetIdLabel.setBounds(50, 60, 80, 20);
        frame.getContentPane().add(karnetIdLabel);

        karnetIdField = new JTextField();
        karnetIdField.setBounds(130, 60, 120, 20);
        frame.getContentPane().add(karnetIdField);

        JLabel klientIdLabel = new JLabel("Klient ID:");
        klientIdLabel.setBounds(50, 90, 80, 20);
        frame.getContentPane().add(klientIdLabel);

        klientIdField = new JTextField();
        klientIdField.setBounds(130, 90, 120, 20);
        frame.getContentPane().add(klientIdField);

        JLabel dataAktywacjiLabel = new JLabel("Data aktywacji:");
        dataAktywacjiLabel.setBounds(50, 120, 100, 20);
        frame.getContentPane().add(dataAktywacjiLabel);

        dataAktywacjiField = new JTextField();
        dataAktywacjiField.setBounds(130, 120, 120, 20);
        frame.getContentPane().add(dataAktywacjiField);

        JLabel dataWaznosciLabel = new JLabel("Data ważności:");
        dataWaznosciLabel.setBounds(50, 150, 100, 20);
        frame.getContentPane().add(dataWaznosciLabel);

        dataWaznosciField = new JTextField();
        dataWaznosciField.setBounds(130, 150, 120, 20);
        frame.getContentPane().add(dataWaznosciField);

        JLabel premiumLabel = new JLabel("Premium:");
        premiumLabel.setBounds(50, 180, 80, 20);
        frame.getContentPane().add(premiumLabel);

        premiumField = new JTextField();
        premiumField.setBounds(130, 180, 120, 20);
        frame.getContentPane().add(premiumField);

        JButton zapiszButton = new JButton("Dodaj");
        zapiszButton.setBounds(50, 210, 80, 25);
        frame.getContentPane().add(zapiszButton);

        JButton anulujButton = new JButton("Anuluj");
        anulujButton.setBounds(180, 210, 80, 25);
        frame.getContentPane().add(anulujButton);

        frame.setVisible(true);
        zapiszButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int karnetId = Integer.parseInt(karnetIdField.getText());
                    int klientId = Integer.parseInt(klientIdField.getText());
                    String dataAktywacji = String.valueOf(dataAktywacjiField.getText());
                    String dataWaznosci = String.valueOf(dataWaznosciField.getText());
                    String premium = String.valueOf(premiumField.getText());
                    Karnet karnet = new Karnet(karnetId,  dataAktywacji, dataWaznosci, premium,klientId);
                    karnetDAO.addKarnet(karnet);

                    setVisible(false);
                    dispose();

                    JOptionPane.showMessageDialog(DodajKarnetDialog.this, "Karnet został zaktualizowany pomyślnie.", "Aktualizacja karnetu", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(DodajKarnetDialog.this, "Błąd podczas aktualizowania karnetu: " + exc.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
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


        pack();
        setResizable(false);
    }

    public void setKarnet(Karnet karnet) {
        karnetIdField.setText(Integer.toString(karnet.getKarnet_id()));
        klientIdField.setText(Integer.toString(karnet.getKlient_id()));
        dataAktywacjiField.setText(karnet.getData_aktywacji().toString());
        dataWaznosciField.setText(karnet.getData_waznosci().toString());
        premiumField.setText(karnet.getPremium().toString());
    }
}