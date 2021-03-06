package ru.javaschool.mobileoperator.utils;

import ru.javaschool.mobileoperator.domain.Address;
import ru.javaschool.mobileoperator.domain.Bill;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.dto.AddressDto;
import ru.javaschool.mobileoperator.domain.dto.BillDto;
import ru.javaschool.mobileoperator.domain.dto.ContractDto;
import ru.javaschool.mobileoperator.domain.dto.CustomerDto;
import ru.javaschool.mobileoperator.domain.dto.OptionDto;
import ru.javaschool.mobileoperator.domain.dto.PhoneNumberDto;
import ru.javaschool.mobileoperator.domain.dto.TariffPlanDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with util methods for entity conversion to dto
 */
public final class DtoConverter {

    private DtoConverter(){

    }

    /**
     * Method to convert option entity to option dto without relation lists
     * @param option option entity
     * @return option dto
     */
    public static OptionDto toOptionDtoWithoutLists(Option option){
        return new OptionDto(option.getId(), option.getName(), option.getPrice(), option.getConnectionCost());
    }

    /**
     * Method to convert option entity to option dto with relation lists
     * @param option option entity
     * @return option dto
     */
    public static OptionDto toOptionDtoWithLists(Option option){
        List<ContractDto> contractDtos = new ArrayList<>();
        option.getContracts().forEach(contract -> contractDtos.add(toContractDtoWithoutLists(contract)));

        List<TariffPlanDto> tariffPlanDtos = new ArrayList<>();
        option.getTariffPlans().forEach(tariffPlan -> tariffPlanDtos.add(toTariffDtoWithoutLists(tariffPlan)));

        List<OptionDto> inclusiveOptions = new ArrayList<>();
        option.getInclusiveOptions().forEach(option1 -> inclusiveOptions.add(toOptionDtoWithoutLists(option1)));

        List<OptionDto> parentInclusive = new ArrayList<>();
        option.getParentInclusive().forEach(option1 -> parentInclusive.add(toOptionDtoWithoutLists(option1)));

        List<OptionDto> exclusiveOptions = new ArrayList<>();
        option.getExclusiveOptions().forEach(option1 -> exclusiveOptions.add(toOptionDtoWithoutLists(option1)));

        OptionDto result = new OptionDto(option.getId(), option.getName(), option.getPrice(), option.getConnectionCost(),
                contractDtos, tariffPlanDtos);
        result.setExclusiveOptions(exclusiveOptions);
        result.setInclusiveOptions(inclusiveOptions);
        result.setParentInclusive(parentInclusive);
        return result;
    }

    /**
     * Method to convert contract entity to contract dto with relation lists
     * @param contract contract entity
     * @return contract dto
     */
    public static ContractDto toContractDtoWithLists(Contract contract){
        List<OptionDto> optionDtos = new ArrayList<>();
        contract.getOptions().forEach(option -> optionDtos.add(toOptionDtoWithoutLists(option)));
        return new ContractDto(contract.getId(), contract.getBalance(), contract.isLocked(), contract.isLockedByUser(),
                toCustomerDtoWithoutLists(contract.getCustomer()), toPhoneNumberDtoWithIdAndNumber(contract.getPhoneNumber()),
                toTariffDtoWithoutLists(contract.getTariffPlan()), optionDtos);
    }

    /**
     * Method to convert contract entity to contract dto without relation lists
     * @param contract
     * @return
     */
    public static ContractDto toContractDtoWithoutLists(Contract contract){
        return new ContractDto(contract.getId(), contract.getBalance(), contract.isLocked(), contract.isLockedByUser(),
                toCustomerDtoWithoutLists(contract.getCustomer()), toPhoneNumberDtoWithIdAndNumber(contract.getPhoneNumber()),
                toTariffDtoWithoutLists(contract.getTariffPlan()));
    }

    /**
     * Method to convert phone number entity to phone number dto
     * @param phoneNumber phone number entity
     * @return phone number dto
     */
    public static PhoneNumberDto toPhoneNumberDto(PhoneNumber phoneNumber){
        return new PhoneNumberDto(phoneNumber.getId(), phoneNumber.getNumber(),
                toContractDtoWithoutLists(phoneNumber.getContract()));
    }

