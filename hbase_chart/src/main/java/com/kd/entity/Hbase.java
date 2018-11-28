package com.kd.entity;

public class Hbase {
    private String rowkey;//行键名
    private String qualifier;//字段名
    private String value;//值
    private String family;//列族名

    public Hbase() {
    }

    public Hbase(String rowkey, String qualifier, String value, String family) {
        this.rowkey = rowkey;
        this.qualifier = qualifier;
        this.value = value;
        this.family = family;
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return "Hbase{" +
                "rowkey='" + rowkey + '\'' +
                ", qualifier='" + qualifier + '\'' +
                ", value='" + value + '\'' +
                ", family='" + family + '\'' +
                '}';
    }
}
