package Shop;
import java.util.Comparator;
import java.util.List;

public class CommoditySortingManager {

    public void sortByName(List<Commodity> commodities) {
        commodities.sort(Comparator.comparing(Commodity::getName));
    }

    public void sortByType(List<Commodity> commodities) {
        commodities.sort(Comparator.comparing(Commodity::getType));
    }

    public void sortByPrice(List<Commodity> commodities) {
        commodities.sort(Comparator.comparingDouble(Commodity::getPrice));
    }
}
