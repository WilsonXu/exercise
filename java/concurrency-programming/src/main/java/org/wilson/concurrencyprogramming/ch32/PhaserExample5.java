package org.wilson.concurrencyprogramming.ch32;

import java.util.concurrent.Phaser;

public class PhaserExample5 {
    public static void main(String[] args) {
        var root = new Phaser(1);
        PhaserExample5.assertState(root, 0, 1, 1);
        assert root.arrive() == 0;

        var child1 = new Phaser(root, 1);
        var child2 = new Phaser(root, 1);
        PhaserExample5.assertState(root, 1, 3,  3);
        PhaserExample5.assertState(child1, 1, 1,  1);
        PhaserExample5.assertState(child2, 1, 1,  1);
    }

    private static void assertState(Phaser phaser, int phase, int parties, int unArrived) {
        assert phaser.getPhase() == phase;
        assert phaser.getRegisteredParties() == parties;
        assert phaser.getUnarrivedParties() == unArrived;
    }
}
