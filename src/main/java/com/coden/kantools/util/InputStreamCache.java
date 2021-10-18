package com.coden.kantools.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流操作过程中处理流的多次消费问题
 *
 * @author LXW
 * @date 2020/6/15 20:33
 */
public class InputStreamCache {
    private static final Logger logger = LoggerFactory.getLogger(InputStreamCache.class);
    /**
     * 将InputStream中的字节保存到ByteArrayOutputStream中。
     */
    private ByteArrayOutputStream byteArrayOutputStream;
    private InputStream inputStream;

    public InputStreamCache(InputStream inputStream) {
        if (inputStream == null) return;
        this.inputStream = inputStream;
        initCache();
    }

    /**
     * 初始化
     */
    private void initCache() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取缓存流
     *
     * @return InputStream
     */
    public InputStream getInputStream() {
        if (byteArrayOutputStream == null) return this.inputStream;
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    /**
     * 销毁
     */
    public void destroyCache() {
        this.byteArrayOutputStream = null;
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
