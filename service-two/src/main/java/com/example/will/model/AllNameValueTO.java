package com.example.will.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vijayendra Mudigal
 */
@Data
public class AllNameValueTO implements Serializable {

  private String originalName;
  private String originalValue;
  private Map<String, String> remainingNameValuePair = new HashMap<>();

}