    /**
     * Method to convert phone number entity to phone number dto without contract
     * @param phoneNumber phone number entity
     * @return phone number dto
     */
    public static PhoneNumberDto toPhoneNumberDtoWithIdAndNumber(PhoneNumber phoneNumber){
        return new PhoneNumberDto(phoneNumber.getId(), phoneNumber.getNumber());
    }

    /**
     * Method to convert tariff plan entity to tariff plan dto with relation lists
     * @param tariffPlan tariff plan entity
     * @return tariff plan dto
     */
    public static TariffPlanDto toTariffDtoWithLists(TariffPlan tariffPlan) {
        List<OptionDto> optionDtos = new ArrayList<>();
        tariffPlan.getOptions().forEach(option -> optionDtos.add(toOptionDtoWithoutLists(option)));
        return new TariffPlanDto(tariffPlan.getId(), tariffPlan.getPrice(), tariffPlan.getName(), tariffPlan.isArchival(),
                optionDtos);
    }

    /**
     * Method to convert tariff plan entity to tariff plan dto without relation lists
     * @param tariffPlan tariff plan entity
     * @return tariff plan dto
     */
    public static TariffPlanDto toTariffDtoWithoutLists(TariffPlan tariffPlan){
        return new TariffPlanDto(tariffPlan.getId(), tariffPlan.getPrice(), tariffPlan.getName(), tariffPlan.isArchival());
    }

    public static TariffPlan dtoToTariffPlanWithoutLists(TariffPlanDto dto){
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setName(dto.getName());
        tariffPlan.setPrice(dto.getPrice());
        tariffPlan.setArchival(dto.isArchival());
        return tariffPlan;
    }

    /**
     * Method to convert customer entity to customer dto with relation lists
     * @param customer customer entity
     * @return customer dto
     */
    public static CustomerDto toCustomerDtoWithLists(Customer customer){
        List<ContractDto> contractDtos = new ArrayList<>();
        customer.getContracts().forEach(contract -> contractDtos.add(toContractDtoWithoutLists(contract)));
        AddressDto addressDto = null;
        if(customer.getAddress() != null){
            addressDto = toAddressDto(customer.getAddress());
        }
        return new CustomerDto(customer.getId(), addressDto, customer.getFirstName(),
                customer.getLastName(), customer.getBirthDate(), customer.getEmail(), customer.getPassport(),
                contractDtos, customer.getUsers());
    }

    /**
     * Method to convert customer entity to customer dto without relation lists
     * @param customer customer entity
     * @return customer dto
     */
    public static CustomerDto toCustomerDtoWithoutLists(Customer customer){
        AddressDto addressDto = null;
        if(customer.getAddress() != null){
            addressDto = toAddressDto(customer.getAddress());
        }
        return new CustomerDto(customer.getId(), addressDto, customer.getFirstName(),
                customer.getLastName(), customer.getBirthDate(), customer.getEmail(), customer.getPassport());
    }

    /**
     * Method to convert address embeddable entity to address dto
     * @param address address embeddable entity
     * @return address dto
     */
    public static AddressDto toAddressDto(Address address){
        return new AddressDto(address.getCity(), address.getStreet(), address.getHouseNumber());
    }

    /**
     * Method to convert customer dto to customer entity without any relation user
     * @param customerDto customer dto
     * @return customer entity
     */
    public static Customer dtoToCustomerWithoutUsers(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setEmail(customerDto.getEmail());
        customer.setPassport(customerDto.getPassport());
        if(customerDto.getAddress() != null){
            customer.setAddress(dtoToAddress(customerDto.getAddress()));
        }
        return customer;
    }

    /**
     * Method to convert address dto to address embeddable entity
     * @param dto address dto
     * @return address entity
     */
    public static Address dtoToAddress(AddressDto dto){
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        return address;
    }

    /**
     * Method to convert bill entity to bill dto
     * @param bill bill entity
     * @return bill dto
     */
    public static BillDto billToBillDto(Bill bill){
        BillDto dto = new BillDto(bill.getId(), toContractDtoWithoutLists(bill.getContract()),
                bill.getBalance(), bill.getDifference(), bill.getDate());
        dto.setOperation(bill.getOperation());
        return dto;
    }

    /**
     * Method to convert bill entity to bill dto without contract
     * @param bill bill entity
     * @return bill dto
     */
    public static BillDto billToBillDtoWithoutContract(Bill bill){
        return new BillDto(bill.getId(), bill.getBalance(), bill.getDifference(), bill.getDate(), bill.getOperation());
    }
}
