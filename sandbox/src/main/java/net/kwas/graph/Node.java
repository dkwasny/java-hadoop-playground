package net.kwas.graph;

import java.util.List;

public interface Node {

    int getId();
    List<Node> getNeighbors();

}
