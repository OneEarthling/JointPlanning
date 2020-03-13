package com.example.jointplanning.model;

public class Tag {

    /**
     * Идентификатор
     */
    public Long id;

    /**
     * Код
     */
    public long n_code;

    /**
     * Наименование
     */
    public String c_name;

    /**
     * Краткое наименование
     */
    public String c_short_name;

    /**
     * Константа
     */
    public String c_const;

    /**
     * отключено
     */
    public boolean b_disabled;

    /**
     * сортировка
     */
    public int n_order;

    /**
     * по умолчанию
     */
    public boolean b_default;

    public Long getId() {
        return id;
    }

    public long getN_code() {
        return n_code;
    }

    public String getC_name() {
        return c_name;
    }

    public String getC_short_name() {
        return c_short_name;
    }

    public String getC_const() {
        return c_const;
    }

    public boolean isB_disabled() {
        return b_disabled;
    }

    public int getN_order() {
        return n_order;
    }

    public boolean isB_default() {
        return b_default;
    }

}
