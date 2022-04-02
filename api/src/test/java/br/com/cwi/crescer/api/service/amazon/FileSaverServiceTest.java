package br.com.cwi.crescer.api.service.amazon;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class FileSaverServiceTest {

    @InjectMocks
    private FileSaverService tested;

    @Mock
    private AmazonS3 amazonS3;

    @Test
    public void deveRetornarUrlCorretamente() throws IOException {

        String BUCKET= "comofaz";

        MockMultipartFile file = new MockMultipartFile("texto", new byte[200]);
        Integer hash = 1;
        String urlAmazon = "http://s3.amazonaws.com/" + BUCKET;
        String url = hash.toString() + file.hashCode() + LocalDate.now().hashCode();

        String result = tested.write(file, hash);

        Assert.assertEquals(urlAmazon + "/" + url, result);
    }

    @Test
    public void deveRetornarNullQuandoFileForVazio(){

        MockMultipartFile file = new MockMultipartFile("texto", new byte[0]);
        Integer hash = 1;

        String result = tested.write(file, hash);

        Assert.assertNull(result);
    }

}