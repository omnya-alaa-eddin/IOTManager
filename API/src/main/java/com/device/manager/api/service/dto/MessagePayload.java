package com.device.manager.api.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessagePayload {
    private Object payload;
    private String topic;
}
