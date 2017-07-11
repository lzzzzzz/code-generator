package org.codegenerator.test;


import com.google.gson.Gson;
import org.codegenerator.entity.DtoImage;
import org.codegenerator.entity.DtoResponse;
import org.codegenerator.utils.DtoCreaterUtils.*;
import org.codegenerator.utils.openmore.DtoService;
import org.codegenerator.utils.openmore.OMMakerConfig;
import org.codegenerator.utils.openmore.OMMakerFactory;
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
        test5();

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
    private static void test5(){
        List<DtoParam> list=new ArrayList<DtoParam>();
        //DtoParam ap1=new DtoParam("id","int","省份id");
        DtoParam ap2=new DtoParam("id","int","省份id");
        DtoParam ap3=new DtoParam("name","String","姓名");
        list.add(ap2);
        list.add(ap3);
        String base_pa="org.codegenerator";
        String sub_pa="";
        /*ProjectPathHelper helper=new ProjectPathHelper();
        String out_file_dir=helper.getBasepackagePath(base_pa);
        out_file_dir=out_file_dir+ProjectPathHelper.splidFileName(sub_pa);
        File outfile=new File("C:\\Users\\LZ\\Desktop\\test2.xml");*/
        Gson gson=new Gson();
        String attrs=gson.toJson(list);
        DtoService service=new DtoService();
        DtoResponse re= service.pageCreateDto("${className}Dto.java", base_pa, sub_pa, "Person",
                "学生",attrs,"学生控制器",true);
        if(re.getResponseCode()==DtoResponse.RESPONSE_CODE_SUCCESS){
            System.out.println(re.getResponse_data());
        }else{
            System.out.println(re.getE().getMessage());
        }

    }
}
