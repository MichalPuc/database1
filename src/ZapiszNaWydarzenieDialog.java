import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ZapiszNaWydarzenieDialog extends JDialog {

    private JTextField klientIdField;
    private JTextField wydarzenieIdField;
    private JButton zapiszButton;
    private JButton anulujButton;

    private ZapisyNaWydarzenieDAO zapisyNaWydarzenieDAO;

    public ZapiszNaWydarzenieDialog(JFrame parent, ZapisyNaWydarzenieDAO zapisyNaWydarzenieDAO, int idKlienta, int idWydarzenia) {
        super(parent, "Zapisz na wydarzenie", true);

        this.zapisyNaWydarzenieDAO = zapisyNaWydarzenieDAO;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);

        panel.add(new JLabel("Id klienta: "), cs);

        klientIdField = new JTextField(20);
        klientIdField.setText(Integer.toString(idKlienta));
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(klientIdField, cs);

        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Id wydarzenia: "), cs);

        wydarzenieIdField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(wydarzenieIdField, cs);

        JPanel buttonPanel = new JPanel();

        zapiszButton = new JButton("Zapisz");
        zapiszButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int klientId = Integer.parseInt(klientIdField.getText());
                    int wydarodzenieId = Integer.parseInt(wydarzenieIdField.getText());
                    ZapisyNaWydarzenie zapisyNaWydarzenie = new ZapisyNaWydarzenie(wydarodzenieId, klientId);
                    zapisyNaWydarzenieDAO.addZapisyNaWydarzenie(zapisyNaWydarzenie);

                    setVisible(false);
                    dispose();

                    JOptionPane.showMessageDialog(ZapiszNaWydarzenieDialog.this, "Zapisano na wydarzenie pomyślnie.", "Zapis na wydarzenie", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(ZapiszNaWydarzenieDialog.this, "Nie udało się zapisać na wydarzenie: " + exc.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(zapiszButton);

        anulujButton = new JButton("Anuluj");
        anulujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        buttonPanel.add(anulujButton);

        getContentPane().add(panel, "Center");
        getContentPane().add(buttonPanel, "South");

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}