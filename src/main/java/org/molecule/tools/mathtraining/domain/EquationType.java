package org.molecule.tools.mathtraining.domain;

/**
 * 算式类型，加减乘除
 *
 * @author Dong Zhuming
 */
public enum EquationType {
    /**
     * 加法
     */
    ADDITION("+"),
    /**
     * 减法
     */
    SUBTRACTION("-"),
    /**
     * 乘法
     */
    MULTIPLICATION("×"),
    /**
     * 除法
     */
    DIVISION("÷");

    private String sign;

    EquationType(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
