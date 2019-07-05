package com.library.client.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Liste qui combine deux listes.
 * @param <T1> 1er liste.
 * @param <T2> 2eme liste.
 */
public class ExtendedList<T1, T2> {

    List<T1> keys;
    List<T2> values;

    public ExtendedList () {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    /**
     * Optenir la première liste
     * @return la première liste
     */
    public List<T1> getKeys() {
        return keys;
    }

    /**
     * Obtenir la deuxième liste
     * @return la deuxième liste
     */
    public List<T2> getValues() {
        return values;
    }

    /**
     * Obtenir un élément de la première liste à l'index i
     * @param i l'index
     * @return élément de la première liste
     */
    public T1 getKey(int i) {
        return keys.get(i);
    }

    /**
     * Obtenir un élément de la deuxième liste à l'index i
     * @param i l'index
     * @return élément de la deuxième liste
     */
    public T2 getValue(int i) {
        return values.get(i);
    }

    /**
     * Obtenir un élément de la deuxième liste à l'index de l'élément de la première liste fournit
     * @param key élément de la première liste
     * @return élément de la deuxième liste
     */
    public T2 getValue(T1 key) {
        return values.get(keys.indexOf(key));
    }

    /**
     * Supprime les éléments des deux listes
     */
    public void clear() {
        keys.clear();
        values.clear();
    }

    /**
     * Ajoute un éléments
     * @param key élément de la première liste
     * @param value élément de la deuxième liste
     */
    public void add (T1 key, T2 value) {
        keys.add(key);
        values.add(value);
    }
}
