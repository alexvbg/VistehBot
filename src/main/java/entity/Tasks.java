package entity;

/**
 * Created by nikita93 on 03.07.2017.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "draw",
        "recordsTotal",
        "recordsFiltered",
        "data"
})
public class Tasks {

    private ArrayList<TaskDescription> tasks = new ArrayList<TaskDescription>();

    public ArrayList<TaskDescription> getTasks() {
        return tasks;
    }

    public void ArrayList(ArrayList<TaskDescription> tasks) {
        this.tasks = tasks;
    }

    @JsonProperty("draw")
    private Integer draw;
    @JsonProperty("recordsTotal")
    private Integer recordsTotal;
    @JsonProperty("recordsFiltered")
    private Integer recordsFiltered;
    @JsonProperty("data")
    private List<List<String>> data = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("draw")
    public Integer getDraw() {
        return draw;
    }

    @JsonProperty("draw")
    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    @JsonProperty("recordsTotal")
    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    @JsonProperty("recordsTotal")
    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    @JsonProperty("recordsFiltered")
    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    @JsonProperty("recordsFiltered")
    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    @JsonProperty("data")
    public List<List<String>> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<List<String>> data) {
        this.data = data;
        for (List <String> task:
                this.data) {
            TaskDescription description = new TaskDescription(
                    task.get(0),
                    task.get(1),
                    task.get(2),
                    task.get(3),
                    task.get(4),
                    task.get(5),
                    task.get(6),
                    task.get(7),
                    task.get(8),
                    task.get(9),
                    task.get(10),
                    task.get(11),
                    task.get(12),
                    task.get(13),
                    task.get(14),
                    task.get(15),
                    task.get(16),
                    task.get(17),
                    task.get(18),
                    task.get(19));
            this.tasks.add(description);
        }
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void echo() {

    }

}
