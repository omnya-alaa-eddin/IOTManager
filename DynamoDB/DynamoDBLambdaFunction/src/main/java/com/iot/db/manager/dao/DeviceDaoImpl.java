
package com.iot.db.manager.dao;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.iot.db.manager.config.DynamoDBManager;
import com.iot.db.manager.domain.Device;
import org.apache.log4j.Logger;

import java.util.List;


public class DeviceDaoImpl implements DeviceDao {

    private static final Logger log = Logger.getLogger(DeviceDaoImpl.class);

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DeviceDaoImpl instance;


    private DeviceDaoImpl() { }

    public static DeviceDaoImpl instance() {

        if (instance == null) {
            synchronized(DeviceDaoImpl.class) {
                if (instance == null)
                    instance = new DeviceDaoImpl();
            }
        }
        return instance;
    }

    @Override
    public List<Device> findAllDevices() {
        return mapper.scan(Device.class, new DynamoDBScanExpression());
    }

    @Override
    public void saveOrUpdateDevice(Device device) {

        mapper.save(device);
        log.info("Device saved successfully");
    }

}

