package com.dustbuster.service;

import static com.dustbuster.repository.entity.CleaningName.BATH;
import static com.dustbuster.repository.entity.CleaningName.RADIATOR;
import static com.dustbuster.repository.entity.CleaningName.WC;
import static com.dustbuster.repository.entity.ProgressStatus.PENDING;

import com.dustbuster.dto.request.CalculateQuotationRequest;
import com.dustbuster.dto.request.CleaningServiceRequest;
import com.dustbuster.dto.response.CalculateResponse;
import com.dustbuster.dto.response.CleaningServiceResponse;
import com.dustbuster.repository.entity.Address;
import com.dustbuster.repository.entity.AvailableSlot;
import com.dustbuster.repository.entity.CalculateQuotation;
import com.dustbuster.repository.entity.CleaningArea;
import com.dustbuster.repository.entity.Discount;
import com.dustbuster.repository.entity.DiscountRepository;
import com.dustbuster.repository.entity.PriceList;
import com.dustbuster.repository.entity.PriceListRepository;
import com.dustbuster.repository.entity.RequestForm;
import com.dustbuster.repository.entity.RequestFromRepository;
import com.dustbuster.repository.entity.Type;
import com.dustbuster.utility.Calculate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CleaningServiceImpl implements CleaningService {

  @Autowired private PriceListRepository priceListRepository;
  @Autowired private DiscountRepository discountRepository;
  @Autowired private RequestFromRepository requestFromRepository;

  @Override
  public CleaningServiceResponse requestForm(CleaningServiceRequest cleaningServiceRequest) {
    if (priceListRepository.count() == 0) {
      priceListRepository.saveAll(Calculate.priceList());
    }

    if (discountRepository.count() == 0) {
      discountRepository.saveAll(Calculate.discountList());
    }
    List<CleaningArea> cleaningAreaList = new ArrayList<>();
    List<PriceList> priceList = priceListRepository.findAll();
    BigDecimal discount =
        getDiscount(cleaningServiceRequest.getCleaningArea().getDiscountCode(), true);
    CalculateResponse calculateResponse =
        Calculate.calculateResponse(cleaningServiceRequest.getCleaningArea(), priceList, discount);

    RequestForm requestForm =
        RequestForm.builder()
            .fullName(cleaningServiceRequest.getFullName())
            .email(cleaningServiceRequest.getEmail())
            .phone(cleaningServiceRequest.getPhone())
            .howDidUHearUs(cleaningServiceRequest.getHowDidUHearUs())
            .typeOfResidence(cleaningServiceRequest.getTypeOfResidence())
            .address(Address.builder().build())
            .additionalComments(cleaningServiceRequest.getAdditionalComments())
            .requestDateTime(LocalDateTime.now())
            .progressStatus(PENDING)
            .updateDateTime(LocalDateTime.now())
            .build();

    var address =
        Address.builder()
            .buildingNumber(cleaningServiceRequest.getAddress().getBuildingNumber())
            .line1(cleaningServiceRequest.getAddress().getLine1())
            .line2(cleaningServiceRequest.getAddress().getLine2())
            .line3(cleaningServiceRequest.getAddress().getLine3())
            .line4(cleaningServiceRequest.getAddress().getLine4())
            .city(cleaningServiceRequest.getAddress().getCity())
            .county(cleaningServiceRequest.getAddress().getCounty())
            .postcode(cleaningServiceRequest.getAddress().getPostcode())
            .country(cleaningServiceRequest.getAddress().getCountry())
            .requestForm(requestForm)
            .build();
    var availableSlot =
        AvailableSlot.builder()
            .availableDate(cleaningServiceRequest.getAvailableSlot().getAvailableDate())
            .availableTime(cleaningServiceRequest.getAvailableSlot().getAvailableTime())
            .requestForm(requestForm)
            .build();

    cleaningServiceRequest
        .getCleaningArea()
        .getRadiatorList()
        .forEach(
            x ->
                cleaningAreaList.add(
                    CleaningArea.builder()
                        .cleaningName(RADIATOR)
                        .type(Type.valueOf(x.getRadiatorType().name()))
                        .count(x.getRadiatorCount())
                        .dirtyLevel(x.getDirtyLevel())
                        .requestForm(requestForm)
                        .build()));
    cleaningServiceRequest
        .getCleaningArea()
        .getBathroomsList()
        .forEach(
            x ->
                cleaningAreaList.add(
                    CleaningArea.builder()
                        .cleaningName(BATH)
                        .type(Type.valueOf(x.getBathroomsType().name()))
                        .count(x.getBathroomCount())
                        .dirtyLevel(x.getDirtyLevel())
                        .requestForm(requestForm)
                        .build()));

    cleaningServiceRequest
        .getCleaningArea()
        .getWc()
        .forEach(
            x ->
                cleaningAreaList.add(
                    CleaningArea.builder()
                        .cleaningName(WC)
                        .type(Type.valueOf("WC"))
                        .count(x.getCountWC())
                        .dirtyLevel(x.getDirtyLevel())
                        .requestForm(requestForm)
                        .build()));
    CalculateQuotation calculateQuotation =
        CalculateQuotation.builder()
            .radiator(calculateResponse.getCalculateQuotation().getRadiator())
            .bathRoom(calculateResponse.getCalculateQuotation().getBathRoom())
            .wc(calculateResponse.getCalculateQuotation().getWc())
            .discount(calculateResponse.getCalculateQuotation().getDiscount())
            .total(calculateResponse.getCalculateQuotation().getTotal())
            .requestForm(requestForm)
            .build();

    requestForm.setAddress(address);
    requestForm.setAvailableSlot(availableSlot);
    requestForm.setCleaningArea(cleaningAreaList);
    requestForm.setCalculateQuotation(calculateQuotation);
    RequestForm requestFormResult = requestFromRepository.save(requestForm);

    ModelMapper modelMapper = new ModelMapper();
    CleaningServiceResponse cleaningServiceResponse =
        modelMapper
            .map(requestFormResult, CleaningServiceResponse.class);

    cleaningServiceResponse.setCleaningArea(cleaningServiceRequest.getCleaningArea());

    return cleaningServiceResponse;
  }

  @Override
  public CalculateResponse calculateRequestForm(
      CalculateQuotationRequest calculateQuotationRequest) {
    if (priceListRepository.count() == 0) {
      priceListRepository.saveAll(Calculate.priceList());
    }

    if (discountRepository.count() == 0) {
      discountRepository.saveAll(Calculate.discountList());
    }
    List<PriceList> priceList = priceListRepository.findAll();
    BigDecimal discount =
        getDiscount(calculateQuotationRequest.getCleaningArea().getDiscountCode(), false);

    return Calculate.calculateResponse(
        calculateQuotationRequest.getCleaningArea(), priceList, discount);
  }

  private BigDecimal getDiscount(String discountCode, Boolean disCodeUpdate) {
    BigDecimal discount = null;
    if (discountCode != null) {
      Optional<Discount> discountOptional =
          discountRepository.findByDiscountCodeAndIsUsed(discountCode, false);
      if (discountOptional.isPresent()) {
        discount = discountOptional.get().getDiscountValue();
        if (Boolean.TRUE.equals(disCodeUpdate)) {
          discountOptional.get().setUsed(true);
          discountOptional.get().setUsedLocalDateTime(LocalDateTime.now());
          discountRepository.save(discountOptional.get());
        }
      }
    }
    return discount;
  }
}
