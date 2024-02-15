package Shop;


import java.time.LocalDate;
import java.util.List;

public class FestivalDiscount {

    public static double calculateDiscountedPrice(List<Commodity> items, LocalDate date) {
        double totalPrice = items.stream().mapToDouble(Commodity::getPrice).sum();
        double discountRate = getDiscountRateForDate(date);

        return totalPrice * discountRate;
    }

    private static double getDiscountRateForDate(LocalDate date) {

        if (date.equals(LocalDate.of(date.getYear(), 11, 11))) {
            return 0.7;
        } else if (date.equals(LocalDate.of(date.getYear(), 12, 12))) {
            return 0.8;
        } else if (date.equals(LocalDate.of(date.getYear(), 10, 24))) {
            return 0.9;
        } else if (date.equals(LocalDate.of(date.getYear(), 1, 1))) {
            return 0.8;
        } else if (date.getMonthValue() == 10 && date.getDayOfMonth() <= 7) {
            return 0.8;
        }
        return 1.0;
    }
}