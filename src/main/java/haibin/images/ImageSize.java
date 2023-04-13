package haibin.images;

import lombok.Data;
import lombok.experimental.Accessors;


public enum ImageSize {
    IMAGE_SIZE_1(1,"256x256"),
    IMAGE_SIZE_2(2,"512x512"),
    IMAGE_SIZE_3(3,"1024x1024");
    private Integer code;
    private String value;
    ImageSize(Integer code,String value){
        this.code=code;
        this.value=value;
    }

    public String getValue() {
        return this.value;
    }
}
