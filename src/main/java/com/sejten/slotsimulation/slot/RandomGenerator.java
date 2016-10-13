package com.sejten.slotsimulation.slot;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by piotr.s on 2016-07-15.
 */
public class RandomGenerator {
    private static final Random randomGen = new Random();

    public static <E> E getWeightedRandom(Stream<Map.Entry<E, Double>> weights) {
        return weights
                .map(e -> new AbstractMap.SimpleEntry<E, Double>(e.getKey(), -Math.log(randomGen.nextDouble()) / e.getValue()))
                .min((e0, e1) -> e0.getValue().compareTo(e1.getValue()))
                .orElseThrow(IllegalArgumentException::new).getKey();
    }


    /**
     * @param max upper bond(exclusive)
     * @return
     */
    public static int nextInt(int max) {
        return randomGen.nextInt(max);
    }

    /**
     * @param min lower bond(inclusive)
     * @param max upper bond(inclusive)
     * @return
     */
    public static int nextInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static double nextDouble() {
        return randomGen.nextDouble();
    }
}
