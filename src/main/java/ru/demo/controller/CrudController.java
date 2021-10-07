package ru.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.demo.domain.dto.ClusterCreateDto;
import ru.demo.domain.dto.ClusterResponseDto;
import ru.demo.domain.dto.ClusterUpdateDto;
import ru.demo.domain.dto.NodeDTO;
import ru.demo.services.CrudService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CrudController {

    private final CrudService crudService;

    public CrudController(CrudService crudService) {
        this.crudService = crudService;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void create(@RequestBody @Valid ClusterCreateDto cluster) {
        crudService.create(cluster);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody ClusterUpdateDto cluster, @RequestParam Long id) {
        crudService.update(cluster, id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@RequestParam Long id) {
        crudService.delete(id);
    }

    @RequestMapping(value = "/addNodes", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNodes(@RequestParam Long id, @RequestBody List<NodeDTO> nodeDTO) {
        crudService.addNodes(id, nodeDTO);
    }

    @RequestMapping(value = "/clusters", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ClusterResponseDto> getAllClusters() {
        return crudService.getClusters();
    }

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<NodeDTO> getNodesById(@RequestParam Long id) {
        return crudService.getNodes(id);
    }
}
