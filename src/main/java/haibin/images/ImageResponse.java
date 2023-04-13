package haibin.images;

import lombok.Data;

import java.util.List;
@Data
public class ImageResponse {
    private String created;
    private List<Url> data;
}
@Data
class Url{
    private String url;
}
