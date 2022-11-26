package io.armazenadorarquivos;

import io.armazenadorarquivos.service.ArquivoService;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
@SpringBootApplication
public class ArmazenadorArquivosApplication implements CommandLineRunner {
    
    @Resource
    ArquivoService storageService;
    
    public static void main (String[] args) {
        SpringApplication.run(ArmazenadorArquivosApplication.class, args);
 
    }
    
    @Override
    public void run (String...arg) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
