package org.codegenerator.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by michael on 2017/6/18.
 */
public class SecurityUtils {
    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String md5(String str) throws Exception{
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误");
        }
    }

    /**
     * 获取指定位数的的string随机数，随机范围为A-Z 2-9
     * @param length string的长度
     * @return 指定lenght的随机字符串
     */
    public  static  String randomString(int length){
        // 去掉I,O, 0, 1
        String str = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(str.length());
            buf.append(str.charAt(num));
        }
        return buf.toString();

    }
}
