package com.ted.milanopizza.service;

import com.ted.milanopizza.util.Util;
import com.ted.milanopizza.model.Zipcode;
import com.ted.milanopizza.repository.ZipcodeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ZipcodeService {
    private final ZipcodeRepository zipcodeRepository;

    public Zipcode updateZipcode(Long id, Zipcode newZipcodeData) {
        Optional<Zipcode> oldZipcodeData = zipcodeRepository.findById(id);

        if (oldZipcodeData.isPresent()) {
            Zipcode updatedZipcode = oldZipcodeData.get();
            Util.updateIfNotNull(newZipcodeData.getZipcodeId(), updatedZipcode::setZipcodeId);
            Util.updateIfNotNull(newZipcodeData.getCustomers(), updatedZipcode::setCustomers);
            Util.updateIfNotNull(newZipcodeData.getCity(), updatedZipcode::setCity);
            Util.updateIfNotNull(newZipcodeData.getState(), updatedZipcode::setState);
            return updatedZipcode;
        }
        log.error("No data located for zipcode: {}", id);
        return null;
    }
}
