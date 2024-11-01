package miridih.view;

import javax.swing.*;

import miridih.controller.CanvasController;
import miridih.objects.Tool;

import java.awt.event.ActionEvent;

public class ToolPanel extends JPanel {
    public ToolPanel(CanvasController controller) {
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));

        JButton rectangleButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");

        // 도구 선택 리스터 추가
        rectangleButton.addActionListener((ActionEvent e) -> controller.setCurrentTool(Tool.RECTANGLE));
        ellipseButton.addActionListener((ActionEvent e) -> controller.setCurrentTool(Tool.ELLIPSE));

        // 패널에 버튼 추가
        toolPanel.add(rectangleButton);
        toolPanel.add(ellipseButton);

        this.add(toolPanel);
    }
}
