package haibin.entity;

import haibin.ChatGptClient;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        ChatGptClient client = new ChatGptClient("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");
        Completion completion = Completion.builder().model(Model.CHAT_GPT_1).message("我想去长沙玩几天，帮我制定一个计划").temperature(0.9).build();
        CompletionResponse completions = client.createCompletions(completion);
        completions.getChoices().forEach(System.out::println);
    }
}
