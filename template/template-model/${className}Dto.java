package ${basepackage!""}.${subpackage!""}.api;

@ApiModel("${className_zn!""}显示模型")
public class ${className!""}Dto {
<#if attrs??>
    <#list attrs!"" as attr>
    @ApiModelProperty(value="${attr.desc!""}")
    private ${attr.value} ${attr.name};
    </#list>

    <#list attrs!"" as attr>
    private void set${attr.name?cap_first}(${attr.value} ${attr.name}){
        this.${attr.name}=${attr.name};
    }

    private ${attr.value} get${attr.name?cap_first}(){
        return this.${attr.name}
    }
    </#list>
</#if>
}