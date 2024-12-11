package miridih.model.objects;

import java.awt.Color;
import java.awt.Graphics2D;

public class LineShape extends Shape {
  @Override
  public boolean contains(double x, double y) {
    return x >= getStartX() && x <= getEndX() && y >= getStartY() && y <= getEndY();
  }

  @Override
  public void draw(Graphics2D g2d) {
    int x1 = (int) getStartX();
    int x2 = (int) getEndX();
    int y1 = (int) getStartY();
    int y2 = (int) getEndY();

    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);

    g2d.setColor(Color.BLACK);
    g2d.drawLine(x1, y1, x2, y2);
  }
}
