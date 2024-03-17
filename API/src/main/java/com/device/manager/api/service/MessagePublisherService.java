package com.device.manager.api.service;

import com.amazonaws.services.iot.client.AWSIotException;
import com.device.manager.api.service.dto.MessagePayload;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessagePublisherService {
    void publishMessage(MessagePayload messagePayload) throws AWSIotException, JsonProcessingException;
}
