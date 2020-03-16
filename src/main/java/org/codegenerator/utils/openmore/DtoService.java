package org.codegenerator.utils.openmore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.codegenerator.entity.DtoResponse;
import org.codegenerator.utils.DtoCreaterUtils.DtoParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by LZ on 2017/7/10.
 */
public class DtoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 一级目录名
     */
    private String base_package = "org.openmore";

    /**
     * 模块名
     */
    private String modluePackage;

    /**
     * 传入所需元素参数生成（可选）目标文件
     *
     * @param om: 封装参数模型
     */
    public DtoResponse pageCreateByOM(OpenMoreEntity om) {
        if (null == om) {
            return new DtoResponse(new Exception("参数错误"));
        }
        return pageCreateDto(om.getModel_name(), om.getClassName(), om.getClassName_zn(),
                om.getAttrs(), om.getController_desc(), om.getFlag_create_file());
    }

    public DtoService setBasePackage(String base_package) {
        this.base_package = base_package;
        return this;
    }

    public String getModluePackage() {
        return modluePackage;
    }

    public DtoService setModluePackage(String modluePackage) {
        this.modluePackage = modluePackage;
        return this;
    }

    /**
     * 传入所需元素参数生成（可选）目标文件
     *
     * @param t:               生成文件类型
     * @param className:       文件名称
     * @param className_zn:    文件中文描述
     * @param attrs:           所需元素
     * @param controller_desc: controller描述信息
     * @param flag_creat_file: 是否生成目标文件
     */
    public DtoResponse pageCreateDto(String t, String className, String className_zn,
                                     String attrs, String controller_desc, boolean flag_creat_file) {
        //创建模板文件和源码文件目录
        Map<String, String> model_names = new HashMap<String, String>();
        if (null == t || "".equals(t)) {
            logger.debug("参数错误->create type(t) is invilid");
            return new DtoResponse(new Exception("参数错误->create type(t) is invilid"));
        } else if ("dtoApi".equals(t)) {
            model_names.clear();
            model_names.put("${className}Dto.java", "dto.api");
        } else if ("dtoCommon".equals(t)) {
            model_names.clear();
            model_names.put("${className}Dto.java", "dto.common");
        } else if ("controllerApi".equals(t)) {
            model_names.clear();
            model_names.put("${className}Controller.java", "controller.api");
        } else if ("controllerCommon".equals(t)) {
            model_names.clear();
            model_names.put("${className}Controller.java", "controller.common");
        } else if ("service".equals(t)) {
            model_names.clear();
            model_names.put("${className}Service.java", "service");
            model_names.put("${className}ServiceImpl.java", "service.impl");
        } else if ("allApi".equals(t)) {
            model_names.clear();
            model_names.put("${className}Dto.java", "dto.api");
            model_names.put("${className}Service.java", "service");
            model_names.put("${className}ServiceImpl.java", "service.impl");
            model_names.put("${className}Controller.java", "controller.api");
        } else if ("allCommon".equals(t)) {
            model_names.clear();
            model_names.put("${className}Dto.java", "dto.common");
            model_names.put("${className}Service.java", "service");
            model_names.put("${className}ServiceImpl.java", "service.impl");
            model_names.put("${className}Controller.java", "controller.common");
        }

        List<DtoParam> att = null;
        try {
            Gson gson = new Gson();
            att = gson.fromJson(attrs,
                    new TypeToken<List<DtoParam>>() {
                    }.getType());
            if (null != att) {
                for (DtoParam ap : att) {
                    if (null == ap || null == ap.getName() || "".equals(ap.getName())) {
                        att.remove(ap);
                    }
                }
            }
            logger.debug(att == null ? "att is null" : att.size() + "");
        } catch (Exception e) {
            logger.debug("参数错误->无法解析");
            return new DtoResponse(new Exception("参数错误->无法解析"));
        }
        try {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("className", className);
            root.put("className_zn", className_zn);
            //root.put("attrs", attrs);
            root.put("controller_desc", controller_desc);
            root.put("attrs", att);
            String base_pa = base_package;
            if (flag_creat_file) {//创建文件
                for (Iterator<String> i = model_names.keySet().iterator(); i.hasNext(); ) {
                    DtoResponse re = null;
                    String model_name = i.next();
                    root.put("basepackage", base_pa);
                    root.put("subpackage", model_names.get(model_name));
                    re = OMMakerFactory.freeMaker(model_name, base_pa, modluePackage, model_names.get(model_name), className, root);
                    if (re.getResponseCode() == DtoResponse.RESPONSE_CODE_ERROR) {
                        return re;
                    }
                }
                return new DtoResponse("文件创建成功");
            } else {//预览源码
                List<DtoResponse> responses = new ArrayList<DtoResponse>();
                for (Iterator<String> i = model_names.keySet().iterator(); i.hasNext(); ) {
                    DtoResponse re = null;
                    String model_name = i.next();
                    root.put("basepackage", base_pa);
                    root.put("subpackage", model_names.get(model_name));
                    re = OMMakerFactory.freeMaker(model_name, base_pa, modluePackage, model_names.get(model_name), className, root);
                    if (re.getResponseCode() == DtoResponse.RESPONSE_CODE_ERROR) {
                        return re;
                    } else {
                        responses.add(re);
                    }
                }
                DtoResponse re3 = new DtoResponse("");
                for (DtoResponse re2 : responses) {
                    re3.setResponse_data(re3.getResponse_data() + "\n\n" + re2.getResponse_data());
                }
                return re3;
            }
        } catch (Exception e) {
            return new DtoResponse(e);
        }
    }
}
