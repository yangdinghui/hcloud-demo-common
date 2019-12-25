package hcloud.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

//        InputStream is = new FileInputStream("C:\\Users\\think\\Pictures\\7092831_140036752037_2.jpg");
//        int available = is.available();
//        byte[] data = new byte[available];
//        is.read(data);
//        is.close();
//        BASE64Encoder encoder = new BASE64Encoder();
//        String base64String = encoder.encode(data).trim().replaceAll("\n", "").replaceAll("\r", "");
//        System.out.println(base64String);

        String s = "13812332321,13812332321,13812332321,13812332321";
        String s1 = "13812332321";
        //集合去重
//        List<String> newMobileList = mobileList.stream().distinct().collect(Collectors.toList());
        String[] split = s.split(",");
        String[] split1 = s1.split(",");
        System.out.println(split);
        System.out.println(split1);
    }


    public static void getminiqrQr(String sceneStr, String accessToken) {
        try
        {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", sceneStr);
            paramJson.put("page", "pages/index/index");
            paramJson.put("width", 430);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            OutputStream os = new FileOutputStream(new File("C:/Users/Administrator/Desktop/1.png"));
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1)
            {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
