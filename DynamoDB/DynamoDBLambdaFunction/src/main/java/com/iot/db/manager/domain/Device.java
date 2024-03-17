
package com.iot.db.manager.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.*;


@DynamoDBTable(tableName = "device")
public class Device {
    @DynamoDBAttribute
    @DynamoDBRangeKey
    @DynamoDBHashKey
    private String deviceID;
    @DynamoDBAttribute
    private String deviceName;

    @DynamoDBAttribute
    private String deviceStatus;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
