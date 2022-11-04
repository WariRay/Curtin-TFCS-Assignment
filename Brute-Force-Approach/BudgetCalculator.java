import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BudgetCalculator {
    public static Edge[] minimumSpanningTree;
    public static int cheapestCost;
    public static List<Edge[]> candidateMst;

    public static void test(Graph graph, int budget)
    {
        int len = graph.getNumOfVertices() - 1;
        List<Edge> edgeList = graph.getEdgeList();
        List<Edge[]> candidateMsts;

        candidateMsts = BudgetCalculator.getCandidateMsts(edgeList, len, 0, new Edge[len]); 
        System.out.println("TEST \n");
        for (int ii = 0; ii < candidateMsts.size(); ii++) {
            System.out.print(ii+1 + ": ");
            for (Edge e : candidateMsts.get(ii))
            {
                System.out.print("(" + e.getSrc().getLabel() + ", " + e.getDest().getLabel() + "), ");
            }
            System.out.println();
        }

    }

    public static void test2() {
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
        int len = graph.getNumOfVertices() - 1;
        List<Edge> edgeList = graph.getEdgeList();
        List<Edge[]> candidateMsts;

        candidateMsts = BudgetCalculator.getCandidateMsts(edgeList, len, 0, new Edge[len]); 
        System.out.println("TEST 2 \n");
        for (int ii = 0; ii < candidateMsts.size(); ii++) {
            System.out.print(ii+1 + ": ");
            for (Edge e : candidateMsts.get(ii))
            {
                System.out.print("(" + e.getSrc().getLabel() + ", " + e.getDest().getLabel() + "), ");
            }
            System.out.println();
        }

    }

    /**
     * @param graphStr A representation of a weighted graph as a set of Vertices
     * @return
     */
    public static boolean withinBudget(Graph graph, int budget) {
        List<Edge> edgeList = graph.getEdgeList();
        int len = graph.getNumOfVertices() - 1;
        List<Edge[]> tempCandidateMST = getCandidateMsts(edgeList, len, 0, new Edge[len]);
        Double positive_inf = Double.POSITIVE_INFINITY;
        int minCost = positive_inf.intValue();
        int currCost = 0;
        Edge[] MST = null;

        for (Edge[] tree : tempCandidateMST) {
            if (checkSpanningTree(tree, graph)) {
                currCost = getTreeCost(tree);

                if (currCost < minCost) {
                    minCost = currCost;
                    MST = tree;
                }
            }
        }

        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA \n");
        // for (int ii = 0; ii < tempCandidateMST.size(); ii++) {
        //     System.out.print(ii+1 + ": ");
        //     for (Edge e : tempCandidateMST.get(ii))
        //     {
        //         System.out.print("(" + e.getSrc().getLabel() + ", " + e.getDest().getLabel() + "), ");
        //     }
        //     System.out.println();
        // }

        candidateMst = tempCandidateMST;
        minimumSpanningTree = MST;
        cheapestCost = minCost;
        return (cheapestCost <= budget ? true : false);
    }

    /**
     * @param allEdges The List of all edges
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

        for (int i = startIdx; i <= allEdges.size()-len; i++){  // O(E)
            candidateMst[candidateMst.length - len] = allEdges.get(i); // O(E) x O(1)
            getCandidateMstsRec(allEdges, len-1, i+1, candidateMst, output); 
        }
        return;
    }

    /**
     * @param 
     * @param 
     * @return true if input edge set is a spanning tree (visits all V), false otherwise
     */
    public static boolean checkSpanningTree(Edge[] candidateSpanningTree, Graph graph)
    {   
        return DFS(candidateSpanningTree, graph);
    }

    /**
     * @param 
     * @param 
     * @return true if input edge set is a spanning tree (visits all V), false otherwise
     */
    public static boolean DFS(Edge[] candidateMst, Graph graph)
    {
        List<Vertex> visitedVertices = new ArrayList<>();
        List<Vertex> candidateMstV = new ArrayList<>();
        Vertex tempV = null;

        // Find all vertices that exists in the candiateMST
        for (Edge e : candidateMst) {
            if (candidateMstV.size() == 0)
            {
                candidateMstV.add(e.getSrc());
                candidateMstV.add(e.getDest());
            }
            else 
            {
                List<Vertex> toAdd = new ArrayList<>();

                for (Vertex v : candidateMstV) {
                    // Check if vertex exists in candidateMstV
                    if (v.getLabel() == e.getSrc().getLabel() || v.getLabel() == e.getDest().getLabel()) {
                        tempV = null;
                    }
                    else if (!(v.getLabel() == e.getSrc().getLabel())) {
                        tempV = new Vertex(e.getSrc().getLabel());
                    }
                    else if (!(v.getLabel() == e.getDest().getLabel())) {
                        tempV = new Vertex(e.getDest().getLabel());
                    }
                }

                if (tempV != null) {
                    toAdd.add(tempV);
                }
            candidateMstV.addAll(toAdd);
            toAdd.clear();
            }
        }
        boolean validMst = true;
        boolean invalidMst = false;
        Queue<Vertex> verticiesToVisit = new LinkedList<>();
        Vertex currentVertex;
        verticiesToVisit.add(candidateMstV.get(0)); // First, we visit the root vertex

        while (verticiesToVisit.size() > 0) {
            currentVertex = verticiesToVisit.poll();

            for (int i = 1; i < candidateMstV.size(); i++) {
                Vertex nextVertex = candidateMstV.get(i);

                if (!visitedVertices.contains(nextVertex)) {
                    verticiesToVisit.add(nextVertex); 
                    Edge tempEdge = graph.getEdge(currentVertex.getLabel(), nextVertex.getLabel()); 
                    
                    // Check if we can traverse from currentVertex to nextVertex
                    if (tempEdge == null)
                    {
                        return invalidMst;
                    }
                    visitedVertices.add(nextVertex); // Traverse to nextVertex 
                    verticiesToVisit.poll(); // Remove nextVertex from verticesToVisit queue
                    currentVertex = nextVertex; // This is the vertex we are currently visting in the graph
                }
            }
        }
        return validMst;
    }

    public static int getTreeCost(Edge[] tree) {
        int individualCost = 0;
        int totalCost = 0;

        for (Edge edge : tree) {
            individualCost = edge.getEdgeWeight();
            totalCost += individualCost;
        }
        return totalCost;
    }

    public static String getCandidateMstStr() {
        String retStr = "";

        for (int i = 0; i < candidateMst.size(); i++) {
            retStr += (i + 1) + ": ";

            for (Edge e : candidateMst.get(i)) {
                retStr += "(" + e.getSrc().getLabel() + ", " + e.getDest().getLabel() + ") ";
            }
            retStr += "\n";
        }
        return retStr;
    }

    public static String getMstStr() {
        int tempSrc;
        int tempDest;
        String retStr = "";

        for (int i = 0; i < minimumSpanningTree.length; i++) {
            retStr += (i == 0) ? "(" : ", ";
            tempSrc = minimumSpanningTree[i].getSrc().getLabel();
            tempDest = minimumSpanningTree[i].getDest().getLabel();
            retStr += "(" + tempSrc + ", " + tempDest + ")";
            retStr += (i == minimumSpanningTree.length - 1) ? ")" : "";
        }
        return retStr;
    }
}