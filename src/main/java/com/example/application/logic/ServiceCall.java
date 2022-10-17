/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.application.logic;

import com.example.application.data.SearchDoc;
import com.example.application.data.SearchTriple;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pierpaolo
 */
public class ServiceCall {

    private static final String SERVICE_URL = "http://193.204.187.101:8083";

    public static SearchTriple[] searchTriple(String query) {
        try {
            URL url = new URL(SERVICE_URL + "/search/triple?q=" + URLEncoder.encode(query, "utf-8"));
            URLConnection connection = url.openConnection();
            connection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (reader.ready()) {
                sb.append(reader.readLine());
                sb.append("\n");
            }
            reader.close();
            Gson gson = new Gson();
            return gson.fromJson(sb.toString().trim(), SearchTriple[].class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SearchTriple[1];
    }

    public static SearchTriple[] getTriplesByPassageId(String docid) {
        try {
            URL url = new URL(SERVICE_URL + "/search/triplebydocid?docid=" + URLEncoder.encode(docid, "utf-8"));
            URLConnection connection = url.openConnection();
            connection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (reader.ready()) {
                sb.append(reader.readLine());
                sb.append("\n");
            }
            reader.close();
            Gson gson = new Gson();
            return gson.fromJson(sb.toString().trim(), SearchTriple[].class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SearchTriple[1];
    }

    public static SearchDoc getPassageById(String docid) {
        try {
            URL url = new URL(SERVICE_URL + "/search/docbyid?id=" + URLEncoder.encode(docid, "utf-8"));
            URLConnection connection = url.openConnection();
            connection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (reader.ready()) {
                sb.append(reader.readLine());
                sb.append("\n");
            }
            reader.close();
            Gson gson = new Gson();
            return gson.fromJson(sb.toString().trim(), SearchDoc.class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SearchDoc("-1");
    }

    public static SearchDoc[] searchPassageByTitle(String query) {
        try {
            URL url = new URL(SERVICE_URL + "/search/docbytitle?q=" + URLEncoder.encode(query, "utf-8"));
            URLConnection connection = url.openConnection();
            connection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (reader.ready()) {
                sb.append(reader.readLine());
                sb.append("\n");
            }
            reader.close();
            Gson gson = new Gson();
            return gson.fromJson(sb.toString().trim(), SearchDoc[].class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SearchDoc[1];
    }

    public static SearchDoc[] searchPassageById(String id) {
        try {
            URL url = new URL(SERVICE_URL + "/search/doc?id=" + URLEncoder.encode(id, "utf-8"));
            URLConnection connection = url.openConnection();
            connection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (reader.ready()) {
                sb.append(reader.readLine());
                sb.append("\n");
            }
            reader.close();
            Gson gson = new Gson();
            return gson.fromJson(sb.toString().trim(), SearchDoc[].class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SearchDoc[1];
    }

}
