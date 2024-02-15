package Shop;

public class Buyer {
    private String name;
    private String address;
    private String phoneNumber;

    public Buyer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "购物人姓名: " + name + ", 地址: " + address + ", 电话号码: " + phoneNumber;
    }
}
