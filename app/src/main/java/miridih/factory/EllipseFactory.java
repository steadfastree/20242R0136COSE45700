package miridih.factory;

import miridih.objects.EllipseShape;
import miridih.objects.Shape;

public class EllipseFactory implements ShapeFactory {

  @Override
  public Shape createShape() {
    return new EllipseShape();
  }
}
