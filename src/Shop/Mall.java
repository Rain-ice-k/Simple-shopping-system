package Shop;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

public class Mall {
    private final List<Commodity> commodities;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private int nextCommodityId;
    public Mall() {
        this.commodities = new ArrayList<>();
        this.nextCommodityId = 1;
    }
    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void initializeCommoditiesFromFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.getString("name");
                double price = obj.getDouble("price");
                String type = obj.getString("type");
                LocalDate productionDate = LocalDate.parse(obj.getString("productionDate"), formatter);
                LocalDate expiryDate = LocalDate.parse(obj.getString("expiryDate"), formatter);
                boolean isBluetoothEnabled = obj.optBoolean("isBluetoothEnabled", false);

                Commodity commodity = createCommodity(name, price, type, productionDate, expiryDate, isBluetoothEnabled);
                if (commodity != null) {
                    commodities.add(commodity);
                }
            }
        } catch (IOException e) {
            System.out.println("读取商品文件失败" + e.getMessage());
        }
    }

    public void initializeCommoditiesFromJson(String jsonContent) {
        JSONArray jsonArray = new JSONArray(jsonContent);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String name = obj.getString("name");
            double price = obj.getDouble("price");
            String type = obj.getString("type");
            LocalDate productionDate = LocalDate.parse(obj.getString("productionDate"));
            LocalDate expiryDate = LocalDate.parse(obj.getString("expiryDate"));
            boolean isBluetoothEnabled = obj.optBoolean("isBluetoothEnabled", false);

            Commodity commodity = createCommodity(name, price, type, productionDate, expiryDate, isBluetoothEnabled);
            if (commodity != null) {
                commodity.setId(nextCommodityId++);
                commodities.add(commodity);
            } else {
                System.out.println("无法从" + name+"中创建商品");
            }
        }
    }


    private Commodity createCommodity(String name, double price, String type, LocalDate productionDate, LocalDate expiryDate, boolean isBluetoothEnabled) {
        Commodity commodity = null;

        if (type.equals("Refrigerator") || type.equals("AirConditioner") ||
                type.equals("Fan") || type.equals("WaterHeater") || type.equals("Humidifier")) {
            commodity = ElectricApplianceFactory.createAppliance(type, name, price, productionDate, expiryDate, isBluetoothEnabled);
        }

        if (commodity == null) {
            switch (type) {
                case "Electronics":
                    commodity = new Electronics(name, price, productionDate, expiryDate, isBluetoothEnabled);
                    break;
                case "HomeAppliance":
                    commodity = new HomeAppliance(name, price, productionDate, expiryDate);
                    break;
                case "DailyGoods":
                    commodity = new DailyGoods(name, price, productionDate, expiryDate);
                    break;
                case "Foods":
                    commodity = new Foods(name, price, productionDate, expiryDate);
                    break;
                default:
                    System.out.println("未知的类型：" + type);
                    return null;
            }
        }

        commodity.setId(nextCommodityId++);
        return commodity;
    }



    public void displayCommodities() {
        for (Commodity commodity : commodities) {
            System.out.println(commodity);
        }
    }

    public Commodity findCommodityById(int id) {
        for (Commodity commodity : commodities) {
            if (commodity.getId() == id) {
                return commodity;
            }
        }
        return null;
    }

    public List<Commodity> findCommoditiesByType(String type) {
        List<Commodity> foundCommodities = new ArrayList<>();
        for (Commodity commodity : commodities) {
            if (commodity.getType().equalsIgnoreCase(type)) {
                foundCommodities.add(commodity);
            }
        }
        return foundCommodities;
    }
    public List<Commodity> findBlueTooth() {
        return commodities.stream()
                .filter(Commodity::hasBluetooth)
                .collect(Collectors.toList());
    }
    public void addCommodity(Commodity commodity) {
        if (commodity != null) {
            commodity.setId(nextCommodityId++);
            commodities.add(commodity);
        }
    }
}


class Main {
    public static void main(String[] args) {

        Mall mall = new Mall();
        mall.initializeCommoditiesFromFile("Goods.json");
        mall.displayCommodities();
    }
}

