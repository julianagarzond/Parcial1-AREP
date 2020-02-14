/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Preconditions;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.regex.Pattern;

import static spark.Spark.*;
/**
 *
 * @author juliana.garzon-d
 */
public class WebService {

    private static ArrayList<Integer> lista;
    private static ArrayList<Integer> lista2;
    private static int sumatoria;

    public static void main(String[] args) {

        lista = new ArrayList<Integer>();
        lista2=new ArrayList<Integer>();
        port(getPort());
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));


    }

    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Ordenamiento</h2>"
                + "<form action=\"/results\">"
                + "Lista de números: Ingrese los valores separados por un guion (-)<br>"
                + "<br>"
                + " <input type=\"text\" name='numbers'>"
                + " <br>"
                + "<br>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "<input type=\"reset\" value=\"Reset\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }


    /**
     * Metodo para crear la pagina que muestra el resultado de la operacion
     *
     * @param req pagina request
     * @param res pagina res
     */
    private static String resultsPage(Request req, Response res) {
        String separator = Pattern.quote("-");

        String[] numbers = req.queryParams("numbers").split(separator);
        int value;

        for (String n : numbers) {
            value = Integer.parseInt(n);
            lista.add(value);
        }

        // Lista json
        lista2=MergeSort.mergeSort(lista);
        GsonBuilder gsonBuilder =new GsonBuilder();
        Gson gson =gsonBuilder.create();
        String json =gson.toJson(lista2);
        Gson fin=new GsonBuilder().create();
        String listafin=fin.toJson(lista2);

        // sumatoria json
        sumatoria=MergeSort.sum(lista);
        String result =Integer.toString(sumatoria);
        Gson g= new Gson();
        String str = g.toJson(result);

        return listafin + result;


    }






        /**
         * This method reads the default port as specified by the PORT variable in
         * the environment.
         *
         * Heroku provides the port automatically so you need this to run the
         * project on Heroku.
         */
        static int getPort () {
            if (System.getenv("PORT") != null) {
                return Integer.parseInt(System.getenv("PORT"));
            }
            return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
        }

    }


