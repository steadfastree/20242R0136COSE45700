package miridih.model.objects.factory;

import miridih.model.objects.EllipseShape;
import miridih.model.objects.Shape;

public class EllipseFactory implements ShapeFactory {
  private static final EllipseFactory instance = new EllipseFactory();

  private EllipseFactory() {}

  public EllipseFactory getInstance() {
    return instance;
  }

  @Override
  public Shape createShape() {
    return new EllipseShape();
  }
}
