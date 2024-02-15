package Shop;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Order {
    private static final AtomicLong orderCounter = new AtomicLong(0);
    private final long orderId;
    private final Buyer buyer;
    private final List<Commodity> items;

    public Order(Buyer buyer, List<Commodity> items) {
        this.orderId = orderCounter.incrementAndGet();
        this.buyer = buyer;
        this.items = items;
    }

    public long getOrderId() {
        return orderId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public List<Commodity> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Buyer: ").append(buyer).append("\n");
        sb.append("Items:\n");
        for (Commodity item : items) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}

