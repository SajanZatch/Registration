package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository regRepo;
    private ModelMapper modMap;


    //Instead of autoWired we can also use below code which is a constructor based dependency injection
    public RegistrationService(RegistrationRepository regRepo, ModelMapper modMap) {
        this.regRepo = regRepo;
        this.modMap = modMap;
    }



    public List<RegistrationDto> getRegistrations(){
        List<Registration> registrations = regRepo.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {

        //copy dto to entity
        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = regRepo.save(registration);

        //copy entity to dto
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    public void deleteRegistration(long id) {
        regRepo.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        //this will convert to Registration object
        Registration r = regRepo.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration savedEntity = regRepo.save(r);
        return savedEntity;
    }

    //copy dto to entity
    Registration mapToEntity(RegistrationDto registrationDto){
        //in bracket left one is where to take data and right is where to paste data
        Registration  registration = modMap.map(registrationDto, Registration.class);
//        Registration registration =new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());

        return registration;
    }

    //copy entity to dto
    RegistrationDto mapToDto(Registration registration){
        //in bracket left one is where to take data and right is where to paste data
        RegistrationDto dto = modMap.map(registration,RegistrationDto.class);
//        RegistrationDto dto= new RegistrationDto();
//        dto.setName(registration.getName());
//        dto.setEmail(registration.getEmail());
//        dto.setMobile(registration.getMobile());
        return dto;
    }
//this will throw a exception if the record is not found in the dataBase
    public RegistrationDto getRegistrationById(long id) {
        Registration registration = regRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Record not found"));
        return mapToDto(registration);
    }
}
