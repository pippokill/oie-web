/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.application.data;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public class SearchTripleComparator implements Comparator<List<SearchTriple>> {

    @Override
    public int compare(List<SearchTriple> o1, List<SearchTriple> o2) {
        return Integer.compare(o1.size(), o2.size());
    }

}
