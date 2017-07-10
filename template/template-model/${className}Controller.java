package ${basepackage!""}.${subpackage!""}.api;

import io.swagger.annotations.*;
import ${basepackage!""}.${subpackage!""}.common.BaseController;
import ${basepackage!""}.dto.common.ErrorResponseEntity;
import ${basepackage!""}.entity.${className!"*"};
import ${basepackage!""}.exception.common.InvalidateParamsException;
import ${basepackage!""}.service.${className!""}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ${basepackage!""}.dto.api.${className!""}Dto;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

<!--
    Created by ${author!"ad"} on ${time!"time"}
-->
@Api(value = "/${className!""}", tags = "${className!""}Api", description = "${controller_desc!""}")
@RequestMapping(value = "/${className!""}", produces = {APPLICATION_JSON_UTF8_VALUE})
@RestController
public class ${className!""}Controller extends BaseController {
     private Logger logger = LoggerFactory.getLogger(this.getClass());

     @Autowired
     private ${className!""}Service ${className!""}Service;
     @ApiOperation(value = "根据id获取${className_zn!""}信息", response = ${className!""}Dto.class)
     @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：找不到id={id}的${className!""}", response = ErrorResponseEntity.class) })
     @RequestMapping(method = RequestMethod.GET, value = "/{id}")
     public ResponseEntity get${className!""}ById(@PathVariable @ApiParam(required = true, value = "${className_zn!""}id") Long id){
        logger.debug(">> get${className}ById");
        ${classNmae!""}Dto ${classNmae!""} = ${classNmae!""}Service.get${classNmae!""}Profile(id);
        if(${classNmae!""} == null){
            throw new InvalidateParamsException("请求失败：找不到id=" + id + "的${className_zn!""}");
        }
        return new ResponseEntity(${classNmae!""}, HttpStatus.OK);
     }

     @ApiOperation("更新${className_zn!""}")
         @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：更新的数据不存在", response = ErrorResponseEntity.class),
                                 @ApiResponse(code = 400, message = "请求失败：数据更新失败", response = ErrorResponseEntity.class) })
         @RequestMapping(method = RequestMethod.PUT, value = "{id}")
         public void update${className!""}(@PathVariable @ApiParam(value = "${className_zn!""}id", required = true) Long id,
                                @RequestBody @ApiParam(value = "新${className_zn!""}信息", required = true) ${className!""} ${className!""})
         {
             logger.debug(">> update${className!""}");
             ${className!""} entity = ${className!""}Service.getById(id);
             if(entity == null){
                 throw new InvalidateParamsException("请求失败：更新的数据不存在");
             }
             ${className!""}Service.update(id, ${className!""});
         }

     @ApiOperation("创建${className_zn!""}")
         @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：数据创建失败", response = ErrorResponseEntity.class) })
         @RequestMapping(method = RequestMethod.POST, consumes = {APPLICATION_JSON_UTF8_VALUE})
         public void create${className!""}(@RequestBody @ApiParam(value = "创建${className_zn!""}", required = true) ${className!""} ${className!""}){
              logger.debug(">> create${className!""}");
              ${className!""}Service.create(${className!""});
         }
     @ApiOperation(value = "删除指定${className_zn!""}")
        @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：数据删除失败", response = ErrorResponseEntity.class) })
        @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
        public void delete${className!""}(@PathVariable @ApiParam(value = "${className_zn!""}id", required = true) Long id)
        {
            logger.debug(">> delete${className!""}");
            ${className!""}Service.deleteById(id);
        }








}




