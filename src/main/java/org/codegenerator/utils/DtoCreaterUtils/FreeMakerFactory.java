package org.codegenerator.utils.DtoCreaterUtils;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.codegenerator.entity.DtoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by LZ on 2017/6/19.
 * 此单例类用于加载freeMaker配置
 */
public class FreeMakerFactory {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static FreeMakerFactory instance;

    /**
     * freeMaker配置设置
     */
    private Configuration cfg = null;

    public static FreeMakerFactory getInstance() throws Exception {
        if (instance == null) {
            instance = new FreeMakerFactory();
        }
        return instance;
    }

    private FreeMakerFactory() throws Exception {
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_22);
            //配置模板所在目录(模板需要放在此目录或子目录下)
            List<String> paths = DtoFileUtils.getModelDirectory();
            FileTemplateLoader[] fileLoaders = new FileTemplateLoader[paths.size()];
            for (int i = 0; i < paths.size(); i++) {
                fileLoaders[i] = new FileTemplateLoader(new File(paths.get(i)));
            }
            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(fileLoaders);
            //加载模板所在目录
            cfg.setTemplateLoader(multiTemplateLoader);
            //设置默认编码
            cfg.setDefaultEncoding(FreeMakerConfig.DEFAULT_ENCODING);
            cfg.setNumberFormat("###############");
            cfg.setBooleanFormat("true,false");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("FreeMakerFactory 配置出错");
            throw new Exception("FreeMakerFactory 配置出错");
        }
    }

    public Configuration getConfiguration() {
        return this.cfg;
    }

    /**
     * 此方法用于生成源码
     *
     * @param modelFileName 模板文件名
     * @param out_file      要生成的文件
     * @param root          模板所需参数
     */
    public DtoResponse freeMaker(String modelFileName, File out_file, Map<String, Object> root) {
        try {
            //设置输出源码文件
            if (out_file.isDirectory()) {
                logger.error("Freemaker请指定正确数据输出文件路径=>file_path：(" + out_file.getPath() + ")");
                return new DtoResponse(new RuntimeException("请指定正确数据输出文件路径=>file_path：(" + out_file.getPath() + ")"));
            }
            if (!out_file.exists()) {
                if (!out_file.getParentFile().exists()) {
                    if (!out_file.getParentFile().mkdirs()) {
                        return new DtoResponse(new RuntimeException("创建目标文件目录失败=>file_path：(" + out_file.getPath() + ")"));
                    }
                }
                out_file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Freemaker指定了错误数据输出文件=>file_path：(" + out_file.getPath() + ")");
            return new DtoResponse(new RuntimeException("请指定正确数据输出文件"));
        }
        //生成文件
        try {
            //加载模板
            Template temp1 = cfg.getTemplate(modelFileName);
            OutputStream fos = new FileOutputStream(out_file);
            Writer out = new OutputStreamWriter(fos);
            //生成源码
            temp1.process(root, out);
            fos.flush();
            fos.close();
            out.close();
            return freeMaker(modelFileName, root);
        } catch (Exception e) {
            return new DtoResponse(new RuntimeException("请指定正确数据输出文件"));
        }
    }

    /**
     * 此方法用于生成源码
     *
     * @param modelFileName 模板文件名
     * @param fileName      要生成的文件名
     * @param root          模板所需参数
     */
    public DtoResponse freeMaker(String modelFileName, String fileName, Map<String, Object> root) {
        try {
            //设置源码根目录
            String source_path = DtoFileUtils.getGeneratorConfigFileName(FreeMakerConfig.GENERATOR_CONFIG_FILE_NAME);
            source_path = PropertiesUtils.getPropertyValueByKey(source_path, "outRoot");//java文件的生成目录
            if (null == source_path || "".equals(source_path)) {
                //自定义目录不存在则使用默认目录
                source_path = FreeMakerConfig.ROOT_PATH + File.separator + FreeMakerConfig.DEFAULT_SOURCES_DIRECTORY;
            } else {
                source_path = FreeMakerConfig.ROOT_PATH + File.separator + source_path;
                File source_dir = new File(source_path);
                if (!source_dir.exists()) {
                    source_dir.mkdirs();
                }
            }
            //设置输出源码文件
            File out_file = new File(source_path + File.separator + fileName);
            if (out_file.isDirectory()) {
                logger.error("请指定数据输出文件，而非目录：" + out_file.getPath());
                return new DtoResponse(new RuntimeException("请指定数据输出文件，而非目录：" + out_file.getPath()));
            }
            if (!out_file.exists()) {
                out_file.createNewFile();
            }

            //加载模板
            Template temp1 = cfg.getTemplate(modelFileName);
            OutputStream fos = new FileOutputStream(out_file);
            Writer out = new OutputStreamWriter(fos);
            //生成源码
            temp1.process(root, out);
            fos.flush();
            fos.close();
            out.close();
            return freeMaker(modelFileName, root);
        } catch (Exception e) {
            return new DtoResponse(e);
        }
    }

    /**
     * 此方法用于生成源码
     *
     * @param modelFileName 模板文件名
     * @param root          模板所需参数
     */
    public DtoResponse freeMaker(String modelFileName, Map<String, Object> root) {

        try {
            //加载模板
            Template temp1 = cfg.getTemplate(modelFileName);

            StringWriter stringWriter = new StringWriter();
            BufferedWriter writer = new BufferedWriter(stringWriter);
            temp1.process(root, writer);
            writer.flush();
            writer.close();
            return new DtoResponse(stringWriter.toString());
        } catch (Exception e) {
            return new DtoResponse(e);
        }
    }

    public String freeMakerControlMethod(String modelFileName, Map<String, Object> root) {
        return null;
    }
}
