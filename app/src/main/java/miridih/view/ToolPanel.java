package miridih.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(128, 1080));

        selectButton = createToolButton("Select", Tool.SELECT);
        rectangleButton = createToolButton("Rectangle", Tool.RECTANGLE);
        ellipseButton = createToolButton("Ellipse", Tool.ELLIPSE);
        lineButton = createToolButton("Line", Tool.LINE);

        // Add buttons to the panel
        this.add(selectButton);
        this.add(rectangleButton);
        this.add(ellipseButton);
        this.add(lineButton);
    }

    private JButton createToolButton(String label, Tool tool) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(108, 32));

        button.addActionListener((ActionEvent e) -> {
            controller.setCurrentTool(tool);
            updateButtonColors(button);
        });
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

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
