package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.modele.dao.OffrirDao;
import gsb.modele.dao.VisiteDao;

public class JFVisiteEdit extends JFrame implements ActionListener, WindowListener{

	private static final long serialVersionUID = 1L;
	
	protected Visite visite;
	protected JPanel p;
	protected JPanel pDetails;
	protected JScrollPane psListe;
	
	protected JLabel JLReference;
	protected JLabel JLDate;
	protected JLabel JLMatricule;
	protected JLabel JLCode;
	protected JLabel JLCommentaire;
	
	protected JTextField JTReference;
	protected JTextField JTDate;
	protected JTextField JTMatricule;
	protected JTextField JTCode;
	protected JTextField JTCommentaire;
	
	protected JTable table;
	
	protected JButton JBEdit;
	protected JButton JBAdd;
	
	protected ArrayList<Offrir> listeOffres;
	
	public JFVisiteEdit(String reference) {
		super("Modifier visite : " + reference);
		this.visite = VisiteDao.rechercher(reference);
		this.setVisible(true);
		this.setSize(500, 300);
		
		p = new JPanel();
		pDetails = new JPanel(new GridLayout(5,2));
		
		JLReference = new JLabel("Référence");
		JLDate = new JLabel("Date");
		JLMatricule = new JLabel("Matricule visiteur");
		JLCode = new JLabel("Code médecin");
		JLCommentaire = new JLabel("Commentaire");
		
		JTReference = new JTextField(visite.getReference(),20);
		JTReference.setEditable(false);
		JTDate = new JTextField(visite.getDate());
		JTDate.setEditable(false);
		JTMatricule = new JTextField(visite.getUnVisiteur().getMatricule());
		JTMatricule.setEditable(false);
		JTCode = new JTextField(visite.getUnMedecin().getCodeMed());
		JTCode.setEditable(false);
		JTCommentaire = new JTextField(visite.getCommentaire());
		
		pDetails.add(JLReference);
		pDetails.add(JTReference);
		pDetails.add(JLDate);
		pDetails.add(JTDate);
		pDetails.add(JLMatricule);
		pDetails.add(JTMatricule);
		pDetails.add(JLCode);
		pDetails.add(JTCode);
		pDetails.add(JLCommentaire);
		pDetails.add(JTCommentaire);
		
		p.add(pDetails);
		
		String[] columnNames = {"", "Dépot légal", "Quantité offerte"};
		
		String[][] data = new String[2][3];
		data[0][0] = "Médicament 1";
		data[1][0] = "Médicament 2";
		int i = 0;
		listeOffres = OffrirDao.recherche(reference);
		for(Offrir ligne : listeOffres) {
			data[i][1] = ligne.getUnMedicament().getDepotLegal();
			data[i][2] = ligne.getQteOfferte()+"";
			i++;
		}
		
		table = new JTable(data, columnNames);
		
		psListe = new JScrollPane(table);
		psListe.setPreferredSize(new Dimension(400,55));
		
		p.add(psListe);
		
		JBEdit = new JButton("Modifier");
		JBEdit.addActionListener(this);
		JBAdd = new JButton("Offrir");
		JBAdd.addActionListener(this);
		
		p.add(JBAdd);
		p.add(JBEdit);
		
		Container contentPane = getContentPane();
		contentPane.add(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == JBEdit) {
			visite.setCommentaire(JTCommentaire.getText());
			VisiteDao.modifier(visite);
			this.dispose();
		}
		else if(source == JBAdd) {
			JFVisiteOffrir jframeOffrir = new JFVisiteOffrir(visite.getReference());
			jframeOffrir.addWindowListener(this);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		String[] columnNames = {"", "Dépot légal", "Quantité offerte"};
		
		listeOffres = OffrirDao.recherche(visite.getReference());
		
		String[][] data = new String[2][3];
		data[0][0] = "Médicament 1";
		data[1][0] = "Médicament 2";
		int i = 0;
		for(Offrir ligne : listeOffres) {
			data[i][1] = ligne.getUnMedicament().getDepotLegal();
			data[i][2] = ligne.getQteOfferte()+"";
			i++;
		}
		
		DefaultTableModel tableur = new DefaultTableModel(data, columnNames);
		table.setModel(tableur);
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
