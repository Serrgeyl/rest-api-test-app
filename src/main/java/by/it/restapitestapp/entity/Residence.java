package by.it.restapitestapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "residence")
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "residence_id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
