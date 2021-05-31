import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class City {
    private int id;
    private String name;
    private int population;
    private String countryCode;
    private static ArrayList<City> cities;

    City(int id, String name, int population, String countryCode){
        this.id = id;
        this.name = name;
        this.population = population;
        this.countryCode = countryCode;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPopulation() {
        return this.population;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String toString(){
        return name + " (" +population+")";
    }
    static{
        cities = new ArrayList<City>();
    }
    public static ArrayList<City> All(){
        return cities;
    }
    public static void loadFile(String filename){
        try {
            FileReader f = new FileReader(filename);
            BufferedReader bf = new BufferedReader(f);
            String line = bf.readLine();
            String[] values;
            while (line != null) {
                values = line.split(",");
                values = Arrays.stream(values).map((s)->{
                    s = s.replaceAll("\"", "");
                    return s.trim();
                }).toArray(String[]::new);
                cities.add(new City(Integer.parseInt(values[0]),values[1], Integer.parseInt(values[2]),values[3]));
                line = bf.readLine();
            }
            bf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Comparator<City> ReversedPopulationComparator = new Comparator<City>(){
        public int compare(City c1,City c2){
            if (c1.population < c2.population) return 1;
            else if(c1.population == c2.population) return 0;
            else return -1;
        }    
    };
    public static Comparator<City> PopulationComparator = new Comparator<City>(){
        public int compare(City c1,City c2){
            if (c1.population < c2.population) return -1;
            else if(c1.population == c2.population) return 0;
            else return 1;
        }    
    };

}