package haibin;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import haibin.entity.Completion;
import haibin.entity.CompletionResponse;
import haibin.util.OkHttpUtils;
import okhttp3.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import java.io.IOException;

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
    public void streamCompletions(Completion completion, EventSourceListener eventSourceListener){
        if (completion.getStream()==null){
            completion.setStream(true);
        }
        Request request = new Request.Builder().url("https://api.openai.com/v1/chat/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+apiKey)
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JSON.toJSONString(completion)))
                .build();
        EventSource.Factory factory = EventSources.createFactory(okHttpClient);
        factory.newEventSource(request,eventSourceListener);
    }

}
