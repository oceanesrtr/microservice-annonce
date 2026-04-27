package ca.uqtr.projet.annonces.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private final Cloudinary cloudinary;

    public PhotoController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // Upload plusieurs photos d'un coup, retourne leurs URLs
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadPhotos(
            @RequestParam("files") List<MultipartFile> files) throws IOException {

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            // Valider le type MIME
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().build();
            }

            // Upload vers Cloudinary dans le dossier "lokaquebec"
            Map result = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "lokaquebec",
                            "resource_type", "image",
                            "transformation", "q_auto,f_auto,w_1200"  // compression auto
                    )
            );
            urls.add((String) result.get("secure_url"));
        }

        return ResponseEntity.ok(urls);
    }
}