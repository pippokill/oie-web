/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.data;

import java.util.Objects;

/**
 *
 * @author pierpaolo
 */
public class Span {

    private String span;

    private int start;

    private int end;

    private float score;

    public Span(String span, int start, int end) {
        this.span = span;
        this.start = start;
        this.end = end;
        this.score = 1;
    }

    public Span(String span, int start, int end, float score) {
        this.span = span;
        this.start = start;
        this.end = end;
        this.score = score;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.span);
        hash = 89 * hash + this.start;
        hash = 89 * hash + this.end;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Span other = (Span) obj;
        if (this.start != other.start) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        if (!Objects.equals(this.span, other.span)) {
            return false;
        }
        return true;
    }

    public String getSpan() {
        return span;
    }

    public void setSpan(String span) {
        this.span = span;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Span{" + "span=" + span + ", start=" + start + ", end=" + end + '}';
    }

}
