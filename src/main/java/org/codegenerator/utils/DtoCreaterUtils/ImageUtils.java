package org.codegenerator.utils.DtoCreaterUtils;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LZ on 2017/7/3.
 */
public class ImageUtils {

    public static String getImageStr(String file_path) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file_path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
