package modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Tomate {

	private TypeTomate typeGraine;
	private Couleur couleur;
	private String désignation;
	private String sousTitre;
	private String nomImage;
	private String description;
	private Integer nombreDeGraines;
	private float prixTTC;
	private boolean disponible;
	private List<Tomate> tomatesApparentées;
	private Integer NbSelectionné;

	public Tomate(TypeTomate typeGraine, Couleur couleur, String désignation, String sousTitre, String nomImage,
			String description, int nombreDeGraines, float prixTTC, boolean disponible) {
		this.typeGraine = typeGraine;
		this.couleur = couleur;
		this.désignation = désignation;
		this.sousTitre = sousTitre;
		this.nomImage = nomImage;
		this.description = description;
		this.nombreDeGraines = nombreDeGraines;
		this.prixTTC = prixTTC;
		this.setDisponible(disponible);
		if (this.isDisponible()) {
			this.NbSelectionné = 1;
		} else {
			this.NbSelectionné = 0;
		}
		this.tomatesApparentées = new LinkedList<Tomate>();
	}

	public TypeTomate getTypeGraine() {
		return typeGraine;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public String getDésignation() {
		return désignation;
	}

	public String getNomImage() {
		return nomImage;
	}

	public String getDescription() {
		return description;
	}

	public Integer getNombreDeGraines() {
		return nombreDeGraines;
	}

	public Integer getNbSelectionné() {
		return NbSelectionné;
	}

	public void setNbSelectionné(int quantite) {
		this.NbSelectionné = quantite;
	}

	public float getPrixTTC() {
		return prixTTC;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getSousTitre() {
		return sousTitre;
	}

	public void setSousTitre(String sousTitre) {
		this.sousTitre = sousTitre;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		if (!this.isDisponible()) {
			res.append("EPUISE ! ");
		}
		res.append(this.getTypeGraine().getDénomination() + ',');
		res.append(this.getCouleur().getDénomination() + ',');
		res.append(this.getDésignation() + ',');
		res.append(this.nombreDeGraines + " graines" + ',');
		res.append(String.valueOf(this.getPrixTTC()) + " €" + ',');
		res.append("Image : " + this.getNomImage());
		return res.toString();
	}

	public String toStringAvecDescription() {
		return this.toString() + '\n' + this.getDescription();
	}

	public String toStringAvecTomatesApparentées() {
		StringBuffer res = new StringBuffer(this.toString());
		res.append("\n Tomates apparentées : ");
		for (Tomate graine : this.getTomatesApparentées()) {
			res.append(graine.getDésignation() + " ");
		}
		return res.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tomate other = (Tomate) obj;
		return Objects.equals(désignation, other.désignation);
	}

	public String[] getTexte() {
		String[] liste = new String[11];
		liste[0] = this.getDésignation();
		liste[1] = this.getDescription();
		liste[2] = "/Tomates200x200/" + this.getNomImage() + ".jpg";
		if (this.isDisponible()) {
			liste[3] = "En Stock";
		} else {
			liste[3] = "En Rupture";
		}
		List<Tomate> TomAPP = new ArrayList<>();
		TomAPP = this.getTomatesApparentées();
		int v = 4;
		for (Tomate i : TomAPP) {
			liste[v] = i.getDésignation();
			v++;
		}
		liste[8] = this.PrixToString();
		liste[9] = this.QtéToString();
		liste[10] = String.valueOf(this.NbSelectionné);
		return (liste);
	}

	public List<Tomate> getTomatesApparentées() {
		return tomatesApparentées;
	}

	public void addTomateApparentée(Tomate tomate) {
		this.tomatesApparentées.add(tomate);
	}

	public String PrixToString() {
		return (String.valueOf(this.getPrixTTC()));
	}

	public String QtéToString() {
		return (String.valueOf(this.nombreDeGraines));
	}

}
