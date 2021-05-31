import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Country {
    private static ArrayList<Country> countries;
    private String code;
    private String name;
    private String continent;
    private double surfaceArea;
    private int population;
    private double gnp;
    private int capitalId;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public int getPopulation() {
        return population;
    }

    public double getGnp() {
        return gnp;
    }

    public int getCapitalId() {
        return capitalId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    public void setCapitalId(int capitalId) {
        this.capitalId = capitalId;
    }
    public Country(String code, String name, String continent, double surfaceArea, int population, double gnp,
            int capitalId) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.surfaceArea = surfaceArea;
        this.population = population;
        this.gnp = gnp;
        this.capitalId = capitalId;
    }

    public String toString() {
        return name + "(" + code + ")";
    }

    static {
        countries = new ArrayList<Country>();
    }

    public static void loadFile(String filename) {
        try {
            FileReader f = new FileReader(filename);
            BufferedReader bf = new BufferedReader(f);
            String line = bf.readLine();
            String[] values;
            while (line != null) {
                values = line.split(",");
                values = Arrays.stream(values).map((s) -> {
                    s = s.replaceAll("\"", "");
                    return s.trim();
                }).toArray(String[]::new);
                try {
                    countries.add(new Country(values[0], values[1], values[2], Double.parseDouble(values[3]),
                            Integer.parseInt(values[4]), Double.parseDouble(values[5]), Integer.parseInt(values[6])));
                } catch (Exception e) {
                    System.out.println("country Escaped");
                }
                line = bf.readLine();
            }
            bf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Comparator<Country> ReversedPopulationComparator = new Comparator<Country>() {
        public int compare(Country c1, Country c2) {
            if (c1.population < c2.population)
                return 1;
            else if (c1.population == c2.population)
                return 0;
            else
                return -1;
        }
    };    
    public static Comparator<Country> PopulationComparator = new Comparator<Country>() {
        public int compare(Country c1, Country c2) {
            if (c1.population < c2.population)
                return -1;
            else if (c1.population == c2.population)
                return 0;
            else
                return 1;
        }
    };

    public static ArrayList<Country> All() {
        return countries;
    }

    public static List<Integer> getCountriesPopulation() {
        return countries.stream().map((c) -> c.population).toList();
    }

    public static Double getAverageCountriesPopulation() {
        return countries.stream().mapToInt(c -> c.population).average().getAsDouble();
    }

    public static Country getMaxPopulationCountry(){
        return countries.stream().max(PopulationComparator).get();
    }
}
