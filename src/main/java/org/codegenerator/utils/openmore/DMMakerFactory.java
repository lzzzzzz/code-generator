package org.codegenerator.utils.openmore;

import com.google.gson.Gson;
import org.codegenerator.entity.DtoResponse;
import org.codegenerator.utils.DtoCreaterUtils.DatabaseUtil;
import org.codegenerator.utils.DtoCreaterUtils.DtoParam;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DMMakerFactory {

    private static DMMakerFactory instance;

    private static DatabaseUtil databaseUtil;

    /**
     * 设置一级目录包名
     */
    private String basePackage = "org.openmore";

    /**
     * 模块名
     */
    private String modulePackage;

    /**
     * 读取数据库表生成实体类和模板源码
     */
    private DMMakerFactory() {
    }

    public static DMMakerFactory build(int DB_VERSION, String URL, String USERNAME, String PASSWORD) {
        if (null == instance) {
            instance = new DMMakerFactory();
        }
        databaseUtil = new DatabaseUtil(DB_VERSION, URL, USERNAME, PASSWORD);
        return instance;
    }

    /**
     * 设置一级目录包名
     */
    public DMMakerFactory setBasePackage(String basePackage) {
        if (null != basePackage && !basePackage.isEmpty())
            this.basePackage = basePackage;
        return this;
    }
    /**
     * 设置模块名
     */
    public DMMakerFactory setModulePackage(String modulePackage) {
        if (!StringUtils.isEmpty(modulePackage)){
            this.modulePackage = modulePackage;
        }
        return this;
    }

    /**
     * 命令行提示操作
     */
    public void start() {
        List<String> tableNames = databaseUtil.getTableNames();
        System.out.println("-1: cancle");
        System.out.println("0: create by all tables");
        for (int i = 0; i < tableNames.size(); i++) {
            System.out.println(i + 1 + ": create by " + tableNames.get(i));
        }
        System.out.println("input unmber will do as resume：");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        if (x < 0) {
            System.out.println("...cancle with do nothing");
            return;
        } else if (x == 0) {
            System.out.println("start create...");
            for (String tableName : tableNames) {
                createByTableName(tableName);
            }
            System.out.println("...create done");
        } else if (x > 0 && x <= tableNames.size()) {
            System.out.println("start create...");
            createByTableName(tableNames.get(x - 1));
            System.out.println("...create done");
        } else {
            System.out.println("cancle for illegal number!");
        }
    }

    /**
     * 直接创建全部
     */
    public void createAll() {
        List<String> tableNames = databaseUtil.getTableNames();
        System.out.println("start create...");
        for (String tableName : tableNames) {
            createByTableName(tableName);
        }
        System.out.println("...create done");
    }

    private void createByTableName(String tableName) {
        List<String> columns = databaseUtil.getColumnNames(tableName);
        List<String> ColumnTypes = databaseUtil.getColumnTypes(tableName);
        List<String> ColumnComments = databaseUtil.getColumnComments(tableName);
        List<DtoParam> dps = new ArrayList<DtoParam>();
        for (int i = 0; i < columns.size(); i++) {
            DtoParam dp = new DtoParam(columns.get(i), ColumnTypes.get(i), ColumnComments.get(i));
            dps.add(dp);
        }
        String t = "allApi";
        Gson gson = new Gson();
        String attrs = gson.toJson(dps);
        DtoService service = new DtoService();
        service.setBasePackage(basePackage);
        service.setModluePackage(modulePackage);
        DtoResponse re = service.pageCreateDto(t, databaseUtil.initcap(tableName),
                null, attrs, null, true);
        if (re.getResponseCode() == DtoResponse.RESPONSE_CODE_SUCCESS) {
            System.out.println("create by：" + tableName);
            //System.out.println(re.getResponse_data());
        } else {
            System.out.println(re.getE().getMessage());
        }
    }

    //TODO: 此处测试需要加上jdbc-connector对应版本依赖
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3307/qu?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        String USERNAME = "root";
        String PASSWORD = "root";
        DMMakerFactory.build(DatabaseUtil.DB_VERSION_8, URL, USERNAME, PASSWORD).start();
    }
}
