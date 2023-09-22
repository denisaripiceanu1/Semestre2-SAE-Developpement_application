package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modele.GenerationArticles;
import modele.Panier;
import modele.Couleur;
import modele.Tomate;
import modele.Tomates;
import modele.TypeTomate;

import java.util.List;

public class FenetreAccueil extends JFrame {

	private JPanel contentPane;
	private JList<String> listeTomates;
	private JComboBox<Couleur> couleurComboBox;
	private JComboBox<String> typeTomateComboBox;
	private Tomates tomates;
	private FenetreDescriptionTomate fenetreDescriptionActuelle;
	static JLabel labelTotalPanier;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreAccueil frame = new FenetreAccueil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FenetreAccueil() {
		Tomates.estTriéType = new String();
		Tomates.estTriéCouleur = new String();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		tomates = GenerationArticles.générationDeBaseDesTomates();

		JPanel panelTitre = new JPanel();
		contentPane.add(panelTitre, BorderLayout.NORTH);

		JLabel lblTitre = new JLabel("Nos grains de tomates");
		lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
		panelTitre.add(lblTitre);

		JLabel labelLogo = new JLabel();
		labelLogo.setText("                                ");
		labelLogo.setIcon(new ImageIcon(FenetreAccueil.class.getResource("/Tomates40x40/4.jpg")));
		panelTitre.add(labelLogo);

		JButton boutonPanier = new JButton("");
		boutonPanier.setHorizontalAlignment(SwingConstants.LEFT);
		boutonPanier.setIcon(new ImageIcon(FenetreAccueil.class.getResource("/Tomates40x40/Design sans titre.png")));
		panelTitre.add(boutonPanier);
		ContrôleurBoutonPanier(boutonPanier);

		labelTotalPanier = new JLabel("");
		panelTitre.add(labelTotalPanier);

		String totalAvecEuro = String.valueOf(Panier.getPanier().getTotal()) + " €";
		labelTotalPanier.setText(totalAvecEuro);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		listeTomates = new JList<String>();
		listeTomates.setModel(new DefaultListModel<String>());
		scrollPane.setViewportView(listeTomates);
		ContrôleurListeTomates();

		JPanel panelOptions = new JPanel();
		contentPane.add(panelOptions, BorderLayout.SOUTH);
		panelOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel icône1 = new JLabel("");
		icône1.setIcon(new ImageIcon(FenetreAccueil.class.getResource("/Tomates40x40/3.jpg")));
		panelOptions.add(icône1);

		typeTomateComboBox = new JComboBox<String>();
		typeTomateComboBox.addItem("Toutes les tomates");
		// Insérer les types de tomates à partir de l'enum TypeTomate dans le JComboBox
		for (TypeTomate type : TypeTomate.values()) {
			typeTomateComboBox.addItem(type.getDénomination());
		}
		panelOptions.add(typeTomateComboBox);
		ContrôleurTypeTomates();

		JLabel icône2 = new JLabel("");
		icône2.setIcon(new ImageIcon(FenetreAccueil.class.getResource("/Tomates40x40/1.jpg")));
		panelOptions.add(icône2);

		couleurComboBox = new JComboBox<Couleur>();
		panelOptions.add(couleurComboBox);
		ContrôleurCouleurTomates();

		JButton boutonConseils = new JButton("");
		boutonConseils.setIcon(new ImageIcon(FenetreAccueil.class.getResource("/Tomates40x40/2.jpg")));
		panelOptions.add(boutonConseils);
		ContrôleurBoutonConseils(boutonConseils);

		ajouterNomsTomatesListe();
		ajouterCouleursComboBox();
	}

