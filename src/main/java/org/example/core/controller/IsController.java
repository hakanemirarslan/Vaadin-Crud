package org.example.core.controller;

import org.example.core.entity.Is;
import org.example.core.entity.dto.IsDto;
import org.example.core.service.IsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/isler")
public class IsController {

    private IsService isService;

    public IsController(IsService isService) {
        this.isService = isService;
    }

    @PostMapping
    public void save(@RequestBody Is is){
         isService.save(is);
    }

    @GetMapping
    public List<Is> findAll(){
        return isService.findAll();
    }

    @GetMapping("/{id}")
    public Is findById(@PathVariable long id){
        return isService.findById(id);
    }

    @GetMapping
    public List<Is> findAllByDto(@RequestBody IsDto isDto){
        return isService.findAllByDto(isDto);
    }

    @DeleteMapping("/{id}")
    public String deleteById(long id){
        Is is = findById(id);
        if (is == null)
            return "Is bulunamadı!";
        else {
            isService.deleteById(id);
            return "Is silindi!";
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable long id,@RequestBody Is is){
        Is isServiceById = isService.findById(id);
        if (isServiceById == null) {
            return "Is bulunamadı!";
        }else {
            isServiceById.setBaslik(is.getBaslik());
            isServiceById.setTarih(is.getTarih());
            isServiceById.setTamamlananSure(is.getTamamlananSure());
            isServiceById.setAciklama(is.getAciklama());
            isServiceById.setOnayDurumu(is.getOnayDurumu());
            isServiceById.setCozenKisi(is.getCozenKisi());
            isServiceById.setYazar(is.getYazar());

        }
        isService.update(isServiceById);
        return "Is güncellendi!";
    }

}
