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
  * **v1.01:  compile 'com.github.lzzzzzz:code-generator:v1.01'**
  * **v1.02:  compile 'com.github.lzzzzzz:code-generator:v1.02'**
  * **v1.03:  compile 'com.github.lzzzzzz:code-generator:v1.03'**
  * **v1.04:  compile 'com.github.lzzzzzz:code-generator:v1.04'**
  * **v1.04.2:  compile 'com.github.lzzzzzz:code-generator:v1.04.2'**


## 2.模板及生成文件目录说明：
  * 需要在主目录下创建template目录
  * template下创建template-model二级文件用于存放模板文件
  * 默认使用template-model下的模板（可制定其它生成目录）
  * 项目源码默认输出template/template-resource目录下，openmore源码文件指定生成在目标目录下


主要工具类：FreeMakerFactory.java   OMMakerFactory.java(openmore 项目定制内容)


## 3.使用方法：

   ### 基本功能
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
    public static DtoResponse freeMaker(String model_file_name, String base_package, String sub_package, 
    String fileName , Map<String, Object> root ){...}
    
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
  ### v1.01:
    * 初版 实现指定模板传入所需元素生成源码基本功能
  ### v1.02:
    * 修改返回值错误状态码
  ### v1.03:
    * 修改异常捕获方式（非抛出式）    
  ### v1.04:
    * FreeMakerFactory基本功能库不受影响
    * openmore定制内容方法参数中添加生成文件目录的一级包铭和二级包名，用于文件内使用和创建生成文件在制定位置
    ### v1.04.2:
    * FreeMakerFactory基本功能库不受影响
    * openmore定制内容生成固定包名下源码文件
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
