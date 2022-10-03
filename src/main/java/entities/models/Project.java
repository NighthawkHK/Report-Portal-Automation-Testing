package entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonPropertyOrder(value = {"id", "name", "lastRun", "creationDate"}, alphabetic = true)
public class Project {

    @JsonProperty("projectName")
    private String name;
    private Integer id;
    private Long creationDate;
    private Long lastRun;
    private String entryType;
    private String organization;
    private String launchesPerWeek;
    private Integer launchesQuantity;
    private Integer uniqueTickets;
    private Integer usersQuantity;
    @JsonIgnore
    private List<Map<String, Object>> launchesPerUser;
}
