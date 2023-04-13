package haibin.chatgpt;

public enum Model {
    CHAT_GPT_1(1,"gpt-3.5-turbo"),
    CHAT_GPT_2(2,"gpt-3.5-turbo-0301"),
    CHAT_GPT_3(3,"text-davinci-003");
    private Integer code;
    private String value;
    Model(Integer code,String value){
        this.code=code;
        this.value=value;
    }

    public String getValue() {
        return this.value;
    }
}
