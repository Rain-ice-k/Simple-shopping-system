package Shop;

import org.json.JSONObject;

import java.time.LocalDate;

public abstract class Commodity {
    private  int id;
    private final String name;
    private final double price;
    private final LocalDate productionDate;
    private final LocalDate expiryDate;
    private LocalDate addedToCartDate;

    public Commodity(String name, double price, LocalDate productionDate, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
    }

    public LocalDate getAddedToCartDate() {
        return addedToCartDate;
    }

    public void setAddedToCartDate(LocalDate addedToCartDate) {
        this.addedToCartDate = addedToCartDate;
    }

    public int getId() { return id; }
    public void setId(int id){
        this.id=id;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public LocalDate getProductionDate() { return productionDate; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public abstract String getType();
    public abstract boolean hasBluetooth();
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", getType());
        json.put("price", price);
        json.put("productionDate", productionDate.toString());
        json.put("expiryDate", expiryDate.toString());
        return json;
    }
    public String toString() {
        return "编号: "+id +", 商品名: " + name + ", 价格: " + price + ", 生产日期: " + productionDate + ", 保质期: " + expiryDate;
    }
}

class Electronics extends Commodity implements USB {
    private final boolean isBluetoothEnabled;

    public Electronics(String name, double price, LocalDate productionDate, LocalDate expiryDate, boolean isBluetoothEnabled) {
        super(name, price, productionDate, expiryDate);
        this.isBluetoothEnabled = isBluetoothEnabled;
    }
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("isBluetoothEnabled", isBluetoothEnabled);
        return json;
    }
    @Override
    public String getType() { return "Electronics"; }
    @Override
    public boolean hasBluetooth() { return isBluetoothEnabled; }

    @Override
    public void open() { System.out.println(getName() + " open"); }
    @Override
    public void read() { System.out.println(getName() + " read"); }
    @Override
    public void close() { System.out.println(getName() + " close"); }
    @Override
    public String toString() {
        return "Electronics - " + super.toString() + ", Bluetooth Enabled: " + isBluetoothEnabled;
    }
}

class HomeAppliance extends Commodity {
    public HomeAppliance(String name, double price, LocalDate productionDate, LocalDate expiryDate) {
        super(name, price, productionDate, expiryDate);
    }

    @Override
    public String getType() { return "HomeAppliance"; }
    @Override
    public boolean hasBluetooth() { return false; }
    @Override
    public String toString() {
        return "HomeAppliance - " + super.toString();
    }
}

class DailyGoods extends Commodity {
    public DailyGoods(String name, double price, LocalDate productionDate, LocalDate expiryDate) {
        super(name, price, productionDate, expiryDate);
    }

    @Override
    public String getType() { return "DailyGoods"; }
    @Override
    public boolean hasBluetooth() { return false; }
    @Override
    public String toString() {
        return "DailyGoods - " + super.toString();
    }
}
class Foods extends Commodity{
    public Foods(String name, double price, LocalDate productionDate, LocalDate expiryDate) {
        super(name, price, productionDate, expiryDate);
    }

    @Override
    public String getType() {
        return "Foods";
    }
    public boolean hasBluetooth(){
        return false;
    }
    @Override
    public String toString() {
        return "Foods - " + super.toString();
    }
}
class Refrigerator extends Commodity {
    private final boolean hasFreezer;

    public Refrigerator(String name, double price, LocalDate productionDate, LocalDate expiryDate, boolean hasFreezer) {
        super(name, price, productionDate, expiryDate);
        this.hasFreezer = hasFreezer;
    }

    @Override
    public String getType() {
        return "Refrigerator";
    }

    @Override
    public boolean hasBluetooth() {
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("name", getName());
        return json;
    }
    @Override
    public String toString() {
        return "Electronics - Refrigerator - " + super.toString() + ", Has Freezer: " + hasFreezer;
    }
}
class AirConditioner extends Commodity {
    private final boolean hasInverter;

    public AirConditioner(String name, double price, LocalDate productionDate, LocalDate expiryDate, boolean hasInverter) {
        super(name, price, productionDate, expiryDate);
        this.hasInverter = hasInverter;
    }

    @Override
    public String getType() {
        return "AirConditioner";
    }

    @Override
    public boolean hasBluetooth() {
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("name", getName());
        return json;
    }
    @Override
    public String toString() {
        return "Electronics - AirConditioner - " + super.toString() + ", Has Inverter: " + hasInverter;
    }
}
class Fan extends Commodity {
    private final int speedLevels;

    public Fan(String name, double price, LocalDate productionDate, LocalDate expiryDate, int speedLevels) {
        super(name, price, productionDate, expiryDate);
        this.speedLevels = speedLevels;
    }

    @Override
    public String getType() {
        return "Fan";
    }

    @Override
    public boolean hasBluetooth() {
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("speedLevels", speedLevels);
        return json;
    }

    @Override
    public String toString() {
        return "Fan - " + super.toString() + ", Speed Levels: " + speedLevels;
    }
}

class WaterHeater extends Commodity {
    private final double tankCapacity;

    public WaterHeater(String name, double price, LocalDate productionDate, LocalDate expiryDate, double tankCapacity) {
        super(name, price, productionDate, expiryDate);
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String getType() {
        return "WaterHeater";
    }

    @Override
    public boolean hasBluetooth() {
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("name", getName());
        return json;
    }

    @Override
    public String toString() {
        return "WaterHeater - " + super.toString() + ", Tank Capacity: " + tankCapacity + " liters";
    }
}

class Humidifier extends Commodity {
    private final double waterTankVolume;

    public Humidifier(String name, double price, LocalDate productionDate, LocalDate expiryDate, double waterTankVolume) {
        super(name, price, productionDate, expiryDate);
        this.waterTankVolume = waterTankVolume;
    }

    @Override
    public String getType() {
        return "Humidifier";
    }

    @Override
    public boolean hasBluetooth() {
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("name", getName());
        return json;
    }

    @Override
    public String toString() {
        return "Humidifier - " + super.toString() + ", Water Tank Volume: " + waterTankVolume + " liters";
    }
}
