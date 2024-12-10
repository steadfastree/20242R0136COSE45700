package miridih.common.manager;

import java.util.ArrayList;
import java.util.List;

import miridih.model.objects.CompositeShape;
import miridih.model.objects.Shape;
import miridih.observer.SelectionChangeListener;

public class SelectionManager {
  private static final SelectionManager instance = new SelectionManager();

  private final CompositeShape selectedShapes = new CompositeShape();
  private final List<SelectionChangeListener> listeners = new ArrayList<>();



  private SelectionManager() {}

  public static SelectionManager getInstance(){
    return instance;
  }

  public void addSelectionChangeListener(SelectionChangeListener listener) {
    listeners.add(listener);
  }

  public CompositeShape getSelectedShapes() {
    return selectedShapes;
  }

  public void selectShape(Shape shape) {
    selectedShapes.selectShape(shape);
    notifySelectionChanged();
  }

  public int getSelectedShapesSize() {
    return selectedShapes.getChildren().size();
  }

  public void removeShape(Shape shape) {
    selectedShapes.removeShape(shape);
    notifySelectionChanged();
  }

  public void clearSelectedShapes() {
    selectedShapes.clearSelectedShapes();
    notifySelectionChanged();
  }

  public void notifySelectionChanged() {
    for (SelectionChangeListener listener : listeners) {
      listener.onSelectionChanged();
    }
  }


}
