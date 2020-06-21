package com.klinika.pregled.cbrApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinika.pregled.dto.CBRDijagnozaDTO;
import com.klinika.pregled.dto.CBRResponseDijagnozaDTO;
import com.klinika.pregled.dto.CBRTestDTO;
import com.klinika.pregled.repository.DijagnozaRepository;
import com.klinika.pregled.repository.TestRepository;

import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

//
@Service
public class CBRServiceDijagnoza {

    //cbrModelPregled -> cbrModelDijagnoza

    @Autowired
    private TestRepository repo;

    @Autowired
    private DijagnozaRepository dijagnozaRepo; //pregled -> dijagnoza


    public List<CBRResponseDijagnozaDTO> getMatches(CBRModelDijagnoza cbr){
        CBRApplicationDijagnoza app = new CBRApplicationDijagnoza(dijagnozaRepo.findAll());

        Collection<RetrievalResult> eval = app.evaluate(cbr);
        eval = SelectCases.selectTopKRR(eval, 5);

        ArrayList<CBRResponseDijagnozaDTO> rezultati = new ArrayList<>();
        for(RetrievalResult r : eval) {
            if(r.getEval() > 0) {
                CBRResponseDijagnozaDTO novi = new CBRResponseDijagnozaDTO();
                novi.setDijagnoza(((CBRModelDijagnoza)r.get_case().getDescription()).getDijagnoza());;
                //novi.setId(((CBRModelSimptomPregled)r.get_case().getDescription()).getId());
                novi.setTestovi(((CBRModelDijagnoza)r.get_case().getDescription()).getTestovi());
                rezultati.add(novi);
            }else {
                System.out.println("Nothing matches!");
            }
        }
//		for(CBRResponseDTO cb : rezultati) {
//			System.out.println(cb);
//		}
        return rezultati;
    }

    // CBRTestDTO -> CBRDijagnozaDTO --> zapravo je ista klasa koja sadrzi samo String dijagnoza
    // CBRModelTest -> CBRModelDijagnoza
    //CBRApplicationTest -> CBRApplicationDijagnoza



//    public List<CBRTestDTO> getDijagnozaMatches(CBRModelDijagnoza cbr){
//        CBRApplicationDijagnoza app = new CBRApplicationDijagnoza(dijagnozaRepo.findAll());
//
//        Collection<RetrievalResult> eval = app.evaluate(cbr);
//        eval = SelectCases.selectTopKRR(eval, 5);
//
//        ArrayList<CBRTestDTO> rezultati = new ArrayList<>();
//        for(RetrievalResult r : eval) {
//            if(r.getEval() > 0) {
//                CBRTestDTO novi = new CBRTestDTO();
//                novi.setTest(((CBRModelDijagnoza)r.get_case().getDescription()).getDijagnoza());
////                novi.setDijagnoza(((CBRModelDijagnoza)r.get_case().getDescription()).getDijagnoza());
//                rezultati.add(novi);
//            }else {
//                System.out.println("Nothing matches!");
//            }
//        }
//        return rezultati;
//    }
}

