package com.example.will.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GptParmeter {
    @JsonProperty(value = "model")
    String model;

    @JsonProperty(value = "contents")
    String contents;

    @JsonProperty(value = "role")
    String role;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
