package org.codegenerator.utils.openmore;


import org.codegenerator.entity.DtoResponse;
import org.codegenerator.utils.DtoCreaterUtils.*;

import java.io.File;
import java.util.Map;

/**
 * Created by LZ on 2017/6/30.
 * openmore定制化代码生成器
 */
public class OMMakerFactory{


    /**指定模板名和目标文件名生成源码文件
     * @param model_file_name: 模板文件名称
     * @param base_package: 生成文件一级目录
     * @param sub_package: 生成文件二级目录
     * @param fileName: 生成文件名称
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker(String model_file_name, String base_package, String sub_package, String fileName , Map<String, Object> root ){

        try {
            if (null == fileName) {
                throw new RuntimeException("指定输出文件不合法");
            }
            //获取输出目录
            String out_file_dir = "";
            if(null==base_package||"".equals(base_package)){
                String source_path = DtoFileUtils.getGeneratorConfigFileName(FreeMakerConfig.GENERATOR_CONFIG_FILE_NAME);
                out_file_dir = PropertiesUtils.getPropertyValueByKey(source_path, "outRoot");
                if (null == source_path || "".equals(source_path)) {
                    //自定义目录不存在则使用默认目录
                    out_file_dir = FreeMakerConfig.ROOT_PATH + "\\" + FreeMakerConfig.DEFAULT_SOURCES_DIRECTORY;
                } else {
                    out_file_dir = FreeMakerConfig.ROOT_PATH + "\\" + out_file_dir;
                    File source_dir = new File(out_file_dir);
                    if (!source_dir.exists()) {
                        source_dir.mkdirs();
                    }
                }
                System.out.println("freemaker未指定生成文件根目录使用默认输出目录："+out_file_dir);
            }else{
                ProjectPathHelper helper = new ProjectPathHelper();
                out_file_dir=helper.getBasepackagePath(base_package);
                String sub_str=ProjectPathHelper.splidFileName(sub_package);
                if(null!=sub_str&&!"".equals(sub_str)){
                    out_file_dir=out_file_dir+"\\"+sub_str;
                }
            }
            if(null!=model_file_name&&!"".equals(model_file_name)&&model_file_name.contains("${className}")){
                fileName=model_file_name.replace("${className}",fileName);
            }
            //获取输出文件
            File out_file = new File(out_file_dir + "\\" + fileName);
            return FreeMakerFactory.getInstance().freeMaker(model_file_name, out_file, root);
        }catch (Exception e){
            return new DtoResponse(e);
        }
    }
    /**指定模板名和生成文件名
     * @param model_file_name: 模板文件名称
     * @param file_path: 生成文件路径
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker(String model_file_name, String file_path , Map<String, Object> root ){

        try {
            if (null == file_path) {
                throw new RuntimeException("指定输出文件不合法");
            }
            return FreeMakerFactory.getInstance().freeMaker(model_file_name, file_path, root);
        }catch (Exception e){
            return new DtoResponse(e);
        }
    }

    /**指定模板名生成所需源码
     * @param model_file_name: 模板文件
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker(String model_file_name, Map<String, Object> root ) {
        try {
            return FreeMakerFactory.getInstance().freeMaker(model_file_name, root);
        }catch (Exception e){
            return new DtoResponse(e);
        }
    }

}
