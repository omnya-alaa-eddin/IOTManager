package com.device.manager.api.service;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.model.CreateThingResult;
import com.device.manager.api.service.dto.DeviceInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DeviceRegistrationService {
    CreateThingResult registerDevice(DeviceInfoDTO deviceInfoDTO) throws Exception;
    void deleteDevice(DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException;
}
