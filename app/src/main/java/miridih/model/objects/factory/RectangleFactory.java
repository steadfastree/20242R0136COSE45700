package miridih.model.objects.factory;

import miridih.model.objects.RectangleShape;
import miridih.model.objects.Shape;

public class RectangleFactory implements ShapeFactory {
  private static final RectangleFactory instance = new RectangleFactory();

  private RectangleFactory() {}

  public static RectangleFactory getInstance() {
    return instance;
  }
  @Override
  public Shape createShape() {
    return new RectangleShape();
  }
}
