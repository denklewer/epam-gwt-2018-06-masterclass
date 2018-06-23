package com.epam.gwt.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

public class Chat implements EntryPoint {

    private static final String[] COLORS = {
            "rgb(0, 0, 0)",
            "rgb(255, 255, 0)",
            "rgb(0, 0, 255)",
            "rgb(0, 255, 0)",
            "rgb(255, 0, 255)",
            "rgb(0, 255, 255)",
    };

    @Override
    public void onModuleLoad() {
        Canvas canvas = Canvas.createIfSupported();
        RootPanel.get().add(canvas);

        CanvasElement canvasElement = canvas.getCanvasElement();
        canvasElement.setWidth(400);
        canvasElement.setHeight(700);

        Controller controller = new Controller();
        Model model = new Model();
        model.addListener(controller);

        View view = new View();
        controller.setView(view);
        controller.setModel(model);

        Context2d context2d = canvas.getContext2d();
        view.setGraphics((x, y, width, height, colorIndex) -> {
            context2d.setFillStyle(COLORS[colorIndex]);
            context2d.fillRect(x, y, width, height);
        });

        canvas.addKeyPressHandler(event -> {
            int code = event.getNativeEvent().getKeyCode();
            switch (event.getNativeEvent().getCharCode()) {
                case 'a':
                    controller.moveLeft();
                    break;

                case 'd':
                    controller.moveRight();
                    break;

                case 's':
                    controller.moveDown();
                    break;

            }
        });

        new Timer() {
            @Override
            public void run() {
                controller.moveDown();
            }
        }.scheduleRepeating(500);
    }
}
