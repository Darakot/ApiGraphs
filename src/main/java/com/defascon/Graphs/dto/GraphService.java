package com.defascon.Graphs.dto;

import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class GraphService {
    private Map<String, List<String>> vertexMap;
    private Map<String, Mark> visitedMap = new LinkedHashMap<>();
    private int counter = 1;

    public GraphService(HashMap<String, List<String>> vertexMap) {
        this.vertexMap = vertexMap;
    }

    /**
     * Обход в глубину DFS
     * @param vertexName - Имя вершина с которой начнется обход в глубину
     */
    public void dfs(String vertexName) {
        if (visitedMap.containsKey(vertexName)) return;
        // задать предустановку (время входа)
        visitedMap.put(vertexName, new Mark(counter,-1));
        counter++;
        // извлеение соседних вершин
        List<String> adjacentVertices = vertexMap.get(vertexName);
        for (String v : adjacentVertices) {
            if (visitedMap.containsKey(v)) {
                continue;
            }
            dfs(v);
        }
        // задать предустановку (время выхода)
        Mark m = visitedMap.get(vertexName);
        m.post = counter++;
    }

    /**
     * Обходо в ширину BFS
     * @param vertexName - Имя вершины с которого начнется обход в ширину
     */
    public void bfs(String vertexName) {
        // Отметьте все вершины как не посещенные (По умолчанию
        // установить как ложь)
        Map<String,Boolean>  visited = new HashMap();
        // Создать очередь для BFS
        LinkedList<String> queue = new LinkedList<>(vertexMap.keySet());
        // Отметить текущий узел как посещенный и записать его
        visited.put(vertexName,true);

        while (queue.size() != 0)
        {
            vertexName = queue.poll();
            System.out.print(vertexName+" ");

            // Получить все соседние вершины развернутой вершины
            // Если соседняя вершина не была посещена, отметьте её
            // Если посетили извлекаем её
            Map<String, List<String>> vm = vertexMap;
            List<String> adjacentVertices = vm.get(vertexName);
            for (String v : adjacentVertices) {
                if (visitedMap.containsKey(v)) continue;
                bfs(v);
            }
        }
    }

    /**
     * @return - возвращает String[] сформированный из мапы после обхода dfs
     */
    public String[] dispOut(){
        String[] sArr = new String[visitedMap.size()];
        int i = 0;
        for (Map.Entry<String, Mark> entry : visitedMap.entrySet()) {
            Mark m = entry.getValue();
            sArr[i] = entry.getKey() + m.pre + m.post;
            i++;
        }

        for (Map.Entry<String, Mark> entry : visitedMap.entrySet()) {
            Mark m = entry.getValue();
            System.out.format("%1$s : (%2$d, %3$d)\n", entry.getKey(), m.pre, m.post);
        }
        return sArr;
    }

    /**
     * Построение графа на основе массива который пришел в виде JSON
     * @param arrPoints - массив который пришел с фронта
     * @return - возвращает построенный неопределенный граф
     */
    public UndirectedGraph graphBuilding(String[] arrPoints){
        UndirectedGraph undirectedGraph = new UndirectedGraph();
        for (String sPoints: arrPoints) {
            String[] arrVertex = sPoints.split(",");
            undirectedGraph.addEdge(arrVertex[0],arrVertex[1]);
        }
        return undirectedGraph;
    }
}
