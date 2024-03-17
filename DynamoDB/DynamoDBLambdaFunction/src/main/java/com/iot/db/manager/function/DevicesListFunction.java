package com.iot.db.manager.function;

import com.iot.db.manager.dao.DeviceDaoImpl;
import com.iot.db.manager.domain.Device;
import org.apache.log4j.Logger;

import java.util.List;

public class DevicesListFunction {
    private static final DeviceDaoImpl dao = DeviceDaoImpl.instance();
    private static final Logger log = Logger.getLogger(DeviceDaoImpl.class);

    public List<Device> findAllDevices() {


        List<Device> devices = dao.findAllDevices();
        log.info("Found " + devices.size() + " total devices.");
        for (Device device : devices){
            log.info("device id :" + device.getDeviceID());

        }
        return devices;
    }
}
