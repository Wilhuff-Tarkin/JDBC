public class Movie {

    private int id;

    public Movie(String name, int productionYear) {
        this.name = name;
        this.productionYear = productionYear;
    }

    private String name;
    private int productionYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", productionYear=" + productionYear +
                '}';
    }
}
