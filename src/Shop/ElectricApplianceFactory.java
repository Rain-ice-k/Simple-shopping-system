package Shop;

import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
public class ElectricApplianceFactory {

    public static Commodity createAppliance(String type, String name, double price, LocalDate productionDate, LocalDate expiryDate, boolean isBluetoothEnabled) {
        switch (type) {
            case "Refrigerator":
                return new Refrigerator(name, price, productionDate, expiryDate, isBluetoothEnabled);
            case "AirConditioner":
                return new AirConditioner(name, price, productionDate, expiryDate, isBluetoothEnabled);
            case "Fan":
                return new Fan(name, price, productionDate, expiryDate, 3);
            case "WaterHeater":
                return new WaterHeater(name, price, productionDate, expiryDate, 80.0);
            case "Humidifier":
                return new Humidifier(name, price, productionDate, expiryDate, 2.0);
            default:
                throw new IllegalArgumentException("未知的类型: " + type);

        }
    }

    public static String createAppliancesJson() {
        JSONArray appliancesJson = new JSONArray();

        addApplianceToJsonArray(appliancesJson, "Refrigerator", "Refrigerator Model A", 2000.0, false);
        addApplianceToJsonArray(appliancesJson, "AirConditioner", "Air Conditioner Model B", 1500.0, true);
        addApplianceToJsonArray(appliancesJson, "Fan", "Fan Model C", 100.0, false);
        addApplianceToJsonArray(appliancesJson, "WaterHeater", "Water Heater Model D", 500.0, false);
        addApplianceToJsonArray(appliancesJson, "Humidifier", "Humidifier Model E", 75.0, false);

        return appliancesJson.toString();
    }

    private static void addApplianceToJsonArray(JSONArray appliancesJson, String type, String modelName, double price, boolean isBluetoothEnabled) {
        LocalDate productionDate = LocalDate.now();
        LocalDate expiryDate = LocalDate.now().plusYears(5);

        Commodity appliance = createAppliance(type, modelName, price, productionDate, expiryDate, isBluetoothEnabled);
        if (appliance != null) {
            JSONObject applianceJson = appliance.toJson();
            appliancesJson.put(applianceJson);
        }
    }
}



