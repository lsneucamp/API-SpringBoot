package co.lsnbox.logistica.web.controller.v1;

import co.lsnbox.logistica.Application;
import co.lsnbox.logistica.domain.Node;
import co.lsnbox.logistica.exceptions.APIConstraintViolationException;
import co.lsnbox.logistica.exceptions.APINotFoundException;
import co.lsnbox.logistica.service.interfaces.INodeService;
import co.lsnbox.logistica.web.dto.NodeDTO;
import co.lsnbox.logistica.web.factory.NodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/nodes")
public class NodeController{
    private final Logger log = LoggerFactory.getLogger(Application.class);


    @Autowired
    INodeService nodeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<NodeDTO>> findAll() throws IOException, APINotFoundException, APIConstraintViolationException {
        List<Node> nodes = nodeService.findAll();        
        return new ResponseEntity<>(
                NodeFactory.toDTOs(nodes)
                ,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NodeDTO> findOne(@PathVariable String id) throws IOException, APINotFoundException, APIConstraintViolationException {
        Node node = nodeService.findOne(id); 
        
        if(node==null){
            throw new APINotFoundException("Node não encontrado!");
        }       
        
        return new ResponseEntity<>(
                NodeFactory.toDTO(node)
                ,HttpStatus.OK);
    }

   

}
