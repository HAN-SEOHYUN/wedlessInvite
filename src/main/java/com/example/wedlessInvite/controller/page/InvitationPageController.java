package com.example.wedlessInvite.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invitations")
public class InvitationPageController {

    @GetMapping("/create")
    public String showCreatePage() {
        return "invitation/create"; // resources/templates/invitation/create.html
    }
}
