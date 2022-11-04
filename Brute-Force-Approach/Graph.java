import java.util.ArrayList;
import java.util.List;

// Graph class=========================================================
public class Graph 
{
    private List<Vertex> verticesList = new ArrayList<>();
    private List<Edge> edgeList = new ArrayList<>();
    private List<List<Vertex>> adjacencyList = new ArrayList<>();
    private int budget;

    public Graph(){}

    public void setBudget(int inBudget) {
        this.budget = inBudget;
    }

    public Vertex getVertex(int inLabel) {
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

    public int getBudget() {
        return this.budget;
    }

    public boolean hasVertex(int label) {
        return getVertex(label) != null;
    }

    public boolean hasEdge(int label1, int label2) {
        return getEdge(label1, label2) != null;
    }

    public List<Edge> getEdgeList() {
        return this.edgeList;
    }

    public int getNumOfVertices() {
        return verticesList.size();
    }

    public Vertex createVertex(int label) {
        Vertex newVertex = new Vertex(label);
        verticesList.add(newVertex);
        return newVertex;
    }

    public Edge createEdge(int inSrc, int inDest, int weight) {
        Vertex src = new Vertex(inSrc);
        Vertex dest = new Vertex(inDest);

        Edge newEdge = new Edge(src, dest, weight);
        edgeList.add(newEdge);
        return newEdge;
    }

    public List<List<Vertex>> createAdjacencyList() {
        List<Vertex> adjacentVertices;

        for (int ii = 0; ii < edgeList.size(); ii++) {
            adjacentVertices = new ArrayList<>();
            adjacentVertices.add(edgeList.get(ii).getSrc());
            adjacentVertices.add(edgeList.get(ii).getDest());
            adjacencyList.add(adjacentVertices);
        }
        return this.adjacencyList;
    }

    public int getEdgeSize() {
        return edgeList.size();
    }

    public String adjacencyListStr() {
        String retStr = "";
        Vertex tempSrc = new Vertex();
        Vertex tempDest = new Vertex();

        for (int ii = 0; ii < adjacencyList.size(); ii++) {
            tempSrc = adjacencyList.get(ii).get(0);
            tempDest = adjacencyList.get(ii).get(1);
            Edge tempEdge = getEdge(tempSrc.getLabel(), tempDest.getLabel());
            retStr += ii+1 + ": " + tempSrc.getLabel() + " --> " + tempDest.getLabel() 
            + " | Path Cost: $" + tempEdge.getEdgeWeight() +"\n";
        }
        return retStr;
    }

    public static void main(String[] args) {
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
        System.out.print(graph.adjacencyListStr());
    }
}