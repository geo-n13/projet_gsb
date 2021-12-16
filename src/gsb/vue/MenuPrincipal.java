/*
 * Créé le 22 févr. 2015
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package gsb.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Isabelle 22 févr. 2015 TODO Pour changer le modèle de ce commentaire
 *         de type généré, allez à : Fenêtre - Préférences - Java - Style de
 *         code - Modèles de code
 */
public class MenuPrincipal extends JFrame implements ActionListener {

    /**
     * Commentaire pour <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2591453837113855452L;

    protected JInternalFrame myJInternalFrame;
    protected JDesktopPane desktopPane;
    protected JMenuBar mbar;
    protected JMenu mMedecins;
    protected JMenu mMedicaments;
    protected JMenu mVisites;

    /**
     *
     */
    public MenuPrincipal() {

        myJInternalFrame = new JInternalFrame(); // pour affichage d'une seule
        // JInternalFrame à la fois
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.gray);
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);

        setTitle("GSB");
        setSize(500, 400);

        // Ajout d'une barre de menus à la fenêtre
        mbar = new JMenuBar();
        mMedecins = new JMenu("Medecins");
        JMenuItem mC1 = new JMenuItem("Consultation Medecin");
        mC1.addActionListener(this); // installation d'un écouteur d'action
        mMedecins.add(mC1);
        JMenuItem mC2 = new JMenuItem("Liste Medecins");
        mC2.addActionListener(this);
        mMedecins.add(mC2);

        mMedicaments = new JMenu("Medicaments");
        JMenuItem mE1 = new JMenuItem("Consultation Medicament");
        mE1.addActionListener(this); // installation d'un écouteur d'action
        mMedicaments.add(mE1);
        JMenuItem mE2 = new JMenuItem("Ajout Medicaments");
        mE2.addActionListener(this);
        mMedicaments.add(mE2);
        JMenuItem mE3 = new JMenuItem("Famille M�dicaments");
        mE3.addActionListener(this);
        mMedicaments.add(mE3);

        mVisites = new JMenu("Visites");
        JMenuItem mA1 = new JMenuItem("Consultation Visite");
        mA1.addActionListener(this); // installation d'un écouteur d'action
        mVisites.add(mA1);
        JMenuItem mA2 = new JMenuItem("Ajout Visite");
        mA2.addActionListener(this);
        mVisites.add(mA2);

        mbar.add(mMedecins);
        mbar.add(mMedicaments);
        mbar.add(mVisites);
        setJMenuBar(mbar);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // TODO Raccord de méthode auto-généré
        if (evt.getSource() instanceof JMenuItem) {
            String ChoixOption = evt.getActionCommand();

            if (ChoixOption.equals("Consultation Medecin")) {
                // Creation d'une sous-fenêtre
                ouvrirFenetre(new JIFMedecinCons());
            }
            
            else if (ChoixOption.equals("Liste Medecins")) {
                ouvrirFenetre(new JIFMedecinListeDic(this));
            }
            
            else if(ChoixOption.equals("Consultation Medicament")) {
            	ouvrirFenetre(new JIFMedicamentListe(this));
            }
            
            else if(ChoixOption.equals("Ajout Medicaments")) {
            	ouvrirFenetre(new JIFMedicamentAjouter());
            }
            
            else if(ChoixOption.equals("Famille M�dicaments")) {
            	ouvrirFenetre(new JIFMedicamentFamille());
            }
            
            else if(ChoixOption.equals("Consultation Visite")) {
            	ouvrirFenetre(new JIFVisiteListe());
            }
            
            else if(ChoixOption.equals("Ajout Visite")) {
            	ouvrirFenetre(new JIFVisiteAjouter());
            }

        }

    }

    public void ouvrirFenetre(JInternalFrame uneFenetre) {
        myJInternalFrame.dispose(); // si une fenêtre était dejà affichée, elle
        // est libérée
        myJInternalFrame = uneFenetre;
        myJInternalFrame.setVisible(true);
        myJInternalFrame.setResizable(true);
        myJInternalFrame.setMaximizable(true);
        myJInternalFrame.setClosable(true);
        myJInternalFrame.setSize(480, 380);
        desktopPane.add(myJInternalFrame);
    }

}