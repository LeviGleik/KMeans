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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\levig\\Documents\\NetBeansProjects\\KMeans\\src\\kmeans\\agrup_centroides_Q1.csv")))) {
            String linha = null;
            Double[][] centroidCSV = new Double[12][5];
            String[] dadosCentroidCSV;
            int ic = 0;
            while ((linha = reader.readLine()) != null) {
                dadosCentroidCSV = linha.split(",");
                centroidCSV[ic][0] = Double.parseDouble(dadosCentroidCSV[0]);
                centroidCSV[ic][1] = Double.parseDouble(dadosCentroidCSV[1]);
                centroidCSV[ic][2] = Double.parseDouble(dadosCentroidCSV[2]);
                centroidCSV[ic][3] = Double.parseDouble(dadosCentroidCSV[3]);
                centroidCSV[ic][4] = Double.parseDouble(dadosCentroidCSV[4]);
                ic++;
            }
            for (int i = 0; i < ic; i++) {
                System.out.println("I: " + centroidCSV[i][0]);
                System.out.println("X1: " + centroidCSV[i][1]);
                System.out.println("X2: " + centroidCSV[i][2]);
                System.out.println("X3: " + centroidCSV[i][3]);
                System.out.println("X4: " + centroidCSV[i][4]);
            }
        }
        
    }
    
}
