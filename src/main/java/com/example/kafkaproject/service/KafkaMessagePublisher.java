package com.example.kafkaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String , Object> template;


    public void sendMessageToTopic(String message){

        CompletableFuture<SendResult<String, Object>> future = template.send("NewTopicTest", message);

        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("Sent Message=" + message + "Offset = " +result.getRecordMetadata().offset() );
            }else{
                System.out.println("Unable to send message sue to this error-->" +ex.getMessage());
            }


                }


                );

    }
}
