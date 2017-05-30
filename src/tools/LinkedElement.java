
package tools;


public class LinkedElement<Data> {
    Data data;
    LinkedElement<Data> rest;
    
    public LinkedElement(Data data, LinkedElement rest){
        this.data=data;
        this.rest=rest;
    }
    public Data getData(){
        return data;
    }
}
