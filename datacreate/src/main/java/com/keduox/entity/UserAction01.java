package com.keduox.entity;

public class UserAction01 {
    private String date;
    private int user_id;
    private String session_id;
    private int page_id;
    private String aciton_time;
    private String search_keyword;
    private int click_category_id;
    private int click_product_id;
    private int order_category_ids;
    private int order_product_ids;
    private int pay_category_ids;
    private int pay_product_ids;
    private int city_id;

    public UserAction01() {
    }

    public UserAction01(String date, int user_id, String session_id, int page_id, String aciton_time, String search_keyword, int click_category_id, int click_product_id, int order_category_ids, int order_product_ids, int pay_category_ids, int pay_product_ids, int city_id) {
        this.date = date;
        this.user_id = user_id;
        this.session_id = session_id;
        this.page_id = page_id;
        this.aciton_time = aciton_time;
        this.search_keyword = search_keyword;
        this.click_category_id = click_category_id;
        this.click_product_id = click_product_id;
        this.order_category_ids = order_category_ids;
        this.order_product_ids = order_product_ids;
        this.pay_category_ids = pay_category_ids;
        this.pay_product_ids = pay_product_ids;
        this.city_id = city_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public String getAciton_time() {
        return aciton_time;
    }

    public void setAciton_time(String aciton_time) {
        this.aciton_time = aciton_time;
    }

    public String getSearch_keyword() {
        return search_keyword;
    }

    public void setSearch_keyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }

    public int getClick_category_id() {
        return click_category_id;
    }

    public void setClick_category_id(int click_category_id) {
        this.click_category_id = click_category_id;
    }

    public int getClick_product_id() {
        return click_product_id;
    }

    public void setClick_product_id(int click_product_id) {
        this.click_product_id = click_product_id;
    }

    public int getOrder_category_ids() {
        return order_category_ids;
    }

    public void setOrder_category_ids(int order_category_ids) {
        this.order_category_ids = order_category_ids;
    }

    public int getOrder_product_ids() {
        return order_product_ids;
    }

    public void setOrder_product_ids(int order_product_ids) {
        this.order_product_ids = order_product_ids;
    }

    public int getPay_category_ids() {
        return pay_category_ids;
    }

    public void setPay_category_ids(int pay_category_ids) {
        this.pay_category_ids = pay_category_ids;
    }

    public int getPay_product_ids() {
        return pay_product_ids;
    }

    public void setPay_product_ids(int pay_product_ids) {
        this.pay_product_ids = pay_product_ids;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    @Override
    public String toString() {
        return  date + "\t" + user_id + "\t"+session_id +"\t" + page_id +"\t"
                + aciton_time +"\t" + search_keyword +"\t"
                + click_category_id +"\t" + click_product_id +"\t"
                + order_category_ids +"\t" + order_product_ids +"\t"
                + pay_category_ids +"\t" + pay_product_ids +"\t"
                + city_id +"\n";
    }
}
