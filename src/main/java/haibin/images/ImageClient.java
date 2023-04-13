package haibin.images;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import haibin.images.ImageCompletion;
import haibin.images.ImageResponse;
import haibin.util.OkHttpUtils;
import okhttp3.*;

import java.io.IOException;

public class ImageClient {
    private OkHttpClient okHttpClient;
    private  String apiKey;
    public ImageClient(String apiKey){
        this.apiKey=apiKey;
        okHttpClient = OkHttpUtils.getOkHttpClient();
    }
    public ImageResponse createCompletions(ImageCompletion completion) throws IOException {
        Request request = new Request.Builder().url("https://api.openai.com/v1/images/generations")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+apiKey)
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JSON.toJSONString(completion)))
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String s = response.body().string();
        ImageResponse completionResponse = JSON.parseObject(s, ImageResponse.class);
        return completionResponse;
    }
}
