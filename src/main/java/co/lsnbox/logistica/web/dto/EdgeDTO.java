package co.lsnbox.logistica.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EdgeDTO {

    private String id;

    private NodeDTO nodeA;

    private NodeDTO nodeB;
    
    private Double distance;

    public EdgeDTO(String _id, NodeDTO nodeA, NodeDTO nodeB, Double distance) {
        this.id = _id;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.distance = distance;
    }
    
    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NodeDTO getNodeA() {
        return nodeA;
    }

    public void setNodeA(NodeDTO nodeA) {
        this.nodeA = nodeA;
    }

    public NodeDTO getNodeB() {
        return nodeB;
    }

    public void setNodeB(NodeDTO nodeB) {
        this.nodeB = nodeB;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    

}
