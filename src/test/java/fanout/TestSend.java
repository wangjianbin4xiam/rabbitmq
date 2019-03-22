package fanout;

import com.zuche.mq.fanout.Sender;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title:
 * @Description:生产者
 * @author: wangjb
 * @create: 2019-03-22 12:01
 */
public class TestSend {

    @Test
    public void send() throws IOException, TimeoutException {
        Sender send = new Sender("routing_fanout","fanout","queue_fanout","cms");
        for(int i=1;i<4;i++){
            String sendMsg = "我是fanout模式发送的第【"+i+"】条信息";
            send.sendMessage(sendMsg.getBytes());
        }
    }
}
