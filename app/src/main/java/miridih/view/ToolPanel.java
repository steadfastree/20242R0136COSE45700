package miridih.view;

import javax.swing.*;

import miridih.objects.Tool;

import java.awt.event.ActionEvent;

public class ToolPanel extends JPanel {
    public ToolPanel(Canvas canvas) {
        JPanel toolPanel = new JPanel();
        JButton rectangleButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");

        // 도구 선택 리스터 추가
        rectangleButton.addActionListener((ActionEvent e) -> canvas.setCurrentTool(Tool.RECTANGLE));
        ellipseButton.addActionListener((ActionEvent e) -> canvas.setCurrentTool(Tool.ELLIPSE));

        // 패널에 버튼 추가
        toolPanel.add(rectangleButton);
        toolPanel.add(ellipseButton);

        this.add(toolPanel);
    }
}
