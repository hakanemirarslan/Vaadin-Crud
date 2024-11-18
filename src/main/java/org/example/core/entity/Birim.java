package org.example.core.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "birim")
public class Birim extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //255
    @Column(length = 200)
    private String name;

    @Override
    public String toString() {
        return  name;
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
//    @Override
//    public boolean equals(Object o){
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Birim that = (Birim) o;
//        return getId().equals(that.getId());
//    }
//
//    @Override
//    public int hashCode(){
//        return getId().hashCode();
//    }
}
