package miridih.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.colorchooser.AbstractColorChooserPanel;

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

        JColorChooser colorChooser = createColorChooser();
        xField = createInputField("X:", 0);
        yField = createInputField("Y:", 0);
        widthField = createInputField("Width:", 0);
        heightField = createInputField("Height:", 0);
        JButton bringToFrontBtn = new JButton("Bring to Front");
        JButton sendToBackBtn = new JButton("Send to Back");
        JButton undoBtn = new JButton("Undo");
        JButton redoBtn = new JButton("Redo");

        ActionListener fieldListener = e -> command.updateShapeFromPanel(
                xField.getText(), yField.getText(), widthField.getText(), heightField.getText());
        xField.addActionListener(fieldListener);
        yField.addActionListener(fieldListener);
        widthField.addActionListener(fieldListener);
        heightField.addActionListener(fieldListener);
        bringToFrontBtn.addActionListener(e -> command.bringToFront());
        sendToBackBtn.addActionListener(e -> command.sendToBack());
        undoBtn.addActionListener(e -> command.undo());
        redoBtn.addActionListener(e -> command.redo());

        canvasPanel.add(colorChooser);
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

    private JColorChooser createColorChooser() {
        JColorChooser colorChooser = new JColorChooser(Color.BLACK);

        // HSV 만 남겨놓기
        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if (!accp.getDisplayName().equals("HSV")) {
                colorChooser.removeChooserPanel(accp);
            }
        }
        colorChooser.setPreviewPanel(new JPanel());

        JComponent current = (JComponent) colorChooser.getComponents()[0];
        while (!current.getClass().toString().equals("class javax.swing.colorchooser.ColorChooserPanel")) {
            current = (JComponent) current.getComponents()[0];
        }
        for (Component jc : current.getComponents()) {
            if (!jc.getClass().toString().equals("class javax.swing.colorchooser.DiagramComponent")) {
                jc.setVisible(false);
            }
        }

        return colorChooser;
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
