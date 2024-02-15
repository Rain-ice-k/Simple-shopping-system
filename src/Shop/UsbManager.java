package Shop;

public class UsbManager {
    public void manageUsbDevice(Commodity commodity) {
        try {
            if (commodity instanceof USB ) {
                USB usbDevice = (USB) commodity;
                usbDevice.open();
                usbDevice.read();
                usbDevice.close();
            } else {
                System.out.println("此商品不支持USB功能.");
            }
        } catch (Exception e) {
            System.out.println("USB工作时出现故障 " + e.getMessage());
        }
    }
}

