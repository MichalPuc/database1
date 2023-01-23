import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class NowyKlient extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JTextField peselTextField;

    private JTextField dataUrodzeniaTextField;
    private JTextField hasloTextField;

    private KlientDAO klientDAO;
    private ListaKlientow listaKlientow;

    private Klient previousKlient = null;
    private boolean updateMode = false;
    public NowyKlient(ListaKlientow tlistaKlientow, KlientDAO tklientDAO, Klient tpreviousKlient, boolean tupdateMode) {
        this();
        klientDAO = tklientDAO;
        listaKlientow = tlistaKlientow;
        previousKlient = tpreviousKlient;
        updateMode = tupdateMode;
        if (updateMode) {
            setTitle("Update Klient");
            populateGui(previousKlient);
        }
    }
    private void populateGui(Klient klient) {
        imieTextField.setText(klient.getImie());
        nazwiskoTextField.setText(klient.getNazwisko());
        peselTextField.setText(String.valueOf(klient.getPesel()));
        dataUrodzeniaTextField.setText(String.valueOf(klient.getData_urodzenia()));
        hasloTextField.setText(String.valueOf(klient.getHaslo()));
    }

    public NowyKlient() {
        setTitle("Dodawanie klienta");
        setBounds(100, 100, 391, 216);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblImie = new JLabel("Imie");
        lblImie.setBounds(10, 11, 82, 14);
        contentPanel.add(lblImie);

        JLabel lblNazwisko = new JLabel("Nazwisko");
        lblNazwisko.setBounds(10, 36, 82, 14);
        contentPanel.add(lblNazwisko);

        JLabel lblPesel = new JLabel("Pesel");
        lblPesel.setBounds(10, 86, 82, 14);
        contentPanel.add(lblPesel);

        JLabel lblData_urodzenia = new JLabel("Data urodzenia");
        lblData_urodzenia.setBounds(10, 61, 82, 14);
        contentPanel.add(lblData_urodzenia);

        JLabel lblHaslo = new JLabel("Id Haslo");
        lblHaslo.setBounds(10, 111, 82, 14);
        contentPanel.add(lblHaslo);

        imieTextField = new JTextField();
        imieTextField.setBounds(102, 8, 179, 20);
        contentPanel.add(imieTextField);
        imieTextField.setColumns(10);

        nazwiskoTextField = new JTextField();
        nazwiskoTextField.setBounds(102, 33, 179, 20);
        contentPanel.add(nazwiskoTextField);
        nazwiskoTextField.setColumns(10);

        peselTextField = new JTextField();
        peselTextField.setBounds(102, 58, 179, 20);
        contentPanel.add(peselTextField);
        peselTextField.setColumns(10);

        dataUrodzeniaTextField = new JTextField();
        dataUrodzeniaTextField.setBounds(102, 83, 179, 20);
        contentPanel.add(dataUrodzeniaTextField);
        dataUrodzeniaTextField.setColumns(10);

        hasloTextField = new JTextField();
        hasloTextField.setBounds(102, 108, 25, 20);
        contentPanel.add(hasloTextField);
        hasloTextField.setColumns(10);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        saveKlient();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
    protected void saveKlient() {
        String imie = imieTextField.getText();
        String nazwisko = nazwiskoTextField.getText();
        String pesel = peselTextField.getText();
        String data_urodzenia = dataUrodzeniaTextField.getText();
        String haslo = hasloTextField.getText();
        Klient tempKlient = null;
        if (updateMode) {
            tempKlient = previousKlient;
            tempKlient.setImie(imie);
            tempKlient.setNazwisko(nazwisko);
            tempKlient.setPesel(Long.parseLong(pesel));
            tempKlient.setData_urodzenia(String.valueOf(data_urodzenia));
            tempKlient.setHaslo(String.valueOf(haslo));
        } else {
            tempKlient = new Klient(5,imie,nazwisko,Long.parseLong(pesel),String.valueOf(data_urodzenia),haslo);
        }
        try {
            if (updateMode) {
                klientDAO.updateKlient(tempKlient);
            } else {
                klientDAO.addKlient(tempKlient);
            }
            setVisible(false);
            dispose();
            listaKlientow.refreshKlient();
            if(updateMode) {
                JOptionPane.showMessageDialog(
                        listaKlientow,
                        "Klient edytowany poprawnie.",
                        "DodanoKlienta",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        listaKlientow,
                        "Klient dodany poprawnie.",
                        "DodanoKlienta",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    listaKlientow,
                    "B��d w czasie interakcji z klientem: " + exc.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        }

    }
}