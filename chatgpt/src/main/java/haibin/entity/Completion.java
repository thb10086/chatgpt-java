package haibin.entity;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Completion {
    @NonNull
    private String model;
    @NonNull
    private List<Message> messages;
    private Double temperature;
    @JSONField(name = "top_p")
    private String topP;
    private Boolean stream;
    private Integer n;
    private List<String> stop;
    @JSONField(name = "max_tokens")
    private Integer maxTokens;
    @JSONField(name = "frequency_penalty")
    private Double presencePenalty;
    @JSONField(name = "frequency_penalty")
    private Double frequencyPenalty;
    @JSONField(name = "logit_bias")
    private Map logitBias;
    private String user;

    public Completion(){}
    public Completion(@NonNull String model, @NonNull List<Message> messages, Double temperature, String topP, Boolean stream, Integer n, List<String> stop, Integer maxTokens, Double presencePenalty, Double frequencyPenalty, Map logitBias,String user) {
        this.model = model;
        this.messages = messages;
        this.temperature = temperature;
        this.topP = topP;
        this.stream = stream;
        this.n = n;
        this.stop = stop;
        this.maxTokens = maxTokens;
        this.presencePenalty = presencePenalty;
        this.frequencyPenalty = frequencyPenalty;
        this.logitBias = logitBias;
        this.user = user;
    }
    public static CompletionBuilder builder(){
        return new CompletionBuilder();
    }
    public static class  CompletionBuilder{
        private String model;
        private List<Message> messages;
        private Double temperature;
        private String topP;
        private Boolean stream;
        private Integer n;
        private List<String> stop;
        private Integer maxTokens;
        private Double presencePenalty;
        private Double frequencyPenalty;
        private Map logitBias;
        private String user;

        public  Completion.CompletionBuilder stop(List<String> stop) {
            if (CollectionUtil.isEmpty(this.stop)){
                this.stop = new ArrayList<>(10);
            }
            this.stop = stop;
            return this;
        }

        public CompletionBuilder stream(Boolean stream) {
            this.stream=stream;
            return this;
        }
        public CompletionBuilder temperature(Double temperature){
            this.temperature = temperature;
            return this;
        }
        public CompletionBuilder topP(String topP){
            this.topP = topP;
            return this;
        }
        public CompletionBuilder model(String model){
            this.model = model;
            return this;
        }
        public CompletionBuilder model(Model model){
            this.model = model.getValue();
            return this;
        }
        public CompletionBuilder user(String user){
            this.user = user;
            return this;
        }
        public CompletionBuilder message(Message message){
            if (CollectionUtil.isEmpty(this.messages)){
                this.messages = new ArrayList<>(10);
            }
            this.messages.add(message);
            return this;
        }
        public CompletionBuilder message(String message){
            if (CollectionUtil.isEmpty(this.messages)){
                this.messages = new ArrayList<>(10);
            }
            Message message1 = new Message();
            message1.setRole("user");
            message1.setContent(message);
            this.messages.add(message1);
            return this;
        }
        public CompletionBuilder message(List<Message> message){
            this.messages.addAll(message);
            return this;
        }
        public CompletionBuilder n(Integer n){
            this.n = n;
            return this;
        }
        public CompletionBuilder maxTokens(Integer maxTokens){
            this.maxTokens = maxTokens;
            return this;
        }
        public CompletionBuilder frequencyPenalty(Double frequencyPenalty) {
            this.frequencyPenalty = frequencyPenalty;
            return this;
        }

        public CompletionBuilder fresencePenalty(Double presencePenalty) {
            this.presencePenalty =presencePenalty;
            return this;
        }
        public CompletionBuilder logitBias(Map logitBias){
            this.logitBias = logitBias;
            return this;
        }
        public Completion build(){
            Assert.notNull(this.model,"model是必填项");
            Assert.notNull(this.messages,"messages是必填项");
            return new Completion(this.model,this.messages,this.temperature,this.topP,this.stream,this.n,this.stop,this.maxTokens,this.presencePenalty,this.frequencyPenalty,this.logitBias,this.user);
        }

    }
}
