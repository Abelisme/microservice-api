package com.example.will.service;


import com.example.will.model.AllNameValueTO;
import com.example.will.model.NameValueTO;

/**
 * @author Vijayendra Mudigal
 */
public interface NameValueService {

  NameValueTO updateNameValue(NameValueTO nameValueTO);

  AllNameValueTO getAllNameValues(String name);

  NameValueTO updateNameValue(NameValueTO nameValueTO, boolean fromRabbit);

  NameValueTO generateUUID();

}
