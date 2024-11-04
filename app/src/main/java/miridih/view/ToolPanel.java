package miridih.view;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import miridih.controller.CanvasController;
import miridih.objects.Tool;

public class ToolPanel extends JPanel {
    public ToolPanel(CanvasController controller) {
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));

        JButton rectangleButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");
        JButton selectButton = new JButton("Select");
        JButton multiSelectButton = new JButton("Multi Select");

        // 도구 선택 리스터 추가
        rectangleButton.addActionListener((ActionEvent e) -> controller.setCurrentTool(Tool.RECTANGLE));
        ellipseButton.addActionListener((ActionEvent e) -> controller.setCurrentTool(Tool.ELLIPSE));
        selectButton.addActionListener((ActionEvent e) -> controller.setCurrentTool(Tool.SELECT));
        multiSelectButton.addActionListener((ActionEvent e) -> controller.setCurrentTool(Tool.MULTI_SELECT));
        // 패널에 버튼 추가
        toolPanel.add(rectangleButton);
        toolPanel.add(ellipseButton);
        toolPanel.add(selectButton);
        toolPanel.add(multiSelectButton);
        this.add(toolPanel);
    }
}
