package edu.reportportal.entities.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class Launch {

    @JsonProperty("launchNumber")
    private Integer order;

    private Map<String, Object> results;
}
