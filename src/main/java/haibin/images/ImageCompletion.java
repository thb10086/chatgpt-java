package haibin.images;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ImageCompletion {
    private String prompt;
    private Integer n;
    private String size;
}
