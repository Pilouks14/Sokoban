package utulities;
/**
 * Interface qui permet d'écouter
 */
public interface EcouteurModele {
    /**
     * Met à jour en fonction du changement à la source
     * @param source Détection d'un changement d'état
     */
    void modeleMisAJour(Object source);

}
