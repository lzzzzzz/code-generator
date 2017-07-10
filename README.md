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


## 2.文件说明：
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

     /**指定模板名和生成文件名（同时生成目标文件）
     * @param flag: 生成文件类型@link(OMMakerConfig)
     * @param fileName: 生成文件名称
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker( int flag , String fileName , Map<String, Object> root ){...}
    
    /**指定模板名生成所需源码（不生成生成目标文件）
     * @param flag: 生成文件类型@link(OMMakerConfig)
     * @param root: 所需元素
     * */
    public static DtoResponse freeMaker(int flag , Map<String, Object> root ) {...}
    
    
## 3.版本说明：
  ### v1.01:
    * 初版 实现指定模板传入所需元素生成源码基本功能
  ### v1.02:
    * 修改返回值错误状态码
  ### v1.03:
    * 修改异常捕获方式（非抛出式）
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
