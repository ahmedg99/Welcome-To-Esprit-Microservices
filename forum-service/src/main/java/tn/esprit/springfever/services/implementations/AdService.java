package tn.esprit.springfever.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.esprit.springfever.entities.Ad;
import tn.esprit.springfever.repositories.AdPagingRepository;
import tn.esprit.springfever.repositories.AdRepository;
import tn.esprit.springfever.services.interfaces.IAdService;
import tn.esprit.springfever.services.interprocess.RabbitMQMessageSender;

import java.util.List;

@Service
@Slf4j
public class AdService implements IAdService {
    @Autowired
    private AdRepository repo;

    @Autowired
    private RabbitMQMessageSender rabbitMQMessageSender;

    @Autowired
    private AdPagingRepository pagerepo;

    @Override
    public Ad addAd(Ad ad) {
        return repo.save(ad);
    }

    @Override
    @CachePut("ad")
    public Ad updateAd(Long id, Ad ad) {
        Ad a = repo.findById(Long.valueOf(id)).orElse(null) ;
        if(a!=null) {
            ad.setId(a.getId());
            repo.save(ad);
        }
        return ad;
    }

    @Override
    @CacheEvict("ad")
    public String deleteAd(Long ad) {
        Ad p = repo.findById(Long.valueOf(ad)).orElse(null) ;
        if(p!=null) {
            repo.delete(p);
            return "Ad was successfully deleted !" ;
        }
        return "Not Found ! ";
    }

    @Override
    @Cacheable("ad")
    public Ad getSingleAd(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Cacheable("ad")
    public List<Ad> getAllLazy(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return pagerepo.findAll(pageable).getContent();
    }
}
