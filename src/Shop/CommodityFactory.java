package Shop;

import java.util.Scanner;
import java.time.LocalDate;
public class CommodityFactory {
    private static final Scanner scanner = new Scanner(System.in);
    public static Commodity createCommodity(Scanner scanner) {
        System.out.println("请输入商品类型（例如：Electronics, HomeAppliance, DailyGoods, Foods, Refrigerator, AirConditioner等）：");
        String type = scanner.next();

        System.out.println("请输入商品的名称：");
        String name = scanner.next();
        System.out.println("请输入商品的价格：");
        double price = scanner.nextDouble();
        LocalDate productionDate = getDateInput("请输入生产日期 (格式: yyyy-MM-dd): ");
        LocalDate expiryDate = getDateInput("请输入保质期 (格式: yyyy-MM-dd): ");
        while (expiryDate.isBefore(productionDate)) {
            System.out.println("保质期必须在生产日期之后，请重新输入保质期。");
            expiryDate = getDateInput("请输入保质期 (格式: yyyy-MM-dd): ");
        }
        boolean isBluetoothEnabled = false;
        if (type.equals("Electronics")) {
            System.out.println("该电子产品是否具备蓝牙功能？(true/false)");
            isBluetoothEnabled = scanner.nextBoolean();
        }

        switch (type) {
            case "Electronics":
                return new Electronics(name, price, productionDate, expiryDate, isBluetoothEnabled);
            case "HomeAppliance":
                return new HomeAppliance(name, price, productionDate, expiryDate);
            case "DailyGoods":
                return new DailyGoods(name, price, productionDate, expiryDate);
            case "Foods":
                return new Foods(name, price, productionDate, expiryDate);
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
                throw new IllegalArgumentException("未知的商品类型: " + type);
        }
    }

    private static LocalDate getDateInput(String prompt) {
        LocalDate date;
        while (true) {
            try {
                System.out.print(prompt);
                String dateString = scanner.nextLine();
                date = LocalDate.parse(dateString);
                break;
            } catch (Exception e) {
                System.out.println("无效的日期格式，请重新输入。");
            }
        }
        return date;
    }
}
