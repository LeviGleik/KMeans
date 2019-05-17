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
import java.util.Arrays;


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
        BufferedReader centroid = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\1721057\\Documents\\NetBeansProjects\\KMeans\\src\\kmeans\\agrup_centroides_Q1.csv")));
        BufferedReader agrupamento = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\1721057\\Documents\\NetBeansProjects\\KMeans\\src\\kmeans\\agrupamento_Q1.csv")));
        String linha = null;
        Double[][] centroidCSV = new Double[12][5];
//        Double[][] centroidCentralizado;
        Double[][] agrupamentoCSV = new Double[1000][5];
        String[] dadosCentroidCSV;
        String[] dadosAgrupamentoCSV;
        Double[][] novoAgrup;
        Double[] classeAgrup;
        int ic = 0;
        int ia = 0;
        int k_nums = 4;
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
        novoAgrup = agrupamentoCSV;
        classeAgrup = classificarAgrup(agrupamentoCSV, centroidCSV, k_nums);
        for (int i = 0; i < 1000; i++) {
            novoAgrup[i][4] = classeAgrup[i];
        } 
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(centroidCSV[i][j]);
            }            
            System.out.println("__________________________");
        }
        centralizarCentroids(novoAgrup, centroidCSV, k_nums);
//        classificarAgrup(agrupamentoCSV, centroidCSV, k_nums);
//        centralizarCentroids(novoAgrup, centroidCSV, k_nums);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(centroidCSV[i][j]);
            }            
            System.out.println("__________________________");
        }
        
//        for(int i = 0; i < 1000; i++){
////            System.out.println("Distância c1 " + dist[i][0]);
////            System.out.println("Distância c1 " + dist[i][1]);
////            System.out.println("Distância c1 " + dist[i][2]);
////            System.out.println("Distância c1 " + dist[i][3]);
////            System.out.println("Distância c1 " + dist[i][4]);
//            System.out.println("Argup novo " + novoAgrup[i][4]);
//            System.out.println("--------------------");
//        }
        //guarda 1000 distancias
        //centroid estabilizar que calcula as distancias
    }
    public static Double[] classificarAgrup(Double[][] agrup, Double[][] centroid, int clusters) {
        Double[] novoAgrup = new Double[1000];
        Double[][] dist = new Double[1000][clusters];
        Double menor = 0.0;
        int indMenor = 0;
        int n = 0;
        for (int i = 0, k = 0; i < 1000; i++, k = 0) {
            while (k < (clusters)) {
                dist[i][k] = distanciaAB(agrup[i], centroid[k]);
                k++;
            }
        }
        menor = dist[0][0];
        for (int i = 0; i < 1000; i++) {
            novoAgrup[i] =  1.0 + Double.parseDouble(menorArray(dist[i]));
        }
//        System.out.println(dist[0].length);
//        for(int i = 0; i < 1000; i++){
////            System.out.println("Distância c1 " + dist[i][0]);
////            System.out.println("Distância c1 " + dist[i][1]);
////            System.out.println("Distância c1 " + dist[i][2]);
////            System.out.println("Distância c1 " + dist[i][3]);
////            System.out.println("Distância c1 " + dist[i][4]);
//            System.out.println("Argup novo " + novoAgrup[i]);
//            System.out.println("--------------------");
//        }
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
    public static Double[][] centralizarCentroids(Double[][] agrupamento, Double[][] centroid, int k) {
        Double[] aux;
        Double[] soma = new Double[4];
        Integer[] qtde = new Integer[k];
        Arrays.fill(qtde, 0);
        Arrays.fill(soma, 0.0);
        int n = 0;
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.println(centroid[i][j]);
//            }            
//            System.out.println("__________________________");
//        }
        while (n < k) {
            for (int i = 0; i < 1000; i++) {
                if ((double) (n+1) == agrupamento[i][4]) {
                    qtde[n]++;
                }
            }
            n++;
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 999; j++) {
                if (agrupamento[i][4] < agrupamento[j][4]) {
                    aux = agrupamento[i];
                    agrupamento[i] = agrupamento[j];
                    agrupamento[j] = aux;
                }
            }
        }
        for (int i = 0, m = 0; i < k; i++) {
            for (int j = 0; j < qtde[i]; j++, m++) {
                for (int l = 0; l < 4; l++) {
                    soma[l] += agrupamento[m][l];
                }
            }
            for (int j = 1, o = 0; j < 5; j++, o++) {  
                centroid[i][j] = soma[o]/qtde[i];
            }
        }
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.println(centroid[i][j]);
//            }
//            System.out.println("__________________________");
//        }
        return centroid;
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
//    public static double distanciasMedias(Double[][] agrupamento) {
//        double soma = 0;
//        double media;
//        for (int i = 0; i < 1000; i++) {
//            for(int j = 0; j < 4; j++) {
//                soma += agrupamento[i]
//            }
//        }
//        return media;
//    }
}
    

