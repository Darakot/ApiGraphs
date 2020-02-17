package com.defascon.Graphs.dto;

import java.util.*;

public class GraphService {
    private Map<String, List<String>> vertexMap;
    private Map<String, Mark> visitedMap = new LinkedHashMap<>();
    private int counter = 1;

    private Set<String> zeroPar = new HashSet<>();
    private Set<String> onePar = new HashSet<>();
    private Set<String> twoPar = new HashSet<>();

    public GraphService(HashMap<String, List<String>> vertexMap) {
        this.vertexMap = vertexMap;
    }

    public void dfs(String vertexName) {
        if (visitedMap.containsKey(vertexName)) return;
        // задать предустановку (время входа)
        visitedMap.put(vertexName, new Mark(counter,-1));
        counter++;
        // извлеение соседних вершин
//        Map<String, List<String>> vm = vertexMap;

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
//        System.out.println("one" + onePar + "\n" +
//                "two" + twoPar);
    }
}
