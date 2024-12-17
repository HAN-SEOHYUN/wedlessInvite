package com.example.wedlessInvite.domain.Post;

import com.example.wedlessInvite.domain.BaseEntity;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="IMAGE_UPLOADS")
public class ImageUploads extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IU_ID")
    private Long id;

    @Column(name="IU_FILE_NAME", nullable = false, length = 255, unique = true)
    @Comment("S3에 저장된 파일의 이름")
    private String fileName;

    @Column(name = "IU_ORG_FILE_NAME", nullable = false, length = 255)
    @Comment("업로드한 사용자가 제공한 원본 파일 이름")
    private String orgFileName;

    @Column(name="IU_S3_URL", columnDefinition = "TEXT")
    @Comment("S3에 업로드된 파일의 전체 URL")
    private String s3Url;

    @Column(name="IU_FILE_SIZE", columnDefinition = "BIGINT")
    private String fileSize;

    @Column(name="IU_DELETE_YN")
    private Boolean deleteYN;

}
