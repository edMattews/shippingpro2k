/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.mavenproject4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

/**
 *
 * @author jezzvega
 */
public class NewClass {
    
    public static void main(String[] args) {
        
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder(URI.create("https://t-express-rest.herokuapp.com/encomiendas/"))
                .header("Content-Type", "application/json")
                .build();
        
        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(res -> {
                    
                    List<Encomiendas> encomiendas = new Gson()
                            .fromJson(res, new TypeToken<List<Encomiendas>>() {}.getType());
                    
                    encomiendas.forEach((encomienda) -> {
                        System.out.println("Id: " + encomienda.getId() + " Descripcion: " + encomienda.getDescripcion() + " Prioridad: " + encomienda.getPrioridadText());
                    });
                    
                }).join();
        
    }
    
}
