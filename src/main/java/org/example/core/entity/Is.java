package org.example.core.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "isler")
public class Is extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String baslik;

    @Temporal(TemporalType.TIMESTAMP)
    private Date tarih;

    private String tamamlananSure;

    private String aciklama;
    @Enumerated(EnumType.STRING)
    private EnumOnayDurumu onayDurumu;

    @ManyToOne(cascade = /* CascadeType.ALL */{CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cozen_kisi_id")
    private Personel cozenKisi;

    @ManyToOne(cascade = /*CascadeType.ALL*/ {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "yazar_id")
    private Personel yazar;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "birim_id")
    private Birim birim;

    public Personel getYazar() {
        return yazar;
    }

    public void setYazar(Personel yazar) {
        this.yazar = yazar;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Personel getCozenKisi() {
        return cozenKisi;
    }

    public void setCozenKisi(Personel cozenKisi) {
        this.cozenKisi = cozenKisi;
    }

    public Birim getBirim() {
        return birim;
    }

    public void setBirim(Birim birim) {
        this.birim = birim;
    }
}
