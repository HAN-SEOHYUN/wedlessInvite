package com.example.wedlessInvite.service;

import com.example.wedlessInvite.domain.Image.ImageUploads;
import com.example.wedlessInvite.domain.Invitation.*;
import com.example.wedlessInvite.dto.ImageUploadDto;
import com.example.wedlessInvite.dto.InvitationMasterRequestDto;
import com.example.wedlessInvite.dto.InvitationMasterResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class InvitationService {

    private final InvitationMasterRepository invitationMasterRepository;

    private final BrideInfoRepository brideInfoRepository;

    private final GroomInfoRepository groomInfoRepository;

    private final ImageUploadService imageUploadService;

    private final S3FileService s3FileService;

    public InvitationMaster saveInvitationMaster(MultipartFile file, InvitationMasterRequestDto data) throws IOException {

        ImageUploadDto imageDto = createImage(file);

        ImageUploads imageUploads = imageUploadService.saveFile(imageDto);
        BrideInfo brideInfo = brideInfoRepository.save(data.getBrideInfo());
        GroomInfo groomInfo = groomInfoRepository.save(data.getGroomInfo());

        data.setMainImage(imageUploads);

        return invitationMasterRepository.save(data.toEntity());
    }

    private ImageUploadDto createImage(MultipartFile file) throws IOException {
        imageUploadService.validateFileSize(file);
        imageUploadService.validateFileExtension(file);
        return s3FileService.uploadS3(file);
    }



    public Page<InvitationMasterResponseDto> getAllInvitations(Pageable pageable) {
        Page<InvitationMaster> entity = invitationMasterRepository.findAll(pageable);

        // Entity에서 DTO로 변환
        return entity.map(invitation -> InvitationMasterResponseDto.builder()
                .id(invitation.getId())
                .date(invitation.getDate())
                .brideInfo(invitation.getBrideInfo())
                .groomInfo(invitation.getGroomInfo())
                .mainImage(invitation.getMainImage())
                .letterTxt(invitation.getLetterTxt())
                .mainTxt(invitation.getMainTxt())
                .greetTxt(invitation.getGreetTxt())
                .build());
    }

    public InvitationMasterResponseDto getInvitationDetail(Long id) {
        InvitationMaster entity = invitationMasterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invitation not found for ID: " + id));

        return InvitationMasterResponseDto.builder()
                .id(entity.getId())
                .brideInfo(entity.getBrideInfo())
                .groomInfo(entity.getGroomInfo())
                .mainImage(entity.getMainImage())
                .date(entity.getDate())
                .letterTxt(entity.getLetterTxt())
                .mainTxt(entity.getMainTxt())
                .greetTxt(entity.getGreetTxt())
                .build();
    }
}
