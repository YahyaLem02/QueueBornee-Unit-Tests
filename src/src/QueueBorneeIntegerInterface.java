package src;

public interface QueueBorneeIntegerInterface {
    public int capacite();
    public int taille();
    public boolean estVide();
    public boolean estPleine();
    public void ajouteElement(int element);
    public int supprimeElement();

}
