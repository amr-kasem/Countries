import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MapHandler {
    // private ArrayList<City> cities;
    private ArrayList<Country> countries;
    private HashMap<String, List<City>> countryMap;

    public MapHandler(ArrayList<City> cities, ArrayList<Country> countries) {
        // this.cities = cities;
        this.countries = countries;
        countryMap = new HashMap<>();
        for (Country c : countries) {
            List<City> x = cities.stream().filter((n) -> n.getCountryCode().equals(c.getCode())).toList();
            countryMap.put(c.getCode(), x);
        }
    }

    public HashMap<String, List<City>> getMap() {
        return countryMap;
    }

    public List<City> exploreCountry(String code) {
        List<City> cities =  countryMap.get(code);
        if(cities == null)
            return new ArrayList<City>();
        ArrayList<City> c = new ArrayList<City>(cities);
        Collections.sort(c, City.ReversedPopulationComparator);
        return c;
    }

    public City getHighestPopulationCity(String code) {
        try {
            return exploreCountry(code).stream().max(City.PopulationComparator).get();
        } catch (Exception e) {
            return null;
        }
    }

    public City getHighestPopulationCapital() {
        return countries.stream().map((c) -> {
            List<City> countryCities = countryMap.get(c.getCode());
            if (countryCities.size() == 0)
                return null;
            return countryCities.stream().filter((ci) -> {
                return ci.getId() == c.getCapitalId();
            }).findAny().get();
        }).filter((x)->x!=null).max(City.PopulationComparator).get();
    }

}
