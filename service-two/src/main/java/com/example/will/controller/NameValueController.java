package com.example.will.controller;


import com.example.will.ServiceTwoApplication;
import com.example.will.model.AllNameValueTO;
import com.example.will.service.NameValueService;
//import io.swagger.v3.oas.annotations.Operation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


/**
 * @author Vijayendra Mudigal
 */

@RestController
@RequestMapping("/second")
public class NameValueController {

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  private NameValueService nameValueService;

  private Logger logger = Logger.getLogger(ServiceTwoApplication.class);

  @GetMapping("/message")
  public String test() {
    return "Hello this is my second microservice";
  }

  @GetMapping(value = "/all-name-value")
//  @Operation(summary = "Get name and value", description = "Get service name and its corresponding values for all the services")
  public AllNameValueTO getAllNameValue() {
    logger.info("Inside " + applicationName + " controller's getAllNameValue() method");
    return nameValueService.getAllNameValues(applicationName);
  }

}
