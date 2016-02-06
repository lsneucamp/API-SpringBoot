package co.lsnbox.logistica.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PathDTO {

    private List<EdgeDTO> path;

    private Double distance;

    private Double cost;

    public PathDTO(List<EdgeDTO> path, Double distance) {
        this.path = path;
        this.distance = distance;
    }

    public List<EdgeDTO> getPath() {
        return path;
    }

    public void setPath(List<EdgeDTO> path) {
        this.path = path;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

   

}
