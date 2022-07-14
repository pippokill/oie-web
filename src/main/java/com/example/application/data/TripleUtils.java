/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.application.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author pierpaolo
 */
public class TripleUtils {

    public static Map<String, List<SearchTriple>> collapse(SearchTriple[] triples, Function<SearchTriple, String> function) {
        List<SearchTriple> triplesList = Arrays.asList(triples);
        Map<String, List<SearchTriple>> map = triplesList.stream().collect(Collectors.groupingBy(function));
        map.values().stream().forEach(v -> Collections.sort(v, Collections.reverseOrder()));
        return map;
    }

    public static List<List<SearchTriple>> collapseAndSort(SearchTriple[] triples, Function<SearchTriple, String> function) {
        List<SearchTriple> triplesList = Arrays.asList(triples);
        Map<String, List<SearchTriple>> map = triplesList.stream().collect(Collectors.groupingBy(function));
        map.values().stream().forEach(v -> Collections.sort(v, Collections.reverseOrder()));

        List<List<SearchTriple>> l = new ArrayList<>(map.values());
        Collections.sort(l, new SearchTripleComparator());
        Collections.reverse(l);
        return l;
    }

}
