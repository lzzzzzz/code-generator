package org.codegenerator.utils.openmore;

/**
 * Created by LZ on 2017/6/30.
 * ssm-openmore-template定制内容
 */
public class OMMakerConfig {

    /**
     * 生成DTO-API下文件
     */
    public final static int FLAG_CREATE_DTO_API = 0x01;
    /**
     * 生成DTO-COMMON下文件
     */
    public final static int FLAG_CREATE_DTO_COMMON = 0x02;
    /**
     * 生成SERVICE下文件
     */
    public final static int FLAG_CREATE_SERVICE = 0X03;
    /**
     * 生成SERVICE-IMPL下文件
     */
    public final static int FLAG_CREATE_SERVICE_IMPL = 0x04;
    /**
     * 生成CONTROLLER-API下文件
     */
    public final static int FLAG_CREATE_CONTROLLER_API = 0x05;
    /**
     * 生成CONTROLLER-COMMON下文件
     */
    public final static int FLAG_CREATE_CONTROLLER_COMMON = 0x06;

    /**
     * 项目主目录相对(project)路径
     */
    public static String PROJECT_ROOT_BASE_PACKAGE = "org.openmore";

    public static String PROJECT_SUB_PACKAGE_DTO_API = "dto.api";
    public static String PROJECT_SUB_PACKAGE_DTO_COMMON = "dto.common";

    public static String PROJECT_SUB_PACKAGE_SERVICE = "service";
    public static String PROJECT_SUB_PACKAGE_SERVICE_IMPL = "service.impl";

    public static String PROJECT_SUB_PACKAGE_CONTROLLER_API = "controller.api";
    public static String PROJECT_SUB_PACKAGE_CONTROLLER_COMMON = "controller.common";


    public static String PROJECT_BASE_PACKAGE_STR = "base_package";
    public static String PROJECT_SUB_PACKAGE_STR = "base_package";

}
