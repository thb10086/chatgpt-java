package haibin.images;

import haibin.chatgpt.ChatGptClient;
import haibin.chatgpt.Completion;
import haibin.chatgpt.CompletionResponse;
import haibin.chatgpt.Model;

import java.io.IOException;

public class Test {


    public static void main(String[] args) throws IOException {
        ImageClient client = new ImageClient("sk-*****");
        ImageCompletion completion = new ImageCompletion().setN(1).setPrompt("一个五彩缤纷的世界").setSize(ImageSize.IMAGE_SIZE_3.getValue());
        ImageResponse completions = client.createCompletions(completion);
        completions.getData().forEach(System.out::println);
    }
}
