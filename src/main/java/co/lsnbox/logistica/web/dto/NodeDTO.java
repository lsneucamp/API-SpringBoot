package co.lsnbox.logistica.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NodeDTO {

    private String id;

    private String name;

    public NodeDTO() {
    }

    public NodeDTO(String _id, String name) {
        this.id = _id;
        this.name = name;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
