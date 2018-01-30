package io;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.LinkedList;
import java.util.Queue;

public class Item implements Itemable {
    private static final double DEFAULT_FADE = 1;

    private static final int IGNORE_EDGE = 0;
    private static final int BOUNCE_EDGE = 1;
    private static final int STOP_EDGE = 2;
    private static final int REMOVE_EDGE = 3;

    private Node node;
    private double dx, dy, dr, da; //deltas x, y, rotate, alpha
    private double dir, speed;
    private EventHandler<Event> edgeHandler;
    private int edge = IGNORE_EDGE;
    private Queue<EventHandler> queue;
    private int waiting = 0;

    public Item(Node node) {
        this.node = node;
        queue = new LinkedList<>();
    }

    public String getId() {
        return node.getId();
    }

    public String getType() {
        return node.getClass().getName();
    }

    void tick() {
        waiting--;

        while (waiting <= 0 && queue.size() > 0) {
            queue.remove().handle(new Event(node,Event.NULL_SOURCE_TARGET,Event.ANY));
        }

        if (dx != 0) {
            node.setLayoutX(node.getLayoutX() + dx);
        }

        if (dy != 0) {
            node.setLayoutY(node.getLayoutY() + dy);
        }

        if (edge != IGNORE_EDGE && (dx != 0 || dy != 0)) {
            Bounds bounds = Window.edges();
        }

        if (dr != 0) {
            double r = (node.getRotate() + dr) % 360;
            node.setRotate(r);
        }

        if (node.getOpacity() + da > 1) {
            node.setOpacity(1);
        } else if (node.getOpacity() + da < 0) {
            node.setOpacity(0);
        } else {
            node.setOpacity(node.getOpacity() + da);
        }
    }


    public Itemable onOver(EventHandler<MouseEvent> handler) {
        if (waiting > 0) {
            queue.add(e->onOver(handler));
        } else {
            node.setOnMouseEntered(handler);
        }
        return this;
    }

    public Itemable onOut(EventHandler<MouseEvent> handler) {
        if (waiting > 0) {
            queue.add(e->onOut(handler));
        } else {
            node.setOnMouseExited(handler);
        }
        return this;
    }

    public Itemable onClick(EventHandler<MouseEvent> handler) {
        if (waiting > 0) {
            queue.add(e->onClick(handler));
        } else {
            node.setOnMousePressed(handler);
        }
        return this;
    }

    @Override
    public Itemable onEdgeHit(EventHandler<Event> handler) {
        if (waiting > 0) {
            queue.add(e->onEdgeHit(handler));
        } else {
            edgeHandler = handler;
        }
        return this;
    }

    public Itemable stroke(Color color) {
        if (waiting > 0) {
            queue.add(e->stroke(color));
        } else {

            if (node instanceof Shape) {
                ((Shape) node).setStroke(color);
            }
        }

        return this;
    }

    public Itemable fill(Color color) {
        // System.out.println(fill);
        if (waiting > 0) {
            queue.add(e->fill(color));
        } else {
            if (node instanceof Shape) {
                ((Shape) node).setFill(color);
            }
        }

        return this;
    }

    private void updateDeltas() {
        dx = Math.cos(Math.toRadians(dir)) * speed;
        dy = Math.sin(Math.toRadians(dir)) * speed;
       // System.out.println("dx="+dx+",dy="+dy);
    }

    private double calculateSpeed(double pps) {
        return pps / Window.FPS;
    }

    public Itemable direction(double angle, double pps) {
        if (waiting > 0) {
            queue.add(e->direction(angle, pps));
        } else {
            speed = pps;
            direction(angle);
        }
        return this;
    }

    public Itemable direction(double angle) {
        if (waiting > 0) {
            queue.add(e->direction(angle));
        } else {
            dir = angle;
            updateDeltas();
        }
        return this;
    }

