package it.uniroma3.siw.sport.Service;

import it.uniroma3.siw.sport.Model.Image;
import it.uniroma3.siw.sport.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void salvaImmagine(Image immagine){
        this.imageRepository.save(immagine);
    }

    public Image findImmagineById (Long id){
        return this.imageRepository.findById(id).get();
    }

    public void deleteImage(Image image) {
        this.imageRepository.delete(image);

    }
}
