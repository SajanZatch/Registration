package com.api.controller;


import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//we are using restController because here we are creating backend api which don't need view/frontend
//@Controller is used to navigate the MVC architecture but here we don't need view so we use restController

//http://localhost:8080/api/v1/registration

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {




    private RegistrationService regSer;

    //below is a example of constructor based dependency injection .
    // Instead of this we can also use auto wired
    public RegistrationController(RegistrationService regSer) {
        this.regSer = regSer;
    }

    //with the help of GetMapping all the java Objects data that comes from database converted to json objects
    @GetMapping
    //here and below all the return types is ResponseEntity because we have to check the status in Postman app
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
        List<RegistrationDto> dtos = regSer.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);


    }

    //postMappig is used to insert data into database
    @PostMapping
    //if a method returns multiple types of data then we should use ? or Object instead of class name
    public ResponseEntity<?> createRegistration(
    //RequestBody will take data that we have entered in postman json and paste it into the object(here the object is registration)
      //To use Spring validation then @valid  annotation should be used
    @Valid @RequestBody RegistrationDto registrationDto,
    //If any error occurs during validation then the error will store to BindingResult
    BindingResult result
   ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
        }
        RegistrationDto regDto = regSer.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }


    //deleteMapping will help us to delete data from dataBase
    @DeleteMapping
    public ResponseEntity<String>  deleteRegistration(
            //requestParam will read the value from url and initialize the value
           @RequestParam long id
    ){
        regSer.deleteRegistration(id);
        return  new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    //putMapping will help us to update the data in dataBase via API
    // to get the Id number from the PathVariable below we have to give {id} to get the id
    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(
    //here we use @PathVariable Instead of RequestParam because we want to learn more annotation
    // by using pathVariable we can just give the id number by / in postman unlike RequestParam to write ?id=
            @PathVariable long id,
            @RequestBody Registration registration
    ){
        Registration updateReg = regSer.updateRegistration(id,registration);
        return new ResponseEntity<>(updateReg,HttpStatus.OK);
    }

    //this getMapping will fetch different url because it will find the data based on id
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(
            @PathVariable long id
    ){
        RegistrationDto dto =regSer.getRegistrationById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
