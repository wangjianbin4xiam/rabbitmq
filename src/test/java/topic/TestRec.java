package topic;

import com.zuche.mq.fanout.Receiver;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-21 10:17
 */
public class TestRec {

    @Test
    public void rec() throws IOException, TimeoutException, InterruptedException {
        //传入 监听的routingKey -
        Receiver receiver = new Receiver("YOU.SELF.KEY","topic","queue_topic","topicExchange");
        receiver.getMesssage();
    }
}
