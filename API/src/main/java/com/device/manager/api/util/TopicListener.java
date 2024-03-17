package com.device.manager.api.util;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotTopic;

public class TopicListener extends AWSIotTopic {
    public TopicListener(String topic) {
        super(topic);
    }

    @Override
    public void onMessage(AWSIotMessage message) {
        System.out.println(System.currentTimeMillis() + ": <<< " + message.getStringPayload());

    }
    @Override
    public void onSuccess() {
        System.out.println("request success for " + topic);
    }
    @Override
    public void onFailure() {
        System.out.println("request success for " + topic);
    }
}
