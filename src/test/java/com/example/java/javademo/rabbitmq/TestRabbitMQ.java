package com.example.java.javademo.rabbitmq;

import com.example.java.javademo.JavaDemoApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@SpringBootTest(classes = JavaDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

//    @Autowired
//private MessageSender messageSender;
//
//    @Autowired
//    private MessageSender messageSender2;
//
//    @Test
//    public void testRabbit() throws InterruptedException {
//
//        while (true){
//            Thread.sleep(1000);
//            for (int i = 0; i < 10; i++) {
//                messageSender.send(i);
//                messageSender2.send(i);
//            }
//        }
//    }
//
//    @Test
//    public void testTopic() throws InterruptedException {
//
//        while (true){
//            Thread.sleep(1000);
//            for (int i = 0; i < 10; i++) {
//                messageSender.send("hello, rabbit");
//            }
//        }
//    }
}
