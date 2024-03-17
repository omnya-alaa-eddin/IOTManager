package com.device.manager.api.service.impl;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.device.manager.api.config.AppConfig;
import com.device.manager.api.constant.DeviceStatus;
import com.device.manager.api.service.ActivationService;
import com.device.manager.api.service.ServicesTopicUpdating;
import com.device.manager.api.service.dto.DeviceDTO;
import com.device.manager.api.service.dto.DeviceInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationServiceImpl implements ActivationService {
    @Autowired
    AppConfig appConfig;
    @Autowired
    ServicesTopicUpdating servicesTopicUpdating;
    @Override
    public void deactivate(DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException {
        AWSIotMqttClient awsIotClient = new AWSIotMqttClient(appConfig.getClientEndpoint(),deviceInfoDTO.getDeviceName(),appConfig.getAccessKeyId(),
                appConfig.getSecretKeyId());
        awsIotClient.connect();

        DeviceDTO device = new DeviceDTO(deviceInfoDTO.getDeviceName());
        awsIotClient.attach(device);
        device.setClient(awsIotClient);
        device.deactivate();
        deviceInfoDTO.setStatus(DeviceStatus.DEACTIVATE.name());
        servicesTopicUpdating.updateDynamoDBTopic(deviceInfoDTO);
    }

    @Override
    public void activate(DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException {
        AWSIotMqttClient awsIotClient = new AWSIotMqttClient(appConfig.getClientEndpoint(),deviceInfoDTO.getDeviceName(),appConfig.getAccessKeyId(),
                appConfig.getSecretKeyId());
        awsIotClient.connect();

        DeviceDTO device = new DeviceDTO(deviceInfoDTO.getDeviceName());
        awsIotClient.attach(device);
        device.setClient(awsIotClient);
        device.activate();
        deviceInfoDTO.setStatus(DeviceStatus.ACTIVATE.name());
        servicesTopicUpdating.updateDynamoDBTopic(deviceInfoDTO);
    }
}
