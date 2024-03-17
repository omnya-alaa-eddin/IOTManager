package com.device.manager.api.service.impl;

import com.amazonaws.services.iot.model.ListThingsRequest;
import com.amazonaws.services.iot.model.ListThingsResult;
import com.device.manager.api.config.AppConfig;
import com.device.manager.api.config.IOTClientConfig;
import com.device.manager.api.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicesServiceImpl implements DevicesService {
    @Autowired
    IOTClientConfig iotClientConfig;
    @Autowired
    AppConfig appConfig;
    @Override
    public ListThingsResult getRegisteredDevices() {
        ListThingsResult responses = iotClientConfig.getIotClient(appConfig)
                .listThings(new ListThingsRequest());
        return responses;
    }
}
