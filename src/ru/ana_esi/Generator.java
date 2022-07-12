package ru.ana_esi;

import java.awt.*;

public class Generator {

//    private int count;
//    private LinkedBag<Color> generated;
//
//    public Generator (int count) {
//        this.count = count;
//        generated = new LinkedBag<>();
//    }
//
//    public Player genPlayer () {
//        Player p = gen();
//
//
//            if (generated.exist(p.getColor()))
//
//        return
//    }
//
//    private Player gen () {
//        Color c = Colors.colors[];
//        int fig = Figure.figures[random(0, Figure.figures.length)];
//
//        return new Player("abc", c, 3, fig);
//    }

    public static int random (int a, int b) {
        return (int) (Math.random() * (b - a) + a);
    }

    public static void shuffle(Cell items[]) {
        for (int i = 0; i < items.length; i++) {
            int random = random(i + 1, items.length - 1);
            Cell temp = items[i];
            items[i] = items[random];
            items[random] = temp;
        }
    }

}
