package com.device.manager.api.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DeviceInfoDTO {
    private String deviceName;
    private String deviceId;
    private String status;
}
