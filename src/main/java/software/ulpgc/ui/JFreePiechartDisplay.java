package software.ulpgc.ui;

import org.jfree.chart.ChartPanel;
import software.ulpgc.model.Piechart;

import javax.swing.*;

public class JFreePiechartDisplay extends JPanel implements PiechartDisplay {
    public JFreePiechartDisplay() {
    }

    public void display(Piechart barchart) {
        this.removeAll();
        this.add(new ChartPanel(JFreePiechartAdapter.adapt(barchart)));
        this.revalidate();
        this.repaint();
    }
}
