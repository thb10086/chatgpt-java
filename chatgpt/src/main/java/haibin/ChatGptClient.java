package haibin;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import haibin.entity.Completion;
import haibin.entity.CompletionResponse;
import haibin.entity.Message;
import haibin.util.OkHttpUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatGptClient {
    private OkHttpClient okHttpClient;
    private  String apiKey;
    public ChatGptClient(String apiKey){
        this.apiKey=apiKey;
        okHttpClient = OkHttpUtils.getOkHttpClient();
    }

    public CompletionResponse createCompletions(Completion completion) throws IOException {
        Request request = new Request.Builder().url("https://api.openai.com/v1/chat/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+apiKey)
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JSON.toJSONString(completion)))
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String s = response.body().string();
        CompletionResponse completionResponse = JSON.parseObject(s, CompletionResponse.class);
        return completionResponse;
    }

}
