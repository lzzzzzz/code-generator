package org.codegenerator.utils.openmore;


import org.codegenerator.entity.DtoResponse;
import org.codegenerator.utils.DtoCreaterUtils.FreeMakerFactory;
import org.codegenerator.utils.DtoCreaterUtils.ProjectPathHelper;

import java.io.File;
import java.util.Map;

/**
 * Created by LZ on 2017/6/30.
 * openmore定制化代码生成器
 */
public class OMMakerFactory{


    /**指定模板名和生成文件名
     * @param flag: 生成文件类型@link(OMMakerConfig)
     * @param fileName: 生成文件名称
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker( int flag , String fileName , Map<String, Object> root ){

        try {
            if (null == fileName) {
                throw new RuntimeException("指定输出文件不合法");
            }
            if (flag < OMMakerConfig.FLAG_CREATE_DTO_API || flag > OMMakerConfig.FLAG_CREATE_CONTROLLER_COMMON) {
                throw new RuntimeException("指定生成文件类型不合法：" + flag);
            }

            String mode_file_path = "";
            String out_file_path = "";
            ProjectPathHelper helper = new ProjectPathHelper();
            switch (flag) {
                case OMMakerConfig.FLAG_CREATE_DTO_API://生成dto-api下文件
                    out_file_path = helper.getSubPackagePath(OMMakerConfig.PROJECT_ROOT_BASE_PACKAGE, OMMakerConfig.PROJECT_SUB_PACKAGE_DTO_API);
                    mode_file_path = "${className}Dto.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_DTO_COMMON://生成dto-common下文件
                    out_file_path = helper.getSubPackagePath(OMMakerConfig.PROJECT_ROOT_BASE_PACKAGE, OMMakerConfig.PROJECT_SUB_PACKAGE_DTO_COMMON);
                    mode_file_path = "${className}Dto.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_SERVICE://生成service下文件
                    out_file_path = helper.getSubPackagePath(OMMakerConfig.PROJECT_ROOT_BASE_PACKAGE, OMMakerConfig.PROJECT_SUB_PACKAGE_SERVICE);
                    mode_file_path = "${className}Service.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_SERVICE_IMPL://生成service_impl下文件
                    out_file_path = helper.getSubPackagePath(OMMakerConfig.PROJECT_ROOT_BASE_PACKAGE, OMMakerConfig.PROJECT_SUB_PACKAGE_SERVICE_IMPL);
                    mode_file_path = "${className}ServiceImpl.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_CONTROLLER_API://生成controller_api下文件
                    out_file_path = helper.getSubPackagePath(OMMakerConfig.PROJECT_ROOT_BASE_PACKAGE, OMMakerConfig.PROJECT_SUB_PACKAGE_CONTROLLER_API);
                    mode_file_path = "${className}Controller.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_CONTROLLER_COMMON://生成controller_common下文件
                    out_file_path = helper.getSubPackagePath(OMMakerConfig.PROJECT_ROOT_BASE_PACKAGE, OMMakerConfig.PROJECT_SUB_PACKAGE_CONTROLLER_COMMON);
                    mode_file_path = "${className}Controller.java";
                    break;
                default:
                    break;
            }
            File out_file = new File(out_file_path + "\\" + fileName);
            FreeMakerFactory.getInstance().freeMaker(mode_file_path, out_file, root);
            return freeMaker(flag, root);
        }catch (Exception e){
            return new DtoResponse(e);
        }
    }

    /**指定模板名生成所需源码
     * @param flag: 生成文件类型
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker(int flag , Map<String, Object> root ) {
        try {
            if (flag < OMMakerConfig.FLAG_CREATE_DTO_API || flag > OMMakerConfig.FLAG_CREATE_CONTROLLER_COMMON) {
                throw new RuntimeException("指定生成文件类型不合法：" + flag);
            }
            String mode_file_path = "";
            ProjectPathHelper helper = new ProjectPathHelper();
            switch (flag) {
                case OMMakerConfig.FLAG_CREATE_DTO_API://生成dto-api下文件
                    mode_file_path = "${className}Dto.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_DTO_COMMON://生成dto-common下文件
                    mode_file_path = "${className}Dto.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_SERVICE://生成service下文件
                    mode_file_path = "${className}Service.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_SERVICE_IMPL://生成service_impl下文件
                    mode_file_path = "${className}ServiceImpl.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_CONTROLLER_API://生成controller_api下文件
                    mode_file_path = "${className}Controller.java";
                    break;
                case OMMakerConfig.FLAG_CREATE_CONTROLLER_COMMON://生成controller_common下文件
                    mode_file_path = "${className}Controller.java";
                    break;
                default:
                    break;
            }
            return FreeMakerFactory.getInstance().freeMaker(mode_file_path, root);
        }catch (Exception e){
            return new DtoResponse(e);
        }
    }

}
