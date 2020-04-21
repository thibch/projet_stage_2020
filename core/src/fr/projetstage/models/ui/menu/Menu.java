package fr.projetstage.models.ui.menu;

/**
 * interface permettant aux boutons d'être utilisés plus facilement dans les différents menus
 */
public interface Menu {
    /**
     * Methode permettant a un bouton de prevenir qu'il a été cliqué
     * @param btnName le nom du bouton cliqué
     */
    void onClick(String btnName);
}
