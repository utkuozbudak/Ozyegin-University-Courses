import java.util.ArrayList;
import java.util.Collections;

public class Route {
    private ArrayList<City> route = new ArrayList<City>();
    private double fitness = 0;
    private int distance = 0;

    public Route(){
        for (int i = 0; i < CityManager.numberOfCities(); i++) {
            route.add(null);
        }
    }

    public Route(ArrayList<City> route){
        this.route = route;
    }

    public void generateIndividual() {
        for (int cityIndex = 0; cityIndex < CityManager.numberOfCities(); cityIndex++) {
            setCity(cityIndex, CityManager.getCity(cityIndex));
        }
        Collections.shuffle(route);
    }

    public City getCity(int index) {
        return (City) route.get(index);
    }

    public void setCity(int index, City city) {
        route.set(index, city);
        fitness = 0;
        distance = 0;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = 1.0 / (double) getDistance();
        }
        return fitness;
    }

    public int getDistance(){
        if (distance == 0) {
            int total_distance = 0;
            for (int i = 0; i < getLength(); i++) {
                City fromCity = getCity(i);
                City destinationCity;
                if(i+1 < getLength()){
                    destinationCity = getCity(i+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                total_distance += fromCity.distanceTo(destinationCity);
            }
            distance = total_distance;
        }
        return distance;
    }

    public int getLength() {
        return route.size();
    }

    public boolean containsCity(City city){
        return route.contains(city);
    }

    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder("Route\n");
        for (int i = 0; i < getLength(); i++) {
            geneString.append(getCity(i)).append(" --> ");
        }
        return geneString.toString();
    }
}
