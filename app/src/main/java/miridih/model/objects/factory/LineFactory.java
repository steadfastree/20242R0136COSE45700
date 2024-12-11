package miridih.model.objects.factory;

import miridih.model.objects.LineShape;
import miridih.model.objects.Shape;

public class LineFactory implements ShapeFactory {
  private static final LineFactory instance = new LineFactory();

  private LineFactory() {
  }

  public static LineFactory getInstance() {
    return instance;
  }

  @Override
  public Shape createShape() {
    return new LineShape();
  }
}
