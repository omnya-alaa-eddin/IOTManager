package com.device.manager.api.service;

import com.amazonaws.services.iot.model.ListThingsResult;

public interface DevicesService {
    ListThingsResult getRegisteredDevices();
}
