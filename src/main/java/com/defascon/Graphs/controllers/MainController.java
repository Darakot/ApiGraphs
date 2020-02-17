package com.defascon.Graphs.controllers;


import com.defascon.Graphs.dto.GraphService;
import com.defascon.Graphs.dto.UndirectedGraph;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController("/api")
public class MainController {
    private Map<String,UndirectedGraph> graphMap = new HashMap<>();

    private GraphService graphService;

    @GetMapping("/getGraph/{nameGraph}")
    public String[] getGraph(@PathVariable String nameGraph){
        graphService = new GraphService(graphMap.get(nameGraph).getVertexMap());
        return graphService.dispOut();
    }

    @PostMapping("/createGraph")
    public void createGraph(@RequestBody UndirectedGraph graph, HttpServletResponse response){
        graphMap.put(graph.getName(),graph);
    }

    @GetMapping("/deleteGraph/{nameGraph}")
    public void deleteGraph(@PathVariable String nameGraph, HttpServletResponse response){
        graphMap.remove(nameGraph);
    }

    @PutMapping("/updateGraph")
    public void updateGraph(@RequestBody UndirectedGraph graph, HttpServletResponse response){
        graphMap.put(graph.getName(),graph);
    }

    @GetMapping("/test")
    public String test(){
        return "Testing good";
    }
}
