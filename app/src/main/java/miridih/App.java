package miridih;

import miridih.view.Canvas;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a JFrame to hold the Canvas
            JFrame frame = new JFrame("Canvas Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            Canvas canvas = new Canvas();
            frame.add(canvas, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}