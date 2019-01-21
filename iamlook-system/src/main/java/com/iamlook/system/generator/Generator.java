package com.iamlook.system.generator;



import com.iamlook.common.utils.GeneratorUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器
 */
public class Generator {
    // 根据命名规范，只修改此常量值即可
    private static String AUTHOR = "youxun";
    private static String MODULE = "sjy-eval-user";
    private static String DATABASE = "eval";
    private static String TABLE_NAME = "d_class";
    private static String PACKAGE_NAME = "com.sjy.eval.user";
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/eval?useSSL=false&serverTimezone=UTC&allowMultiQueries=true";
    private static String JDBC_USERNAME = "root";
    private static String JDBC_PASSWORD = "root";
    // 需要insert后返回主键的表配置，key:表名,value:主键名
    private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();
    static {
        LAST_INSERT_ID_TABLES.put("d_class", "code");
    }

    /**
     * 自动代码生成
     */
    public static void main(String[] args) {
        try {
            GeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE, DATABASE, TABLE_NAME, PACKAGE_NAME, LAST_INSERT_ID_TABLES, AUTHOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
