package com.cnd.greencube.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.common.collect.Maps;

public class HuanXinHttpUtil {

	// 传输超时时间，默认30秒
    private final static int connectTimeout = 30000;
    private final static String default_charset = "UTF-8";

    /**
     * 以post方式提交xml数据流
     * 
     * @param url
     * @param paramXml
     * @return
     */
    public static String doPost(String url, String paramXml) {
        return doPost(url, paramXml, default_charset, connectTimeout, connectTimeout, null);
    }

    /**
     * 以post方式提交xml数据流
     * 
     * @param url
     * @param paramXml
     * @return
     */
    public static String doDelete(String url, String paramXml) {
        return doDelete(url, paramXml, default_charset, connectTimeout, connectTimeout, null);
    }

    /**
     * 环信
     * 
     * @param url
     * @param paramXml
     * @param token
     * @return
     */
    public static String easeMobPost(String url, String paramXml, String token) {
        Map<String, String> header = Maps.newHashMap();
        if (!StringUtils.isEmpty(token)) {
            header.put("Authorization", "Bearer " + token);
        }
        return doPost(url, paramXml, default_charset, connectTimeout, connectTimeout, header);
    }

    /**
     * 环信
     * 
     * @param url
     * @param paramXml
     * @param token
     * @return
     */
    public static String easeMobDelete(String url, String paramXml, String token) {
        Map<String, String> header = Maps.newHashMap();
        if (!StringUtils.isEmpty(token)) {
            header.put("Authorization", "Bearer " + token);
        }
        return doDelete(url, paramXml, default_charset, connectTimeout, connectTimeout, header);
    }

    /**
     * 以post方式提交xml数据流
     * 
     * @param url
     * @param paramXml
     * @param charset
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    public static String doPost(String url, String paramXml, String charset, int connectTimeout, int readTimeout,
            Map<String, String> requestProperties) {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        String result = "";
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
            SSLContext.setDefault(ctx);
            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

            conn = getConnection(new URL(url), "POST", "text/xml", requestProperties);
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }

            });
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            if (!StringUtils.isEmpty(paramXml)) {
                out = conn.getOutputStream();
                out.write(paramXml.getBytes(default_charset));
            }
            
            result = getResponseAsString(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 以post方式提交xml数据流
     * 
     * @param url
     * @param paramXml
     * @param charset
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    public static String doDelete(String url, String paramXml, String charset, int connectTimeout, int readTimeout,
            Map<String, String> requestProperties) {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        String result = "";
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
            SSLContext.setDefault(ctx);
            conn = getConnection(new URL(url), "DELETE", "text/xml", requestProperties);
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }

            });
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            if (!StringUtils.isEmpty(paramXml)) {
                out = conn.getOutputStream();
                out.write(paramXml.getBytes(default_charset));
            }
            result = getResponseAsString(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



    /**
     * 以post方式提交xml数据流
     * 
     * @param url
     * @param paramXml
     * @return
     */
    public static InputStream doPostIs(String url, String paramXml) {
        return doPostIs(url, paramXml, default_charset, connectTimeout, connectTimeout, null);
    }

    /**
     * 以post方式提交xml数据流
     * 
     * @param url
     * @param paramXml
     * @param charset
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    public static InputStream doPostIs(String url, String paramXml, String charset, int connectTimeout,
            int readTimeout, Map<String, String> requestProperties) {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        InputStream is = null;
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
            SSLContext.setDefault(ctx);
            conn = getConnection(new URL(url), "POST", "text/xml", requestProperties);
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }

            });
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);

            out = conn.getOutputStream();
            out.write(paramXml.getBytes());
            is = conn.getInputStream();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return is;
    }

    private static HttpsURLConnection getConnection(URL url, String method, String ctype,
            Map<String, String> requestProperties) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        conn.setRequestProperty("User-Agent",
                "mozilla/5.0 (windows nt 6.1; wow64) applewebkit/537.36 (khtml, like gecko) chrome/43.0.2357.81 safari/537.36");
        conn.setRequestProperty("Content-Type", ctype);
        conn.setRequestProperty("Accept-Charset", default_charset);
        if (null != requestProperties) {
            for (String key : requestProperties.keySet()) {
                conn.setRequestProperty(key, requestProperties.get(key));
            }
        }
        return conn;
    }

    /**
     * @param conn
     * @return
     * @throws IOException
     */
    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    /**
     * @param stream
     * @param charset
     * @return
     * @throws IOException
     */
    public static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     * @param ctype
     * @return
     */
    private static String getResponseCharset(String ctype) {
        String charset = default_charset;

        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }
}
