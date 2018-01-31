package com.ming.docker.tomcat;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author CJ
 */
public class SystemEnvironment implements Environment {
    @Override
    public int size() {
        return origin.size();
    }

    @Override
    public boolean isEmpty() {
        return origin.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return origin.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return origin.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return origin.get(key);
    }

    @Override
    public String put(String key, String value) {
        return origin.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return origin.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        origin.putAll(m);
    }

    @Override
    public void clear() {
        origin.clear();
    }

    @Override
    public Set<String> keySet() {
        return origin.keySet();
    }

    @Override
    public Collection<String> values() {
        return origin.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return origin.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return origin.equals(o);
    }

    @Override
    public int hashCode() {
        return origin.hashCode();
    }

    @Override
    public String getOrDefault(Object key, String defaultValue) {
        return origin.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super String> action) {
        origin.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super String, ? extends String> function) {
        origin.replaceAll(function);
    }

    @Override
    public String putIfAbsent(String key, String value) {
        return origin.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return origin.remove(key, value);
    }

    @Override
    public boolean replace(String key, String oldValue, String newValue) {
        return origin.replace(key, oldValue, newValue);
    }

    @Override
    public String replace(String key, String value) {
        return origin.replace(key, value);
    }

    @Override
    public String computeIfAbsent(String key, Function<? super String, ? extends String> mappingFunction) {
        return origin.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public String computeIfPresent(String key, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return origin.computeIfPresent(key, remappingFunction);
    }

    @Override
    public String compute(String key, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return origin.compute(key, remappingFunction);
    }

    @Override
    public String merge(String key, String value, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return origin.merge(key, value, remappingFunction);
    }

    private final Map<String, String> origin;

    public SystemEnvironment(Map<String, String> origin) {
        this.origin = origin;
    }

    public SystemEnvironment() {
        this(System.getenv());
    }
}
