package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private String fileName;
    private String downloadUrl;
    private String fileType;
    private long fileSize;


}
