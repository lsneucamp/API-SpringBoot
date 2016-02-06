package co.lsnbox.logistica.web.controller.v1;

import co.lsnbox.logistica.Application;
import co.lsnbox.logistica.domain.Edge;
import co.lsnbox.logistica.domain.Node;
import co.lsnbox.logistica.exceptions.APIBadRequestException;
import co.lsnbox.logistica.exceptions.APIConstraintViolationException;
import co.lsnbox.logistica.exceptions.APINotFoundException;
import co.lsnbox.logistica.service.interfaces.IEdgeService;
import co.lsnbox.logistica.service.interfaces.INodeService;
import co.lsnbox.logistica.web.dto.EdgeDTO;
import co.lsnbox.logistica.web.dto.NodeDTO;
import co.lsnbox.logistica.web.dto.PathDTO;
import co.lsnbox.logistica.web.factory.EdgesFactory;
import co.lsnbox.logistica.web.factory.NodeFactory;
import co.lsnbox.logistica.web.factory.PathsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@RestController
@RequestMapping(value = "/api/v1/edges")
public class EdgeController {

    private final Logger log = LoggerFactory.getLogger(Application.class);

    @Inject
    IEdgeService edgeService;

    @Inject
    INodeService nodeService;

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {})
    public ResponseEntity<List<EdgeDTO>> findAll() throws IOException, APINotFoundException, APIConstraintViolationException {
        List<Edge> edges = edgeService.findAll();
        return new ResponseEntity<>(
                EdgesFactory.toDTOs(edges), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EdgeDTO> findOne(@PathVariable String id) throws IOException, APINotFoundException, APIConstraintViolationException {
        Edge edge = edgeService.findOne(id);

        if (edge == null) {
            throw new APINotFoundException("Edge não encontrado!");
        }

        return new ResponseEntity<>(
                EdgesFactory.toDTO(edge), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"paths", "sourceNodeId", "destNodeId", "autonomy", "fuelCost"})
    public ResponseEntity findPaths(
            @RequestParam(value = "paths") String type,
            @RequestParam(value = "sourceNodeId") String sourceNodeId,
            @RequestParam(value = "destNodeId") String destNodeId,
            @RequestParam(value = "autonomy") Double autonomy,
            @RequestParam(value = "fuelCost") Double fuelCost)
            throws IOException, APINotFoundException, APIConstraintViolationException {
        return walker(type, sourceNodeId, destNodeId, autonomy, fuelCost);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"paths", "sourceNodeId", "destNodeId"})
    public ResponseEntity findPathsWithCost(
            @RequestParam(value = "paths") String type,
            @RequestParam(value = "sourceNodeId") String sourceNodeId,
            @RequestParam(value = "destNodeId") String destNodeId)
            throws IOException, APINotFoundException, APIConstraintViolationException {
        return walker(type, sourceNodeId, destNodeId, null, null);
    }

    private ResponseEntity walker(
            String type,
            String sourceNodeId,
            String destNodeId,
            Double autonomy,
            Double fuelCost) {
        if (type == null) {
            throw new APIBadRequestException("Parametro type não especificado ou invalido");
        }

        if (sourceNodeId == null) {
            throw new APIBadRequestException("Ponto de origem não especificado");
        }
        if (destNodeId == null) {
            throw new APIBadRequestException("Ponto de destino não especificado");
        }

        Node sourceNode = nodeService.findOne(sourceNodeId);
        if (sourceNode == null) {
            throw new APINotFoundException("Ponto de origem não encontrado");
        }

        Node destNode = nodeService.findOne(destNodeId);
        if (destNode == null) {
            throw new APINotFoundException("Ponto de destino não encontrado");
        }
        List<PathDTO> paths;
        List<PathDTO> pathsDTO = null;
        switch (type) {
            case "all":
                pathsDTO = PathsFactory.toDTOs(edgeService.walker(sourceNode, destNode));
                calculateCosts(pathsDTO, autonomy, fuelCost);
                break;
            case "best":
                paths = PathsFactory.toDTOs(edgeService.walker(sourceNode, destNode));
                if (paths != null && !paths.isEmpty()) {
                    PathDTO best = paths.stream().min((p1, p2) -> Double.compare(p1.getDistance(), p2.getDistance())).get();
                    pathsDTO = new ArrayList<>();
                    pathsDTO.add(best);
                    calculateCosts(pathsDTO, autonomy, fuelCost);
                }
                break;
            case "worst":
                paths = PathsFactory.toDTOs(edgeService.walker(sourceNode, destNode));
                if (paths != null && !paths.isEmpty()) {
                    PathDTO worst = paths.stream().max((p1, p2) -> Double.compare(p1.getDistance(), p2.getDistance())).get();
                    pathsDTO = new ArrayList<>();
                    pathsDTO.add(worst);
                    calculateCosts(pathsDTO, autonomy, fuelCost);
                }
                break;
        }

        return new ResponseEntity(pathsDTO, HttpStatus.OK);
    }

    private void calculateCosts(List<PathDTO> dtos, Double autonomy, Double fuelCost) {
        if (autonomy != null && autonomy > 0 && fuelCost != null && fuelCost > 0) {
            dtos.stream().forEach((dto) -> {
                dto.setCost((dto.getDistance() / autonomy) * fuelCost);
            });
        }

    }

}
