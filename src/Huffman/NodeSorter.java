import java.util.Comparator;

public class NodeSorter implements Comparator<Node> {
    public int compare(Node n1, Node n2){
        return n1.getValue().compareTo(n2.getValue());
    }
}
