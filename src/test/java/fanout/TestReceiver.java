package fanout;

import com.zuche.mq.fanout.Receiver;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-22 12:09
 */
public class TestReceiver {

    @Test
    public void receiver() throws IOException, TimeoutException, InterruptedException {
        Receiver re = new Receiver("routing_fanout","fanout","queue_fanout","cms");
        re.getMesssage();
    }
    @Test
    public void receiver2() throws IOException, TimeoutException, InterruptedException {
        Receiver re = new Receiver("routing_fanout","fanout","queue_fanout","cms");
        re.getMesssage();
    }
    @Test
    public void receiver3() throws IOException, TimeoutException, InterruptedException {
        Receiver re = new Receiver("routing_fanout","fanout","queue_fanout","cms");
        re.getMesssage();
    }
}
