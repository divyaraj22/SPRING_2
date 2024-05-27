package com.div.util;

import com.div.pojo.FormDetail;
import com.div.pojo.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<FormDetail> parseCsvFile(MultipartFile file) throws Exception {
        List<FormDetail> formDetails = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (CSVParser csvParser = CSVFormat.DEFAULT
                .withHeader("title", "speakers", "publicURL", "videoDate", "publishedDate", "description", "synopsis", "bannerPath", "videoUrl", "previewVideoUrl", "accessCategory", "freeViewExpiry", "userId", "contentType", "isPremium")
                .withSkipHeaderRecord()
                .parse(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            for (CSVRecord csvRecord : csvParser) {
                FormDetail formDetail = new FormDetail();

                formDetail.setTitle(csvRecord.get("title"));
                formDetail.setSpeakers(csvRecord.get("speakers"));
                formDetail.setPublicURL(csvRecord.get("publicURL"));

                try {
                    formDetail.setVideoDate(new Date(dateFormat.parse(csvRecord.get("videoDate")).getTime()));
                    formDetail.setPublishedDate(new Date(dateFormat.parse(csvRecord.get("publishedDate")).getTime()));
                    formDetail.setFreeViewExpiry(new Date(dateFormat.parse(csvRecord.get("freeViewExpiry")).getTime()));
                } catch (ParseException e) {
                    throw new Exception("Error parsing date: " + e.getMessage(), e);
                }

                formDetail.setDescription(csvRecord.get("description"));
                formDetail.setSynopsis(csvRecord.get("synopsis"));

                // Read banner image from file path
                String bannerPath = csvRecord.get("bannerPath");
                File bannerFile = new File(bannerPath);
                if (bannerFile.exists() && bannerFile.isFile()) {
                    byte[] bannerBytes = Files.readAllBytes(bannerFile.toPath());
                    formDetail.setBanner(bannerBytes);

                    // Set contentType based on file extension
                    String fileExtension = getFileExtension(bannerPath).toLowerCase();
                    if ("jpg".equals(fileExtension) || "jpeg".equals(fileExtension)) {
                        formDetail.setContentType("image/jpeg");
                    } else if ("png".equals(fileExtension)) {
                        formDetail.setContentType("image/png");
                    }

                    System.out.println("Image read successfully: " + bannerPath);
                } else {
                    System.err.println("Banner file not found or is not a file: " + bannerPath);
                }

                formDetail.setVideoUrl(csvRecord.get("videoUrl"));
                formDetail.setPreviewVideoUrl(csvRecord.get("previewVideoUrl"));
                formDetail.setAccessCategory(csvRecord.get("accessCategory"));

                User user = new User();
                user.setId(Integer.parseInt(csvRecord.get("userId")));
                formDetail.setUser(user);

                formDetail.setPremium(Boolean.parseBoolean(csvRecord.get("isPremium")));

                formDetails.add(formDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error parsing CSV file: " + e.getMessage(), e);
        }
        return formDetails;
    }

    private static String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex + 1);
        }
        return "";
    }
}