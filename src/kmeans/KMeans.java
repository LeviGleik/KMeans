/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author levig
 */
public class KMeans {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader centroid = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\levig\\Documents\\NetBeansProjects\\KMeans\\src\\kmeans\\agrup_centroides_Q1.csv")));
        BufferedReader agrupamento = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\levig\\Documents\\NetBeansProjects\\KMeans\\src\\kmeans\\agrupamento_Q1.csv")));
        String linha = null;
        Double[][] centroidCSV = new Double[12][5];
        Double[][] agrupamentoCSV = new Double[1000][4];
        String[] dadosCentroidCSV;
        String[] dadosAgrupamentoCSV;
        Double[] t = new Double[1000];
        int ic = 0;
        int ia = 0;
        while ((linha = centroid.readLine()) != null) {
            dadosCentroidCSV = linha.split(",");
            centroidCSV[ic][0] = Double.parseDouble(dadosCentroidCSV[0]);
            centroidCSV[ic][1] = Double.parseDouble(dadosCentroidCSV[1]);
            centroidCSV[ic][2] = Double.parseDouble(dadosCentroidCSV[2]);
            centroidCSV[ic][3] = Double.parseDouble(dadosCentroidCSV[3]);
            centroidCSV[ic][4] = Double.parseDouble(dadosCentroidCSV[4]);
            ic++;
        }
        while ((linha = agrupamento.readLine()) != null) {
            dadosAgrupamentoCSV = linha.split(",");
            agrupamentoCSV[ia][0] = Double.parseDouble(dadosAgrupamentoCSV[0]);
            agrupamentoCSV[ia][1] = Double.parseDouble(dadosAgrupamentoCSV[1]);
            agrupamentoCSV[ia][2] = Double.parseDouble(dadosAgrupamentoCSV[2]);
            agrupamentoCSV[ia][3] = Double.parseDouble(dadosAgrupamentoCSV[3]);
            ia++;
        }
        t = classificarAgrup(agrupamentoCSV, centroidCSV, 5);
    }
    public static Double[] classificarAgrup(Double[][] agrup, Double[][] centroid, int clusters) {
        Double[] novoAgrup = new Double[1000];
        Double[][] dist = new Double[1000][clusters];
        Double menor = 0.0;
        int indMenor = 0;
        int n = 0;
        for (int i = 0, k = 0; i < 1000; i++) {
            while (k < (clusters)) {
                dist[i][k] = distanciaAB(agrup[i], centroid[k]);
                k++;
            }
            k = 0;
        }
        menor = dist[0][0];
        for (int i = 0; i < 1000; i++) {
            novoAgrup[i] =  1.0 + Double.parseDouble(menorArray(dist[i]));
        }
        System.out.println(dist[0].length);
        for(int i = 0; i < 1000; i++){
            System.out.println("Distância c1 " + dist[i][0]);
            System.out.println("Distância c1 " + dist[i][1]);
            System.out.println("Distância c1 " + dist[i][2]);
            System.out.println("Distância c1 " + dist[i][3]);
            System.out.println("Distância c1 " + dist[i][4]);
            System.out.println("Argup novo " + novoAgrup[i]);
            System.out.println("--------------------");
        }
        return novoAgrup;
    }
    public static double distanciaAB(Double[] a, Double[] b) {
        double result = 0;
        for(int i = 0; i < 4; i++) {
            result += Math.pow((a[i] - b[i+1]), 2);
        }
        result = Math.sqrt(result);
        return result;
    }
    public static String menorArray(Double[] array) {
        Double menor = array[0];
        String indMenor = null;
        for (Integer i = 0; i < array.length; i++) {
            if(menor >= array[i]) {
                menor = array[i];
                indMenor =  i.toString();
            }
        }
        return indMenor;
    }
}
    

