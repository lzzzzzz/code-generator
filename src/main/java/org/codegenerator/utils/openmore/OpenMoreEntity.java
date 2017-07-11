package org.codegenerator.utils.openmore;

/**
 * Created by LZ on 2017/7/11.
 */
public class OpenMoreEntity {
    private String model_name;
    private String base_package;
    private String sub_package;
    private String className;
    private String className_zn;
    private String attrs;
    private String controller_desc;
    private boolean flag_create_file;


    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getBase_package() {
        return base_package;
    }

    public void setBase_package(String base_package) {
        this.base_package = base_package;
    }

    public String getSub_package() {
        return sub_package;
    }

    public void setSub_package(String sub_package) {
        this.sub_package = sub_package;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName_zn() {
        return className_zn;
    }

    public void setClassName_zn(String className_zn) {
        this.className_zn = className_zn;
    }

    public String getController_desc() {
        return controller_desc;
    }

    public void setController_desc(String controller_desc) {
        this.controller_desc = controller_desc;
    }

    public boolean getFlag_create_file() {
        return flag_create_file;
    }

    public void setFlag_create_file(boolean flag_create_file) {
        this.flag_create_file = flag_create_file;
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }
}
