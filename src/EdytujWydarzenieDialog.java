import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EdytujWydarzenieDialog extends JDialog {

    private JTextField wydarzenieIdField;
    private JTextField rodzajField;
    private JTextField dataField;
    private JTextField trenerIdField;
    private JButton zapiszButton;
    private JButton anulujButton;

    private WydarzenieDAO wydarzenieDAO;
    private Wydarzenie wydarzenie;

    public EdytujWydarzenieDialog(JFrame parent, WydarzenieDAO wydarzenieDAO, Wydarzenie wydarzenie, boolean modal) {
        super(parent, "Edytuj wydarzenie", modal);

        this.wydarzenieDAO = wydarzenieDAO;
        this.wydarzenie = wydarzenie;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);

        panel.add(new JLabel("Id wydarzenia: "), cs);

        wydarzenieIdField = new JTextField(20);
        wydarzenieIdField.setText(Integer.toString(wydarzenie.getWydarzenie_id()));
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(wydarzenieIdField, cs);

        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Rodzaj: "), cs);

        rodzajField = new JTextField();
        rodzajField.setText(wydarzenie.getRodzaj());
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(rodzajField, cs);

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Data: "), cs);

        dataField = new JTextField(20);
        dataField.setText(wydarzenie.getData().toString());
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(dataField, cs);

        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Id trenera: "), cs);

        trenerIdField = new JTextField(20);
        trenerIdField.setText(Integer.toString(wydarzenie.getTrener_id()));
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(trenerIdField, cs);

        JPanel buttonPanel = new JPanel();

        zapiszButton = new JButton("Zapisz");
        zapiszButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int wydarzenieId = Integer.parseInt(wydarzenieIdField.getText());
                    String rodzaj = String.valueOf(rodzajField.getText());
                    String data = String.valueOf(dataField.getText());
                    int trenerId = Integer.parseInt(trenerIdField.getText());
                    wydarzenie.setWydarzenie_id(wydarzenieId);
                    wydarzenie.setRodzaj(rodzaj);
                    wydarzenie.setData(data);
                    wydarzenie.setTrener_id(trenerId);
                    wydarzenieDAO.updateWydarzenie(wydarzenie);

                    setVisible(false);
                    dispose();

                    JOptionPane.showMessageDialog(EdytujWydarzenieDialog.this, "Wydarzenie zostało zaktualizowane pomyślnie.", "Aktualizacja wydarzenia", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(EdytujWydarzenieDialog.this, "Błąd podczas aktualizowania wydarzenia: " + exc.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
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

        getContentPane().add(panel, "Center");
        getContentPane().add(buttonPanel, "South");

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}