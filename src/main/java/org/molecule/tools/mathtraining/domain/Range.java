package org.molecule.tools.mathtraining.domain;

import lombok.Getter;

/**
 * 数字范围，包括值域为闭区间
 *
 * @author Dong Zhuming
 */
@Getter
public class Range {
    private Integer begin;
    private Integer end;

    private Range(String value) {
        String[] split = value.split("-", 2);
        this.begin = Integer.valueOf(split[0]);
        this.end = Integer.valueOf(split[1]);
        if (begin >= end) {
            throw new IllegalArgumentException(value);
        }
    }

    public static Range of(String value) {
        return new Range(value);
    }

    public boolean includes(Integer i) {
        return i >= begin && i <= end;
    }
}
