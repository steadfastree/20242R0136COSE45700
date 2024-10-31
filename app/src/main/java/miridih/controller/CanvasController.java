package miridih.controller;

import java.awt.event.MouseAdapter;

import miridih.model.CanvasModel;

public class CanvasController extends MouseAdapter {

    private final CanvasModel canvasModel;

    public CanvasController(CanvasModel model) {
        canvasModel = model;
    }

}
