package org.codegenerator.utils.openmore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.codegenerator.entity.DtoResponse;
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

    public DtoResponse pageCreateDto( String t , String className , String className_zn ,
                              String attrs , String controller_desc ){
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
            root.put("basepackage", "org.openmore");
            root.put("className", className);
            root.put("className_zn", className_zn);
            //root.put("attrs", attrs);
            root.put("controller_desc", controller_desc);
            root.put("attrs",att);
            if(null==t){
                return new DtoResponse(new Exception("参数错误->t is null"));
            }
            //添加生成类型
            int create_type=0;
            if (t.equals("dto")) {//生成dto
                create_type=OMMakerConfig.FLAG_CREATE_DTO_API;
            } else if (t.equals("service")) {//生成service
                create_type=OMMakerConfig.FLAG_CREATE_SERVICE;
            } else if(t.equals("serviceImpl")){//生成serviceImpl
                create_type=OMMakerConfig.FLAG_CREATE_SERVICE_IMPL;
            }else if (t.equals("controller")) {//生成controller
                create_type=OMMakerConfig.FLAG_CREATE_CONTROLLER_API;
            } else {
                return new DtoResponse( new Exception ( "参数错误->create type("+t+") is invalid" ));
            }
            OMMakerFactory.freeMaker(create_type,className,root);
            return OMMakerFactory.freeMaker(create_type,root);
        }catch (Exception e){
            return new DtoResponse(e);
        }

    }
}
