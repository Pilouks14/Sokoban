package utulities;
/**
 * L'interface ModeleEcouteur permet à une instance l'implémantant
 * de se faire écouter par un écouteurs
 */

public interface ModeleEcouteur {

    /**
     * Permet d'ajouter un écouteur
     * @param e Instance d'un objet implémentant EcouteurModele
     */
    void ajoutEcouteur(utulities.EcouteurModele e);

    /**
     * Permet de retirer un écouteur
     * @param e Instance d'un objet implémentant EcouteurModele
     */
    void retraitEcouteur(utulities.EcouteurModele e);


}
