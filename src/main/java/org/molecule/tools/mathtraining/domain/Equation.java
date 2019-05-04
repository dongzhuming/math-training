package org.molecule.tools.mathtraining.domain;

/**
 * @author Dong Zhuming
 */
public interface Equation {
    /**
     *
     * @return 计算结果
     */
    Integer getResultItem();

    /**
     * @param index 位置
     * @return 获取指定位置的运算项值
     */
    String getItem(int index);

}
