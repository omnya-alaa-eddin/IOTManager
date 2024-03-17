package com.device.manager.api.service;

import com.amazonaws.services.iot.client.AWSIotException;
import com.device.manager.api.service.dto.MessagePayload;
import com.device.manager.api.service.dto.TopicDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageSubscriberService {
    void subscribe(TopicDTO topicDTO) throws AWSIotException, JsonProcessingException;
}
