package com.device.manager.api.service.impl;

import com.amazonaws.services.iot.client.AWSIotException;
import com.device.manager.api.constant.TOPIC;
import com.device.manager.api.service.MessagePublisherService;
import com.device.manager.api.service.ServicesTopicUpdating;
import com.device.manager.api.service.dto.DeviceInfoDTO;
import com.device.manager.api.service.dto.MessagePayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesTopicUpdatingImpl implements ServicesTopicUpdating {
    @Autowired
    MessagePublisherService messagePublisherService;

    @Override
    public void updateDynamoDBTopic(DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException {
        MessagePayload messagePayload = MessagePayload.builder().
                topic(TOPIC.GENERAL_TOPIC_NAME).payload(deviceInfoDTO).build();
        messagePublisherService.publishMessage(messagePayload);
    }
}
