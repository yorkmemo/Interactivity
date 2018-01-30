package io;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class ItemGroup implements Itemable {
    private LinkedList<Item> items;
    private String type;

    public ItemGroup() {
        this.type = null;
        items = new LinkedList<>();
    }

    public ItemGroup(String type) {
        this.type = type;
        items = new LinkedList<>();
    }

    public boolean add(Item item) {
       // System.out.println(type+"|"+item.getClass().getName());
        if (type == null || !item.getType().equals(type)) return false;

        return items.add(item);
    }

    public int size() {
        return items.size();
    }

    @Override
    public Itemable stroke(Color color) {
        for (Item item : items) {
            item.stroke(color);
        }

        return this;
    }

    @Override
    public Itemable fill(Color color) {
        for (Item item : items) {
            item.fill(color);
        }

        return this;
    }

    @Override
    public Itemable onOver(EventHandler<MouseEvent> handler) {
        for (Item item : items) {
            item.onOver(handler);
        }

        return this;
    }

    @Override
    public Itemable onOut(EventHandler<MouseEvent> handler) {
        for (Item item : items) {
            item.onOut(handler);
        }

        return this;
    }

    @Override
    public Itemable onClick(EventHandler<MouseEvent> handler) {
        for (Item item : items) {
            item.onClick(handler);
        }

        return this;
    }

    @Override
    public Itemable onEdgeHit(EventHandler<Event> handler) {
        for (Item item : items) {
            item.onEdgeHit(handler);
        }

        return this;
    }

    @Override
    public Itemable direction(double angle, double pps) {
        for (Item item : items) {
            item.direction(angle, pps);
        }

        return this;
    }

    @Override
    public Itemable direction(double angle) {
        for (Item item : items) {
            item.direction(angle);
        }

        return this;
    }

    @Override
    public Itemable speed(double pps) {
        for (Item item : items) {
            item.speed(pps);
        }

        return this;
    }

    @Override
    public Itemable left(double pps) {
        for (Item item : items) {
            item.left(pps);
        }

        return this;
    }

    @Override
    public Itemable left() {
        for (Item item : items) {
            item.left();
        }

        return this;
    }

    @Override
    public Itemable right(double pps) {
        for (Item item : items) {
            item.right(pps);
        }

        return this;
    }

    @Override
    public Itemable right() {
        for (Item item : items) {
            item.right();
        }

        return this;
    }

    @Override
    public Itemable up(double pps) {
        for (Item item : items) {
            item.up(pps);
        }

        return this;
    }

    @Override
    public Itemable up() {
        for (Item item : items) {
            item.up();
        }

        return this;

    }

    @Override
    public Itemable down(double pps) {
        for (Item item : items) {
            item.down(pps);
        }

        return this;
    }

    @Override
    public Itemable down() {
        for (Item item : items) {
            item.down();
        }

        return this;
    }

    @Override
    public Itemable rotate(double degrees) {
        for (Item item : items) {
            item.rotate(degrees);
        }

        return this;
    }

    @Override
    public Itemable fadeIn() {
        for (Item item : items) {
            item.fadeIn();
        }

        return this;
    }

    @Override
    public Itemable fadeOut() {
        for (Item item : items) {
            item.fadeOut();
        }

        return this;
    }

    @Override
    public Itemable fadeIn(double seconds) {
        for (Item item : items) {
            item.fadeIn(seconds);
        }

        return this;
    }

    @Override
    public Itemable fadeOut(double seconds) {
        for (Item item : items) {
            item.fadeOut(seconds);
        }

        return this;
    }

    @Override
    public Itemable sleep(double seconds) {
        for (Item item : items) {
            item.sleep(seconds);
        }

        return this;
    }

    @Override
    public Itemable bounceOnEdge() {
        for (Item item : items) {
            item.bounceOnEdge();
        }

        return this;
    }

    @Override
    public Itemable stopOnEdge() {
        for (Item item : items) {
            item.stopOnEdge();
        }

        return this;
    }

    @Override
    public Itemable removeOnEdge() {
        for (Item item : items) {
            item.removeOnEdge();
        }

        return this;
    }

    @Override
    public Itemable ignoreEdge() {
        for (Item item : items) {
            item.ignoreEdge();
        }

        return this;
    }
}
