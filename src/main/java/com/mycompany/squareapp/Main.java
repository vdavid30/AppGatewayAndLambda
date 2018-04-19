/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.squareapp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import static spark.Spark.get;
import spark.*;
import static spark.Spark.init;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
/**
 *
 * @author 2114928
 */
public class Main {
    public static void main(String[] args){
        staticFileLocation("/static");
        get("/square", (Request req,Response res) -> {
            return getResponse(Integer.parseInt(req.queryParams("number")));
        });
    }
    public static String getResponse (int numero) throws MalformedURLException, IOException {
        String URL_API = "https://8qyaty9r75.execute-api.us-west-2.amazonaws.com/squareDeploy/square?value=" + String.valueOf(numero);
        URL num = new URL(URL_API);
        String response = "";
        try (BufferedReader reader 
                = new BufferedReader(new InputStreamReader(num.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                response+= inputLine;
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return response;
    }
    
}
