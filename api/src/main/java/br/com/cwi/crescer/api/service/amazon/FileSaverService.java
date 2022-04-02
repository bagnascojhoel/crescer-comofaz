package br.com.cwi.crescer.api.service.amazon;

import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class FileSaverService {

    public static final int TAMANHO_ARQUIVO_VAZIO = 100;

    @Autowired
    private AmazonS3 amazonS3;

    private static final String BUCKET = "comofaz";

    public String write(MultipartFile file, Integer hash) {
        if (file.getSize() <= TAMANHO_ARQUIVO_VAZIO)
            return null;

        try {
            String url = hash.toString() + file.hashCode() + LocalDate.now().hashCode();
            amazonS3.putObject(new PutObjectRequest(BUCKET,
                    url, file.getInputStream(),null));
            return "http://s3.amazonaws.com/"+BUCKET+"/" + url;

        } catch (IllegalStateException | IOException e) {
            throw new ValidacaoNegocioException("Erro ao cadastrar arquivo no s3: " + e.getMessage());
        }
    }
}