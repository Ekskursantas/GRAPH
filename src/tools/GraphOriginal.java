package tools;

import tools.Graph.Edge;
import tools.Graph.Vertex;
import tools.LinkedQueue;
import tools.Queue;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for graph related functions.
 */
public class GraphOriginal {
  
  private static <D,W> void depthFirst(
      Set<Vertex<D,W>> marked,
      RootedTree<D,W> edgesTo,
      Graph<D,W> graph, 
      Vertex<D,W> vertex
      ) {
    marked.add(vertex);
    for (Edge<D,W> edge : graph.getEdgesFrom(vertex)) {
      if (marked.contains(edge.getHeadVertex())) continue;
      edgesTo.add(edge);
      depthFirst(marked, edgesTo, graph, edge.getHeadVertex());
      }
    }
  
   public static <D,W> RootedTree<D,W> depthFirst(Graph<D,W> graph, Vertex<D,W> root) {
    Set<Vertex<D,W>> marked = new HashSet<>();
    RootedTree<D,W> edgesTo = new SimpleRootedTree<>(graph, root);
    
    depthFirst(marked, edgesTo, graph, root);
    
    return edgesTo;
    }
  
  private static <D,W> void breadthFirst(
      Set<Vertex<D,W>> marked,
      RootedTree<D,W> edgesTo,
      Graph<D,W> graph, 
      Queue<Vertex<D,W>> queue
      ) {
    if (queue.isEmpty()) return;
    Vertex<D,W> vertex = queue.dequeue();
    for (Edge<D,W> edge : graph.getEdgesFrom(vertex)) {
      Vertex<D,W> headVertex = edge.getHeadVertex();
      if (marked.contains(headVertex)) continue;
      marked.add(headVertex);
      queue.enqueue(headVertex);
      edgesTo.add(edge);
      }
    breadthFirst(marked, edgesTo, graph, queue);
    }
  
  public static <D,W> RootedTree<D,W> breadthFirst(Graph<D,W> graph, Vertex<D,W> root) {
    Set<Vertex<D,W>> marked = new HashSet<>();
    RootedTree<D,W> edgesTo = new SimpleRootedTree<>(graph, root);
    Queue<Vertex<D,W>> queue = new LinkedQueue<>();

    marked.add(root);
    queue.enqueue(root);
    breadthFirst(marked, edgesTo, graph, queue);

    return edgesTo;
    }

  }