package tn.esprit.springfever.repositories;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JobApplicatonPdfRepository {
    public List<String> save(byte[] contentCv, byte[] contentLettre, String location_Cv, String location_LettreMotivation) throws Exception {
        Path cvFile = Paths.get("C:\\Users\\User\\Desktop\\" + new Date().getTime() + "-" + location_Cv);
        Path lettreFile = Paths.get("C:\\Users\\User\\Desktop\\" + new Date().getTime() + "-" + location_LettreMotivation);

        Files.write(cvFile, contentCv);
        Files.write(lettreFile, contentLettre);

        return Arrays.asList(cvFile.toAbsolutePath().toString(), lettreFile.toAbsolutePath().toString());
    }


    public Resource[] findInFileSystem(String location_Cv, String location_LettreMotivation) {
        try {
            Resource cvResource = new FileSystemResource(Paths.get(location_Cv));
            Resource lettreMotivationResource = new FileSystemResource(Paths.get(location_LettreMotivation));
            return new Resource[]{cvResource, lettreMotivationResource};
        } catch (Exception e) {
            // Handle access or file not found problems.
            throw new RuntimeException();
        }
    }

}
