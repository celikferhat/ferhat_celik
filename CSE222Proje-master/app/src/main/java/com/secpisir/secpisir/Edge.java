package com.secpisir.secpisir;

class Edge {
    private int weight;
    private int source, destination;
    Edge(int source, int destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    Edge(int source, int destination){
        this.source = source;
        this.destination = destination;
        weight = 1;
    }

    public int getDest() {
        return destination;
    }
    public int getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "[" + source + "," + destination + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Edge))
            return false;
        else {
            Edge other = (Edge)obj;
            return (source == other.source && destination == other.destination);
        }
    }
}
