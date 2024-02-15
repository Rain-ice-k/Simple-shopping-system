package Shop;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mall mall = new Mall();
        ShoppingCart shoppingCart = new ShoppingCart();
        CommoditySortingManager sortingManager = new CommoditySortingManager();
        UsbManager usbManager = new UsbManager();
        System.out.println("从什么中初始化商品？:");
        System.out.println("1. JSON 文件");
        System.out.println("2. 电子厂");
        System.out.print("输入你的选择: ");
        int initChoice = scanner.nextInt();
        scanner.nextLine();
        if (initChoice == 1) {
            mall.initializeCommoditiesFromFile("Goods.json");
        } else if (initChoice == 2) {
            String jsonContent = ElectricApplianceFactory.createAppliancesJson();
            mall.initializeCommoditiesFromJson(jsonContent);
        }

        Buyer buyer = new Buyer("晨", "喜马拉雅山脉", "110119120");
        List<Commodity> items;
        boolean running = true;
        System.out.println("欢迎来到购物系统");
        while (running) {
            System.out.println("1. 展示所有商品");
            System.out.println("2. 以类型搜索商品");
            System.out.println("3. 以编号搜索商品");
            System.out.println("4. 把商品加入购物车");
            System.out.println("5. 将商品移出购物车");
            System.out.println("6. 查看购物车");
            System.out.println("7. 清空购物车");
            System.out.println("8. 结账");
            System.out.println("9. 将商品排序");
            System.out.println("10.管理USB设备");
            System.out.println("11.展示商品具体信息");
            System.out.println("12.管理商品");
            System.out.println("13.查找具有蓝牙的商品");
            System.out.println("14.退出");
            System.out.print("请输入你的选择: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    mall.displayCommodities();
                    break;
                case 2:
                    Set<String> availableTypes = mall.getCommodities().stream()
                            .map(Commodity::getType)
                            .collect(Collectors.toSet());
                    System.out.println("已有的类型: " + availableTypes);
                    System.out.print("输入你要搜索的商品类型: ");
                    String type = scanner.next();
                    List<Commodity> foundByType = mall.findCommoditiesByType(type);
                    foundByType.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("输入商品编号: ");
                    int commodityId = Integer.parseInt(scanner.next());
                    Commodity commodity = mall.findCommodityById(commodityId);
                    if (commodity != null) {
                        System.out.println(commodity);
                    } else {
                        System.out.println("没有找到商品.");
                    }
                    break;
                case 4:
                    System.out.print("将商品的编号输入以增加到购物车: ");
                    int addId = Integer.parseInt(scanner.next());
                    Commodity toAdd = mall.findCommodityById(addId);
                    if (toAdd != null) {
                        shoppingCart.addCommodity(toAdd);
                        System.out.println(toAdd.getName() + "成功加入购物车.");
                    } else {
                        System.out.println("没有找到商品");
                    }
                    break;
                case 5:
                    System.out.print("请输入要移出的商品的编号: ");
                    int removeId = Integer.parseInt(scanner.next());
                    shoppingCart.removeItemById(removeId);
                    break;
                case 6:
                    System.out.println("购物车:");
                    shoppingCart.displayItems();
                    break;
                case 7:
                    shoppingCart.clearCart();
                    break;
                case 8:
                    System.out.print("请输入你的名字: ");
                    String name = scanner.next();
                    System.out.print("请输入你的地址: ");
                    String address = scanner.next();
                    System.out.print("请输入你的电话号码: ");
                    String phone = scanner.next();
                    buyer = new Buyer(name, address, phone);
                    items = shoppingCart.getItems();
                    LocalDate checkoutDate = LocalDate.now();
                    double totalPrice = FestivalDiscount.calculateDiscountedPrice(items, checkoutDate);
                    System.out.println("折扣后的总价为: " + totalPrice);
                    Order order = new Order(buyer, items);
                    String logisticsData = LogisticsCompany.generateLogisticsData(order);
                    System.out.println("物流订单: " + logisticsData);
                    shoppingCart.clearCart();
                    break;
                case 9:
                    System.out.println("以什么方式将购物车商品排序？:");
                    System.out.println("1. 商品名");
                    System.out.println("2. 类型");
                    System.out.println("3. 价格");
                    System.out.println("4. 编号");
                    System.out.println("5. 加入时间");
                    System.out.print("请输入你的选择: ");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine();
                    items = shoppingCart.getItems();
                    switch (sortChoice) {
                        case 1:
                            sortingManager.sortByName(items);
                            break;
                        case 2:
                            sortingManager.sortByType(items);
                            break;
                        case 3:
                            sortingManager.sortByPrice(items);
                            break;
                        case 4:
                            shoppingCart.sortById();
                            break;
                        case 5:
                            shoppingCart.sortByAddedDate();
                            break;
                        default:
                            System.out.println("无效的选择.");
                            break;
                    }
                    shoppingCart.displayItems();
                    break;
                case 10:
                    System.out.print("输入需要进行USB管理的商品编号 ");
                    int usbId = Integer.parseInt(scanner.next());
                    Commodity usbCommodity = mall.findCommodityById(usbId);
                    if (usbCommodity != null) {
                        usbManager.manageUsbDevice(usbCommodity);
                    } else {
                        System.out.println("商品没找到或不支持USB.");
                    }
                    break;
                case 11:
                    System.out.print("请输入你所要查看的商品的编号: ");
                    int detailId = Integer.parseInt(scanner.next());
                    Commodity detailCommodity = mall.findCommodityById(detailId);
                    if (detailCommodity != null) {
                        System.out.println("商品名: " + detailCommodity.getName());
                        System.out.println("生产日期: " + detailCommodity.getProductionDate());
                        System.out.println("保质期: " + detailCommodity.getExpiryDate());
                        System.out.println("有蓝牙与否: " + detailCommodity.hasBluetooth());
                    } else {
                        System.out.println("没有找到商品.");
                    }
                    break;
                case 12:
                    Commodity newCommodity = CommodityFactory.createCommodity(scanner);
                    if (newCommodity != null) {
                        mall.addCommodity(newCommodity);
                        System.out.println("商品已成功添加到商城。");
                    } else {
                        System.out.println("商品添加失败。");
                    }
                    break;

                case 13:
                    List<Commodity> bluetoothCommodities = mall.findBlueTooth();
                    if (bluetoothCommodities.isEmpty()) {
                        System.out.println("没有找到具有蓝牙功能的商品。");
                    } else {
                        bluetoothCommodities.forEach(System.out::println);
                    }
                    break;
                case 14:
                    running = false;
                    System.out.println("系统关闭，谢谢惠顾!");
                    break;
                default:
                    System.out.println("无效的选择，请再次选择");
                    break;
            }
            }
            scanner.close();
            System.out.println("谢谢惠顾!");
        }
    }