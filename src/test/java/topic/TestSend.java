package topic;

import com.alibaba.fastjson.JSON;
import com.zuche.mq.CardNodeObject;
import com.zuche.mq.fanout.Sender;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-21 10:09
 */
public class TestSend {

    @Test
    public void send(){
        final CardNodeObject object = new CardNodeObject();
        byte[] bodys = null;
        //数据准备
        object.setMessage("success");
        object.setType("1");
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("params1", "123456789");
        m.put("params1", "987654321");
        m.put("params1", "备注说明");
        object.setData(m);
        try {
            //将数据对象object 转换成JSON字符串格式
            String jsonStr = JSON.toJSONString(object);
            System.out.println("生产者发送的消息 == " + jsonStr);

            bodys = jsonStr.getBytes("UTF-8");

            //实例化一个 发送消息 的生产者; 传入 需要绑定的routingKey
            Sender sender = new Sender("*.KEY","topic","queue_topic","topicExchange");
            sender.sendMessage(bodys);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
