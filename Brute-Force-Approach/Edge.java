// Edge class=========================================================
public class Edge 
{
    private Vertex src;
    private Vertex dest;
    private int weight;

    Edge()
    {}

    Edge(Vertex src, Vertex dest, int weight)
    {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Vertex getSrc()
    {
        return this.src;
    }

    public Vertex getDest()
    {
        return this.dest;
    }

    public int getEdgeWeight()
    {
        return this.weight;
    }
}
