package com.device.manager.api.controller;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.model.CreateThingResult;
import com.amazonaws.services.iot.model.ListThingsResult;
import com.device.manager.api.service.*;
import com.device.manager.api.service.dto.DeviceInfoDTO;
import com.device.manager.api.service.dto.MessagePayload;
import com.device.manager.api.service.dto.TopicDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceManagerApi {
	@Autowired
	DeviceRegistrationService deviceRegistrationService;
	@Autowired
	MessagePublisherService messagePublisherService;
	@Autowired
	DevicesService devicesService;
	@Autowired
	MessageSubscriberService messageSubscriberService;
	@Autowired
	ActivationService activationService;

	@PostMapping("/register")
	public ResponseEntity<CreateThingResult> registerDevice(@RequestBody DeviceInfoDTO deviceInfoDTO) throws Exception {
		 return ResponseEntity.status(HttpStatus.OK).body(deviceRegistrationService.registerDevice(deviceInfoDTO));
	}
	@PostMapping("/publishToTopic")
	public ResponseEntity publishToTopic(@RequestBody MessagePayload message) throws AWSIotException, JsonProcessingException {
		messagePublisherService.publishMessage(message);
		return ResponseEntity.status(HttpStatus.OK).body("Message Published Successfully");
	}

	@GetMapping("/device/list")
	public ResponseEntity<ListThingsResult> getThings() {
		return ResponseEntity.status(HttpStatus.OK).body(devicesService.getRegisteredDevices());
	}

	@PostMapping("/subscribe")
	public void subscribe(@RequestBody TopicDTO topic) throws AWSIotException, JsonProcessingException {
		messageSubscriberService.subscribe(topic);
	}
	@PostMapping("/deactivate")
	public void deactivate(@RequestBody DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException {
		activationService.deactivate(deviceInfoDTO);
	}

	@PostMapping("/activate")
	public void activate(@RequestBody DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException {
		activationService.activate(deviceInfoDTO);
	}

	@DeleteMapping("/delete")
	public ResponseEntity deleteDevice(@RequestBody DeviceInfoDTO deviceInfoDTO) throws AWSIotException, JsonProcessingException {
		deviceRegistrationService.deleteDevice(deviceInfoDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Device deleted Successfully");
	}
}
