package miridih.view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import miridih.controller.CanvasController;
import miridih.objects.Shape;
import miridih.observer.ShapeChangeListener;

public class CanvasPanel extends JPanel implements ShapeChangeListener {
    private JTextField xField;
    private JTextField yField;
    private JTextField widthField;
    private JTextField heightField;

    private CanvasController controller;
    private Canvas canvas;

    public CanvasPanel(CanvasController controller, Canvas canvas) {
        this.controller = controller;
        this.canvas = canvas;
        controller.addShapeChangeListener(this);

        JPanel canvasPanel = new JPanel();
        canvasPanel.setLayout(new BoxLayout(canvasPanel, BoxLayout.Y_AXIS));

        xField = createInputField("X:", 0);
        yField = createInputField("Y:", 0);
        widthField = createInputField("Width:", 0);
        heightField = createInputField("Height:", 0);

        xField.addActionListener(e -> updateShapeFromPanel());
        yField.addActionListener(e -> updateShapeFromPanel());
        widthField.addActionListener(e -> updateShapeFromPanel());
        heightField.addActionListener(e -> updateShapeFromPanel());

        canvasPanel.add(xField.getParent());
        canvasPanel.add(yField.getParent());
        canvasPanel.add(widthField.getParent());
        canvasPanel.add(heightField.getParent());

        this.add(canvasPanel);
    }

    private JTextField createInputField(String label, double value) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel jLabel = new JLabel(label);
        JTextField textField = new JTextField(String.valueOf(value), 10);

        panel.add(jLabel);
        panel.add(textField);

        this.add(panel);
        return textField;
    }

    private void updateShapeFromPanel() {
        Shape selectedShape = controller.getSelectedShape();
        if (selectedShape != null) {
            try {
                double x = Double.parseDouble(xField.getText());
                double y = Double.parseDouble(yField.getText());
                double width = Double.parseDouble(widthField.getText());
                double height = Double.parseDouble(heightField.getText());

                controller.updateSelectedShape(x, y, width, height);
                canvas.repaint();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    @Override
    public void onShapeChanged() {
        if (controller.getSelectedShape() != null) {
            xField.setText(String.valueOf(controller.getSelectedShape().getStartX()));
            yField.setText(String.valueOf(controller.getSelectedShape().getStartY()));
            widthField.setText(String.valueOf(controller.getSelectedShape().getWidth()));
            heightField.setText(String.valueOf(controller.getSelectedShape().getHeight()));
        } else {
            xField.setText("");
            yField.setText("");
            widthField.setText("");
            heightField.setText("");
        }
    }
}
