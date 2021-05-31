import java.util.List;
import java.util.Scanner;
// import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        Country.loadFile("Countries.csv");
        City.loadFile("Cities.csv");
        MapHandler mh = new MapHandler(City.All(),Country.All());
        // HashMap<String,List<City>> countryMap = mh.getMap();
        System.out.println(Country.getCountriesPopulation());
        System.out.println("Average: "+ Country.getAverageCountriesPopulation());
        Country max = Country.getMaxPopulationCountry();
        System.out.println("Maximum: "+ max.getName()+" with population: " + max.getPopulation());
        for(Country c: Country.All())
            System.out.println(mh.getHighestPopulationCity(c.getCode()) +" of "+c.getName());
        System.out.println("############################");
        City maxCap = mh.getHighestPopulationCapital();
        System.out.println("Highest Population Capital is: " +maxCap+" capital of "+maxCap.getCountryCode());
        System.out.println("############################");
        System.out.print("Input Country Key: ");
        Scanner s = new Scanner(System.in);
        String countryCode = s.nextLine();
        s.close();
        List<City> cities = mh.exploreCountry(countryCode);
        System.out.println("Cities in "+countryCode);
        cities.forEach(System.out::println);
    }
}
