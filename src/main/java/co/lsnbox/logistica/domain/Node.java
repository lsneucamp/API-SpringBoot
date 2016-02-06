package co.lsnbox.logistica.domain;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document(collection = Node.COLLECTION)
public class Node  {
    public final static String COLLECTION = "nodes";
    
    @Id
    private String id;
    
    @NotNull
    private String name;
   
    public Node() {
    }

    public Node(String id) {
        this.id = id;
    }
   
    

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
        final Node other = (Node) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    
}
