package com.device.manager.api.util;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;

public class IOTMessage extends AWSIotMessage {
    public IOTMessage(String topic, AWSIotQos qos, String payload) {
        super(topic, qos, payload);
    }

    @Override
    public void onSuccess() {
        System.out.println("Message published successfully");
    }

    @Override
    public void onFailure() {
        System.out.println("Message published failed");
    }

    @Override
    public void onTimeout() {
        System.out.println("time out for sending the message");
    }
}
