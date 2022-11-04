import java.io.*;

public class GraphImporter {
    public static Graph importGraph (String fileName) {
        Graph graph = new Graph();
        String line;
        String vertciesStr[];
        String adjacentVertexPair[];
        String budgetStr;
        InputStreamReader reader;
        BufferedReader bufferedReader;
        FileInputStream fileStream = null;
        int i = 0;

        try {
            fileStream = new FileInputStream(fileName);
            reader = new InputStreamReader(fileStream);
            bufferedReader = new BufferedReader(reader);
            line = bufferedReader.readLine();

            while (line != null) {
                switch (i) {
                case 0:
                    vertciesStr = mySplit(line, "[\\W]+", 1);

                    // Create vertices
                    for (int j = 0; j < vertciesStr.length; j++) {
                        String vertexStr = vertciesStr[j];
                        int tempVertex = Integer.parseInt(vertexStr);
                        graph.createVertex(tempVertex);
                        //System.out.println("Make this vertex: " + tempVertex);
                    }
                    i++;
                    break;
                case 1:
                    adjacentVertexPair = mySplit(line, "[\\W]+", 2);
                    i++;

                    // Create edges
                    for (int k = 1; k < adjacentVertexPair.length; k++) {

                        if ((k + 1) % 3 == 0) {
                            int weight = Integer.parseInt(adjacentVertexPair[k]);
                            int tempSrc = Integer.parseInt(adjacentVertexPair[k-2]);
                            int tempDest = Integer.parseInt(adjacentVertexPair[k-1]);
                            graph.createEdge(tempSrc, tempDest, weight);
                            //System.out.println("Make this edge: " + tempSrc + " : " + tempDest + " : " + weight);
                        }
                    }
                    break;
                case 2:
                    budgetStr = line;
                    int budget = Integer.parseInt(budgetStr);
                    graph.setBudget(budget);
                    break;
                }
                line = bufferedReader.readLine();
            }
            fileStream.close();
        } catch (IOException e) {

            if (fileStream != null) {

                try {
                    fileStream.close();
                }
                catch (IOException e2) {}
            }
            throw new IllegalArgumentException("ERROR: File reading unsuccessful; " + e.getMessage());
        }
        return graph;
    }

    public static String[] mySplit(final String string, final String delimiter, int trimLen) {
        String modifiedStr = string.substring(trimLen, string.length() - trimLen);
        return modifiedStr.split(delimiter);
    }
}