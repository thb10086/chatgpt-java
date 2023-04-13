package haibin.chatgpt;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Delta {
    private String role;
    private String content;
}
