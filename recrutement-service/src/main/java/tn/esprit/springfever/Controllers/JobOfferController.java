package tn.esprit.springfever.Controllers;

import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springfever.Services.Interfaces.IJobOffer;
import tn.esprit.springfever.entities.Job_Offer;

import java.util.List;

@RestController
public class JobOfferController  {
    @Autowired
    IJobOffer iJobOffer;
    @PostMapping("addJobOffer/")
    public Job_Offer addJobOffer(@Validated @RequestBody Job_Offer job_offer){
        return iJobOffer.addJobOffer(job_offer);

    }
    @GetMapping("getAllJobOffers/")
    public List<Job_Offer> getAllJobOffers(){
        return iJobOffer.getAllJobOffers();
    }

    @PutMapping("updateJobOffer/{id}")
    public Job_Offer updateJobOffer(@PathVariable("id") Long Id_Job_Offer ,@RequestBody Job_Offer job_offer ){
        return iJobOffer.updateJobOffer(Id_Job_Offer,job_offer);

    }



    @DeleteMapping("deleteJobOffer/{id}")
    public  String deleteJobOffer(@PathVariable("id") Long  Id_Job_Offer){
        return iJobOffer.deleteJobOffer(Id_Job_Offer);
    }
    @PutMapping("AssignJobOfferToCategory/{Id_Job_Offer}/{Id_Job_Category}")
    public String AssignCategoryToJobOffer( Long Id_Job_Offer, Long Id_Job_Category){
        return iJobOffer.AssignCategoryToJobOffer(Id_Job_Offer,Id_Job_Category);

    }


    @PutMapping("AssignImageToJobOffer/{Id_Job_Offer}/{id}")
    public String AssignImageToJobOffer(@PathVariable("Id_Job_Offer") Long Id_Job_Offer ,@PathVariable("id") Long id ){
        return iJobOffer.AssignImageToJobOffer(Id_Job_Offer,id);

    }

    @PutMapping("AssignJobApplicationToJobOffer/{Id_Job_Offer}/{id}")
    public String AssignJobApplicationToJobOffer(@PathVariable("Id_Job_Offer") Long Id_Job_Offer ,@PathVariable("id") Long id ){
        return iJobOffer.AssignJobApplicationToJobOffer(Id_Job_Offer,id);

    }

    @PutMapping("AssignUserToJobApplication/{id}/{Id_Job_Application}")
    public String AssignUserToJobApplication(@PathVariable("id") Long id ,@PathVariable("Id_Job_Application") Long Id_Job_Application ){
         return iJobOffer.AssignUserToJobApplication(id,Id_Job_Application);

    }
    @GetMapping(value = "/rss", produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getRSSFeed() throws FeedException {
        String rssFeed = iJobOffer.generateRSSFeed();
        return ResponseEntity.ok(rssFeed);
    }


}
