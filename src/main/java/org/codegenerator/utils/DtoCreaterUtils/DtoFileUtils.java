package org.codegenerator.utils.DtoCreaterUtils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZ on 2017/6/19.
 * DTO文件工具类
 */
public class DtoFileUtils {

    /**获取资源配置文件()*/
    public static String getGeneratorConfigFileName(String fileName){
        try{
            ResourceLoader loader = new DefaultResourceLoader();
            //注意这里前缀不能使用“classpath*:”，这样不能真正访问到对应的资源，exists()返回false
            Resource resource = loader.getResource("classpath:"+fileName);
            return resource.getURI().getPath();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**获取模块根目录*/
    public static String getRootGeneratorDirectory() throws Exception{
            String projectPath= FreeMakerConfig.ROOT_PATH+"\\"+ FreeMakerConfig.MODEL_ROOT_DIRECTORY;
            File file=new File(projectPath);
            if(!file.exists()){
                file.mkdirs();
            }
            return projectPath;
    }
    /**获取模板存放路径*/
    public static List<String> getModelDirectory() throws Exception{
        try {
            //模板根目录路径
            String projectPath=getRootGeneratorDirectory();
            System.out.println("projectPath:"+projectPath);
            File f=new File(projectPath);
            if(!f.exists()){
                    f.mkdirs();
            }
            List<String> paths=new ArrayList<String>();
            paths.add(projectPath);
            return DtoFileUtils.getAllFiles(new File(projectPath), paths);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("模板路径异常");
        }
    }
    /**获取某一目录下所有层级目录*/
    public static List<String> getAllFiles(File dir, List<String> resultPath)
    {
        File[] files=dir.listFiles();
        if(null==files){
            return resultPath;
        }
        for(int i=0;i<files.length;i++)
        {
            if(files[i].isDirectory()){//是目录则保存路径
                //这里面用了递归的算法
                String path=files[i].getPath();
                resultPath.add(path);
                getAllFiles(files[i], resultPath);
            }
            else {
                //是文件
               // System.out.println(getLevel(level)+files[i]);
            }
        }
        return resultPath;
    }
}
