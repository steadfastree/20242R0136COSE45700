package miridih.objects;

public class RectangleShape extends Shape {
    @Override
    public boolean contains(double x, double y) {
        return x >= getStartX() && x <= getEndX() && y >= getStartY() && y <= getEndY();
    }
  
}
