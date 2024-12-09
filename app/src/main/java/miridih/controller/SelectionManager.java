package miridih.controller;

import miridih.objects.CompositeShape;

public class SelectionManager {
  private final CompositeShape selectedShapes = new CompositeShape();

  public CompositeShape getSelectedShapes() {
    return selectedShapes;
  }

  public void addSelectedShape(CompositeShape shape) {
    selectedShapes.addShape(shape);
  }

  public void removeSelectedShape(CompositeShape shape) {
    selectedShapes.removeShape(shape);
  }

  // public void clearSelectedShapes() {
  //   selectedShapes.clear();
  // }


}
