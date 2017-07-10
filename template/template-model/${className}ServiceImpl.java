package ${basepackage!""}.${subpackage!""}.impl;

import ${basepackage!""}.${subpackage!""}.dto.service.${className!""}Service;

import ${basepackage!""}.${subpackage!""}.dto.api.${className!""}Dto;

@Service
public interface ${className!""}ServiceImpl implements ${className!""}Service{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ${className!""}Mapper ${className!""}Dao;

    public ${className!""} getById(){
        return ${className!""}Dao.get${className!""}ById();
        }

    /**增*/
    public void create(${className!""} ${className!""}){
        ${className!""}Dao.insert${className!""}(${className!""} );
        }
    /**删*/
    public void deleteById(Long id){
        ${className!""}Dao.deleteById(id);
        }
    /**改*/
    public void update(Long id, ${className!""} ${className!""}){
        ${className!""}Dao.update(id , ${className!""});
        }
    /**查*/
    public ${className!""}Dto get${className!""}Profile(long uid){
        ${className!""} ${className!""}=${className!""}Dao.getById(uid);
        ${className!""}Dto dto= new ${className!""}Dto();
        BeanUtils.copyProperties(${className!""}, dto);
        return dto;
        }
}