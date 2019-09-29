package hcloud.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-09-29
 */
public class RequestReader extends HttpServletRequestWrapper {
    private static final Logger log = LoggerFactory.getLogger(RequestReader.class);
    private final byte[] body;

    public RequestReader(HttpServletRequest request) throws IOException {
        super(request);
        String sessionStream = this.getBodyString(request);
        this.body = sessionStream.getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body);
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public InputStream cloneInputStream(ServletInputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        boolean var4 = false;

        try {
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            byteArrayOutputStream.flush();
        } catch (IOException var6) {
            log.info("clone servletInputStream failed", var6);
            throw var6;
        }

        InputStream byInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byInputStream;
    }

    public String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = this.cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException var18) {
            log.error("get request body code error:", var18);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            }

        }

        return sb.toString();
    }
}
