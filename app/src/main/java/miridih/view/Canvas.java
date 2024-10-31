package miridih.view;

import java.awt.Color;
import javax.swing.JPanel;

import miridih.model.CanvasModel;
import miridih.objects.Tool;

public class Canvas extends JPanel {
    private final CanvasModel canvasModel;

    public Canvas(CanvasModel model) {
        canvasModel = model;
        setBackground(Color.WHITE);
    }

    public void setCurrentTool(Tool tool) {
        canvasModel.setCurrentTool(tool);
    }
}
