public class BudgetCalculatorDriver {
    public static void main(String[] args){
        Graph graph = GraphImporter.importGraph("graph_example.txt");
        int budget = graph.getBudget();
        boolean meetBudget = BudgetCalculator.withinBudget(graph, budget);
        String allCandidateST = BudgetCalculator.getCandidateMstStr();

        System.out.println("Pair of Locations info: ");
        graph.createAdjacencyList();
        System.out.println(graph.adjacencyListStr());

        System.out.println("All Candidate Spanning Trees in the given Graph: ");
        System.out.println(allCandidateST);

        if (meetBudget) {
            System.out.println("SYSTEM OUTCOME: Accept.\n"
            + "Cheapest path: " + BudgetCalculator.getMstStr() + "\n"
            + "Cost: $" + BudgetCalculator.cheapestCost);
        }
        else {
            System.out.println("SYSTEM OUTCOME: Reject." 
            + "Cost of the cheapest path: " + BudgetCalculator.cheapestCost
            + "Budget: " + budget);
        }
    }
}