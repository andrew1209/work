package com.chaojia.okhttpwork;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;


@SpringBootApplication
public class OkhttpWorkApplication {

    private static OkHttpClient okHttpClient =new OkHttpClient();

    public static void main(String[] args) throws IOException {
        String result = doGet("http://localhost:8081");
        System.out.println(result);
        okHttpClient = null;
        //SpringApplication.run(OkhttpWorkApplication.class, args);
    }

    //get请求
    public static String doGet(String url) throws IOException {
        String result = null;
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody body = response.body();
            if(body != null){
                byte[] bytes = body.bytes();
                result = new String(bytes);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return result;
    }

}
