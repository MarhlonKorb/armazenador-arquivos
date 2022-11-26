package io.armazenadorarquivos.rest;

import io.armazenadorarquivos.entities.Arquivo;
import io.armazenadorarquivos.rest.messages.ResponseMessage;
import io.armazenadorarquivos.service.ArquivoService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
    
    @Autowired
    ArquivoService arquivoService;
    
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        StringBuilder message = new StringBuilder();
        try {
            List<String> fileNames = new ArrayList<>();
            
            Arrays.asList(files).stream().forEach(file -> {
                arquivoService.save(file);
                fileNames.add(file.getOriginalFilename());
            });
            
            message.append(fileNames.size() > 1 ? "Arquivos enviados com sucesso: " + fileNames : "Arquivo enviado com sucesso: " + fileNames);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message.append("Falha ao salvar arquivo!");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    
    @GetMapping("/files")
    public ResponseEntity<List<Arquivo>> getListFiles() {
        List<Arquivo> arquivos = arquivoService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ArquivoController.class, "getFile", path.getFileName().toString()).build().toString();
            
            return new Arquivo(filename, url);
        }).collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(arquivos);
    }
    
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = (Resource) arquivoService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.name() + "\"").body(file);
    }
}
