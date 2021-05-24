package com.andrew.mysqltest.config;

public class DataBaseContextHolder {
    public enum DataBaseType{
        master,slave
    }

    private static final ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<>();

    public static DataBaseType getDataBaseType() {
        return contextHolder.get() == null ? DataBaseType.master : DataBaseType.slave;
    }

    public static void setDataBaseType(DataBaseType dataBaseType){
        contextHolder.set(dataBaseType);
    }

    public static void clean(){
        contextHolder.remove();
    }

}
