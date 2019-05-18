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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;


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
        String linha;
        Double[][] centroidCSV = new Double[12][5];
        Double[][] centroidCentralizado;
        Double[][] agrupamentoCSV = new Double[1000][5];
        String[] dadosCentroidCSV;
        String[] dadosAgrupamentoCSV;
        Double[][] novoAgrup;
        Double[] classeAgrup;
        int ic = 0;
        int ia = 0;
        int k_nums = 2;
        Double[] menorDist = new Double[1000];        
        XYSeries varianciaXY = new XYSeries("Variância");
        
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
        classeAgrup = classificarAgrup(agrupamentoCSV, centroidCSV, 3, menorDist);
        for (int i = 0; i < 1000; i++) {
            novoAgrup[i][4] = classeAgrup[i];
        }        
        centroidCentralizado = centralizarCentroids(novoAgrup, centroidCSV, 3);
        int f = 0;
        while(k_nums < 12) {
            
            
            classeAgrup = classificarAgrup(novoAgrup, centroidCentralizado, 3, menorDist);
            for (int i = 0; i < 1000; i++) {
                novoAgrup[i][4] = classeAgrup[i];
            }
            centralizarCentroids(novoAgrup, centroidCentralizado, 3);
            varianciaXY.add(k_nums,variancia(menorDist)/k_nums);            
//            System.out.println("Soma " + variancia(menorDist)/k_nums);

            k_nums++;
        }
        k_nums = 4;
        for (int i = 0; i < k_nums; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(centroidCentralizado[i][j]);
            }            
            System.out.println("__________________________");
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(varianciaXY);
        LineChart_AWT chart = new LineChart_AWT("Gráfico", "", dataset);
        chart.pack( );          
        RefineryUtilities.centerFrameOnScreen( chart );          
        chart.setVisible( true );
    }
    public static Double[] classificarAgrup(Double[][] agrup, Double[][] centroid, int clusters, Double[] menorDist) {
        Double[] classeAgrup = new Double[1000];
        Double[][] dist = new Double[1000][clusters];
        int k = 0;
        while (k < (clusters)) {
            for (int i = 0, n = 0; i < 1000; i++) {
                dist[i][k] = distanciaAB(agrup[i], centroid[k]);
            }               
            k++;
        }
        for (int i = 0; i < 1000; i++) {
            classeAgrup[i] =  1.0 + Double.parseDouble(menorArray(dist[i]));
            menorDist[i] = menorDistancia(dist[i]);
        }
        return classeAgrup;
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
        Integer[][] pos = new Integer[k][1000];
        Double[][] soma = new Double[k][4];
        Double[][] novoAgrup = agrupamento;
        Double[] classeAgrup = new Double[1000];
        Double[][] novoCentroid = centroid;
        Integer[] qtde = new Integer[k];
        Arrays.fill(qtde, 0);
        for (int i = 0; i < k; i++) {
            Arrays.fill(soma[i], 0.0);
        }
        for (int i = 0; i < k; i++) {
            Arrays.fill(pos[i], 0);
        }
        for (int i = 0; i < 1000; i++) {
            classeAgrup[i] = novoAgrup[i][4];
        }
        int n = 0, m = 0;
        for (int i = 0, p = 0; i < 1000; i++) {
            while ((double) (n+1) != classeAgrup[i]) {
                n++;
            }                
            pos[n][qtde[n]] = i;
            qtde[n]++;
            n = 0;
        }
        while (n < k) {
            for (int i = 0; i < 1000; i++) {
                if (pos[n][m] == i) {
                    for (int j = 0; j < 4; j++) {
                        soma[n][j] += agrupamento[i][j];
                    }
                    m++;
                }
            }                                    

            n++;
            m = 0;
        }
        for (int i = 0; i < k; i++) {
            for (int j = 1; j < 5; j++) {
                novoCentroid[i][j] = soma[i][j-1]/qtde[i];
            }
        }
        return novoCentroid;
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
    public static Double menorDistancia(Double[] array) {
        Double menor = array[0];
        for (int i = 0; i < array.length; i++) {
            if(menor >= array[i]) {
                menor = array[i];
            }
        }
        return menor;
    }
    public static Double variancia(Double[] dist) {
        Double soma = 0.0;

        for (int i = 0; i < 1000; i++) {
            soma += dist[i];
        }
        return soma;
    }
}
    

