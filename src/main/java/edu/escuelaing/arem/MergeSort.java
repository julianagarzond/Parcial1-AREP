/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;
import java.util.ArrayList;

/**
 *
 * @author juliana.garzon-d
 */
public class MergeSort {

    private static ArrayList<Integer> lista2;
    private ArrayList<Integer> lista;


    public MergeSort(ArrayList<Integer> lista) {
        this.lista = lista;
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> lista) {
        return merge1(lista);
    }

    private static ArrayList<Integer> merge1(ArrayList<Integer> lista) {
        int mitad = lista.size() / 2;
        ArrayList<Integer> izq = new ArrayList<>();
        ArrayList<Integer> der = new ArrayList<>();
        if (lista.size() > 1) {
            mitad = lista.size() / 2;

            for (int i = 0; i < mitad; i++)
                izq.add(lista.get(i));
            for (int j = mitad; j < lista.size(); j++)
                der.add(lista.get(j));
            merge1(izq);
            merge1(der);
            lista2=merge2(lista,der,izq);

        }

        return lista2;
    }
    private static ArrayList<Integer>merge2(ArrayList<Integer> lista, ArrayList<Integer> der, ArrayList<Integer> izq) {
        ArrayList<Integer> lis = new ArrayList<>();
        int ind = 0;
        int izquierda = 0;
        int derecha = 0;

        while (izquierda < izq.size() && derecha < der.size()) {
            if (izq.get(izquierda) < der.get(derecha)) {
                lista.set(ind, izq.get(derecha));
                izquierda++;
            } else {
                lista.set(ind, der.get(derecha));
                derecha++;
            }
            ind++;
        }

        int tempIndex = 0;
        if (izquierda >= izq.size()) {
            lis = der;
            tempIndex = derecha;
        } else {
            lis = izq;
            tempIndex = izquierda;
        }

        for (int i = tempIndex; i < lis.size(); i++) {
            lista.set(ind, lis.get(i));
            ind++;
        }
        return lista;
    }

    public static int sum(ArrayList<Integer> lista){
        int cont=0;
        for(int i=0; i<lista.size();i++){
            cont+=lista.get(i);
        }
        return cont;
    }


}
