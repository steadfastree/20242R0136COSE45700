package miridih.view;

import java.awt.Color;
import javax.swing.JPanel;

import miridih.utils.Tool;

public class Canvas extends JPanel {
    private Tool currentTool = Tool.RECTANGLE;

    public Canvas() {
        setBackground(Color.WHITE);
    }

    public void setCurrentTool(Tool tool) {
        currentTool = tool;
    }
}
