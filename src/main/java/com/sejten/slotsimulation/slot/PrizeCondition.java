package com.sejten.slotsimulation.slot;

import java.util.Optional;

/**
 * Created by piotr.s
 */
public interface PrizeCondition {
    Optional<Prize> evaluate(int occurrences);
}
