package com.device.manager.api.service.impl;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.model.*;
import com.device.manager.api.config.AppConfig;
import com.device.manager.api.config.IOTClientConfig;
import com.device.manager.api.constant.DeviceStatus;
import com.device.manager.api.service.DeviceRegistrationService;
import com.device.manager.api.service.ServicesTopicUpdating;
import com.device.manager.api.service.dto.DeviceDTO;
import com.device.manager.api.service.dto.DeviceInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceRegistrationServiceImpl implements DeviceRegistrationService {
    @Autowired
    AppConfig appConfig;
    @Autowired
    IOTClientConfig iotClientConfig;
    @Autowired
    ServicesTopicUpdating servicesTopicUpdating;
    @Override
    public CreateThingResult registerDevice(DeviceInfoDTO deviceInfoDTO) throws Exception {
        //Check If device name Exists before
        if(!checkDevice(deviceInfoDTO.getDeviceName())) {

            CreateThingResult response = iotClientConfig.getIotClient(appConfig)
                    .createThing(new CreateThingRequest()
                            .withThingName(deviceInfoDTO.getDeviceName())).withThingId(UUID.randomUUID().toString());

            System.out.print("response:" + "Device has been created Successfully");
            deviceInfoDTO.setStatus(DeviceStatus.REGISTERED.name());
            deviceInfoDTO.setDeviceId(response.getThingId());
            servicesTopicUpdating.updateDynamoDBTopic(deviceInfoDTO);
            return  response;
        }

        throw new Exception("Thing Already Exists on IOT Console");
    }

    @Override
    public void deleteDevice(DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException {
        AWSIotMqttClient awsIotClient = new AWSIotMqttClient(appConfig.getClientEndpoint(),deviceInfoDTO.getDeviceName(),appConfig.getAccessKeyId(),
                appConfig.getSecretKeyId());
        awsIotClient.connect();

        DeviceDTO device = new DeviceDTO(deviceInfoDTO.getDeviceName());
        awsIotClient.attach(device);
        device.setClient(awsIotClient);
        device.delete();

        deviceInfoDTO.setStatus(DeviceStatus.DELETED.name());
        servicesTopicUpdating.updateDynamoDBTopic(deviceInfoDTO);
    }

    private boolean checkDevice(String thingName) throws Exception {
        if(thingName == null) {
            throw new Exception("Device name could not be null");        }

        try {
            isDeviceExisted(thingName);
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }

    }

    private DescribeThingResult isDeviceExisted(String thingName){
        DescribeThingRequest describeThingRequest = new DescribeThingRequest();
        describeThingRequest.setThingName( thingName);
        return iotClientConfig.getIotClient(appConfig).describeThing(describeThingRequest);
    }
}
