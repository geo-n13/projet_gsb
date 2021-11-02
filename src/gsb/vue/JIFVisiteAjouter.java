package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import gsb.modele.Visite;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;

public class JIFVisiteAjouter extends JInternalFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	protected JPanel p;
	protected JPanel pTexte;
	
	protected JLabel JLReference;
	protected JLabel JLDate;
	protected JLabel JLCommentaire;
	protected JLabel JLVisiteur;
	protected JLabel JLMedecin;
	
	protected JTextField JTReference;
	protected JFormattedTextField JTDate;
	protected JTextField JTCommentaire;
	protected JTextField JTVisiteur;
	protected JTextField JTMedecin;
	
	protected JButton JBAjouter;
	
	public JIFVisiteAjouter() {
		
		p = new JPanel();
		pTexte = new JPanel(new GridLayout(5,2));
		
		JLReference = new JLabel("Référence");
		JLDate = new JLabel("Date visite");
		JLCommentaire = new JLabel("Commentaire");
		JLVisiteur = new JLabel("Matricule visiteur");
		JLMedecin = new JLabel("Code médecin");
		
		JTReference = new JTextField(20);
		
		MaskFormatter mask = null;
		
		try {
			mask = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JTDate = new JFormattedTextField(mask);
		JTCommentaire = new JTextField();
		JTVisiteur = new JTextField();
		JTMedecin = new JTextField();
		
		pTexte.add(JLReference);
		pTexte.add(JTReference);
		pTexte.add(JLDate);
		pTexte.add(JTDate);
		pTexte.add(JLCommentaire);
		pTexte.add(JTCommentaire);
		pTexte.add(JLVisiteur);
		pTexte.add(JTVisiteur);
		pTexte.add(JLMedecin);
		pTexte.add(JTMedecin);
		
		JBAjouter = new JButton("Ajouter");
		JBAjouter.addActionListener(this);
		
		p.add(pTexte);
		
		p.add(JBAjouter);
		
		Container contentPane = getContentPane();
		contentPane.add(p);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == JBAjouter) {
			if((!JTReference.getText().isEmpty()) && (JTDate.getText().replaceAll(" ", "").length() == 10) && (!JTVisiteur.getText().isEmpty()) && (!JTMedecin.getText().isEmpty())) {
				
				String reference = JTReference.getText();
				String date = JTDate.getText();
				String commentaire = JTCommentaire.getText();
				String medecin = JTMedecin.getText();
				String visiteur = JTVisiteur.getText();
				
				if(VisiteDao.rechercher(reference) == null) {
					if(MedecinDao.rechercher(medecin) != null) {
						if(VisiteurDao.rechercher(visiteur) != null) {
							Visite uneVisite = new Visite(reference, date, commentaire, MedecinDao.rechercher(medecin), VisiteurDao.rechercher(visiteur));
							VisiteDao.creer(uneVisite);
						}
						else {
							System.out.println("Ce visiteur n'existe pas");
						}
					}
					else {
						System.out.println("Ce médecin n'existe pas");
					}
				}
				else {
					System.out.println("Cette référence existe déjà !");
				}
				
			}
			else {
				System.out.println("Compléter tous les champs");
			}
		}
	}

}
