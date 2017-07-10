package org.codegenerator.test;


import org.codegenerator.entity.DtoImage;
import org.codegenerator.utils.DtoCreaterUtils.*;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LZ on 2017/6/29.
 */
public class Test {

    public static void main(String[] args){
        test4();

    }


    private static void test1(){
        ProjectPathHelper helper=new ProjectPathHelper();
        //String path=helper.getSubPackagePath("controller");
        String path=helper.getBasepackagePath("com.openmore");
        System.out.println(path);
    }
    private static void test2(){
       String path= DtoFileUtils.getGeneratorConfigFileName("generator.xml");
        System.out.println(path);
    }

    private static void test3(){
        try {
            ResourceLoader loader = new DefaultResourceLoader();
            Resource resource = loader.getResource("classpath:"+ FreeMakerConfig.GENERATOR_CONFIG_FILE_NAME);
            String path= null;
            path = resource.getURI().getPath().toString();
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test4(){
        try{
            List<DtoImage> list=new ArrayList<DtoImage>();
            DtoImage image1=new DtoImage();
            image1.setIndex(1);
            image1.setBinaryData(ImageUtils.getImageStr("D:\\lizhu\\photo\\2015基地合照\\基地\\IMG_1785.JPG"));
            image1.setPath("D:\\lizhu\\photo\\2015基地合照\\基地\\IMG_1785.JPG");
            list.add(image1);
            DtoImage image2=new DtoImage();
            image2.setIndex(2);
            image2.setBinaryData(ImageUtils.getImageStr("D:\\lizhu\\photo\\2015基地合照\\基地\\IMG_1785.JPG"));
            image2.setPath("D:\\lizhu\\photo\\2015基地合照\\基地\\IMG_1785.JPG");
            list.add(image2);
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("images", list);
            File outfile=new File("C:\\Users\\LZ\\Desktop\\test2.xml");
            FreeMakerFactory.getInstance().freeMaker("test.xml",outfile,root);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("出错");
        }
    }
}
