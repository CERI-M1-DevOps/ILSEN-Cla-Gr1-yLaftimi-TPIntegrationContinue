package liste;

/**
 * Liste simplement chaînée d'éléments, construite à partir de {@link Noeud}.
 * Les ajouts se font en tête de liste.
 */
public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne le nombre d'éléments présents dans la liste.
     * @return le nombre d'éléments de la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un élément en tête de liste et incrémente la taille.
     * @param element la valeur à ajouter
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Remplace la valeur de la première occurrence d'un élément dans la liste.
     * Ne fait rien si l'élément est absent.
     * @param element l'élément à rechercher
     * @param nouvelleValeur la valeur qui remplace l'élément trouvé
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Remplace la valeur de toutes les occurrences d'un élément dans la liste.
     * @param element l'élément à rechercher
     * @param nouvelleValeur la valeur qui remplace chaque occurrence trouvée
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Construit une représentation textuelle de la liste.
     * @return la liste sous la forme "ListeSimple(Noeud(a), Noeud(b), ...)"
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime la première occurrence d'un élément de la liste et décrémente la taille.
     * Ne fait rien si l'élément est absent.
     * @param element l'élément à supprimer
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences d'un élément de la liste.
     * @param element l'élément à supprimer
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Supprime récursivement toutes les occurrences d'un élément à partir d'un noeud donné.
     * @param element l'élément à supprimer
     * @param tete le noeud à partir duquel effectuer la suppression
     * @return la nouvelle tête de la sous-liste, sans l'élément supprimé
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne l'avant-dernier noeud de la liste.
     * @return l'avant-dernier noeud, ou null si la liste contient moins de deux éléments
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
            
        }
    }

    /**
     * Inverse l'ordre des éléments de la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne le noeud qui précède un noeud donné dans la liste.
     * Le noeud doit appartenir à la liste et ne pas être la tête.
     * @param r le noeud dont on cherche le précédent
     * @return le noeud situé juste avant r
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange la position de deux noeuds de la liste.
     * Ne fait rien si les deux noeuds sont identiques.
     * @param r1 le premier noeud à échanger
     * @param r2 le second noeud à échanger
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        } else { // ici r2 == tete
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}