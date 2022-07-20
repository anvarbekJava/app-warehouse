package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.appwarehouse.domain.Attachment;
import uz.pdp.appwarehouse.domain.AttachmentContent;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ResponseData;
import uz.pdp.appwarehouse.service.impl.AttachmentService;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public HttpEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downloadURL = "";
        attachment = attachmentService.saveAttachment(file);
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download/")
                .path(attachment.getId()).toUriString();
        ResponseData responseData =  new ResponseData(
                attachment.getFileName(),
                downloadURL,
                file.getContentType(),
                file.getSize(),
                attachment.getId()
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment  = null;
        attachment = attachmentService.getAttachment(fileId);
        AttachmentContent attachmentContent = attachmentService.getAttachmentContent(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; fileName=\""+attachment.getFileName()
                                +"\"")
                .body(new ByteArrayResource(attachmentContent.getData()));
    }

}
