package com.example.wedlessInvite.controller.api;

import com.example.wedlessInvite.domain.Invitation.InvitationMaster;
import com.example.wedlessInvite.dto.InvitationMasterDto;
import com.example.wedlessInvite.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/invitations")
@RequiredArgsConstructor
public class InvitationController {
    private final InvitationService invitationService;

    @PostMapping
    public ResponseEntity<InvitationMaster> createInvitation(@Valid @RequestBody InvitationMasterDto request) {
        InvitationMaster savedInvitation = invitationService.saveInvitationMaster(request);
        return ResponseEntity.ok(savedInvitation);
    }
}
