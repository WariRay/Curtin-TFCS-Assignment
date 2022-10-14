import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Graph class=========================================================
public class Graph 
{
    private List<Vertex> verticesList = new ArrayList<>();
    private List<Edge> edgeList = new ArrayList<>();
    private List<List<Vertex>> adjacencyList = new ArrayList<>();
    private Set<Set<Vertex>> edgePairSet = new HashSet<>();

    public Graph()
    {}

    public Vertex getVertex(int inLabel)
    {
        Vertex retVertex = null;

        for (Vertex v : verticesList)
        {
            if (inLabel == v.getLabel())
            {
                retVertex = v;
            }
        }   

        return retVertex;
    }

    public Edge getEdge(int label1, int label2) {
        Edge retEdge = null;

        for (Edge e : edgeList) {
            if ((e.getSrc().getLabel() == label1 && e.getDest().getLabel() == label2) 
                    || e.getSrc().getLabel() == label2 && e.getDest().getLabel() == label1) {
                retEdge = e;
                break;
            }
        }

        return retEdge;
    }

    public boolean hasVertex(int label) {
        return getVertex(label) != null;
    }

    public boolean hasEdge(int label1, int label2) {
        return getEdge(label1, label2) != null;
    }

    public List<Edge> getEdgeList()
    {
        return this.edgeList;
    }

    public int getNumOfVertices()
    {
        return verticesList.size();
    }

    public Vertex createVertex(int label)
    {
        Vertex newVertex = new Vertex(label);
        verticesList.add(newVertex);

        return newVertex;
    }

    public Edge createEdge(int inSrc, int inDest, int weight)
    {
        Vertex src = new Vertex(inSrc);
        Vertex dest = new Vertex(inDest);

        Edge newEdge = new Edge(src, dest, weight);
        edgeList.add(newEdge);

        return newEdge;
    }

    public List<List<Vertex>> createAdjacencyList()
    {
        List<Vertex> adjacentVertices;

        for (int ii = 0; ii < edgeList.size(); ii++)
        {
            adjacentVertices = new ArrayList<>();
            adjacentVertices.add(edgeList.get(ii).getSrc());
            adjacentVertices.add(edgeList.get(ii).getDest());
            adjacencyList.add(adjacentVertices);
        }

        return this.adjacencyList;
    }

    public int getEdgeSize()
    {
        return edgeList.size();
    }

    public String displayAdjacencyList()
    {
        String retStr = "";
        Vertex tempSrc = new Vertex();
        Vertex tempDest = new Vertex();

        for (int ii = 0; ii < adjacencyList.size(); ii++)
        {
            tempSrc = adjacencyList.get(ii).get(0);
            tempDest = adjacencyList.get(ii).get(1);
            retStr += ii+1 + ": " + tempSrc.getLabel() + "->" + tempDest.getLabel() + "\n";
        }

        return retStr;
    }

    public static Graph fromEdges(List<Edge> edges) {
        Graph graph = new Graph();

        for (Edge edge : edges) {
        }

        return graph;
    }

    // public void DFS(Vertex v)
    // {
    //     boolean already[] = new boolean[getEdgeSize()]; 
    //     DFSUtil(v, already);
    // }

    // public void DFSUtil(Vertex vertex, boolean nodes[])
    // {
    //     nodes[vertex] = true;                        
    //     // System.out.print(vertex + " ");
    //     int a = 0;
 
    //     for (int i = 0; i < adj[vertex].size(); i++) {
    //         a = adj[vertex].get(i);
    //         if (!nodes[a])                 
    //         {
    //             DFSUtil(a, nodes);
    //         }
    //     }  
    // }

    public static void main(String[] args)
    {
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

        graph.createAdjacencyList();
        System.out.println("Adjacency List :D");
        System.out.print(graph.displayAdjacencyList());
    }
}

    // public Set<Set<Vertex>> getEdgePairSet()
    // {
    //     Set<Vertex> edgePair = new HashSet<>();

    //     for (int ii = 0; ii < edgeList.size(); ii++)
    //     {
    //         edgePair = new HashSet<>();
    //         edgePair.add(edgeList.get(ii).getSrc());
    //         edgePair.add(edgeList.get(ii).getDest());
    //         edgePairSet.add(edgePair);
    //     }

    //     return this.edgePairSet;
    // }

    // public Set<Vertex> getEdgePair(Vertex src, Vertex dest)
    // {
    //     Set<Vertex> edgePair = new HashSet<>();

    //     edgePair.add(src);
    //     edgePair.add(dest);

    //     return edgePair;
    // }