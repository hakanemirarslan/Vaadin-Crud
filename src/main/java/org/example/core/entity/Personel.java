package org.example.core.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personel")
public class Personel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String kullaniciAdi;

    private String password;

    @OneToMany(mappedBy = "yazar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Is> yapilanIsler = new ArrayList<>();

//
//    @OneToMany(mappedBy = "cozenKisi", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Is> cozulenIsler = new ArrayList<>();

    public Personel() {
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Personel that = (Personel) o;
//
//        return getId().equals(that.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getId().hashCode();
//    }

}
