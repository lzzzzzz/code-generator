package org.codegenerator.utils.DtoCreaterUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by LZ on 2017/6/19.
 * DTO生成器xml或properties文件解析器工具类
 */
public class PropertiesUtils {


    /**
     * cml或properties中读取属性(欠缺properties)
     * */
    public static String getPropertyValueByKey(String propertyFile, String key) throws Exception{
        if(null!=propertyFile&&!"".equals(propertyFile)){
            int index = propertyFile.indexOf(".");
            String f_type=propertyFile.substring(index+1,propertyFile.length());
            if("xml".equals(f_type)){//xml解析
                System.out.println("开始解析xml");
                return getPropertyValueXml(propertyFile,key);
            }
            if("properties".equals(f_type)){//properties解析
                throw new Exception("非法解析格式("+f_type+")=>目前仅支持xml、properties格式配置解析");
            }
            throw new Exception("非法解析格式("+f_type+")=>目前仅支持xml、properties格式配置解析");
        }else{
            throw new Exception("文件不存在");
        }
    }

    /**DOM解析xml获取需要的值*/
    private static String getPropertyValueXml(String propertyFile, String key) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            //Ⅱ获得DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // ❸Ⅲ--获得文档对象--
            Document doc = builder.parse(propertyFile);
            // ❹Ⅳ获得根元素
            Element element = doc.getDocumentElement();
            NodeList nodes = element.getElementsByTagName("entry");//得到所有entry节点
            if(nodes.getLength()==0){
                return null;
            }else{
                for(int i=0;i<nodes.getLength();i++){
                    String value=nodes.item(i).getFirstChild().getNodeValue();
                    Element e1=(Element)nodes.item(i);
                    String key_str=e1.getAttribute("key");
                    if(key_str.equals(key)){//如果与key相同则返回value值
                        return value;
                    }
                }
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("解析数据出错");
        }
    }
}
