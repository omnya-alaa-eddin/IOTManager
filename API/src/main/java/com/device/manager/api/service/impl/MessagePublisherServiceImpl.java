package com.device.manager.api.service.impl;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.device.manager.api.config.AppConfig;
import com.device.manager.api.service.MessagePublisherService;
import com.device.manager.api.service.dto.MessagePayload;
import com.device.manager.api.util.IOTMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.iot.client.AWSIotQos;
@Service
public class MessagePublisherServiceImpl implements MessagePublisherService
{
    @Autowired
    AppConfig appConfig;
    @Override
    public void publishMessage(MessagePayload messagePayload) throws AWSIotException, JsonProcessingException {
        AWSIotQos qos = AWSIotQos.QOS0;
        AWSIotMqttClient awsIotClient = new AWSIotMqttClient(appConfig.getClientEndpoint(),"testTopic",appConfig.getAccessKeyId(),
                appConfig.getSecretKeyId());
        awsIotClient.connect();
        IOTMessage message = new IOTMessage(messagePayload.getTopic(), qos, new ObjectMapper().writeValueAsString(messagePayload.getPayload()));

        awsIotClient.publish(message);
    }
}
