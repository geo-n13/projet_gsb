package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.StringConcatFactory;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;

public class JIFVisiteurListe extends JInternalFrame implements ActionListener{

    private static final long serialVersionUID = 1L;

    protected JPanel p;
    protected JPanel pListe;
    protected JPanel pCherche;
    protected JScrollPane psListe;

    protected JTable table;

    protected JLabel JLMatricule;

    protected JTextField JTMatricule;

    protected JButton JBRechercher;

    public JIFVisiteurListe() {

        p = new JPanel();
        pListe = new JPanel();
        pCherche = new JPanel(new GridLayout(1, 3));

        //construction panel de recherche

        JLMatricule = new JLabel("Matricule visiteur");
        JTMatricule = new JTextField(10);

        JBRechercher = new JButton("Fiche détaillée");
        JBRechercher.addActionListener(this);

        pCherche.add(JLMatricule);
        pCherche.add(JTMatricule);
        pCherche.add(JBRechercher);

        p.add(pCherche);

        //construction panel de la liste

        ArrayList<Visiteur> listeVisites = VisiteurDao.retournerCollectionDesVisiteurs();
        String[] columnNames = {"Matricule", "Nom", "Prénom"};

        int i = 0;
        Object[][] data = new Object[listeVisites.size()][3];

        for(Visiteur visiteur : listeVisites) {
            data[i][0] = visiteur.getMatricule();
            data[i][1] = visiteur.getNom();
            data[i][2] = visiteur.getPrenom();
            i++;
        }

        table = new JTable(data, columnNames);

        psListe = new JScrollPane(table);
        psListe.setPreferredSize(new Dimension(300, 100));

        pListe.add(psListe);
        p.add(pListe);

        Container contentPane = getContentPane();
        contentPane.add(p);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == JBRechercher) {
            if((!JTMatricule.getText().isEmpty())) {
                String matricule = JTMatricule.getText();

                if(VisiteurDao.rechercher(matricule) != null) {
                    new JIFVisiteurDetail(matricule);
                }
                else {
                    System.out.println("Ce matricule n'existe pas !");
                }

            }
        }
    }
}
