package Shop;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;

public class ShoppingCart {
    private final List<Commodity> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addCommodity(Commodity commodity) {
        commodity.setAddedToCartDate(LocalDate.now());
        items.add(commodity);
    }

    public void displayItems() {
        for (Commodity item : items) {
            System.out.println(item);
        }
    }

    public void removeItemById(int id) {
        boolean removed = items.removeIf(item -> item.getId() == id);
        if (!removed) {
            System.out.println("未找到编号为 " + id + " 的商品。");
        } else {
            System.out.println("编号为 " + id + " 的商品已从购物车中移除。");
        }
    }

    public void clearCart() {
        if (items.isEmpty()) {
            System.out.println("购物车已空，无需清空。");
        } else {
            items.clear();
            System.out.println("购物车已清空。");
        }
    }

    public void sortByName() {
        items.sort(Comparator.comparing(Commodity::getName));
    }

    public void sortById() {
        items.sort(Comparator.comparingInt(Commodity::getId));
    }

    public void sortByAddedDate() {
        items.sort(Comparator.comparing(Commodity::getAddedToCartDate).reversed());
    }
    public List<Commodity> getItems() {
        return new ArrayList<>(items);
    }
}
