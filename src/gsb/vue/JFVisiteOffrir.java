package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gsb.modele.Offrir;
import gsb.modele.Stocker;
import gsb.modele.Visite;
import gsb.modele.dao.OffrirDao;
import gsb.modele.dao.StockerDao;
import gsb.modele.dao.VisiteDao;

public class JFVisiteOffrir extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	protected JPanel p;
	protected JPanel pFields;
	
	protected JLabel JLMedicament;
	protected JLabel JLQuantite;
	
	protected JComboBox<String> JCBMedicament;
	protected JTextField JTQuantite;
	
	protected JButton JBAdd;
	
	protected ArrayList<Stocker> listeStock;
	
	protected Visite visite;

	public JFVisiteOffrir(String reference) {
		super("Offrir médicament");
		this.setVisible(true);
		this.setSize(500, 150);
		
		visite = VisiteDao.rechercher(reference);
		
		p = new JPanel();
		pFields = new JPanel(new GridLayout(2,2));
		
		JLMedicament = new JLabel("Médicament");
		JLQuantite = new JLabel("Quantité");
		
		JCBMedicament = new JComboBox<String>();
		
		listeStock = StockerDao.listeStock(visite.getUnVisiteur().getMatricule());
		
		for(Stocker stock : listeStock) {
			JCBMedicament.addItem(stock.getUnMedicament().getDepotLegal() + " (" + stock.getQteStock() +")");
		}
		
		JTQuantite = new JTextField(20);
		
		JBAdd = new JButton("Ajouter");
		JBAdd.addActionListener(this);
		
		pFields.add(JLMedicament);
		pFields.add(JCBMedicament);
		pFields.add(JLQuantite);
		pFields.add(JTQuantite);
		
		p.add(pFields);
		
		p.add(JBAdd);
		
		Container contentPane = getContentPane();
		contentPane.add(p);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == JBAdd) {
			if((!JTQuantite.getText().isEmpty()) && (!JCBMedicament.getSelectedItem().toString().isEmpty())) {
				int quantite = Integer.parseInt(JTQuantite.getText());
				Stocker stock = listeStock.get(JCBMedicament.getSelectedIndex());
				if(quantite <= stock.getQteStock()) {
					ArrayList<Offrir> listeOffres = OffrirDao.recherche(visite.getReference());
					ArrayList<String> listeMedicaments = new ArrayList<String>();
					for(Offrir offre : listeOffres) {
						listeMedicaments.add(offre.getUnMedicament().getDepotLegal());
					}
					
					if(listeMedicaments.contains(stock.getUnMedicament().getDepotLegal())) {
						int quantiteTotal = 0;
						for(Offrir offre : listeOffres) {
							if(offre.getUnMedicament().getDepotLegal().equals(stock.getUnMedicament().getDepotLegal())) {
								quantiteTotal = quantite + offre.getQteOfferte();
							}
						}
						Offrir offre = new Offrir(stock.getUnMedicament(), visite, quantiteTotal);
						OffrirDao.modifier(offre);
						stock.setQteStock(stock.getQteStock()-quantite);
						StockerDao.modifier(stock);
					}
					else {
						if(listeOffres.size() < 2) {
							Offrir offre = new Offrir(stock.getUnMedicament(), visite, quantite);
							OffrirDao.creer(offre);
							stock.setQteStock(stock.getQteStock()-quantite);
							StockerDao.modifier(stock);
						}
						else {
							System.out.println("Vous avez déjà offert 2 médicaments");
						}
					}
					this.dispose();
				}
				else {
					System.out.println("Quantité indisponible");
				}
			}
		}
	}

}
