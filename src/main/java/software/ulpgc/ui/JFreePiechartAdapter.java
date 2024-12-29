//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package software.ulpgc.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import software.ulpgc.model.Piechart;


public class JFreePiechartAdapter {

    public JFreePiechartAdapter() {
    }

    public static JFreeChart adapt(Piechart piechart) {
        DefaultPieDataset dataset = datasetof(piechart);

        return ChartFactory.createPieChart("",dataset,true,true,false);
    }

    private static DefaultPieDataset datasetof(Piechart piechart){
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(String category: piechart.categories()){
            dataset.setValue(category, piechart.get(category));
        }
        return dataset;


    }

}
