package ${basepackage!""}.${subpackage!""};

import ${basepackage!""}.${subpackage!""}.dto.api.${className}Dto;

public interface ${className!""}Service {

    ${className!""} getById();

    /**增*/
    void create(${className!""} ${className!""});
    /**删*/
    void deleteById(Long id);
    /**改*/
    void update(Long id, ${className!""} ${className!""});
    /**查*/
    ${className!""}Dto get${className!""}Profile(long uid);
}