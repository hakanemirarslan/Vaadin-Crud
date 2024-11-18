package org.example.core.entity.dto;

import org.example.core.entity.EnumOnayDurumu;

import java.util.Date;

public class IsDto {
    private String baslik;
    private String aciklama;
    private Date tarih;
    private String tamamlananSure;
    private EnumOnayDurumu onayDurumu;

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getTamamlananSure() {
        return tamamlananSure;
    }

    public void setTamamlananSure(String tamamlananSure) {
        this.tamamlananSure = tamamlananSure;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public EnumOnayDurumu getOnayDurumu() {
        return onayDurumu;
    }

    public void setOnayDurumu(EnumOnayDurumu onayDurumu) {
        this.onayDurumu = onayDurumu;
    }
}
