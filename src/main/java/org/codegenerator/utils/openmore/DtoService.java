package org.codegenerator.utils.openmore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.codegenerator.entity.DtoResponse;
import org.codegenerator.utils.DtoCreaterUtils.DtoParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LZ on 2017/7/10.
 */
public class DtoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 传入所需元素参数生成（可选）目标文件
     * @param om: 封装参数模型
     * */
    public DtoResponse pageCreateDto(OpenMoreEntity om){
       if(null==om) {
           return new DtoResponse(new Exception("参数错误"));
       }
       return pageCreateDto(om.getModel_name(),om.getBase_package(),om.getSub_package(),
               om.getClassName(),om.getClassName_zn(),om.getAttrs(),om.getController_desc(),om.getFlag_create_file());
    }

/**
     * 传入所需元素参数生成（可选）目标文件
     * @param model_name: 模板文件名称
     * @param base_package: 生成文件一级目录
     * @param base_package: 生成文件二级目录级目录
     * @param className: 文件名称
     * @param className_zn: 文件中文描述
     * @param attrs: 所需元素
     * @param controller_desc: controller描述信息
     * @param flag_creat_file: 是否生成目标文件
     * */
    public DtoResponse pageCreateDto( String model_name , String base_package, String sub_package, String className , String className_zn ,
                              String attrs , String controller_desc , boolean flag_creat_file){
        List<DtoParam> att=null;
        try{
            Gson gson=new Gson();
            att = gson.fromJson(attrs,
                    new TypeToken<List<DtoParam>>() {
                    }.getType());
            if(null!=att){
                for(DtoParam ap:att){
                    if(null==ap||null==ap.getName()||"".equals(ap.getName())){
                        att.remove(ap);
                    }
                }
            }
            logger.debug(att==null?"att is null":att.size()+"");
        }catch (Exception e){
            logger.debug("参数错误->无法解析");
            return new DtoResponse(new Exception("参数错误->无法解析"));
        }
        try {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("basepackage", base_package);
            root.put("subpackage", sub_package);
            root.put("className", className);
            root.put("className_zn", className_zn);
            //root.put("attrs", attrs);
            root.put("controller_desc", controller_desc);
            root.put("attrs",att);
            if(flag_creat_file){
                DtoResponse re=OMMakerFactory.freeMaker(model_name, base_package,sub_package, className,root);
                if(re.getResponseCode()==DtoResponse.RESPONSE_CODE_ERROR){
                    logger.error("freeMaker==>创建输出文件出错:"+re.getE().getMessage());
                }
            }
            return OMMakerFactory.freeMaker(model_name,root);
        }catch (Exception e){
            return new DtoResponse(e);
        }
    }
}
