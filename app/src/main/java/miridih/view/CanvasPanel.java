package miridih.view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import miridih.command.CanvasPanelCommand;
import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;
import miridih.model.objects.Shape;
import miridih.observer.SelectionChangeListener;
import miridih.observer.ShapeChangeListener;

public class CanvasPanel extends JPanel implements ShapeChangeListener, SelectionChangeListener {
    private JTextField xField;
    private JTextField yField;
    private JTextField widthField;
    private JTextField heightField;

    private CanvasPanelCommand command;
    private final SelectionManager selectionManager = SelectionManager.getInstance();

    public CanvasPanel(CanvasController controller) {
        this.command = new CanvasPanelCommand(controller);
        controller.addShapeChangeListener(this);
        controller.addSelectionChangeListener(this);

        JPanel canvasPanel = new JPanel();
        canvasPanel.setLayout(new BoxLayout(canvasPanel, BoxLayout.Y_AXIS));

        xField = createInputField("X:", 0);
        yField = createInputField("Y:", 0);
        widthField = createInputField("Width:", 0);
        heightField = createInputField("Height:", 0);
        JButton bringToFrontBtn = new JButton("Bring to Front");
        JButton sendToBackBtn = new JButton("Send to Back");
        JButton undoBtn = new JButton("Undo");
        JButton redoBtn = new JButton("Redo");

        xField.addActionListener(e -> command.updateShapeFromPanel(xField.getText(), yField.getText(),
                widthField.getText(), heightField.getText()));
        yField.addActionListener(e -> command.updateShapeFromPanel(xField.getText(), yField.getText(),
                widthField.getText(), heightField.getText()));
        widthField.addActionListener(e -> command.updateShapeFromPanel(xField.getText(), yField.getText(),
                widthField.getText(), heightField.getText()));
        heightField.addActionListener(e -> command.updateShapeFromPanel(xField.getText(), yField.getText(),
                widthField.getText(), heightField.getText()));
        bringToFrontBtn.addActionListener(e -> command.bringToFront());
        sendToBackBtn.addActionListener(e -> command.sendToBack());
        undoBtn.addActionListener(e -> command.undo());
        redoBtn.addActionListener(e -> command.redo());

        canvasPanel.add(xField.getParent());
        canvasPanel.add(yField.getParent());
        canvasPanel.add(widthField.getParent());
        canvasPanel.add(heightField.getParent());
        canvasPanel.add(bringToFrontBtn);
        canvasPanel.add(sendToBackBtn);
        canvasPanel.add(undoBtn);
        canvasPanel.add(redoBtn);

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
        if (selectionManager.getSelectedShapes().getChildren().size() == 1) {
            Shape selectedShape = selectionManager.getSelectedShapes().getChildren().get(0);
            xField.setText(String.valueOf(selectedShape.getStartX()));
            yField.setText(String.valueOf(selectedShape.getStartY()));
            widthField.setText(String.valueOf(selectedShape.getWidth()));
            heightField.setText(String.valueOf(selectedShape.getHeight()));
        } else {
            xField.setText("");
            yField.setText("");
            widthField.setText("");
            heightField.setText("");
        }
    }

    @Override
    public void onSelectionChanged() {
        if (selectionManager.getSelectedShapes().getChildren().size() == 1) {
            Shape selectedShape = selectionManager.getSelectedShapes().getChildren().get(0);
            xField.setText(String.valueOf(selectedShape.getStartX()));
            yField.setText(String.valueOf(selectedShape.getStartY()));
            widthField.setText(String.valueOf(selectedShape.getWidth()));
            heightField.setText(String.valueOf(selectedShape.getHeight()));
        } else {
            xField.setText("");
            yField.setText("");
            widthField.setText("");
            heightField.setText("");
        }
    }
}
