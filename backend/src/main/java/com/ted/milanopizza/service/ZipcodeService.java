package com.ted.milanopizza.service;

import com.ted.milanopizza.model.Zipcode;
import com.ted.milanopizza.repository.ZipcodeRepository;
import com.ted.milanopizza.util.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ZipcodeService {
    private final ZipcodeRepository zipcodeRepository;

    public void updateZipcode(String id, Zipcode newZipcodeData) {
        Optional<Zipcode> oldZipcodeData = zipcodeRepository.findById(id);
        if (oldZipcodeData.isEmpty()) {
            log.error("### ERROR: No data located for zipcode: {}", id);
            return;
        }
        Zipcode updatedZipcode = oldZipcodeData.get();
        Util.updateIfNotNull(newZipcodeData.getZipcode(), updatedZipcode::setZipcode);
        Util.updateIfNotNull(newZipcodeData.getCustomers(), updatedZipcode::setCustomers);
        Util.updateIfNotNull(newZipcodeData.getCity(), updatedZipcode::setCity);
        Util.updateIfNotNull(newZipcodeData.getState(), updatedZipcode::setState);
    }
}
