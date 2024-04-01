package com.ted.milanopizza.controller;

import com.ted.milanopizza.model.Zipcode;
import com.ted.milanopizza.repository.ZipcodeRepository;
import com.ted.milanopizza.service.ZipcodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")

@Slf4j
@AllArgsConstructor
@RestController
public class ZipcodeController {

    @Autowired
    private ZipcodeRepository zipcodeRepository;
    private final ZipcodeService zipcodeService;

    // get all zipcodes
    @GetMapping("/zipcode")
    public ResponseEntity<List<Zipcode>> getAllZipcodes() {
        try {
            List<Zipcode> zipcodeList = new ArrayList<>(zipcodeRepository.findAll());

            if (zipcodeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(zipcodeList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/zipcode/{id}")
    public ResponseEntity<Zipcode> getZipcodeById(@PathVariable Long id) {
        Optional<Zipcode> zipcodeData = zipcodeRepository.findById(id);

        if (zipcodeData.isPresent()) {

            return new ResponseEntity<>(zipcodeData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/zipcode")
    public ResponseEntity<Zipcode> addZipcode(@RequestBody Zipcode zipcode) {

        Zipcode zipcodeObj = zipcodeRepository.save(zipcode);

        return new ResponseEntity<>(zipcodeObj, HttpStatus.OK);
    }

    @PutMapping("/zipcode/{id}")
    public ResponseEntity<Map<String, String>> updateZipcodeById(@PathVariable Long id, @RequestBody Zipcode newZipcodeData) {
        try {
            zipcodeService.updateZipcode(id, newZipcodeData);
            return new ResponseEntity<>(Map.of("Success", "Zipcode Updated!"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("### ERROR: {} not processed, {}", id, e.getMessage());
            return new ResponseEntity<>(Map.of("error", "Zipcode not updated"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/zipcode/{id}")
    public ResponseEntity<HttpStatus> deleteZipcodeById(@PathVariable Long id) {
        zipcodeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