	private void ContrôleurCouleurTomates() {
		couleurComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération de la couleur sélectionnée dans la JComboBox
				Couleur couleurSelectionnee = (Couleur) couleurComboBox.getSelectedItem();

				// Filtrage des tomates par couleur
				filtrerTomatesParCouleur(couleurSelectionnee);
			}
		});
	}

	private void ContrôleurTypeTomates() {
		typeTomateComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération du type sélectionné dans la JComboBox
				String typeSelectionne = (String) typeTomateComboBox.getSelectedItem();

				// Filtrage des tomates par type
				filtrerTomatesParType(typeSelectionne);
			}
		});
	}

	private void ContrôleurBoutonConseils(JButton boutonConseils) {
		boutonConseils.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ouverture de la fenêtre de conseils
				FenetreConseilsCulture fenetreConseils = new FenetreConseilsCulture();
				fenetreConseils.setVisible(true);
			}
		});
	}

	private void ContrôleurListeTomates() {
		listeTomates.addListSelectionListener(e -> {
			if (fenetreDescriptionActuelle != null) {
				// Fermeture de la fenêtre de description actuelle
				fenetreDescriptionActuelle.dispose();
				// Réinitialisation de la référence
				fenetreDescriptionActuelle = null;
			}
			// Récupération de la tomate sélectionnée dans la liste
			String tomateSelectionnee = listeTomates.getSelectedValue();
			if (tomateSelectionnee != null) {
				// Ouverture de la page de description de la tomate sélectionnée
				ouvrirPageTomate(tomateSelectionnee);
			}
		});
	}

	private void ContrôleurBoutonPanier(JButton boutonPanier) {
		boutonPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String[]> informations = Panier.getPanier().getTableau();
				if (Panier.getPanier().getTableau().isEmpty()) {
					// Afficher un message d'erreur si le panier est vide
					JOptionPane.showMessageDialog(FenetreAccueil.this, "Le panier est vide", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// Ouvrir la fenêtre panier
					FenetrePanier fenetrePanier = new FenetrePanier(informations, Panier.getPanier());
					FenetrePanier.sousTotal.setText(Panier.getPanier().getTotal() + " €");
					FenetrePanier.montantTotal.setText(Panier.getPanier().getTotalLivraison() + " €");
					fenetrePanier.setVisible(true);
				}
			}
		});
	}

	private void ajouterNomsTomatesListe() {
		// Génération des tomates de base
		Tomates tomates = GenerationArticles.générationDeBaseDesTomates();
		List<Tomate> lesTomates = tomates.getLesTomates();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Tomate tomate : lesTomates) {
			// Ajout du nom de chaque tomate à la liste
			listModel.addElement(tomate.getDésignation());
		}
		// Attribution du modèle à la JList
		listeTomates.setModel(listModel);
	}

	private void ajouterCouleursComboBox() {
		Couleur[] couleurs = Couleur.values();
		DefaultComboBoxModel<Couleur> couleurComboBoxModel = new DefaultComboBoxModel<>();
		// Ajout des couleurs réelles à la JComboBox
		for (Couleur couleur : couleurs) {
			couleurComboBoxModel.addElement(couleur);
		}
		// Attribution du modèle à la JComboBox
		couleurComboBox.setModel(couleurComboBoxModel);
	}

	private void ouvrirPageTomate(String tomate) {
		Tomate tomateSelectionnee = null;
		// Recherche de la tomate sélectionnée dans la liste des tomates
		for (Tomate t : tomates.getLesTomates()) {
			if (t.getDésignation().equals(tomate)) {
				tomateSelectionnee = t;
				break;
			}
		}
		if (tomateSelectionnee != null) {
			// Ouverture de la fenêtre de description de la tomate sélectionnée
			fenetreDescriptionActuelle = new FenetreDescriptionTomate(tomateSelectionnee, tomates);
			fenetreDescriptionActuelle.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					// Réinitialisation de la référence lorsque la fenêtre est fermée
					fenetreDescriptionActuelle = null;
				}
			});
			fenetreDescriptionActuelle.setVisible(true);
		}
	}

	private void filtrerTomatesParType(String typeSelectionne) {
		DefaultListModel<String> listModel = (DefaultListModel<String>) listeTomates.getModel();
		listModel.clear();
		String comparer = new String();
		if (!(Tomates.estTriéCouleur).equals(comparer)) {

			if (typeSelectionne.equals("Toutes les tomates")) {
				List<Tomate> tomatesFiltrées = tomates
						.filtrerTomatesParCouleur(Couleur.getCouleur(Tomates.estTriéCouleur));
				for (Tomate tomate : tomatesFiltrées) {
					listModel.addElement(tomate.getDésignation());
				}
			} else {
				TypeTomate type = TypeTomate.getTypeTomate(typeSelectionne);
				List<Tomate> tomatesFiltrées = tomates.filtrerTomatesParTypeEtCouleur(type,
						Couleur.getCouleur(Tomates.estTriéCouleur));
				for (Tomate tomate : tomatesFiltrées) {
					listModel.addElement(tomate.getDésignation());
				}
			}
		} else {
			if (typeSelectionne.equals("Toutes les tomates")) {
				for (Tomate tomate : tomates.getLesTomates()) {
					listModel.addElement(tomate.getDésignation());
				}
			} else {
				TypeTomate type = TypeTomate.getTypeTomate(typeSelectionne);
				if (type != null) {
					List<Tomate> tomatesFiltrées = tomates.filtrerTomatesParType(type);
					for (Tomate tomate : tomatesFiltrées) {
						listModel.addElement(tomate.getDésignation());
					}
				}
			}
		}

		// Mise à jour du modèle de la JList
		listeTomates.setModel(listModel);
		Tomates.estTriéType = typeSelectionne;
	}

	private void filtrerTomatesParCouleur(Couleur couleurSelectionnee) {

		DefaultListModel<String> listModel = (DefaultListModel<String>) listeTomates.getModel();
		listModel.clear();
		String comparer = new String();
		if (!(Tomates.estTriéType).equals(comparer)) {

			if (Tomates.estTriéType.equals("Toutes les tomates")) {
				List<Tomate> tomatesFiltrées = tomates.filtrerTomatesParCouleur(couleurSelectionnee);
				for (Tomate tomate : tomatesFiltrées) {
					listModel.addElement(tomate.getDésignation());
				}
			} else {

				List<Tomate> tomatesFiltrées = tomates.filtrerTomatesParTypeEtCouleur(
						TypeTomate.getTypeTomate(Tomates.estTriéType), couleurSelectionnee);
				for (Tomate tomate : tomatesFiltrées) {
					listModel.addElement(tomate.getDésignation());
				}
			}
		} else { 

			List<Tomate> tomatesFiltrées = tomates.filtrerTomatesParCouleur(couleurSelectionnee);
			for (Tomate tomate : tomatesFiltrées) {
				listModel.addElement(tomate.getDésignation());
			}
		}

		// Mise à jour du modèle de la JList
		listeTomates.setModel(listModel);
		Tomates.estTriéCouleur = couleurSelectionnee.toString();
	}
}