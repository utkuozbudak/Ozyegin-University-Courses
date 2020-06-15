public class Main {

    public static void main(String[] args) {
        City city = new City(60, 200);
        CityManager.addCity(city);
        City city2 = new City(180, 200);
        CityManager.addCity(city2);
        City city3 = new City(80, 180);
        CityManager.addCity(city3);
        City city4 = new City(140, 180);
        CityManager.addCity(city4);
        City city5 = new City(20, 160);
        CityManager.addCity(city5);
        City city6 = new City(100, 160);
        CityManager.addCity(city6);
        City city7 = new City(200, 160);
        CityManager.addCity(city7);
        City city8 = new City(140, 140);
        CityManager.addCity(city8);
        City city9 = new City(40, 120);
        CityManager.addCity(city9);
        City city10 = new City(100, 120);
        CityManager.addCity(city10);
        City city11 = new City(180, 100);
        CityManager.addCity(city11);
        City city12 = new City(60, 80);
        CityManager.addCity(city12);
        City city13 = new City(120, 80);
        CityManager.addCity(city13);
        City city14 = new City(180, 60);
        CityManager.addCity(city14);
        City city15 = new City(20, 40);
        CityManager.addCity(city15);
        City city16 = new City(100, 40);
        CityManager.addCity(city16);
        City city17 = new City(200, 40);
        CityManager.addCity(city17);
        City city18 = new City(20, 20);
        CityManager.addCity(city18);
        City city19 = new City(60, 20);
        CityManager.addCity(city19);
        City city20 = new City(160, 20);
        CityManager.addCity(city20);

        RouteManager routes = new RouteManager(50, true);
        System.out.println("Initial distance: " + routes.findBestFitness().getDistance());

        routes = GenericAlgorithmSolver.evolveRoute(routes);
        for (int i = 0; i < 100; i++) {
            routes = GenericAlgorithmSolver.evolveRoute(routes);
        }

        System.out.println("Finished");
        System.out.println("Final distance: " + routes.findBestFitness().getDistance());
        System.out.println("Solution:");
        System.out.println(routes.findBestFitness());
    }
}
