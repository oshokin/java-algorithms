public class Main {
    public static void main(String[] args) {
        var cities = new Graph<Integer>(32);

        cities.addVertex("Yaroslavl", 612_662);
        cities.addVertex("Moscowbad", 12_640_818);
        cities.addVertex("Saint Petersburg", 5_028_000);
        cities.addVertex("Vologda", 314_900);
        cities.addVertex("Cherepovets", 315_738);
        cities.addVertex("Vladimir", 310_024);
        cities.addVertex("Nizhniy Novgorod", 1_252_917);
        cities.addVertex("Kazan", 1_104_738);

        cities.addVertex("Lima", 7_737_002);
        cities.addVertex("Havana", 2_145_691);
        cities.addVertex("Rio de Janeiro", 13_634_274);
        cities.addVertex("Sao Paulo", 10_021_295);
        cities.addVertex("Porto Alegre", 1_372_741);
        cities.addVertex("Foz do Iguacu", 293_523);
        cities.addVertex("Buenos Aires", 15_369_919);

        cities.addEdge("Moscowbad", "Havana");
        cities.addEdge("Havana", "Lima");
        cities.addEdge("Lima", "Rio de Janeiro");
        cities.addEdge("Rio de Janeiro", "Sao Paulo");
        cities.addEdge("Sao Paulo", "Porto Alegre");
        cities.addEdge("Porto Alegre", "Foz do Iguacu");
        cities.addEdge("Foz do Iguacu", "Buenos Aires");

        cities.addEdge("Moscowbad", "Vologda");
        cities.addEdge("Moscowbad", "Yaroslavl");
        cities.addEdge("Moscowbad", "Nizhniy Novgorod");
        cities.addEdge("Nizhniy Novgorod", "Kazan");
        cities.addEdge("Nizhniy Novgorod", "Vladimir");
        cities.addEdge("Vologda", "Cherepovets");
        cities.addEdge("Vologda", "Saint Petersburg");

        System.out.println("Now let's see DFS");
        cities.depthFirstSearch();

        System.out.println("And then BFS");
        cities.breadthFirstSearch();
    }
}