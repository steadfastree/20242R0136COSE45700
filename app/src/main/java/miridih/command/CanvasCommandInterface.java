package miridih.command;

import java.awt.Graphics2D;

public interface CanvasCommandInterface {

    public void drawShapes(Graphics2D g2d);

    public void drawSelectedShapes(Graphics2D g2d);
}
