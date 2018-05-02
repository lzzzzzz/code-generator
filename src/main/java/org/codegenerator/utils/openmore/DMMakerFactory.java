package org.codegenerator.utils.openmore;

import com.google.gson.Gson;
import org.codegenerator.entity.DtoResponse;
import org.codegenerator.utils.DtoCreaterUtils.DatabaseUtil;
import org.codegenerator.utils.DtoCreaterUtils.DtoParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DMMakerFactory {

    private static DMMakerFactory instance;

    private static DatabaseUtil databaseUtil;

    /**读取数据库表生成实体类和模板源码*/
    private DMMakerFactory(){}

    public static DMMakerFactory build(String DRIVER, String URL, String USERNAME, String PASSWORD){
        if(null==instance){
            instance = new DMMakerFactory();
        }
        databaseUtil = new DatabaseUtil(DRIVER,URL,USERNAME,PASSWORD);
        return instance;
    }

    /**命令行提示操作*/
    public void start(){
        List<String> tableNames = databaseUtil.getTableNames();
        System.out.println("-1: cancle");
        System.out.println("0: create by all tables");
        for (int i=0;i < tableNames.size();i++) {
            System.out.println(i+1+": create by "+tableNames.get(i));
        }
        System.out.println("input unmber will do as resume：");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        if(x<0){
            System.out.println("...cancle with do nothing");
            return;
        }else if(x ==0){
            System.out.println("start create...");
            for (String tableName : tableNames) {
                createByTableName(tableName);
            }
            System.out.println("...create done");
        }else if(x>0&&x<=tableNames.size()){
            System.out.println("start create...");
            createByTableName(tableNames.get(x-1));
            System.out.println("...create done");
        }else{
            System.out.println("cancle for illegal number!");
        }
    }

    /**直接创建全部*/
    public void createAll(){
        List<String> tableNames = databaseUtil.getTableNames();
        System.out.println("start create...");
        for (String tableName : tableNames) {
            createByTableName(tableName);
        }
        System.out.println("...create done");
    }

    private void createByTableName(String tableName){
        List<String> columns = databaseUtil.getColumnNames(tableName);
        List<String> ColumnTypes = databaseUtil.getColumnTypes(tableName);
        List<String> ColumnComments = databaseUtil.getColumnComments(tableName);
        List<DtoParam> dps=new ArrayList<DtoParam>();
        for(int i=0;i<columns.size();i++){
            DtoParam dp=new DtoParam(columns.get(i),ColumnTypes.get(i),ColumnComments.get(i));
            dps.add(dp);
        }
        String t="allApi";
        Gson gson=new Gson();
        String attrs=gson.toJson(dps);
        DtoService service=new DtoService();
        DtoResponse re= service.pageCreateDto(t, databaseUtil.initcap(tableName),
                null,attrs,null,true);
        if(re.getResponseCode()==DtoResponse.RESPONSE_CODE_SUCCESS){
            System.out.println("create by："+tableName);
            //System.out.println(re.getResponse_data());
        }else{
            System.out.println(re.getE().getMessage());
        }
    }

    public static void main(String[] args){
        String DRIVER = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://39.108.123.150:3306/tb_han?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        String USERNAME = "user_lz";
        String PASSWORD = "lz_627458";
        DMMakerFactory.build(DRIVER,URL,USERNAME,PASSWORD).start();
    }
}
