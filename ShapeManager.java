import javax.swing.*;
import java.awt.*;

public class ShapeManager extends JFrame {
    private JPanel listContainer; // This will hold our rows
    private JScrollPane scrollPane;

    public ShapeManager() {
        setTitle("Shape Manager with Actions");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Setup the list container (Vertical Layout)
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(listContainer);
        add(scrollPane, BorderLayout.CENTER);

        // Control Panel (Adding Shapes)
        JPanel controls = new JPanel(new GridLayout(1, 4));
        String[] shapes = {"Circle", "Square", "Rectangle", "Triangle"};

        for (String type : shapes) {
            JButton btn = new JButton("Add " + type);
            btn.addActionListener(e -> promptForShape(type));
            controls.add(btn);
        }
        add(controls, BorderLayout.SOUTH);
    }

    private void addShapeToList(Shape shape) {
        // Create a "Row" Panel
        JPanel row = new JPanel(new BorderLayout());
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        // JLabel label = new JLabel(" " + shape.getClassName());
        JLabel label = new JLabel("  " + shape);
        JButton showAreaBtn = new JButton("Show Area");

        // The button action: Open a new small window (Dialog)
        showAreaBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "The area of this " + shape.getClassName() + " is: "
                            + String.format("%.2f", shape.getArea()),
                    "Shape Area", JOptionPane.INFORMATION_MESSAGE);
        });

        row.add(label, BorderLayout.CENTER);
        row.add(showAreaBtn, BorderLayout.EAST);

        listContainer.add(row);
        listContainer.revalidate(); // Refresh the UI
        listContainer.repaint();
    }

    private void promptForShape(String type) {
        try {
            if (type.equals("Circle")) {
                double r = Double.parseDouble(JOptionPane.showInputDialog("Radius:"));
                addShapeToList(new Circle(r));
            } else if (type.equals("Square")) {
                double s = Double.parseDouble(JOptionPane.showInputDialog("Side:"));
                addShapeToList(new Square(s));
            } else if (type.equals("Rectangle")) {
                double w = Double.parseDouble(JOptionPane.showInputDialog("Width:"));
                double h = Double.parseDouble(JOptionPane.showInputDialog("Height:"));
                addShapeToList(new Rectangle(w, h));
            } else if (type.equals("Triangle")) {
                double b = Double.parseDouble(JOptionPane.showInputDialog("Base:"));
                double h = Double.parseDouble(JOptionPane.showInputDialog("Height:"));
                addShapeToList(new Triangle(b, h));
            }
        } catch (Exception ex) {
            /* Handle cancel or invalid input */ }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapeManager().setVisible(true));
    }
}
