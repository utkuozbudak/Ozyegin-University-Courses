import sun.invoke.empty.Empty;

import java.util.Random;

public class GenericAlgorithmSolver {
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    public static RouteManager evolveRoute(RouteManager routes)
    {
        RouteManager r = new RouteManager(routes.getLength(),false);

        //elitism
        r.setRoute(0,routes.findBestFitness());

        /* select the best 2 node from tournament
        and crossover them to create a child node */
        for (int i=1; i<r.getLength();i++)
        {
            Route node1 = tournamentSelection(routes);
            Route node2 = tournamentSelection(routes);
            Route child_node = crossover(node1, node2);
            r.setRoute(i,child_node);
        }

        // mutate
        for(int i=1; i<r.getLength();i++)
        {
            mutate(r.getRoute(i));
        }
        return r;
    }

    public static Route crossover(Route parent1, Route parent2)
    {
        Route new_route = new Route();
        int l = parent1.getLength(), tmp=0;
        int range = l-(l/2)-1;

        /* choose a random index and start filling
        the new route from 1st parent */
        int idx1 = (int) Math.random() * (range);
        int idx2 = idx1 + l/2;
        for(int i=idx1; i<idx2 ; i++)
        {

            new_route.setCity(i, parent1.getCity(i));
        }

        /* fill the rest in new route
        from 2nd parent */
        for(int i=0; i<parent2.getLength();i++)
        {
            if (new_route.getCity(i) == null)
            {
                while (new_route.containsCity(parent2.getCity(tmp)))
                {
                    tmp++;
                }
                new_route.setCity(i, parent2.getCity(tmp));
                tmp++;
            }

        }
        return new_route;
    }

    private static void mutate(Route route)
    {
        /* change the random 2 city with each other
        in a given route */
        for(int i=0; i<route.getLength();i++)
        {
            if(Math.random() < mutationRate)
            {
                int random_index = (int) (Math.random() * route.getLength());
                City c1 = route.getCity(i);
                City c2 = route.getCity(random_index);
                route.setCity(i, c2);
                route.setCity(random_index, c1);
            }
        }
    }

    private static Route tournamentSelection(RouteManager routes)
    {
        RouteManager r2 = new RouteManager(tournamentSize, false);

        /* choose random routes and
        find and return the best one among them */
        int i=0;
        while(i<r2.getLength())
        {
            int rand = (int) Math.random() * routes.getLength();
            r2.setRoute(i, routes.getRoute(rand));
            i++;
        }
        return r2.findBestFitness();
    }
}