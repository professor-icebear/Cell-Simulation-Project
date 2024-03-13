import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class represents the code for the 2 pie charts that help compare the initial and final state of the simulation.
 * 
 * @author Mohammad Abdullah & Aryaman Mehta
 * @version 2022.01.06
 */

public class StatChart extends JFrame {

    // Data before and after the simulation
    private List<Integer> dataBefore;
    private List<Integer> dataAfter;

    // Constructor for the StatChart class
    public StatChart(String title, List<Integer> dataBefore, List<Integer> dataAfter) {
        super(title);

        // Initialize data
        this.dataBefore = dataBefore;
        this.dataAfter = dataAfter;

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        // Create the first chart panel
        JPanel chartPanel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g, getWidth(), getHeight(), dataBefore, new Color[]{Color.ORANGE, new Color(102, 221, 170), new Color(135, 206, 250), new Color(255, 87, 51)});
            }
        };

        // Create the label panel for the first chart
        JPanel labelPanel1 = new JPanel();
        labelPanel1.add(new JLabel("Before simulation"));

        // Set layout and add components to the first chart panel
        chartPanel1.setLayout(new BorderLayout());
        chartPanel1.add(labelPanel1, BorderLayout.NORTH);
        mainPanel.add(chartPanel1);

        // Create the second chart panel
        JPanel chartPanel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g, getWidth(), getHeight(), dataAfter, new Color[]{Color.ORANGE, new Color(102, 221, 170), new Color(135, 206, 250), new Color(255, 87, 51)});
            }
        };

        // Create the label panel for the second chart
        JPanel labelPanel2 = new JPanel();
        labelPanel2.add(new JLabel("After simulation"));

        // Set layout and add components to the second chart panel
        chartPanel2.setLayout(new BorderLayout());
        chartPanel2.add(labelPanel2, BorderLayout.NORTH);
        mainPanel.add(chartPanel2);

        // Set the main panel as the content pane
        setContentPane(mainPanel);
    }

    // Method to draw a pie chart
    private void drawPieChart(Graphics g, int width, int height, List<Integer> values, Color[] colors) {
        int total = values.stream().mapToInt(Integer::intValue).sum();

        int startAngle = 0;
        for (int i = 0; i < values.size(); i++) {
            int arcAngle = (int) Math.round((double) values.get(i) / total * 360);
            g.setColor(colors[i]);
            g.fillArc(50, 50, width - 100, height - 100, startAngle, arcAngle);
            startAngle += arcAngle;
        }
    }

    // Main method to create and display the StatChart
    public static void main(List<Integer> dataBefore, List<Integer> dataAfter) {
        SwingUtilities.invokeLater(() -> {
            StatChart pieCharts = new StatChart("Statistics Chart", dataBefore, dataAfter);
            pieCharts.setSize(400, 900);

            // Get screen dimensions
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Set the location of the window to the right side of the screen
            int x = (int) (screenSize.getWidth() - pieCharts.getWidth());
            int y = 0; // You can adjust this value based on your preference

            // Set window location and properties
            pieCharts.setLocation(x, y);
            pieCharts.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            pieCharts.setVisible(true);
        });
    }
}
