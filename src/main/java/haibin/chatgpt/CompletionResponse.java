package haibin.chatgpt;
import lombok.Data;
import java.util.List;

@Data
public class CompletionResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private List<Choice> choices;
    public CompletionResponse() {
    }
}