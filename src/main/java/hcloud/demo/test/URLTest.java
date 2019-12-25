package hcloud.demo.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-12-25
 */
@Slf4j
public class URLTest {
    public String getweChatQrCode(String vo, String access_token) {
        InputStream inputStream = null;
        try {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + "");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("page", "");
            paramJson.put("scene", "");
            paramJson.put("width", "");
            paramJson.put("auto_color", true);
            paramJson.put("line_color", "");
//          paramJson.put("is_hyaline",weChatMiNiAppQrCodeVo.getLineColor());

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
//            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            inputStream = httpURLConnection.getInputStream();

            byte[] bytes = IOUtils.toByteArray(inputStream);
            String encoded = Base64.getEncoder().encodeToString(bytes);
            System.out.println(encoded);
            return encoded;
        } catch (Exception e) {
            log.error("调用微信接口生成小程序码错误");
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
