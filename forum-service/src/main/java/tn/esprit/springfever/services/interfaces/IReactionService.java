package tn.esprit.springfever.services.interfaces;

import org.springframework.core.io.FileSystemResource;
import tn.esprit.springfever.entities.AdMedia;
import tn.esprit.springfever.entities.Post;
import tn.esprit.springfever.entities.Reaction;

import java.util.List;

public interface IReactionService {
    public Reaction save(byte[] bytes, String imageName, String label) throws Exception ;
    public FileSystemResource find(Long imageId) ;
}
