package haibin.util;

import okhttp3.*;

import javax.net.ssl.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {
    private static volatile OkHttpClient okHttpClient = null;
    /**
     * 初始化okHttpClient，并且允许https访问
     */
    private OkHttpUtils() {

    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpClient == null) {
                    TrustManager[] trustManagers = buildTrustManagers();
                    okHttpClient = new OkHttpClient.Builder()
                            //设置连接超时时间
                            .connectTimeout(50, TimeUnit.SECONDS)
                            //写入超时时间
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 4780)))
                            //从连接成功到响应的总时间
                            .readTimeout(20, TimeUnit.SECONDS)
                            //跳过ssl认证(https)
                            .sslSocketFactory(createSSLSocketFactory(trustManagers), (X509TrustManager) trustManagers[0])
                            .hostnameVerifier(new TrustAllHostnameVerifier())
                            .retryOnConnectionFailure(true)
                            //设置连接池  最大连接数量  , 持续存活的连接
                            .connectionPool(new ConnectionPool(50, 10, TimeUnit.MINUTES))
                            .build();
                }
            }
        }
        return okHttpClient;
    }
    public static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

}



