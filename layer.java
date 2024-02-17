import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NeuralNetwork {
    private List<List<Double>>[] weights;

    public NeuralNetwork(int layersCount, int[] nodesInLayer) {
        weights = new ArrayList[layersCount - 1];
        for (int i = 0; i < layersCount - 1; i++) {
            weights[i] = new ArrayList<>();
            for (int j = 0; j < nodesInLayer[i]; j++) {
                List<Double> nodeWeights = new ArrayList<>();
                for (int k = 0; k < nodesInLayer[i + 1]; k++) {
                    nodeWeights.add(0.0);
                }
                weights[i].add(nodeWeights);
            }
        }
    }

    public void setWeight(int layer, int nodeFromIndex, int nodeToIndex, double weightVal) {
        weights[layer - 1].get(nodeFromIndex).set(nodeToIndex, weightVal);
    }

    public double getWeight(int layer, int nodeFromIndex, int nodeToIndex) {
        return weights[layer - 1].get(nodeFromIndex).get(nodeToIndex);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of layers: ");
        int layersCount = scanner.nextInt();
        int[] nodesInLayer = new int[layersCount];
        for (int i = 0; i < layersCount; i++) {
            System.out.print("Enter the number of nodes in layer " + i + ": ");
            nodesInLayer[i] = scanner.nextInt();
        }
        NeuralNetwork nn = new NeuralNetwork(layersCount, nodesInLayer);
        System.out.println("Enter the weights for each edge:");
        for (int i = 1; i < layersCount; i++) {
            for (int j = 0; j < nodesInLayer[i - 1]; j++) {
                for (int k = 0; k < nodesInLayer[i]; k++) {
                    System.out.print("Enter the weight for edge from node " + j + " in layer " + (i - 1) + " to node " + k + " in layer " + i + ": ");
                    double weightVal = scanner.nextDouble();
                    nn.setWeight(i, j, k, weightVal);
                }
            }
        }
        System.out.println("Enter the node indices to query the weight (layer, nodeFrom, nodeTo):");
        int layer = scanner.nextInt();
        int nodeFromIndex = scanner.nextInt();
        int nodeToIndex = scanner.nextInt();
        double weight = nn.getWeight(layer, nodeFromIndex, nodeToIndex);
        System.out.println("Weight: " + weight);
    }
}