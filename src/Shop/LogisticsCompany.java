package Shop;


import org.json.JSONObject;

public class LogisticsCompany {

    public static String generateLogisticsData(Order order) {
        Buyer buyer = order.getBuyer();
        JSONObject logisticsJson = new JSONObject();
        logisticsJson.put("orderId", order.getOrderId())
                .put("contactName", buyer.getName())
                .put("contactPhone", buyer.getPhoneNumber())
                .put("shippingAddress", buyer.getAddress())
                .put("items", order.getItems());

        return logisticsJson.toString();
    }
}

