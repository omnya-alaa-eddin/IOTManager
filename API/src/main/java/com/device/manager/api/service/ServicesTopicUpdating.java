package com.device.manager.api.service;

import com.amazonaws.services.iot.client.AWSIotException;
import com.device.manager.api.service.dto.DeviceInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * This Service to send any devices process updates to a general topic which is responsible to update DynamoDB table
 * */
public interface ServicesTopicUpdating {
    void updateDynamoDBTopic(DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException;
}
