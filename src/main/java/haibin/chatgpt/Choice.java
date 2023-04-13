package haibin.chatgpt;
import lombok.Data;
@Data
public class Choice {
    private long index;
    private String finishReason;
    private Message message;
    private Delta delta;
    public Choice() {
    }
}