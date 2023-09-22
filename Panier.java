package modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	private List<String[]> tableau;
	private Float total;
	private List<Tomate> tomatesCommandees;
	private List<Float> prixLigne;
	public static boolean tomateDouble;
	public static Panier panier;

	public static Panier getPanier() {
		if (panier == null) {
			panier = new Panier();
		}
		return panier;
	}

	public static Panier getPanierInitial() {
		if (panier == null || panier.getTotal() == 0.0F) {
			panier = new Panier();
		}
		return panier;
	}

	public Panier() {
		this.total = 0.0F;
		this.tableau = new ArrayList<>();
		this.tomatesCommandees = new ArrayList<>();
		this.prixLigne = new ArrayList<>();
		tomateDouble = false;
	}

	public void ajouterTomate(Tomate tomate, int quantite) {
		if (this.tomatesCommandees.contains(tomate)) {
			tomateDouble = true;
		} else {
			tomateDouble = false;
		}
		tomate.setNbSelectionné(quantite);
		this.tomatesCommandees.add(tomate);
	}

	public Float getTotal() {
		return this.total;
	}

	public Float getTotalLivraison() {
		return this.total + 4.5F;
	}

	public void setQuantite(float quantite, int ligne) {
		this.tableau.get(ligne)[3] = String.valueOf(quantite);
		this.prixLigne.set(ligne, quantite * Float.valueOf(this.tableau.get(ligne)[2]));
		this.tableau.get(ligne)[4] = String.format("%.2f", prixLigne.get(ligne)) + " €";
		this.total = this.calculerTotal();
	}

	public void miseAJour() {
		if (tomateDouble) {
			float resultat = 0.0F;
			for (int i = 0; i < tableau.size(); i++) {
				resultat += prixLigne.get(i);
				if (tomatesCommandees.get(tomatesCommandees.size() - 1).getDésignation().equals(tableau.get(i)[1])) {
					float value = Float.parseFloat(tableau.get(i)[3]);
					resultat -= prixLigne.get(i); // Soustraire l'ancienne valeur de la ligne

					prixLigne.set(i, (tomatesCommandees.get(tomatesCommandees.size() - 1).getNbSelectionné() + value)
							* tomatesCommandees.get(tomatesCommandees.size() - 1).getPrixTTC());
					resultat += prixLigne.get(i); // Ajouter la nouvelle valeur de la ligne

					String[] ligne = { tomatesCommandees.get(tomatesCommandees.size() - 1).getNomImage(),
							tomatesCommandees.get(tomatesCommandees.size() - 1).getDésignation(),
							tomatesCommandees.get(tomatesCommandees.size() - 1).PrixToString(),
							String.valueOf(
									tomatesCommandees.get(tomatesCommandees.size() - 1).getNbSelectionné() + value),
							String.format("%.2f", prixLigne.get(i)) + " €" };
					tableau.set(i, ligne);
					tomateDouble = false;
					total = resultat;
					break;
				}
			}
		} else {
			float resultat = 0.0F;
			for (int j = 0; j < prixLigne.size(); j++) {
				resultat += prixLigne.get(j);
			}
			String[] ligne = { tomatesCommandees.get(tomatesCommandees.size() - 1).getNomImage(),
					tomatesCommandees.get(tomatesCommandees.size() - 1).getDésignation(),
					tomatesCommandees.get(tomatesCommandees.size() - 1).PrixToString(),
					String.valueOf(tomatesCommandees.get(tomatesCommandees.size() - 1).getNbSelectionné()),
					String.valueOf(tomatesCommandees.get(tomatesCommandees.size() - 1).getNbSelectionné()
							* tomatesCommandees.get(tomatesCommandees.size() - 1).getPrixTTC()) + " €" };
			prixLigne.add(tomatesCommandees.get(tomatesCommandees.size() - 1).getNbSelectionné()
					* tomatesCommandees.get(tomatesCommandees.size() - 1).getPrixTTC());
			tableau.add(ligne);
			resultat += prixLigne.get(prixLigne.size() - 1);
			total = resultat;
		}
	}

	public List<String[]> getTableau() {
		return this.tableau;
	}

	public void setTotal() {
		float resultat = 0.0F;
		for (int i = 0; i < this.prixLigne.size(); i++) {
			resultat += this.prixLigne.get(i);
		}
		this.total = resultat;
	}

	public void viderPanier() {
		this.tableau.clear();
		this.total = 0.0F;
		this.tomatesCommandees.clear();
		this.prixLigne.clear();
		tomateDouble = false;
	}

	public Float calculerTotal() {
		Float resultat = 0.0F;
		for (int i = 0; i < this.prixLigne.size(); i++) {
			resultat += this.prixLigne.get(i);
		}
		return resultat;
	}

	public String totalEnString() {
		return (String.valueOf(this.total) + " €");
	}

}
