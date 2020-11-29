package com.learn.java.enumerate;

import lombok.Getter;

/**
 * @author xzy
 * @date 2020/11/29 21:44
 * 说明：
 */
@Getter
public enum SeasonEnum {
    /**/
    SPRING(0, "春", "几处早莺争暖树，谁家新燕啄春泥。") {
        @Override
        void explanation() {}
    },
    SUMMER(1, "夏", "小荷才露尖尖角，早有蜻蜓立上头。") {
        @Override
        void explanation() {}
    },
    AUTUMN(2, "秋", "一年好景君须记，正是橙黄橘绿时。") {
        @Override
        void explanation() {}
    },
    WINTER(3, "冬", "忽如一夜春风来，千树万树梨花开。") {
        @Override
        void explanation() {}
    };

    /**
     * 序号
     */
    private final int number;
    /**
     * 名称
     */
    private final String name;
    /**
     * 描述
     */
    private final String description;

    SeasonEnum(int number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
    }

    abstract void explanation();
}
