# chatgpt-java
java对接chagpt接口
![image](https://user-images.githubusercontent.com/68690699/224280713-3b3657ef-312b-49e6-ac84-7479af6d54cd.png)
### 使用ChatGptClient对象，传入你的api-key
### 创建Completion对象选择你要使用的model，还有你要发送的消息，这两个是必填的参数，其他参数可以参考官网。
### CompletionResponse是返回的数据
### 要实现连续对话，把返回的消息再添加到Message对象里面，role设置为 assistant
