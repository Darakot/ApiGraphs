package com.defascon.Graphs.dto;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Класс неопределенного графа
 * метод addVertex  добавляет вершину графа
 * метод addEdge добавляет ребра графов
 * метод hasVertex проверяет есть ли такая вершина графа
 * метод hasEdge проверяет есть ли такое ребро графов
 * метод getVertexMap возвращает текущий граф
 * метод sortGraph возвращает текущий граф отсортированный лексикографически,
 * если граф пустой выдаст исключение NullPointerException
 */

@NoArgsConstructor
public class UndirectedGraph {
    private HashMap<String, List<String>> vertexMap = new HashMap<>();

    /**
     * @param vertexName - имя вершины
     */
    public void addVertex(String vertexName) {
        if (!hasVertex(vertexName)) {
            vertexMap.put(vertexName, new ArrayList<>());

        }
    }

    /**
     * @param vertexName - вершина графа
     * @return возвращает true, если вершина уже добавлена
     */
    public boolean hasVertex(String vertexName) {
        return vertexMap.containsKey(vertexName);
    }

    /**
     * @param vertexName1 - родительская вершина
     * @param vertexName2 - дочерняя вершина
     * @return - возвращает true если vertexName1 вершина имеет связь с vertexName2.
     */
    public boolean hasEdge(String vertexName1, String vertexName2) {
        if (!hasVertex(vertexName1)) return false;
        List<String> edges = vertexMap.get(vertexName1);
        return Collections.binarySearch(edges, vertexName2) != -1;
    }

    /**
     * @param vertexName1 - родительская вершина
     * @param vertexName2 - дочерняя вершина
     *                    Добавляет новую связь в граф
     */

    public void addEdge(String vertexName1, String vertexName2) {
        if (!hasVertex(vertexName1)) addVertex(vertexName1);
        if (!hasVertex(vertexName2)) addVertex(vertexName2);
        List<String> edges1 = vertexMap.get(vertexName1);
        List<String> edges2 = vertexMap.get(vertexName2);
        edges1.add(vertexName2);
        edges2.add(vertexName1);
        Collections.sort(edges1);
        Collections.sort(edges2);
    }

    /**
     * @return построенный граф
     */
    public HashMap<String, List<String>> getVertexMap() {
        return vertexMap;
    }

    /**
     * @return - отсортированный лексикографически граф
     * @throws NullPointerException - возвращает если граф пусттой
     */
    public List<String> sortGraph() throws NullPointerException {
        if (vertexMap.isEmpty()) throw new NullPointerException();
        List<String> vertexList = new ArrayList<>(vertexMap.size());
        vertexList.addAll(vertexMap.keySet());
        Collections.sort(vertexList);
        return vertexList;
    }
}
