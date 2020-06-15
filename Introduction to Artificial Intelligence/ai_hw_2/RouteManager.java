public class RouteManager {
    Route[] routes;

    public RouteManager(int populationSize, boolean initialise) {
        routes = new Route[populationSize];
        if (initialise) {
            for (int i = 0; i < getLength(); i++) {
                Route newRoute = new Route();
                newRoute.generateIndividual();
                setRoute(i, newRoute);
            }
        }
    }

    public void setRoute(int index, Route route) {
        routes[index] = route;
    }

    public Route getRoute(int index) {
        return routes[index];
    }

    public Route findBestFitness() {
        Route best_route = routes[0];
        for (int i = 1; i < getLength(); i++) {
            if (best_route.getFitness() <= getRoute(i).getFitness()) {
                best_route = getRoute(i);
            }
        }
        return best_route;
    }

    public int getLength() {
        return routes.length;
    }
}
