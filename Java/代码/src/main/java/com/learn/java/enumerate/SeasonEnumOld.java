package com.learn.java.enumerate;

import lombok.Getter;
import lombok.ToString;

/**
 * @author xzy
 * @date 2020/11/29 20:46
 * 说明：手动创建的季节枚举类
 */
@Getter
@ToString
public class SeasonEnumOld {
    /**
     * 季节对应的序号
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

    private SeasonEnumOld(int number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
    }

    public static final SeasonEnumOld SPRING = new SeasonEnumOld(0, "春", "几处早莺争暖树，谁家新燕啄春泥。");
    public static final SeasonEnumOld SUMMER = new SeasonEnumOld(1, "夏", "小荷才露尖尖角，早有蜻蜓立上头。");
    public static final SeasonEnumOld AUTUMN = new SeasonEnumOld(2, "秋", "一年好景君须记，正是橙黄橘绿时。");
    public static final SeasonEnumOld WINTER = new SeasonEnumOld(3, "冬", "忽如一夜春风来，千树万树梨花开。");

    public static SeasonEnumOld valueOf(int number) {
        switch (number) {
            case 0:
                return SPRING;
            case 1:
                return SUMMER;
            case 2:
                return AUTUMN;
            case 3:
                return WINTER;
            default:
                return null;
        }
    }

    public static SeasonEnumOld valueOf(String name) {
        switch (name) {
            case "春":
                return SPRING;
            case "夏":
                return SUMMER;
            case "秋":
                return AUTUMN;
            case "冬":
                return WINTER;
            default:
                return null;
        }
    }
}
