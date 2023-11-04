package org.sid.achat.controllers;

import org.sid.achat.models.AchatRequest;
import org.sid.achat.models.AchatResponse;
import org.sid.achat.services.AchatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/achat/")
public class AchatController {
    private AchatService achatService;
    public AchatController(AchatService achatService) {
        this.achatService = achatService;
    }

    @PostMapping("/add")
    public AchatResponse addAchat(@RequestBody AchatRequest achatRequest) {
        return achatService.addAchat(achatRequest);
    }

    @GetMapping("/{id}")
    public AchatResponse getOneAchat(@PathVariable("id") Long id) {
        return achatService.getOneAchat(id);
    }
}
