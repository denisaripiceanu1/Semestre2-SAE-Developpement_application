package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modele.Facture;
import modele.Panier;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FenetreCoordonnees extends JFrame {

	private JPanel contenu;
	private JTextField champNom;
	private JTextField champPrenom;
	private JTextField champAdresse1;
	private JTextField champAdresse2;
	private JTextField champCodePostal;
	private JTextField champVille;
	private JTextField champTelephone;
	private JTextField champMail;
	private JRadioButton casePaypal;
	private JRadioButton caseCheque;
	private JRadioButton caseCarteCredit;
	private JRadioButton caseAbonnementOui;
	private JRadioButton caseAbonnementNon;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreCoordonnees fenetre = new FenetreCoordonnees();
					fenetre.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FenetreCoordonnees() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(550, 550));
		setBounds(100, 100, 583, 409);
		contenu = new JPanel();
		contenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenu);
		contenu.setLayout(new BorderLayout(0, 0));

		JPanel panneauTitre = new JPanel();
		panneauTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contenu.add(panneauTitre, BorderLayout.NORTH);

		JLabel lblTitre = new JLabel("Vos coordonnées");
		lblTitre.setFont(new Font("Arial", Font.BOLD, 16));
		panneauTitre.add(lblTitre);

		JPanel panneauCentral = new JPanel();
		panneauCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		contenu.add(panneauCentral, BorderLayout.CENTER);
		panneauCentral.setLayout(new BoxLayout(panneauCentral, BoxLayout.Y_AXIS));

		JPanel panneauNomPrenom = new JPanel();
		panneauNomPrenom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panneauCentral.add(panneauNomPrenom);
		panneauNomPrenom.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JLabel lblNom = new JLabel("Nom:");
		panneauNomPrenom.add(lblNom);

		champNom = new JTextField();
		panneauNomPrenom.add(champNom);
		champNom.setColumns(15);

		JLabel lblPrenom = new JLabel("Prénom:");
		panneauNomPrenom.add(lblPrenom);

		champPrenom = new JTextField();
		panneauNomPrenom.add(champPrenom);
		champPrenom.setColumns(10);

		JPanel panneauAdresse1 = new JPanel();
		panneauAdresse1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panneauCentral.add(panneauAdresse1);
		panneauAdresse1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JLabel lblAdresse1 = new JLabel("Adresse 1:");
		panneauAdresse1.add(lblAdresse1);

		champAdresse1 = new JTextField();
		panneauAdresse1.add(champAdresse1);
		champAdresse1.setColumns(30);

		JPanel panneauAdresse2 = new JPanel();
		panneauAdresse2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panneauCentral.add(panneauAdresse2);
		panneauAdresse2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JLabel lblAdresse2 = new JLabel("Adresse 2:");
		panneauAdresse2.add(lblAdresse2);

		champAdresse2 = new JTextField();
		panneauAdresse2.add(champAdresse2);
		champAdresse2.setColumns(30);

		JPanel panneauVilleCP = new JPanel();
		panneauVilleCP.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panneauCentral.add(panneauVilleCP);
		panneauVilleCP.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JLabel lblVille = new JLabel("Ville:");
		panneauVilleCP.add(lblVille);

		champVille = new JTextField();
		panneauVilleCP.add(champVille);
		champVille.setColumns(15);

		JLabel lblCodePostal = new JLabel("Code postal:");
		panneauVilleCP.add(lblCodePostal);

		champCodePostal = new JTextField();
		panneauVilleCP.add(champCodePostal);
		champCodePostal.setColumns(8);

		JPanel panneauTelephone = new JPanel();
		panneauTelephone.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panneauCentral.add(panneauTelephone);
		panneauTelephone.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JLabel lblTelephone = new JLabel("Téléphone:");
		panneauTelephone.add(lblTelephone);

		champTelephone = new JTextField();
		panneauTelephone.add(champTelephone);
		champTelephone.setColumns(15);

		JPanel panneauMail = new JPanel();
		panneauMail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panneauCentral.add(panneauMail);
		panneauMail.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JLabel lblMail = new JLabel("Mail:");
		panneauMail.add(lblMail);

		champMail = new JTextField();
		panneauMail.add(champMail);
		champMail.setColumns(30);

		JPanel panneauInferieur = new JPanel();
		panneauInferieur.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		contenu.add(panneauInferieur, BorderLayout.SOUTH);
		panneauInferieur.setLayout(new BorderLayout(0, 0));

		JPanel panneauAbonnement = new JPanel();
		panneauInferieur.add(panneauAbonnement, BorderLayout.NORTH);

		JLabel lblAbonnement = new JLabel("Abonnement à la Newsletter:");
		panneauAbonnement.add(lblAbonnement);

		caseAbonnementOui = new JRadioButton("Oui");
		caseAbonnementNon = new JRadioButton("Non");

		ButtonGroup groupeAbonnement = new ButtonGroup();
		groupeAbonnement.add(caseAbonnementOui);
		groupeAbonnement.add(caseAbonnementNon);

		panneauAbonnement.add(caseAbonnementOui);
		panneauAbonnement.add(caseAbonnementNon);

		JPanel panneauPaiement = new JPanel();
		panneauInferieur.add(panneauPaiement, BorderLayout.CENTER);

		JLabel lblPaiement = new JLabel("Moyen de paiement:");
		panneauPaiement.add(lblPaiement);

		casePaypal = new JRadioButton("Paypal");
		caseCheque = new JRadioButton("Chèque");
		caseCarteCredit = new JRadioButton("Carte de crédit");

		ButtonGroup groupePaiement = new ButtonGroup();
		groupePaiement.add(casePaypal);
		groupePaiement.add(caseCheque);
		groupePaiement.add(caseCarteCredit);

		panneauPaiement.add(casePaypal);
		panneauPaiement.add(caseCheque);
		panneauPaiement.add(caseCarteCredit);

		JPanel panneauBoutons = new JPanel();
		panneauInferieur.add(panneauBoutons, BorderLayout.SOUTH);

		JButton btnAnnuler = new JButton("Annuler");
		panneauBoutons.add(btnAnnuler);
		ContrôleurBoutonAnnuler(btnAnnuler);

		JButton btnOk = new JButton("OK");
		panneauBoutons.add(btnOk);
		ContrôleurBoutonOK(btnOk);

		pack();

	}

	private void ContrôleurBoutonOK(JButton btnOk) {
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validerChamps()) {
					JOptionPane.showMessageDialog(FenetreCoordonnees.this,
							"Veuillez remplir tous les champs obligatoires.", "Champs obligatoires manquants",
							JOptionPane.ERROR_MESSAGE);
				} else if (!validerPayement() || !validerNewsletter()) {
					JOptionPane.showMessageDialog(FenetreCoordonnees.this,
							"Veuillez sélectionner le moyen de paiement et l'abonnement à la newsletter.",
							"Sélection manquante", JOptionPane.ERROR_MESSAGE);
				} else {
					// Création et ouverture de la facture
					Facture facturedonnee=new Facture(FenetreCoordonnees.this);
					List<String> donnees = facturedonnee.getDonnee();
					
					FenetreFacture facture = new FenetreFacture(donnees);
					facture.setVisible(true);

					Panier.getPanier().viderPanier();
				}
			}
		});
	}

	private void ContrôleurBoutonAnnuler(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public boolean validerChamps() {
		if (champNom.getText().isEmpty() || champPrenom.getText().isEmpty() || champAdresse1.getText().isEmpty()
				|| champCodePostal.getText().isEmpty() || champVille.getText().isEmpty()
				|| champTelephone.getText().isEmpty() || champMail.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean validerPayement() {
		// Vérifier si une option de paiement est sélectionnée
		if (casePaypal.isSelected() || caseCheque.isSelected() || caseCarteCredit.isSelected()) {
			return true;
		}
		return false;
	}

	private boolean validerNewsletter() {
		// Vérifier si l'option de la newsletter est sélectionnée
		if (caseAbonnementOui.isSelected() || caseAbonnementNon.isSelected()) {
			return true;
		}
		return false;
	}

	public List<Boolean> obtenirValidé(){
		List<Boolean> resultat = new ArrayList<>();
		resultat.add(casePaypal.isSelected());
		resultat.add(caseCheque.isSelected());
		resultat.add(caseCarteCredit.isSelected());
		resultat.add(caseAbonnementOui.isSelected());
		return resultat;
	}
	public List<String> obtenirDonnees() {
		List<String> resultat = new ArrayList<>();
		if (validerChamps()) {
			resultat.add(champNom.getText());
			resultat.add( champPrenom.getText());
			resultat.add(champAdresse1.getText());
			resultat.add(champAdresse2.getText());
			resultat.add(champCodePostal.getText());
			resultat.add( champVille.getText());
			resultat.add( champTelephone.getText());
			resultat.add(champMail.getText() + "\n");
		}

		return resultat;
	}
	public boolean estValidé() {
		return(validerChamps());
	}
	

}
