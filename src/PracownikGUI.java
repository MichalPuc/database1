import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class PracownikGUI extends JFrame {


    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel karnetyPanel;
    private JPanel klienciPanel;
    private JPanel wydarzeniaPanel;
    private JButton dodajKarnetButton;
    private JButton usunKarnetButton;
    private JButton edytujKarnetButton;
    private JButton dodajKlientaButton;
    private JButton usunKlientaButton;
    private JButton edytujKlientaButton;
    private JButton dodajWydarzenieButton;
    private JButton usunWydarzenieButton;
    private JButton edytujWydarzenieButton;
    private JButton zapiszNaWydarzenieButton;
    private JTable karnetyTable;
    private JTable klienciTable;
    private JTable wydarzeniaTable;
    private KarnetDAO karnetDAO;
    private KlientDAO klientDAO;
    private WydarzenieDAO wydarzenieDAO;
    private ZapisyNaWydarzenieDAO zapisyNaWydarzenieDAO;

    public PracownikGUI() {
        try {
            karnetDAO = new KarnetDAO();
            klientDAO = new KlientDAO();
            wydarzenieDAO = new WydarzenieDAO();
            zapisyNaWydarzenieDAO = new ZapisyNaWydarzenieDAO();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
        }
        setTitle("Panel Pracownika");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        tabbedPane = new JTabbedPane();
        karnetyPanel = new JPanel();
        klienciPanel = new JPanel();
        wydarzeniaPanel = new JPanel();
        dodajKarnetButton = new JButton("Dodaj KarnET");
        usunKarnetButton = new JButton("Usuń KarnET");
        edytujKarnetButton = new JButton("Edytuj KarnET");
        dodajKlientaButton = new JButton("Dodaj KLIENTA");
        usunKlientaButton = new JButton("Usuń KLIENTA");
        edytujKlientaButton = new JButton("Edytuj KLIENTA");
        dodajWydarzenieButton = new JButton("Dodaj WYDARZENIE");
        usunWydarzenieButton = new JButton("Usuń WYDARZENIE");
        edytujWydarzenieButton = new JButton("Edytuj WYDARZENIE");
        zapiszNaWydarzenieButton = new JButton("Zapiszna WYDARZENIE");
        karnetyTable = new JTable();
        klienciTable = new JTable();
        wydarzeniaTable = new JTable();

// KarnETY PANEL
        karnetyPanel.setLayout(new BorderLayout());
        karnetyPanel.add(new JScrollPane(karnetyTable), BorderLayout.CENTER);
        JPanel karnetyButtonPanel = new JPanel();
        karnetyButtonPanel.setLayout(new FlowLayout());
        karnetyButtonPanel.add(dodajKarnetButton);
        karnetyButtonPanel.add(usunKarnetButton);
        karnetyButtonPanel.add(edytujKarnetButton);
        karnetyPanel.add(karnetyButtonPanel, BorderLayout.SOUTH);

// KLIENTA PANEL
        klienciPanel.setLayout(new BorderLayout());
        klienciPanel.add(new JScrollPane(klienciTable), BorderLayout.CENTER);
        JPanel klienciButtonPanel = new JPanel();
        klienciButtonPanel.setLayout(new FlowLayout());
        klienciButtonPanel.add(dodajKlientaButton);
        klienciButtonPanel.add(usunKlientaButton);
        klienciButtonPanel.add(edytujKlientaButton);
        klienciButtonPanel.add(zapiszNaWydarzenieButton);
        klienciPanel.add(klienciButtonPanel, BorderLayout.SOUTH);

// WYDARZENIA PANEL
        wydarzeniaPanel.setLayout(new BorderLayout());
        wydarzeniaPanel.add(new JScrollPane(wydarzeniaTable), BorderLayout.CENTER);
        JPanel wydarzeniaButtonPanel = new JPanel();
        wydarzeniaButtonPanel.setLayout(new FlowLayout());
        wydarzeniaButtonPanel.add(dodajWydarzenieButton);
        wydarzeniaButtonPanel.add(usunWydarzenieButton);
        wydarzeniaButtonPanel.add(edytujWydarzenieButton);
        wydarzeniaPanel.add(wydarzeniaButtonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Karnety", karnetyPanel);
        tabbedPane.addTab("Klienci", klienciPanel);
        tabbedPane.addTab("Wydarzenia", wydarzeniaPanel);
        mainPanel.add(tabbedPane);
        add(mainPanel);

        dodajKarnetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DodajKarnetDialog dialog = new DodajKarnetDialog(PracownikGUI.this,karnetDAO);
                dialog.setVisible(true);
            }
        });

        usunKarnetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = karnetyTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Nie wybrano karnetu do usunięcia", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = (int) karnetyTable.getModel().getValueAt(row, 0);
                int result = JOptionPane.showConfirmDialog(PracownikGUI.this, "Czy na pewno chcesz usunąć wybrany karnet?", "Usuwanie karnetu", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        karnetDAO.deleteKarnet(id);
                        refreshKarnetyView();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(PracownikGUI.this, "Error deleting karnet: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        edytujKarnetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = karnetyTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Nie wybrano karnetu do edycji", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = (int) karnetyTable.getModel().getValueAt(row, 0);
                try {
                    Karnet tempKarnet = karnetDAO.searchKarnet(id);
                    EdytujKarnetDialog dialog = new EdytujKarnetDialog(PracownikGUI.this, karnetDAO);
                    dialog.setVisible(true);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Error editing karnet: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dodajKlientaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DodajKlientaDialog dialog = new DodajKlientaDialog(PracownikGUI.this, klientDAO);
                dialog.setVisible(true);
            }
        });

        usunKlientaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = klienciTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Nie wybrano klienta do usunięcia", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = (int) klienciTable.getModel().getValueAt(row, 0);
                int result = JOptionPane.showConfirmDialog(PracownikGUI.this, "Czy na pewno chcesz usunąć wybranego klienta?", "Usuwanie klienta", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        klientDAO.deleteKlient(id);
                        refreshKlienciView();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(PracownikGUI.this, "Error deleting klient: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        edytujKlientaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = klienciTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Nie wybrano klienta do edycji", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = (int) klienciTable.getModel().getValueAt(row, 0);
                try {
                    Klient tempKlient = klientDAO.searchKlient(id);
                    EdytujKlientaDialog dialog = new EdytujKlientaDialog(PracownikGUI.this, klientDAO, tempKlient, true);
                    dialog.setVisible(true);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Error editing klient: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dodajWydarzenieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DodajWydarzenieDialog dialog = new DodajWydarzenieDialog(PracownikGUI.this, wydarzenieDAO);
                dialog.setVisible(true);
            }
        });

        usunWydarzenieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = wydarzeniaTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Nie wybrano wydarzenia do usunięcia", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = (int) wydarzeniaTable.getModel().getValueAt(row, 0);
                int result = JOptionPane.showConfirmDialog(PracownikGUI.this, "Czy na pewno chcesz usunąć wybrane wydarzenie?", "Usuwanie wydarzenia", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        wydarzenieDAO.deleteWydarzenie(id);
                        refreshWydarzeniaView();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(PracownikGUI.this, "Error deleting wydarzenie: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        edytujWydarzenieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = wydarzeniaTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Nie wybrano wydarzenia do edycji", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = (int) wydarzeniaTable.getModel().getValueAt(row, 0);
                try {
                    Wydarzenie tempWydarzenie = wydarzenieDAO.searchWydarzenie(id);
                    EdytujWydarzenieDialog dialog = new EdytujWydarzenieDialog(PracownikGUI.this, wydarzenieDAO, tempWydarzenie, true);
                    dialog.setVisible(true);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Error editing wydarzenie: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        zapiszNaWydarzenieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowKlienci = klienciTable.getSelectedRow();
                int rowWydarzenia = wydarzeniaTable.getSelectedRow();
                if (rowKlienci == -1 || rowWydarzenia == -1) {
                    JOptionPane.showMessageDialog(PracownikGUI.this, "Nie wybrano klienta lub wydarzenia do zapisu na wydarzenie", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idKlienta = (int) klienciTable.getModel().getValueAt(rowKlienci, 0);
                int idWydarzenia = (int) wydarzeniaTable.getModel().getValueAt(rowWydarzenia, 0);
                ZapiszNaWydarzenieDialog dialog = new ZapiszNaWydarzenieDialog(PracownikGUI.this, zapisyNaWydarzenieDAO, idKlienta, idWydarzenia);
                dialog.setVisible(true);
            }
        });

        refreshKarnetyView();
        refreshKlienciView();
        refreshWydarzeniaView();
    }

    public void refreshKarnetyView() {
        try {
            List<Karnet> karnety = karnetDAO.getAllKarnet();
            DefaultTableModel model = (DefaultTableModel) karnetyTable.getModel();
            model.setRowCount(0);
            for (Karnet karnet : karnety) {
                Object[] row = new Object[5];
                row[0] = karnet.getKarnet_id();
                row[1] = karnet.isPremium();
                row[2] = karnet.getData_aktywacji();
                row[3] = karnet.getData_waznosci();
                model.addRow(row);
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error refreshing karnety view: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshKlienciView() {
        try {
            List<Klient> klienci = klientDAO.getAllKlient();
            DefaultTableModel model = (DefaultTableModel) klienciTable.getModel();
            model.setRowCount(0);
            for ( Klient klient : klienci) {
                Object[] row = new Object[4];
                row[0] = klient.getKlient_id();
                row[1] = klient.getImie();
                row[2] = klient.getNazwisko();
                row[3] = klient.getPesel();
                row[4] = klient.getData_urodzenia();
                model.addRow(row);
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error refreshing klienci view: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshWydarzeniaView() {
        try {
            List<Wydarzenie> wydarzenia = wydarzenieDAO.getAllWydarzenie();
            DefaultTableModel model = (DefaultTableModel) wydarzeniaTable.getModel();
            model.setRowCount(0);
            for ( Wydarzenie wydarzenie : wydarzenia) {
                Object[] row = new Object[5];
                row[0] = wydarzenie.getWydarzenie_id();
                row[1] = wydarzenie.getData();
                row[2] = wydarzenie.getTrener_id();
                model.addRow(row);
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error refreshing wydarzenia view: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
