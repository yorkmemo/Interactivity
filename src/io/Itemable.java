package io;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface Itemable {
    //todo: return Itemable so commands can be chained

    Itemable stroke(Color color);
    Itemable fill(Color color);
    Itemable onOver(EventHandler<MouseEvent> handler);
    Itemable onOut(EventHandler<MouseEvent> handler);
    Itemable onClick(EventHandler<MouseEvent> handler);
    Itemable onEdgeHit(EventHandler<Event> handler);
    Itemable direction(double angle, double pps);
    Itemable direction(double angle);
    Itemable speed(double pps);
    Itemable left(double pps);
    Itemable left();
    Itemable right(double pps);
    Itemable right();
    Itemable up(double pps);
    Itemable up();
    Itemable down(double pps);
    Itemable down();
    Itemable rotate(double degrees);
    Itemable fadeIn();
    Itemable fadeOut();
    Itemable fadeIn(double seconds);
    Itemable fadeOut(double seconds);
    Itemable sleep(double seconds);

    Itemable bounceOnEdge();
    Itemable stopOnEdge();
    Itemable removeOnEdge();
    Itemable ignoreEdge();



}
