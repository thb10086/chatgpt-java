package haibin.entity;
import lombok.Data;
@Data
public class Choice {
    private long index;
    private String finishReason;
    private Message message;
    public Choice() {
    }
}