package utulities;

import java.util.ArrayList;

/**
 * Classe abstraite qui permet le fonctionnement des écouteurs en implémentant ModeleEcouteur
 */
abstract public class AbstractModeleEcouteur implements ModeleEcouteur {

    private ArrayList<EcouteurModele> ecouteurModeles = new ArrayList<>();

    /**
     * Permet d'ajouter un écouteur
     * @param e Instance d'un objet implémentant EcouteurModele
     */
    @Override
    public void ajoutEcouteur(EcouteurModele e) {
        ecouteurModeles.add(e);
    }

    /**
     * Permet de retirer un écouteur
     * @param e Instance d'un objet implémentant EcouteurModele
     */
    @Override
    public void retraitEcouteur(EcouteurModele e) {
        ecouteurModeles.remove(e);
    }

    /**
     * Détecte un changement et permet d'actualiser la vue
     */
    protected void fireChangement() {
        for (EcouteurModele e : ecouteurModeles) {
            e.modeleMisAJour(this);
        }
    }
}