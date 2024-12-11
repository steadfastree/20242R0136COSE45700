package miridih.view;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import miridih.controller.CanvasController;
import miridih.controller.state.Tool;
import miridih.observer.ToolChangeListener;

public class ToolPanel extends JPanel implements ToolChangeListener {
    private JButton rectangleButton;
    private JButton ellipseButton;
    private JButton lineButton;
    private JButton selectButton;

    private CanvasController controller;

    public ToolPanel(CanvasController controller) {
        this.controller = controller;
        controller.addToolChangeListener(this);

        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));

        selectButton = createToolButton("Select", Tool.SELECT);
        rectangleButton = createToolButton("Rectangle", Tool.RECTANGLE);
        ellipseButton = createToolButton("Ellipse", Tool.ELLIPSE);
        lineButton = createToolButton("Line", Tool.LINE);

        // Add buttons to the panel
        toolPanel.add(selectButton);
        toolPanel.add(rectangleButton);
        toolPanel.add(ellipseButton);
        toolPanel.add(lineButton);
        this.add(toolPanel);
    }

    private JButton createToolButton(String label, Tool tool) {
        JButton button = new JButton(label);
        button.addActionListener((ActionEvent e) -> {
            controller.setCurrentTool(tool);
            updateButtonColors(button);
        });
        button.setFocusPainted(false); // Optional: remove focus border
        button.setOpaque(true); // Ensure the background color is painted

        return button;
    }

    // Method to update button colors
    private void updateButtonColors(JButton selectedButton) {
        // Reset all buttons to default color
        selectButton.setBackground(null);
        rectangleButton.setBackground(null);
        ellipseButton.setBackground(null);
        lineButton.setBackground(null);

        // Set the selected button's color to yellow
        selectedButton.setBackground(Color.YELLOW);
    }

    @Override
    public void onToolChanged(Tool newTool) {
        selectButton.setBackground(newTool == Tool.SELECT ? Color.YELLOW : null);
        rectangleButton.setBackground(newTool == Tool.RECTANGLE ? Color.YELLOW : null);
        ellipseButton.setBackground(newTool == Tool.ELLIPSE ? Color.YELLOW : null);
        lineButton.setBackground(newTool == Tool.LINE ? Color.YELLOW : null);
    }
}
