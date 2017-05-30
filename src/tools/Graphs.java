package tools;


import tools.Graph.Edge;
import tools.Graph.Vertex;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Graphs {

    public static <D, W> boolean depthFirst(Graph<D, W> graph, Vertex<D, W> pointA, Vertex<D, W> pointB) {
        Set<Vertex<D, W>> marked = new HashSet<>();
        ArrayStack<Vertex<D, W>> stack = new ArrayStack(1000);
        int i = 0;
        stack.push(pointA);
        Vertex<D, W> current;
        while (!stack.isEmpty()) {
            i++;
            while (marked.contains(current = stack.pop()));
            if (current.equals(pointB)){
                System.out.println("Found location B! With DepthFirst "+i);
                if(!stack.isEmpty())
                return true;
            }
            marked.add(current);
            Collection<Edge<D, W>> edgesFrom = graph.getEdgesFrom(current);
            for (Edge<D, W> edge : edgesFrom) {
                if (!marked.contains(edge.getHeadVertex())) {
                    stack.push(edge.getHeadVertex());
                }
            }
        }

        return false;
    }

    
    
    public static <D, W> boolean breadthFirst(Graph<D, W> graph, Vertex<D, W> pointA, Vertex<D, W> pointB){
        Set<Vertex<D, W>> marked = new HashSet<>();
        Queue<Vertex<D, W>> queue = new LinkedQueue<>();
        RootedTree<D, W> edgesTo = new SimpleRootedTree<>(graph, pointA);
        int i=0;

        queue.enqueue(pointA);
        Vertex<D, W> current;
        while (!queue.isEmpty()) {
            i++;
            current = queue.dequeue();
            if (current.equals(pointB)){
                System.out.println("Found location B! With BreadthFirst "+i);
                
                return true;
            }
            marked.add(current);
            Collection<Edge<D, W>> edgesFrom = graph.getEdgesFrom(current);
            for (Edge<D, W> edge : edgesFrom) {
                if (!marked.contains(edge.getHeadVertex())) {
                    queue.enqueue(edge.getHeadVertex());
                }
            }

        }

        return false;
    }

    

}
