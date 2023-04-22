package vn.tdtu.edu.commons.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brands", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;
    private String name;
    private boolean is_deleted;
    private boolean is_activated;

    public Brand(String name){
        this.name = name;
        this.is_activated = true;
        this.is_deleted = false;
    }
}
