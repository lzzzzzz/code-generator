# code-generator
半自动代码生成器


## 1.远程仓库使用：
~~~
    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }
~~~
依赖(分版本)：
  * **v1.07:  compile 'com.github.lzzzzzz:code-generator:v1.07'**


## 2.模板及生成文件目录说明：
  * 需要在主目录下创建template目录
  * template下创建template-model二级文件用于存放模板文件
  * 默认使用template-model下的模板（可制定其它生成目录）
  * 项目源码默认输出template/template-resource目录下，openmore源码文件指定生成在目标目录下


主要工具类：FreeMakerFactory.java、 openmore 项目定制内容(OMMakerFactory.java、DMMakerFactory.java)


## 3.使用方法：

   ### 公共基本功能
     /**
     * 此方法用于生成源码(同时生成目标文件)
     * @param modelFileName 模板文件名
     * @param out_file 要生成的文件
     * @param root 模板所需参数
     * */
    public DtoResponse freeMaker(String modelFileName, File out_file, Map<String, Object> root){...}

     /**
     * 此方法用于生成源码(同时生成目标文件)
     * @param modelFileName 模板文件名
     * @param fileName 要生成的文件名
     * @param root 模板所需参数
     * */
    public DtoResponse freeMaker(String modelFileName,String fileName,Map<String, Object> root){...}
    
    /**
     * 此方法用于生成源码(不生成目标文件)
     * @param modelFileName 模板文件名
     * @param root 模板所需参数
     * */
    public DtoResponse freeMaker(String modelFileName, Map<String, Object> root){...}
    
    
  ### openmore定制内容（用于指定一级目录、二级目录、直接在项目中目标包下生成源码文件）

     /**指定模板名和目标文件名生成源码文件
     * @param model_file_name: 模板文件名称
     * @param base_package: 生成文件一级目录
     * @param base_package: 生成文件二级目录级目录
     * @param fileName: 生成文件名称
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker(String model_file_name, String base_package, String modulePackage,
        String sub_package, String fileName , Map<String, Object> root ){...}
    
    /**指定模板名和目标文件名生成
    源码文件 * @param model_file_name: 模板文件名称
     * @param file_path: 生成文件路径
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker(String model_file_name, String file_path , Map<String, Object> root ) {...}
    
    /**指定模板名生成所需源码
     * @param model_file_name: 模板文件
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker(String model_file_name, Map<String, Object> root ){...} 
    
## 3.版本说明：
新版本从v1.05开始添加新旧mysql数据库支持，旧版本请更新
    
    ### 1.05新加基础功能:
  * FreeMakerFactory基本功能库不受影响
  * openmore定制内容生成固定包名下源码文件
  * 添加DMMakerFactory工厂类，读取数据库表映射生成实体类等模板源码
  *添加项目多模块支持
  * DMMakerFactory使用示例：
  
          String URL = "jdbc:mysql://localhost:3306/dbname?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
          String USERNAME = "root";
          String PASSWORD = "root";
          DMMakerFactory.build(DatabaseUtil.DB_VERSION_8,URL,USERNAME,PASSWORD).start();//根据命令行提示操作
          DMMakerFactory.build(DatabaseUtil.DB_VERSION_8,URL,USERNAME,PASSWORD).createAll();//直接根据所有表生成模板源码
    
   DB_VERSION_5、 DB_VERSION_8 为新旧版本支持添加内容，旧版mysql8以下使用com.mysql.jdbc.Driver驱动，新版本使用com.mysql.cj.jdbc.Driver驱动
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
