package miridih.model.objects;

import java.awt.Graphics2D;

public class LineShape extends Shape {
  @Override
  public boolean contains(double x, double y) {
    double x1 = getStartX();
    double x2 = getEndX();
    double y1 = getStartY();
    double y2 = getEndY();

    return x >= Math.min(x1, x2) && x <= Math.max(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
  }

  @Override
  public void draw(Graphics2D g2d) {
    int x1 = (int) getStartX();
    int x2 = (int) getEndX();
    int y1 = (int) getStartY();
    int y2 = (int) getEndY();

    g2d.setColor(getColor());
    g2d.drawLine(x1, y1, x2, y2);
  }
}
