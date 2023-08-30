package com.ogutcenali.controller;

import com.ogutcenali.dto.request.ActivationCodeRequest;
import com.ogutcenali.service.ActivationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activation-code")
@RequiredArgsConstructor
public class ActivationController {

    private final ActivationCodeService activationCodeService;


    @PostMapping()
    public ResponseEntity<Void> processActivationCodeForAccount(@RequestBody ActivationCodeRequest request) {
        activationCodeService.processActivationCodeForAccount(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{token}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Void> activationUserWithLink(@PathVariable String token){
        activationCodeService.activationUserWithLink(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
