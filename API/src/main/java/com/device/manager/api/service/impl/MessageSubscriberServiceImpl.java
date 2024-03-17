package com.device.manager.api.service.impl;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.device.manager.api.config.AppConfig;
import com.device.manager.api.service.MessageSubscriberService;
import com.device.manager.api.service.dto.MessagePayload;
import com.device.manager.api.service.dto.TopicDTO;
import com.device.manager.api.util.TopicListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSubscriberServiceImpl implements MessageSubscriberService {
    @Autowired
    AppConfig appConfig;
    @Override
    public void subscribe(TopicDTO topicDTO) throws AWSIotException, JsonProcessingException {
        TopicListener topicListener = new TopicListener(topicDTO.getName());
        AWSIotMqttClient awsIotClient = new AWSIotMqttClient(appConfig.getClientEndpoint(),topicDTO.getName(),appConfig.getAccessKeyId(),
                appConfig.getSecretKeyId());
        awsIotClient.connect();
        awsIotClient.subscribe(topicListener);
    }
}
