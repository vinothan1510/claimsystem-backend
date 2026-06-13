package com.vinothan.claimsystem.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vinothan.claimsystem.dto.ClaimDocumentResponseDto;
import com.vinothan.claimsystem.entity.Claim;
import com.vinothan.claimsystem.entity.ClaimDocument;
import com.vinothan.claimsystem.repository.ClaimDocumentRepository;
import com.vinothan.claimsystem.repository.ClaimRepository;




@Service
public class ClaimDocumentServiceImpl implements ClaimDocumentService{
	

	    private static final String UPLOAD_DIR = "uploads/";

	    @Autowired
	    private ClaimDocumentRepository claimDocumentRepository;

	    @Autowired
	    private ClaimRepository claimRepository;

	    @Override
	    public ClaimDocumentResponseDto uploadDocument(
	            Long claimId,
	            MultipartFile file) {

	        // ✅ Validate PDF
	        if (!file.getContentType().equals("application/pdf")) {
	            throw new RuntimeException("Only PDF files allowed");
	        }

	        Claim claim = claimRepository.findById(claimId)
	                .orElseThrow(() -> new RuntimeException("Claim not found"));

	        // ✅ Create directory if not exists
	        File dir = new File(UPLOAD_DIR);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	        // ✅ Unique file name
	        String fileName =
	                UUID.randomUUID() + "_" + file.getOriginalFilename();

	        String filePath = UPLOAD_DIR + fileName;

	        try {
	            Files.copy(
	                file.getInputStream(),
	                Paths.get(filePath),
	                StandardCopyOption.REPLACE_EXISTING
	            );
	        }  catch (java.io.IOException e) {
	        	throw new RuntimeException("Failed to store file: " + file.getOriginalFilename(), e);
			}

	       
	        ClaimDocument document = new ClaimDocument();
	        document.setClaim(claim);
	        document.setDocumentName(file.getOriginalFilename());
	        document.setDocumentPath(filePath);

	        ClaimDocument saved =
	                claimDocumentRepository.save(document);

	        ClaimDocumentResponseDto response =
	                new ClaimDocumentResponseDto();
	        response.setDocumentId(saved.getDocumentId());
	        response.setDocumentName(saved.getDocumentName());
	        response.setDownloadUrl(
	                "/api/documents/download/" + saved.getDocumentId()
	        );

	        return response;
	    }
	    
	    // to download the document by claimofficer

	    @Override
	    public Resource downloadDocument(Long documentId) {

	        ClaimDocument document =
	                claimDocumentRepository.findById(documentId)
	                        .orElseThrow(() ->
	                                new RuntimeException("Document not found"));

	        try {
	            Path path = Paths.get(document.getDocumentPath());
	            return new UrlResource(path.toUri());
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("File not found");
	        }
	    }
	}
	

