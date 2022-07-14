/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.views.detail;

import com.example.application.data.SearchTriple;

/**
 *
 * @author pierpaolo
 */
public class DetailCard {
    
    private String text;
    
    private SearchTriple triple;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SearchTriple getTriple() {
        return triple;
    }

    public void setTriple(SearchTriple triple) {
        this.triple = triple;
    }

    public DetailCard(String text, SearchTriple triple) {
        this.text = text;
        this.triple = triple;
    }
    
    
    
}
