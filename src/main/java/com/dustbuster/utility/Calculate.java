package com.dustbuster.utility;

import static com.dustbuster.repository.entity.CleaningName.BATH;
import static com.dustbuster.repository.entity.CleaningName.RADIATOR;
import static com.dustbuster.repository.entity.CleaningName.WC;

import com.dustbuster.dto.Bathrooms;
import com.dustbuster.dto.CalculateQuotation;
import com.dustbuster.dto.CleaningArea;
import com.dustbuster.dto.Radiator;
import com.dustbuster.dto.RadiatorType;
import com.dustbuster.dto.response.CalculateResponse;
import com.dustbuster.repository.entity.Discount;
import com.dustbuster.repository.entity.PriceList;
import java.math.BigDecimal;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Calculate {

  public static CalculateResponse calculateResponse(
      CleaningArea cleaningArea, List<PriceList> priceList, BigDecimal discountValue) {

    BigDecimal sumRadiator = BigDecimal.ZERO;
    BigDecimal sumBathRoom = BigDecimal.ZERO;
    BigDecimal sumWc = BigDecimal.ZERO;
    BigDecimal total = BigDecimal.ZERO;

    List<PriceList> radiatorPriceList =
        priceList.stream().filter(x -> x.getName().equals(RADIATOR)).toList();
    List<PriceList> bathRoomPriceList =
        priceList.stream().filter(x -> x.getName().equals(BATH)).toList();
    List<PriceList> wcPriceList = priceList.stream().filter(x -> x.getName().equals(WC)).toList();

    for (var price : radiatorPriceList) {
      for (var value : cleaningArea.getRadiatorList()) {
        sumRadiator = sumRadiator.add(getBigDecimal(value, price));
      }
    }

    for (var price : bathRoomPriceList) {
      for (var value : cleaningArea.getBathroomsList()) {
        sumBathRoom = sumBathRoom.add(getBigDecimal(value, price));
      }
    }

    for (var price : wcPriceList) {
      for (var value : cleaningArea.getWc()) {
        sumWc = sumWc.add(getBigDecimal(value, price));
      }
    }

    total = total.add(sumBathRoom);
    total = total.add(sumRadiator);
    total = total.add(sumWc);

    return CalculateResponse.builder()
        .calculateQuotation(
            CalculateQuotation.builder()
                .radiator(sumRadiator)
                .bathRoom(sumBathRoom)
                .wc(sumWc)
                .discount(discountValue)
                .total(discountValue != null ? total.subtract(discountValue) : total)
                .build())
        .build();
  }

  public static List<PriceList> priceList() {
    return List.of(
        PriceList.builder().name(RADIATOR).type("SMALL").price(BigDecimal.valueOf(5)).build(),
        PriceList.builder().name(RADIATOR).type("MEDIUM").price(BigDecimal.valueOf(10)).build(),
        PriceList.builder().name(RADIATOR).type("BIG").price(BigDecimal.valueOf(15)).build(),
        PriceList.builder().name(RADIATOR).type("DUSAKABIN").price(BigDecimal.valueOf(10)).build(),
        PriceList.builder().name(BATH).type("KUVET").price(BigDecimal.valueOf(15)).build(),
        PriceList.builder().name(WC).type("WC").price(BigDecimal.valueOf(15)).build());
  }

  public static List<Discount> discountList() {
    return List.of(
        Discount.builder().discountCode("123x").discountValue(BigDecimal.valueOf(10)).build(),
        Discount.builder().discountCode("123q").discountValue(BigDecimal.valueOf(20)).build());
  }

  private static BigDecimal getBigDecimal(Bathrooms bathrooms, PriceList priceList2) {
    BigDecimal val = BigDecimal.ZERO;
    if (priceList2.getType().equals(bathrooms.getBathroomsType().name())) {
      int count = bathrooms.getBathroomCount();
      int level = bathrooms.getDirtyLevel();
      if (level >= 4) {
        val =
            priceList2
                .getPrice()
                .multiply(BigDecimal.valueOf(count).multiply(BigDecimal.valueOf(1.2)));
      } else {
        val = priceList2.getPrice().multiply(BigDecimal.valueOf(count));
      }
    }
    return val;
  }

  private static BigDecimal getBigDecimal(com.dustbuster.dto.WC wc, PriceList priceList2) {
    BigDecimal val = BigDecimal.ZERO;
    if (priceList2.getType().equals("WC")) {
      int count = wc.getCountWC();
      int level = wc.getDirtyLevel();
      if (level >= 3) {
        val =
            priceList2
                .getPrice()
                .multiply(BigDecimal.valueOf(count).multiply(BigDecimal.valueOf(1.2)));
      } else {
        val = priceList2.getPrice().multiply(BigDecimal.valueOf(count));
      }
    }
    return val;
  }

  public static void main(String[] args) {
    List<PriceList> priceList1 =
        List.of(
            PriceList.builder().name(RADIATOR).type("SMALL").price(BigDecimal.valueOf(1)).build(),
            PriceList.builder().name(RADIATOR).type("MEDIUM").price(BigDecimal.valueOf(2)).build(),
            PriceList.builder().name(RADIATOR).type("BIG").price(BigDecimal.valueOf(3)).build());

    List<Radiator> radiatorList =
        List.of(
            Radiator.builder()
                .radiatorType(RadiatorType.SMALL)
                .radiatorCount(10)
                .dirtyLevel(2)
                .build(),
            Radiator.builder()
                .radiatorType(RadiatorType.MEDIUM)
                .radiatorCount(20)
                .dirtyLevel(2)
                .build(),
            Radiator.builder()
                .radiatorType(RadiatorType.BIG)
                .radiatorCount(30)
                .dirtyLevel(3)
                .build());

    BigDecimal radiator = BigDecimal.ZERO;

    for (PriceList priceList2 : priceList1) {
      for (Radiator rad : radiatorList) {
        radiator = radiator.add(getBigDecimal(rad, priceList2));
      }
    }

    System.out.println(radiator);
  }

  private static BigDecimal getBigDecimal(Radiator radiator, PriceList priceList2) {
    BigDecimal val = BigDecimal.ZERO;
    if (priceList2.getType().equals(radiator.getRadiatorType().name())) {
      int count = radiator.getRadiatorCount();
      int level = radiator.getDirtyLevel();
      if (level >= 3) {
        val =
            priceList2
                .getPrice()
                .multiply(BigDecimal.valueOf(count).multiply(BigDecimal.valueOf(1.2)));
      } else {
        val = priceList2.getPrice().multiply(BigDecimal.valueOf(count));
      }
    }
    return val;
  }
}
