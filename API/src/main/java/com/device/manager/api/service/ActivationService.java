package com.device.manager.api.service;

import com.amazonaws.services.iot.client.AWSIotException;
import com.device.manager.api.service.dto.DeviceInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ActivationService {
    void deactivate(DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException;
    void activate(DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException;
}
