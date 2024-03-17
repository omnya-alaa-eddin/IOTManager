package com.device.manager.api.service.dto;

import com.amazonaws.services.iot.client.AWSIotDevice;

public class DeviceDTO extends AWSIotDevice {
    public DeviceDTO(String thingName) {
        super(thingName);
    }
}
