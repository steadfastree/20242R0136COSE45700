package miridih.model;

import java.util.ArrayList;

import miridih.objects.Shape;
import miridih.objects.Tool;

public class CanvasModel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Shape selectedShape = null;
    private Tool currentTool = null;

    public void setCurrentTool(Tool tool) {
        currentTool = tool;
    }
}
