package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appwarehouse.domain.Attachment;
import uz.pdp.appwarehouse.domain.AttachmentContent;
import uz.pdp.appwarehouse.repository.AttachmentContentRepository;
import uz.pdp.appwarehouse.repository.AttachmentRepository;

import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")){
                throw  new Exception("Filename contains invalid path sequense "+fileName);
            }
            Attachment attachmentCon = new Attachment(
                    fileName,
                    file.getContentType()
                    );
            Attachment saveAttachment = attachmentRepository.save(attachmentCon);
            AttachmentContent attachmentContent = new AttachmentContent(
                    file.getBytes(),
                    saveAttachment
            );
            attachmentContentRepository.save(attachmentContent);
            return saveAttachment;

        }catch (Exception e){
            throw new Exception("Could not save File "+fileName);
        }
    }

    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with "+fileId));
    }

    public AttachmentContent getAttachmentContent(String fileId) throws Exception {
       return attachmentContentRepository.findByAttachmentId(fileId).orElseThrow(() -> new Exception("File data not foun"+fileId));
    }
}

