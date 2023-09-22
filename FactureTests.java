package modele;

import ihm.FenetreCoordonnees;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FactureTests {

    private Panier panier;
    private FenetreCoordonnees formulaire;
    private Facture facture;
    private Tomate tomate1;
    private Tomate tomate2;

    @Before
    public void setUp() {
        panier = new Panier();
        formulaire = new FenetreCoordonnees();
        facture = new Facture(formulaire); 
        tomate1 = new Tomate(TypeTomate.TOMATES_CERISES, Couleur.ROUGE, "Tomate Miel du Mexique", null, "IMG_5460",
                "Description de la tomate 1", 50, 3.95F, true);
        tomate2 = new Tomate(TypeTomate.TOMATES, Couleur.ROUGE, "Tomate Joie de la Table", null,
                "Tomate-Joie-de-la-Table-ressemble-scaled", "Description de la tomate 2", 50, 3.95F, true);
    }

    @Test
    public void testSousTotalEnStringSansFraisExpedition() {
    	panier.ajouterTomate(tomate2, 2);
    	
        String sousTotal = facture.sousTotalEnString();
        assertNotEquals("4.0 €", sousTotal);
    }

    @Test
    public void testFraisExpeditionSansSousTotal() {
        panier.ajouterTomate(tomate2, 2);
        String fraisExpedition = facture.FraisExpedition();
        assertEquals("", fraisExpedition);
    }
}
