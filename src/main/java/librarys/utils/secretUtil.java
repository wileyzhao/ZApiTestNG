package librarys.utils;

/**
 * Created by wanli on 16/11/1.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;

public class secretUtil {

    public static String getRequestSecretString(String type, String parameters)
    {
        if ((type == null) || (parameters == null)) {
            return null;
        }
        return getRequestSecretString(type, parameters, false);
    }

    public static String getRequestSecretString(String type, String parameters, boolean isPay)
    {
        String strSecret = null;
        if ((parameters == null) || (parameters.length() == 0)) {
            return null;
        }
        String privateKey = null;

        if ("Android".equals(type))
        {
            privateKey = "fffffff";
            if (isPay) {
                privateKey = "aaaaaaa";
            }
        }
        else if ("iOS".equals(type))
        {
            privateKey = "ddddddd";
            if (isPay) {
                privateKey = "bbbbbbb";
            }
        }
        if (privateKey == null) {
            return null;
        }
        parameters = parameters + "&private_key=" + privateKey;

        parameters = parameters.replaceAll("\n", "");
        parameters = parameters.replaceAll("\r", "");
        parameters = parameters.replaceAll("\t", "");

        String[] arr = parameters.split("&");

        Arrays.sort(arr, new Comparator<String>()
        {
            public int compare(String lhs, String rhs) {
                // TODO Auto-generated method stub
                return lhs.compareTo(rhs);
            }

        });
        String sortParameters = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sortParameters = arr[i];
            } else {
                sortParameters = sortParameters + "&" + arr[i];
            }
        }
        strSecret = encryptStringToMd5(sortParameters, "32");
        return strSecret;
    }

    public static String encryptStringToMd5(String src, String flag)
    {
        if ((src == null) || (src.length() == 0)) {
            return null;
        }
        MessageDigest md = null;
        byte[] b = null;
        int i = 0;
        StringBuffer buf = null;
        try
        {
            md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            b = md.digest();
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            if (flag != null)
            {
                if (!flag.equals("16")) {
                    return buf.toString();
                }
                return buf.toString().substring(8, 24);
            }
            return buf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}

