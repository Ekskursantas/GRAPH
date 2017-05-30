
package tools;

import tools.Graph;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class LinkedGraph<Data, Weight> implements Graph<Data, Weight> {

    LinkedElement<LinkedVertex> vertices;
    LinkedElement<Edge> edges;
    int size;


    @Override
    public void addVertex(Data... data) {
        for (Data d : data) {
            Vertex v = new LinkedVertex(d);
            vertices = new LinkedElement(v, vertices);
            size++;
        }
    }

    @Override
    public void addEdge(Weight weight, Vertex<Data, Weight> tail, Vertex<Data, Weight> head, boolean directed) {

        LinkedEdge edge = new LinkedEdge(tail, head, weight);
        edges = new LinkedElement(edge, edges);
        if (tail.getClass()== LinkedVertex.class)((LinkedVertex)tail).addEdge(edge);

    }

    @Override
    public Collection<Vertex<Data, Weight>> getVertices() {
        List<Vertex<Data, Weight>> v = new ArrayList();

        LinkedElement<LinkedVertex> traverse = vertices;
        while (traverse != null) {
            v.add(traverse.getData());
            traverse = traverse.rest;
        }
        return v;
    }

    @Override
    public Collection<Edge<Data, Weight>> getEdges() {

        List<Edge<Data, Weight>> e = new ArrayList();

        LinkedElement<Edge> traverse = edges;
        while (traverse != null) {
            e.add(traverse.getData());
            traverse = traverse.rest;
        }
        return e;

    }

    @Override
    public Vertex<Data, Weight> vertexOf(Data data) {
        LinkedElement<LinkedVertex> traverse = vertices;
        while (traverse != null) {
            if (traverse.getData().getData() == data) {
                return traverse.getData();
            }
            traverse = traverse.rest;
        }
        return null;
    }

    @Override
    public Collection<Edge<Data, Weight>> getEdgesFrom(Vertex<Data, Weight> vertex) {
        Collection<Edge<Data, Weight>> adjacentEdges =null;
        if (vertex.getClass() == LinkedVertex.class) {
            adjacentEdges = ((LinkedVertex)vertex).getAdjacentEdges();
        }
        return adjacentEdges;
    }

    public class LinkedVertex implements Vertex<Data, Weight> {

        Data d;
        LinkedElement<Edge> localEdges;

        LinkedVertex(Data d) {
            this.d = d;
        }

        public void addEdge(LinkedEdge edge) {
            localEdges = new LinkedElement(edge, localEdges);
        }
        @Override
        public String toString(){
            return d.toString();
        }

        @Override
        public Data getData() {
            return d;
        }

        @Override
        public Collection<Edge<Data, Weight>> getAdjacentEdges() {
            List<Edge<Data, Weight>> al = new ArrayList();
            LinkedElement<Edge> traverse = localEdges;
            while (traverse!= null) {
                al.add(traverse.getData());
                traverse = traverse.rest;
            }
            return al;
        }
    }

    private class LinkedEdge implements Edge<Data, Weight> {

        Weight w;
        Vertex tail;
        Vertex head;

        public LinkedEdge(Vertex tail, Vertex head, Weight weight) {
            this.tail = tail;
            this.head = head;
            this.w = weight;

        }

        @Override
        public Weight getWeight() {
            return w;
        }

        @Override
        public Vertex<Data, Weight> getHeadVertex() {
            return head;
        }

        
    }

}