    public Itemable speed(double pps) {
        if (waiting > 0) {
            queue.add(e->speed(pps));
        } else {
            speed = calculateSpeed(pps);
            updateDeltas();
        }
        return this;
    }

    public Itemable left(double pps) {
        if (waiting > 0) {
            queue.add(e->left(pps));
        } else {
            this.speed = calculateSpeed(pps);
            direction(180);
        }
        return this;
    }

    public Itemable left() {
        if (waiting > 0) {
            queue.add(e->left());
        } else {
            direction(180);
        }
        return this;
    }

    public Itemable right(double pps) {
        if (waiting > 0) {
            queue.add(e->right(pps));
        } else {
            this.speed = calculateSpeed(pps);
            direction(0);
        }
        return this;
    }

    public Itemable right() {
        if (waiting > 0) {
            queue.add(e->right());
        } else {
            direction(0);
        }
        return this;
    }

    @Override
    public Itemable up(double pps) {
        if (waiting > 0) {
            queue.add(e->up(pps));
        } else {
            this.speed = calculateSpeed(pps);
            direction(270);
        }
        return this;
    }

    @Override
    public Itemable up() {
        if (waiting > 0) {
            queue.add(e->up());
        } else {
            direction(270);
        }
        return this;
    }

    @Override
    public Itemable down(double pps) {
        if (waiting > 0) {
            queue.add(e->down(pps));
        } else {
            this.speed = calculateSpeed(pps);
            direction(90);
        }
        return this;
    }

    @Override
    public Itemable down() {
        if (waiting > 0) {
            queue.add(e->down());
        } else {
            direction(90);
        }
        return this;
    }


    public Itemable rotate(double degrees) {
        if (waiting > 0) {
            queue.add(e->rotate(degrees));
        } else {
            dr = degrees;
        }
        return this;
    }

    @Override
    public Itemable fadeIn() {
        if (waiting > 0) {
            queue.add(e->fadeIn());
        } else {
            da = 1 / (Window.FPS * DEFAULT_FADE);
            sleep(DEFAULT_FADE);
        }
        return this;
    }

    @Override
    public Itemable fadeOut() {
        if (waiting > 0) {
            queue.add(e->fadeOut());
        } else {
            da = -1 / (Window.FPS * DEFAULT_FADE);
            sleep(DEFAULT_FADE);
        }

        return this;
    }


    @Override
    public Itemable fadeIn(double seconds) {
        if (waiting > 0) {
            queue.add(e->fadeIn(seconds));
        } else {
            node.setOpacity(0);
            da = 1 / (Window.FPS * seconds);
            sleep(seconds);
        }
        return this;
    }

    @Override
    public Itemable fadeOut(double seconds) {
        if (waiting > 0) {
            queue.add(e->fadeOut(seconds));
        } else {
            node.setOpacity(1);
            da = -1 / (Window.FPS * seconds);
            sleep(seconds);
        }

        return this;
    }

    @Override
    public Itemable sleep(double seconds) {
        if (waiting > 0) {
            queue.add(e->sleep(seconds));
        } else {
            waiting = (int) Math.round(seconds * Window.FPS);
        }

        return this;
    }


    @Override
    public Itemable bounceOnEdge() {
        if (waiting > 0) {
            queue.add(e->bounceOnEdge());
        } else {
            edge = BOUNCE_EDGE;
        }
        return this;
    }

    @Override
    public Itemable stopOnEdge() {
        if (waiting > 0) {
            queue.add(e->stopOnEdge());
        } else {
            edge = STOP_EDGE;
        }
        return this;
    }

    @Override
    public Itemable removeOnEdge() {
        if (waiting > 0) {
            queue.add(e->removeOnEdge());
        } else {
            edge = REMOVE_EDGE;
        }
        return this;
    }

    @Override
    public Itemable ignoreEdge() {
        if (waiting > 0) {
            queue.add(e->removeOnEdge());
        } else {
            edge = IGNORE_EDGE;
        }
        return this;
    }



}