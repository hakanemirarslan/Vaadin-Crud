package org.example.core.controller;

import org.example.core.entity.Personel;
import org.example.core.entity.dto.PersonelDto;
import org.example.core.service.PersonelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personel")
public class PersonelController {

    private PersonelService personelService;

    public PersonelController(PersonelService personelService) {
        this.personelService = personelService;
    }

    @PostMapping
    public void save(@RequestBody Personel personel){
         personelService.save(personel);
    }

    @GetMapping
    public List<Personel> findAll(){
        return personelService.findAll();
    }

    @GetMapping("/{id}")
    public Personel findById(@PathVariable long id){
        return personelService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<Personel> findByName(@PathVariable String name){
        return personelService.findByName(name);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id){
        Personel personel = findById(id);
        if (personel == null)
            return "Personel bulunamadı!";
        else {
            personelService.deleteById(id);
            return "Personel silindi";
        }
    }

    @GetMapping
    public List<Personel> findAllByDto(@RequestBody PersonelDto personelDto){
        return personelService.findAllByDto(personelDto);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable long id,@RequestBody String name, @RequestBody Personel personel){
        Personel personelServiceById = personelService.findById(id);
        if (personelServiceById == null){
            return "Personel bulunamadı!";
        }else {
            personelServiceById.setName(personel.getName());
            personelServiceById.setKullaniciAdi(personel.getKullaniciAdi());
            personelServiceById.setPassword(personel.getPassword());
        }
        personelService.update(personelServiceById);
        return "Personel Güncellendi!";
    }
}
