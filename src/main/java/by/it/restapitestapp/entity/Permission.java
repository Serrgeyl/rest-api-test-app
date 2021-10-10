package by.it.restapitestapp.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "permission_name")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    private List<Employee> employees;
}
