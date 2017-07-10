# template
## 介绍
半自动代码生成器(用于生成系统DTO、Service、ServiceImpl、Controller类)
未实现功能：
    1.从数据库读取实体类生成Entity、Dao、DaoImpl类
    2.非基本数据类型无法import对应类
    3.未实现通过反射entity生成DTO、Service、ServiceImpl、Controller类


##环境配置

* 生成器默认模板类位于template及其子目录中（建议放在template下template-model目录或其子目录）
* 生成器生成的源码文件默认位于template-source目录中（可在generator配置文件中自定义）
* generator配置文件需要放在工程资源目录下
* 其它自定义信息可在genetator配置文件及FreeMakerConfig.java配置文件中修改


##开始

浏览器打开/page/dtoCreater，进入Dto生成器界面

工具类使用：
    FreeMakerFactory.getInstance().freeMaker(...);


