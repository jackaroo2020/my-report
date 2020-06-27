package com.report.myreport;
/**
 * @description  用例状态
 *
 * @author  LT
 * @date  2020/6/27
 */
public enum StatusEnum {

    //失败，未执行，通过，错误
    PASS("通过", 1),
    ERROR("错误", 2),
    FAIL("失败", 3),
    SKIP("未执行", 4);

    private String name;
    private int index;

    // 构造方法
    private StatusEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (StatusEnum c : StatusEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
