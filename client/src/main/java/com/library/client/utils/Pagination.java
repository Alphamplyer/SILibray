package com.library.client.utils;

import java.util.List;

/**
 * Système de pagination.
 * @param <T> Type de la liste à séparer.
 */
public class Pagination<T> {

    private int itemByPage;
    private int itemNumber;
    private List<T> items;

    public Pagination(int itemByPage, List<T> items) {
        this.itemByPage = itemByPage;
        this.itemNumber = items.size();
        this.items = items;
    }

    /**
     * retourne le nombre d'élément par page.
     * @return le nombre d'élément par page.
     */
    public int getItemByPage() {
        return itemByPage;
    }

    /**
     * Défini le nombre d'élément par page.
     * @param itemByPage le nombre d'élément par page.
     */
    public void setItemByPage(int itemByPage) {
        this.itemByPage = itemByPage;
    }

    /**
     * Obtient le nombre d'éléments dans la liste.
     * @return le nombre d'éléments dans la liste.
     */
    public int getItemNumber() {
        return itemNumber;
    }

    /**
     * Obtient le nombre de page maximum.
     * @return le nombre de page maximum.
     */
    public int getMaxPage() {
        return (int)Math.ceil(itemNumber / (double)itemByPage);
    }

    /**
     * Obtient la liste d'éléments de la page fourni.
     * @param page la page.
     * @return la liste d'éléments de la page fourni.
     */
    public List<T> getPageItem(int page) {
        if (page > getMaxPage())
            return null;

        if (hasNextPage(page))
            return items.subList((page - 1) * itemByPage, page * itemByPage);

        return items.subList((page - 1) * itemByPage, items.size());
    }

    /**
     * Obtient si oui ou non il y a une page précédente en fonction de la page.
     * @param page la page
     * @return true s"il y a une page précédente, false sinon.
     */
    public boolean hasPrevPage(int page) {
        return page > 1;
    }

    /**
     * Obtient si oui ou non il y a une page suivante en fonction de la page.
     * @param page la page
     * @return true s"il y a une page suivante, false sinon.
     */
    public boolean hasNextPage(int page) {
        return page < getMaxPage();
    }
}
