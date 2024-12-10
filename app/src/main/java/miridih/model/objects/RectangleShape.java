package miridih.model.objects;

import java.awt.Color;
import java.awt.Graphics2D;

public class RectangleShape extends Shape {
    @Override
    public boolean contains(double x, double y) {
        return x >= getStartX() && x <= getEndX() && y >= getStartY() && y <= getEndY();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) getStartX(), (int) getStartY(), (int) (getEndX() - getStartX()),
                (int) (getEndY() - getStartY()));
        g2d.setColor(Color.BLACK);
        g2d.drawRect((int) getStartX(), (int) getStartY(), (int) (getEndX() - getStartX()),
                (int) (getEndY() - getStartY()));
    }
}
