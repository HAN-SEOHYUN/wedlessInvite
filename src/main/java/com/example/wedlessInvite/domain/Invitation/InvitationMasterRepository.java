package com.example.wedlessInvite.domain.Invitation;

import com.example.wedlessInvite.common.YN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationMasterRepository extends JpaRepository<InvitationMaster, Long> {
    Page<InvitationMaster> findByDeleteYN(String deleteYN, Pageable pageable);
}
