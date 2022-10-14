import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeniusInventor {
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
        int len = graph.getNumOfVertices() - 1;
        List<Edge> edgeList = graph.getEdgeList();
        List<Edge[]> candidateMsts;

        //candidateMsts = getCandidateMsts(edgeList, len, 0, new Edge[len], new ArrayList<Edge[]>(len)); 
        candidateMsts = getCandidateMsts(edgeList, len, 0, new Edge[len]); 

        System.out.println("tree: " + candidateMsts.size());
        
        for (int ii = 0; ii < candidateMsts.size(); ii++)
        {
            System.out.print(ii+1 + ": ");
            for (Edge e : candidateMsts.get(ii))
            {
                System.out.print("(" + e.getSrc().getLabel() + ", " + e.getDest().getLabel() + "), ");
            }
            System.out.println();
        }

        System.out.println();
    }

    /**
     * @param allEdges The set of all edges
     * @param len The length of sub-array we are currently recursively looking at
     * @param startIdx The start of the sub-list we are currently looking at
     * @param candidateMsts The st of all candidate MSTs
     * @param treeLength The length required for a candidate MST
     * @return
     * https://stackoverflow.com/a/16256122
     */
    public static List<Edge[]> getCandidateMsts(List<Edge> allEdges, int len, int startIdx, Edge[] candidateMst){
        List<Edge[]> candidateMsts = new ArrayList<Edge[]>();
        getCandidateMstsRec(allEdges, len, startIdx, candidateMst, candidateMsts);

        return candidateMsts;
    }

    public static void getCandidateMstsRec(List<Edge> allEdges, int len, int startIdx, Edge[] candidateMst, List<Edge[]> output){
        if (len == 0){
            output.add(Arrays.copyOf(candidateMst, candidateMst.length));

            return;
        }       

        for (int ii = startIdx; ii <= allEdges.size()-len; ii++){  // O(E)
            candidateMst[candidateMst.length - len] = allEdges.get(ii); // O(E) x O(1)
            getCandidateMstsRec(allEdges, len-1, ii+1, candidateMst, output); 
        }

        return;
    }
    
    public static boolean checkSpanningTree(List<Edge[]> candidateMst)
    {   
        // if 
        // return false;
        return true;
    }
}
// System.out.println("candidateMst.length: " + candidateMst.length);
// System.out.println("len length: " + len);
// System.out.print(allEdges.get(ii).getSrc().getLabel() + ", ");
// System.out.println(allEdges.get(ii).getDest().getLabel());

// METHOD: 
//     getSpanningTreeCost(spanningTree)
// BEGIN:
// 	  cost <- 0
// 	  totalCost <- 0
// 	      for each e in spanningTree
// 	          cost <- e.get(ii).weight  
// 	          totalCost <- totalCost + cost
// 	  return totalCost
// END

// // Check if spanning tree exists in graph
// METHOD: 
//     checkSpanningTree
// INPUT: 
//     spanningTree
// BEGIN:
//     if DFS(spanningTree) is false
//         return false
//     else 
//         return true
// END