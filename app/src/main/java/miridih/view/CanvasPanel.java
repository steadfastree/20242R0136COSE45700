package miridih.view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import miridih.controller.CanvasController;
import miridih.observer.ShapeChangeListener;

public class CanvasPanel extends JPanel implements ShapeChangeListener {
    private JTextField xField;
    private JTextField yField;
    private JTextField widthField;
    private JTextField heightField;
    private CanvasController controller;

    public CanvasPanel(CanvasController controller) {
        this.controller = controller;
        controller.addShapeChangeListener(this);

        JPanel canvasPanel = new JPanel();
        canvasPanel.setLayout(new BoxLayout(canvasPanel, BoxLayout.Y_AXIS));

        xField = createInputField("X:", 0);
        yField = createInputField("Y:", 0);
        widthField = createInputField("Width:", 0);
        heightField = createInputField("Height:", 0);

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
