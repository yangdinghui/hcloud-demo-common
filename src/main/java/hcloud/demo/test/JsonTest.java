package hcloud.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-12-17
 */
public class JsonTest {
    public static void main(String[] args) throws Exception {
//        JSONObject jsonObject = JSON.parseObject(response);
//        String access_token = jsonObject.getString("access_token");
//        Map<String, String> map = new HashMap<>(1);
//        map.put("access_token", null);

        InputStream is = new FileInputStream("C:\\Users\\think\\Pictures\\7092831_140036752037_2.jpg");
        int available = is.available();
        byte[] data = new byte[available];
        is.read(data);
        is.close();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64String = encoder.encode(data).trim().replaceAll("\n", "").replaceAll("\r", "");
        System.out.println(base64String);
    }

}
