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

public class DodajWydarzenieDialog extends JDialog {

    private JTextField wydarzenieIdField;
    private JCheckBox rodzajCheckBox;
    private JTextField dataField;
    private JTextField trenerIdField;
    private JButton zapiszButton;
    private JButton anulujButton;

    private WydarzenieDAO wydarzenieDAO;

    public DodajWydarzenieDialog(JFrame parent, WydarzenieDAO wydarzenieDAO ) {
        super(parent, "Dodaj wydarzenie", true);

        this.wydarzenieDAO = wydarzenieDAO;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints cs = new GridBagConstraints();
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);

        panel.add(new JLabel("Id wydarzenia: "), cs);

        wydarzenieIdField = new JTextField(20);
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

        rodzajCheckBox = new JCheckBox();
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(rodzajCheckBox, cs);

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Data: "), cs);

        dataField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(dataField, cs);

        cs.gridx =0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        cs.insets = new Insets(5, 5, 0, 5);
        panel.add(new JLabel("Id trenera: "), cs);

        trenerIdField = new JTextField(20);
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
                    boolean rodzaj = rodzajCheckBox.isSelected();
                    Date data = Date.valueOf(dataField.getText());
                    int trenerId = Integer.parseInt(trenerIdField.getText());
                    Wydarzenie wydarzenie = new Wydarzenie(wydarzenieId, rodzaj, data, trenerId);
                    wydarzenieDAO.addWydarzenie(wydarzenie);

                    setVisible(false);
                    dispose();

                    JOptionPane.showMessageDialog(DodajWydarzenieDialog.this, "Wydarzenie zostało dodane pomyślnie.", "Dodawanie wydarzenia", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(DodajWydarzenieDialog.this, "Błąd podczas dodawania wydarzenia: " + exc.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
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