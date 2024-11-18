package org.example.core.controller;

import org.example.core.entity.Yetki;
import org.example.core.service.YetkiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yetki")
public class YetkiController {

    private YetkiService yetkiService;

    public YetkiController(YetkiService yetkiService) {
        this.yetkiService = yetkiService;
    }

    @PostMapping
    public void save(@RequestBody Yetki yetki) {
         yetkiService.save(yetki);
    }

    @GetMapping
    public List<Yetki> findAll(){
        return yetkiService.findAll();
    }

    @GetMapping("/{id}")
    public Yetki findById(@PathVariable long id){
        return yetkiService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id){
        Yetki yetki = findById(id);
        if (yetki == null){
            return "Yetki Bulunamadı!";
        }else {
            yetkiService.deleteById(id);
            return "Yetki Silindi";
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable long id,@RequestBody Yetki yetki){
        Yetki yetkiServiceById = yetkiService.findById(id);
        if (yetkiServiceById == null) {
            return "Yetki bulunamadı!";
        }else {
            yetkiServiceById.setYetkiAdi(yetki.getYetkiAdi());
        }
        yetkiService.update(yetkiServiceById);
        return "Yetki güncellendi!";
    }
}
