package com.example.will.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vijayendra Mudigal
 */
@Data
public class AllNameValueTO {

  private String originalName;
  private String originalValue;
  private Map<String, String> remainingNameValuePair = new HashMap<>();

}
