package co.lsnbox.logistica.domain;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@org.springframework.data.mongodb.core.mapping.Document(collection = Edge.COLLECTION)
public class Edge {

    public final static String COLLECTION = "edges";

    @Id
    private String id;

    @DBRef
    private Node nodeA;

    @DBRef
    private Node nodeB;

    @NotNull
    private Double distance;

    public Edge() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public void setNodeB(Node nodeB) {
        this.nodeB = nodeB;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    

}
