package org.codegenerator.test;

import org.codegenerator.entity.TestCase;
import org.codegenerator.utils.DtoCreaterUtils.FreeMakerFactory;
import org.codegenerator.utils.OfficePoi.POIReader;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by LZ on 2017/7/19.
 */
public class test2 {

    public static void main(String[] args){
        //excel转换测试用例
        try{
            test2x();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void test1(String file_path , int startNuber){

        try {
            if(!new File(file_path).exists()){
                throw new RuntimeException("文件不存在");
            }
            List<TestCase> cases=POIReader.readXlsx(file_path,startNuber);
            Map<String,Object> root=new HashMap<String,Object>();
            root.put("attrs",cases);
            FreeMakerFactory.getInstance().freeMaker("testlink_model.xml","testlink.xml",root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test2x()throws IOException{
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
        System.out.println("请输入需要转换的文件路径：");
        String line =null;
        int number=3252;
        while((line=bufr.readLine())!=null)    {
            if("over".equals(line)) {
                //判断输入over，就结束循环
                break;
            }else{
                test1(line,number);
            }
            bufw.write(line.toUpperCase());
            bufw.newLine();                    //换行
            bufw.flush();                      //刷新
        }
        bufw.close();                          //关闭
    }
}
