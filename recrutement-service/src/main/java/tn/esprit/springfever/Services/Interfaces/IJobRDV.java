package tn.esprit.springfever.Services.Interfaces;

import tn.esprit.springfever.entities.Job_Offer;
import tn.esprit.springfever.entities.Job_RDV;

import java.util.List;

public interface IJobRDV {

    public Job_RDV addJobRDV(Job_RDV job_rdv);
    public List<Job_RDV> getAllJobRDVs() ;
    public Job_RDV updateJobRDV (Long ID_Job_DRV , Job_RDV job_rdv ) ;
    public  String deleteJobOffer(Long  ID_Job_DRV) ;
    public String AssignEntretienToRDV( Long ID_Job_Entretien, Long ID_Job_DRV);

    public String AssignJobApplicationToRDV(Long Id_Job_Application ,Long ID_Job_DRV);

}