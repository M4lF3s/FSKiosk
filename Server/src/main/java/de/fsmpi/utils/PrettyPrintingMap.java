package de.fsmpi.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Marcel Fraas on 30.08.16.
 */
public class PrettyPrintingMap<K, V> {
    private Map<K, V> map;

    public PrettyPrintingMap(Map<K, V> map) {
        this.map = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<K, V> entry = iter.next();
            sb.append(entry.getKey());
            sb.append(':').append(' ');
            sb.append(entry.getValue());
            sb.append('€');
            if (iter.hasNext()) {
                //sb.append(',').append(' ');
                sb.append('\n');
            }
        }
        return sb.toString();

    }
}