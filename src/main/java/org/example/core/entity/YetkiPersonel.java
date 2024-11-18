package org.example.core.entity;


import javax.persistence.*;

@Entity
@Table(name = "yetki_personel")
public class YetkiPersonel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "yetki_id")
    private Yetki yetki;

    @ManyToOne
    @JoinColumn(name = "personel_id")
    private Personel personel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Yetki getYetki() {
        return yetki;
    }

    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }
}
