package com.iot.db.manager.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iot.db.manager.dao.DeviceDaoImpl;
import com.iot.db.manager.domain.Device;
import com.iot.db.manager.dto.DeviceInfoDTO;

public class Handler implements RequestHandler<Object, String> {
    private static final DeviceDaoImpl dao = DeviceDaoImpl.instance();
    @Override
    public String handleRequest(Object event, Context context) {
        DeviceInfoDTO deviceInfo = (DeviceInfoDTO) event;
        System.out.println(event);
        Device device = new Device();
        device.setDeviceID(deviceInfo.getDeviceId());
        device.setDeviceName(deviceInfo.getDeviceName());
        device.setDeviceStatus(deviceInfo.getStatus());
        dao.saveOrUpdateDevice(device);
        return "Device Saved";
    }
}