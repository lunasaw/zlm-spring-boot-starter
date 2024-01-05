package io.github.lunasaw.zlm.enums;

import lombok.Getter;

/**
 * @author weidian
 */

@Getter
public enum LoadBalancerEnums {
    RANDOM(0, "Random"),

    ROUND_ROBIN(0, "RoundRobin"),

    CONSISTENT_HASHING(0, "ConsistentHashing"),

    WEIGHT_ROUND_ROBIN(0, "WeightRoundRobin"),

    WEIGHT_RANDOM(0, "WeightRandom");

    private int code;
    private String type;

    private LoadBalancerEnums(int code, String type) {
        this.code = code;
        this.type = type;
    }

}
