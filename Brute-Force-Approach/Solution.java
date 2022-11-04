import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args){
        Graph graph = new Graph();

        // Make vertices
        graph.createVertex(1);
        graph.createVertex(2);
        graph.createVertex(3);
        graph.createVertex(4);

        // Make edges
        graph.createEdge(1, 2, 10);
        graph.createEdge(2, 3, 20);
        graph.createEdge(3, 1, 30);
        graph.createEdge(1, 4, 10);

        // THE ACTUAL PROBLEM
        List<List<Vertex>> edgePairList = graph.createAdjacencyList();
        int len = graph.getEdgeSize() - 1;
        List<List<Vertex>> allSpanningTrees;

        //allSpanningTrees = comb(edgePairList, len, 0, new ArrayList<>(len), len); 

        List<Vertex> aSpanningTrees;

        aSpanningTrees = comb(edgePairList, len, 0, new ArrayList<>(len), len); 

        List<Vertex> spanningTree;

        // for (int ii = 0; ii < allSpanningTrees.size(); ii++)
        // {
        //     for (int jj = 0; jj < len; ii++)
        //     {
        //         System.out.println(allSpanningTrees.get(ii).get(jj).getLabel());
        //     }
        // }

        System.out.println(aSpanningTrees.size());

        for (int ii = 0; ii < aSpanningTrees.size(); ii++)
        {
            System.out.print(aSpanningTrees.get(ii).getLabel() + " ");
        }

        System.out.println();

        // Set<Set<Vertex>> edgePairSet = graph.getEdgePairSet(); // A set of edge pairs.  
        // Set<Set<Vertex>> spanningTree;
        // int len = graph.getEdgeSize() - 1;

        // comb(edgePairSet, len, 0, new HashSet<>(len));  
        // edgePairSet.get(0) = temp;
    }

    //public static List<List<Vertex>> comb(List<List<Vertex>> allEdgePairs, int len, int startIdx, List<List<Vertex>> inSpanningTree, int treeLength){
        //List<List<Vertex>> allSpanningTrees = new ArrayList<>();
    public static List<Vertex> comb(List<List<Vertex>> allEdgePairs, int len, int startIdx, List<Vertex> allSpanningTrees, int treeLength){

        if (len == 0){
            return allSpanningTrees;
        }       
        for (int ii = 0; ii <= allEdgePairs.size() - len; ii++){
            int index = treeLength - len;
            allSpanningTrees.add(index, allEdgePairs.get(ii).get(0));
            allSpanningTrees.add(index, allEdgePairs.get(ii).get(1));
            comb(allEdgePairs, len-1, ii+1, allSpanningTrees, treeLength);
        }
        return allSpanningTrees;
    }
}