package com.defascon.Graphs.controllers;


import com.defascon.Graphs.dto.GraphService;
import com.defascon.Graphs.dto.PointsGraph;
import com.defascon.Graphs.dto.UndirectedGraph;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/api")
public class MainController {
    private Map<String,UndirectedGraph> graphMap = new HashMap<>();
    private GraphService graphService = new GraphService();

    @GetMapping("/getGraph/{nameGraph}")
    public String getGraph(@PathVariable String nameGraph,HttpServletResponse response){
        if(!graphMap.containsKey(nameGraph)){
            response.setStatus(404);
            return "Graph no found";
        }
        else {
            response.setStatus(200);
            return graphMap.get(nameGraph).getVertexMap().toString();
        }
    }

    @PostMapping("/graphBuilding")
    public void graphBuilding(@RequestBody PointsGraph pointsGraph, HttpServletResponse response){
        graphMap.put(pointsGraph.getName(),
                graphService.graphBuilding(pointsGraph.getPoints()));
    }

    @GetMapping("/deleteGraph/{nameGraph}")
    public void deleteGraph(@PathVariable String nameGraph, HttpServletResponse response){
        graphMap.remove(nameGraph);
    }

    @PutMapping("/updateGraph")
    public void updateGraph(@RequestBody PointsGraph pointsGraph, HttpServletResponse response){
        graphMap.put(pointsGraph.getName(),
                graphService.graphBuilding(pointsGraph.getPoints()));
    }

    @GetMapping("/test")
    public String test(){
        return "Testing good";
    }
}
