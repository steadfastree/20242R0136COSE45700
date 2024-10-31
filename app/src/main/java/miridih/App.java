package miridih;

import miridih.view.Canvas;
import miridih.view.ToolPanel;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Paint");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // 캔버스
            Canvas canvas = new Canvas();
            frame.add(canvas, BorderLayout.CENTER);

            // 패널
            ToolPanel toolPanel = new ToolPanel(canvas);

            frame.add(toolPanel, BorderLayout.WEST);
            frame.setVisible(true);
        });
    }
}