package com.library.client.utils;

import java.util.ArrayList;
import java.util.List;

public class ExtendedList<T1, T2> {

    List<T1> keys;
    List<T2> values;

    public ExtendedList () {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    public List<T1> getKeys() {
        return keys;
    }

    public List<T2> getValues() {
        return values;
    }

    public T1 getKey(int i) {
        return keys.get(i);
    }

    public T2 getValue(int i) {
        return values.get(i);
    }

    public T2 getValue(T1 key) {
        return values.get(keys.indexOf(key));
    }

    public void clear() {
        keys.clear();
        values.clear();
    }

    public void add (T1 key, T2 value) {
        keys.add(key);
        values.add(value);
    }
}
