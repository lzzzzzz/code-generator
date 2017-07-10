package org.codegenerator.utils.DtoCreaterUtils;

/**
 * Created by LZ on 2017/6/19.
 * Dto生成器属性工具类
 */
public class DtoParam {

    /**DTO模型属性名*/
    private String name;
    /**DTO模型属性类型*/
    private String value;
    /**DTO模型属性描述*/
    private String desc;

    public DtoParam(String name,String value,String desc){
        this.name=name;
        this.value=value;
        this.desc=desc;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
