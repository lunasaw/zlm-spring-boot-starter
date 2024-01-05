package io.github.lunasaw.zlm.enums;

import lombok.Getter;

/**
 * @author weidian
 */

@Getter
public enum LoadBalancerEnums {
    RANDOM(1, "Random"),

    ROUND_ROBIN(2, "RoundRobin"),

    CONSISTENT_HASHING(3, "ConsistentHashing"),

    WEIGHT_ROUND_ROBIN(4, "WeightRoundRobin"),

    WEIGHT_RANDOM(5, "WeightRandom");

    private int code;
    private String type;

    private LoadBalancerEnums(int code, String type) {
        this.code = code;
        this.type = type;
    }

}
