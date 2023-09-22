package modele;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import java.util.ArrayList;
import java.util.List;

import ihm.FenetreCoordonnees;

public class Facture {

	private List<String> donnee;

	public Facture(FenetreCoordonnees formulaire) {
		List<String> resultat = new ArrayList<>();
		LocalDate dateDuJour = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRANCE);
		String dateFormatee = dateDuJour.format(formatter);
		LocalTime heureActuelle = LocalTime.now();
		String heureFormatee = String.format("%02d:%02d:%02d", heureActuelle.getHour(), heureActuelle.getMinute(),
				heureActuelle.getSecond());
		resultat.add("Dégustez vos propres tomates ! \n");
		resultat.add("Toulouse, le " + dateFormatee + " 2023 à " + heureFormatee + " heure d'été en France \n");
		resultat.add("VOS INFORMATIONS : \n");
		if (formulaire.estValidé()) {
			resultat.add("Nom : " + formulaire.obtenirDonnees().get(0));
			resultat.add("Prénom : " + formulaire.obtenirDonnees().get(1));
			resultat.add("Adresse : " + formulaire.obtenirDonnees().get(2));
			resultat.add("Complément d'adresse : " + formulaire.obtenirDonnees().get(3));
			resultat.add("Code postal : " + formulaire.obtenirDonnees().get(4));
			resultat.add("Ville : " + formulaire.obtenirDonnees().get(5));
			resultat.add("Téléphone : " + formulaire.obtenirDonnees().get(6));
			resultat.add("E-mail : " + formulaire.obtenirDonnees().get(7) + "\n");
		}
		if (formulaire.obtenirValidé().get(0)) {
			resultat.add("Moyen de Paiement : Paypal \n");
		}
		if (formulaire.obtenirValidé().get(1)) {
			resultat.add("Moyen de Paiement : Chèque \n");
		}
		if (formulaire.obtenirValidé().get(2)) {
			resultat.add("Moyen de Paiement : carte de crédit \n");
		}
		if (formulaire.obtenirValidé().get(3)) {
			resultat.add("Le client est abonné à la Newsletter \n");
		} else {
			resultat.add("Le client n'est pas abonné à la Newsletter \n ");
		}
		resultat.add("VOTRE COMMANDE : \n");
		String ligneDetailCommande = "";
		for (int i = 0; i < Panier.getPanier().getTableau().size(); i++) {
			ligneDetailCommande = String.valueOf(i + 1) + " : " + Panier.getPanier().getTableau().get(i)[1]
					+ ", Prix TTC : " + Panier.getPanier().getTableau().get(i)[2] + " €" + ", quantité commandée : "
					+ Panier.getPanier().getTableau().get(i)[3] + ", Sous-Total : "
					+ Panier.getPanier().getTableau().get(i)[4];
			resultat.add(ligneDetailCommande);
		}
		resultat.add("\n");
		resultat.add("La commande             :  " + this.sousTotalEnString());
		resultat.add(this.FraisExpedition());
		resultat.add("Prix Total              :  " + Panier.getPanier().totalEnString());

		this.donnee = resultat;
	}

	public List<String> getDonnee() {
		return this.donnee;
	}

	public String sousTotalEnString() {
		if (!(Panier.getPanier()==null) || Panier.getPanier().getTotal() > 4.5F) {
			return (String.valueOf(Panier.getPanier().getTotal() - 4.5F) + " €");
		} else {
			return (String.valueOf(0.0F));
		}
	}

	public String FraisExpedition() {
		if (Panier.getPanier().getTotal() > 4.5F) {
			return ("Frais d'expédition      :   4.5 €");
		} else {
			return ("");
		}

	}

}
