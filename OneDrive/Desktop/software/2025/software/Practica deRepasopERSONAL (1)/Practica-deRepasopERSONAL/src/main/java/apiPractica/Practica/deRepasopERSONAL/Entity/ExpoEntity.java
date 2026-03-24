package apiPractica.Practica.deRepasopERSONAL.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "")
@ToString
@EqualsAndHashCode
public class ExpoEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_expo")
    @SequenceGenerator(name = "seq_expo", sequenceName = "seq_expo", allocationSize = 1)
    private Long id;

    @Column
    private String Nombre;

    @Column
    private String Apellido;

    @Column
    private String Contrasena;

    @Column
    private Long id_rol;
}
