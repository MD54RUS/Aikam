package DTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnswerStatisticsDTO extends Answer {
  private int totalDays;
  private LocalDate startDay;
  private LocalDate endDay;
  private List<CustomersDTO> customers;
  private int totalCost;

  public AnswerStatisticsDTO(LocalDate startDay, LocalDate endDay) {
    this.startDay = startDay;
    this.endDay = endDay;
    type = "stat";
    totalDays = daysBetween(startDay, endDay);
  }

  public AnswerStatisticsDTO(LocalDate startDay, LocalDate endDay, List<CustomersDTO> customers) {
    this.startDay = startDay;
    this.endDay = endDay;
    type = "stat";
    totalDays = daysBetween(startDay, endDay);
    this.customers = customers;
  }

  public int getTotalDays() {
    return totalDays;
  }

  public List<CustomersDTO> getCustomers() {
    return customers;
  }

  public void setCustomers(List<CustomersDTO> customers) {
    this.customers = customers;
  }

  public void addCustomer(CustomersDTO customer) {
    customers.add(customer);
  }

  private int daysBetween(final LocalDate start, final LocalDate end) {
    int days = (int) ChronoUnit.DAYS.between(start, end);
    List<DayOfWeek> ignore = new ArrayList<>();
    ignore.add(DayOfWeek.SATURDAY);
    ignore.add(DayOfWeek.SUNDAY);

    if (days == 0) {
      return 0;
    }

    if (!ignore.isEmpty()) {
      int startDay = start.getDayOfWeek().getValue();
      int endDay = end.getDayOfWeek().getValue();
      int diff = days / 7 * ignore.size();

      for (DayOfWeek day : ignore) {
        int currDay = day.getValue();
        if (startDay <= currDay) {
          diff++;
        }
        if (endDay > currDay) {
          diff++;
        }
      }
      if (endDay > startDay) {
        diff -= endDay - startDay;
      }

      return days - diff;
    }

    return days;
  }

  public static class CustomersDTO {
    String name;
    List<GoodsDTO> purchases;
    int totalExpenses;

    public CustomersDTO(String name) {
      this.name = name;
      purchases = new LinkedList<>();
      totalExpenses = 0;
    }

    public void addPurchases(GoodsDTO purchases) {
      this.purchases.add(purchases);
      totalExpenses += purchases.expenses;
    }

    public String getName() {
      return name;
    }

    public List<GoodsDTO> getPurchases() {
      return purchases;
    }

    public int getTotalExpenses() {
      return totalExpenses;
    }
  }

  public static class GoodsDTO {
    String name;
    int expenses;

    public GoodsDTO(String name, int expenses) {
      this.name = name;
      this.expenses = expenses;
    }

    public String getName() {
      return name;
    }

    public int getExpenses() {
      return expenses;
    }
  }
}
