
package generator;


public class Path {
    private double distance;
    private double time;

    public Path(double distance, double time) {
        this.distance = distance;
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    
    
}
