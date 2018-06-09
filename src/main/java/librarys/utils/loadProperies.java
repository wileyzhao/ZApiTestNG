package librarys.utils;

/**
 * Created by wanli on 16/11/1.
 */

import librarys.log.logLib;

import java.io.*;
import java.util.Properties;

public class loadProperies {

    /**
     *根据file和要读取的key,来获取value的值
     * @param filePath 文件的绝对路径加文件名
     * @param key 要获取的key值
     * @return key对应的value
     */
    public static String getValue(String filePath, String key) {
        try {
            Properties props = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将文件读取并加载。
     * @param fname 要读取加载的文件名或目录,目录则加载目录下所有文件
     */
    public static void readProperites(String fname) {
        File f =new File(fname);
        if (f.isFile() && f.getName().substring(f.getName().lastIndexOf(".") + 1).equals("properties")) {
            FileInputStream propFile = null;
            try {
                propFile = new FileInputStream(f);
                Properties p = new Properties(System.getProperties());
                p.load(propFile);
                System.setProperties(p);
            } catch (IOException ex) {
                logLib.logError("file " + fname + " load failed!");
                logLib.logError(ex.getMessage());
            } finally {
                try {
                    propFile.close();
                } catch (IOException ex) {
                    logLib.logError(fname + " load failed!");
                    logLib.logError(ex.getMessage());
                }
            }
        } else if (f.isDirectory()) {
            for (File subf : f.listFiles()) {
                readProperites(subf.getAbsolutePath());
            }
        }
        else{
        }
    }

    /**
     * 根据key获取value,用在readProperites加载了指定文件之后。
     * @param key 要获取的key值
     * @return key对应的value名
     */
    public static String get(String key) {
        String result = "";
        try {
            String prop = System.getProperty(key);
            if (prop == null || prop.equals("")) {
                return null;
            }
            byte[] prop_return = prop.getBytes("ISO-8859-1");
            result = new String(prop_return, "utf-8");
        }
        catch (UnsupportedEncodingException ex) {
        }
        return result;
    }
}

