package org.codegenerator.utils.DtoCreaterUtils;

import org.codegenerator.utils.DtoCreaterUtils.DtoFileUtils;
import org.codegenerator.utils.DtoCreaterUtils.FreeMakerConfig;
import org.codegenerator.utils.DtoCreaterUtils.PropertiesUtils;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * Created by LZ on 2017/6/29.
 */
public class ProjectPathHelper {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取项目包路径
     */
    public String getNormalBasepackagePath(String modulePackage) {
        String base_str = "";
        try {
            String project_path = System.getProperty("user.dir");
            if(!StringUtils.isEmpty(modulePackage)){
                project_path += "/"+modulePackage;
            }
            base_str = PropertiesUtils.getPropertyValueByKey(DtoFileUtils.getGeneratorConfigFileName(FreeMakerConfig.GENERATOR_CONFIG_FILE_NAME), FreeMakerConfig.DEFAULT_BASEPACKAGE_KEY);
            if (null == base_str || "".equals(base_str)) {
                return null;
            } else {
                String sub_str_re = splidFileName(base_str);
                if (null == sub_str_re || "".equals(sub_str_re)) {
                    logger.debug(">>加载自定义输出目录subpackage：(" + base_str + ")出错，使用默认路径输出");
                    return null;
                }
                String path = project_path + FreeMakerConfig.PROJECT_MAIN_JAVA_PATH + File.separator + sub_str_re;
                return path;
            }
        } catch (Exception e) {
            logger.debug(">>加载配置输出目录basepackage：" + base_str + "出错，使用默认路径输出");
            return null;
        }
    }
    /**
     * 获取项目包路径
     */
    public String getBasepackagePath(String base_path_str, String modlePackage) {
        String base_str = base_path_str;
        if(!StringUtils.isEmpty(modlePackage)){
            base_str += "/" + modlePackage;
        }
        try {
            String project_path = System.getProperty("user.dir");
            if (null == base_str || "".equals(base_str)) {
                return null;
            } else {
                String sub_str_re = splidFileName(base_str);
                if (null == sub_str_re || "".equals(sub_str_re)) {
                    logger.debug(">>加载自定义输出目录basepackage：(" + base_str + ")出错，使用默认路径输出");
                    return null;
                }
                String path = project_path + FreeMakerConfig.PROJECT_MAIN_JAVA_PATH + File.separator + sub_str_re;
                return path;
            }
        } catch (Exception e) {
            logger.debug(">>加载自定义输出目录basepackage：(" + base_str + ")出错，使用默认路径输出");
            return null;
        }
    }

    /**
     * 获取二级包名路径
     */
    public String getNormalSubPackagePath(String modlePackage) {
        String base_path = getNormalBasepackagePath(modlePackage);
        if (null == base_path || "".equals(base_path)) {
            return null;
        }
        String sub_str = "";
        try {
            sub_str = PropertiesUtils.getPropertyValueByKey(DtoFileUtils.getGeneratorConfigFileName(FreeMakerConfig.GENERATOR_CONFIG_FILE_NAME), FreeMakerConfig.DEFAULT_SUBPACKAGE_PATH);
            String sub_str_re = splidFileName(sub_str);
            if (null == sub_str_re || "".equals(sub_str_re)) {
                logger.debug(">>加载自定义输出目录subpackage：(" + sub_str + ")出错，使用默认路径输出");
                return null;
            }
            sub_str = base_path + File.separator + sub_str_re;
            return sub_str;
        } catch (Exception e) {
            logger.debug(">>加载自定义输出目录subpackage：(" + sub_str + ")出错，使用默认路径输出");
            return null;
        }
    }

    /**
     * 获取二级包名路径
     */
    public String getSubPackagePath(String file_name_path_str, String modlePackage) {
        String base_path = getNormalBasepackagePath(modlePackage);
        if (null == base_path || "".equals(base_path)) {
            return null;
        }
        String sub_str = file_name_path_str;
        try {
            String sub_str_re = splidFileName(sub_str);
            if (null == sub_str_re || "".equals(sub_str_re)) {
                logger.debug(">>加载自定义输出目录subpackage：(" + sub_str + ")出错，使用默认路径输出");
                return null;
            }
            sub_str = base_path + File.separator + sub_str_re;
            return sub_str;
        } catch (Exception e) {
            logger.debug(">>加载自定义输出目录subpackage：(" + sub_str + ")出错，使用默认路径输出");
            return null;
        }
    }

    /**
     * 获取二级包名路径
     */
    public String getSubPackagePath(String base_file_path, String file_name_path_str, String modulePackage) {
        String base_path = getBasepackagePath(base_file_path, modulePackage);
        if (null == base_path || "".equals(base_path)) {
            return null;
        }
        String sub_str = file_name_path_str;
        try {
            String sub_str_re = splidFileName(sub_str);
            if (null == sub_str_re || "".equals(sub_str_re)) {
                logger.debug(">>加载自定义输出目录subpackage：(" + sub_str + ")出错，使用默认路径输出");
                return null;
            }
            sub_str = base_path + File.separator + sub_str_re;
            return sub_str;
        } catch (Exception e) {
            logger.debug(">>加载自定义输出目录subpackage：(" + sub_str + ")出错，使用默认路径输出");
            return null;
        }
    }


    /**
     * 字符串替换
     */
    public static String splidFileName(String filename_str) {
        if (null != filename_str && !"".equals(filename_str)) {
            String path_str = filename_str.replace(".", File.separator);
            return path_str;
        } else {
            return null;
        }
    }
}
