import com.zuche.mq.Receiver;
import org.junit.Test;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-21 10:17
 */
public class TestRec {

    @Test
    public void rec(){
        try {
            //传入 监听的routingKey -
            Receiver receiver = new Receiver("YOU.SELF.KEY");
            receiver.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
