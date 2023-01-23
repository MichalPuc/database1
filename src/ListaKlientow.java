import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ListaKlientow extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    //private String[] nazwy_kolumn = {"ID", "Imie", "Nazwisko","E-mail","Numer tel.","AdresId"};
    //private Object[][] data = {{"roman", "kowalski", "romankowalski420@interia.pl", "6828737417"}};
    //private List<Klient> klienci;
    private JTable table;
    private KlientDAO KlientDAO;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListaKlientow dialog = new ListaKlientow();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListaKlientow() {
        try {
            KlientDAO = new KlientDAO();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
        setTitle("Lista Klient\u00F3w");
        setBounds(100, 100, 608, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JLabel lblPodajNazwisko = new JLabel("Podaj Nazwisko");
            lblPodajNazwisko.setBounds(24, 14, 96, 14);
            contentPanel.add(lblPodajNazwisko);
        }
        {
            textField = new JTextField();
            textField.setBounds(130, 11, 86, 20);
            contentPanel.add(textField);
            textField.setColumns(10);
        }
        {
            JButton btnSzukaj = new JButton("Szukaj id");
            btnSzukaj.setBounds(226, 10, 63, 23);
            btnSzukaj.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String nazwisko = textField.getText();
                    List<Klient> klient = null;
                    try {
                        if(nazwisko != null && nazwisko.trim().length() > 0) {
                            klient = KlientDAO.searchKlient(nazwisko);
                        } else {
                            klient = KlientDAO.getAllKlient();
                        }
                        TablicaKlientow model = new TablicaKlientow(klient);
                        table.setModel(model);

                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(ListaKlientow.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            contentPanel.add(btnSzukaj);
        }
        {
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 45, 572, 173);
            contentPanel.add(scrollPane);
            {
                table = new JTable();
                scrollPane.setViewportView(table);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnUsuKlienta = new JButton("Usu\u0144 Klienta");
                btnUsuKlienta.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int row = table.getSelectedRow();
                            if (row<0) {
                                JOptionPane.showMessageDialog(ListaKlientow.this, "Musisz wybra� klienta", "ERROR", JOptionPane.ERROR_MESSAGE );
                                return;
                            }
                            int response = JOptionPane.showConfirmDialog(ListaKlientow.this, "Czy na pewno chcesz usun�� tego klienta?", "Potwierd�",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                            if (response!=JOptionPane.YES_OPTION) {
                                return;
                            }

                            Klient tempKlient = (Klient) table.getValueAt(row, TablicaKlientow.OBJECT_COL);
                            KlientDAO.deleteKlient(tempKlient.getKlient_id());
                            refreshKlient();
                            JOptionPane.showMessageDialog(ListaKlientow.this, "Klient usuni�ty pomy�lnie", "Klient usuni�ty", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception exc) {
                            JOptionPane.showMessageDialog(ListaKlientow.this, "B��d w czasie usuwania: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                buttonPane.add(btnUsuKlienta);
            }
            {
                JButton btnNewButton_1 = new JButton("Edytuj Klienta");
                buttonPane.add(btnNewButton_1);
                btnNewButton_1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int row = table.getSelectedRow();
                        if(row<0) {
                            JOptionPane.showMessageDialog(ListaKlientow.this, "Musisz wybra� Klienta", "Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Klient tempKlient = (Klient) table.getValueAt(row, TablicaKlientow.OBJECT_COL);
                        NowyKlient dialog = new NowyKlient(ListaKlientow.this, KlientDAO, tempKlient, true);
                        dialog.setVisible(true);
                    }
                });
            }

            JButton btnNewButton = new JButton("Dodaj Klienta");
            buttonPane.add(btnNewButton);
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Klient tempKlient = null;
                    NowyKlient dialog = new NowyKlient(ListaKlientow.this, KlientDAO, tempKlient , false);
                    dialog.setVisible(true);
                }
            });
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
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
    public void refreshKlient() {
        List<Klient> klient = null;
        try {
            klient = KlientDAO.getAllKlient();
            TablicaKlientow model = new TablicaKlientow(klient);
            table.setModel(model);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}