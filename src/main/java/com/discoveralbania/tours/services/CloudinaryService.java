package com.discoveralbania.tours.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) throws IOException {

        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "tours"
                )
        );

        return uploadResult.get("secure_url").toString();
    }

    public void deleteImage(String imageUrl) throws IOException {
        String publicId = extractPublicId(imageUrl);
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
    private String extractPublicId(String imageUrl) {
        String withoutExtension = imageUrl.substring(0, imageUrl.lastIndexOf('.'));
        return withoutExtension.substring(withoutExtension.indexOf("/upload/") + 8);
    }
}