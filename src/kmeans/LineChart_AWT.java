/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.awt.BasicStroke;
import java.awt.Color; 

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author 1721057
 */
public class LineChart_AWT extends ApplicationFrame{
    public LineChart_AWT( String applicationTitle , String chartTitle, XYDataset dataset) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createXYLineChart(
           chartTitle,
           "Variância","Número de clusters",
           dataset);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 1280 , 670 ) );

        final XYPlot plot = (XYPlot) lineChart.getXYPlot( );
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        lineChart.getPlot().setBackgroundPaint(Color.WHITE);
        renderer.setSeriesPaint( 0 , Color.BLACK );
        renderer.setSeriesStroke( 0 , new BasicStroke( 0.8f ) );

        renderer.setSeriesLinesVisible(2, false);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();  
        xAxis.setTickUnit(new NumberTickUnit(1));
        plot.setRenderer(renderer);
        setContentPane( chartPanel ); 
   }   
}
