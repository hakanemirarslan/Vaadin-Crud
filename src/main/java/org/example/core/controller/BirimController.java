package org.example.core.controller;

import org.example.core.entity.Birim;
import org.example.core.entity.dto.BirimDto;
import org.example.core.service.BirimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/birim")
public class BirimController {

    private BirimService birimService = new BirimService();

    @PostMapping
    public void save(@RequestBody Birim birim) {
         birimService.save(birim);
    }

    @GetMapping
    public List<Birim> findAll() {
        return birimService.findAll();
    }

    @GetMapping("/{id}")
    public Birim findById(@PathVariable long id) {
        return birimService.findById(id);
    }

    @GetMapping("/birim/{birim}")
    public List<Birim> findByBaslik(@PathVariable String baslik){
        return birimService.findByBaslik(baslik);
    }

    @GetMapping
    public List<Birim> findAllByDto(@RequestBody BirimDto birimDto){
        return birimService.findAllByDto(birimDto);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id) {
        Birim birim = findById(id);
        if (birim == null)
            return "Birim bulunamadı!";
        else {
            birimService.deleteById(id);
            return "Birim silindi!";
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable long id, @RequestBody Birim birim){
     Birim birimServiceById = birimService.findById(id);
     if(birimServiceById == null){
         return "Birim bulunamadı!";
     }else {
         birimServiceById.setName(birim.getName());
     }
     return "Birim güncellendi!";
    }
}
