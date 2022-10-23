package br.com.miseenscene.miseenscene.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SEGUIDORES")
public class Seguidor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "seguidores_generator", sequenceName = "seguidores_sequence", initialValue = 1)
    private Long idSeguidores;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_usuario_seguidor")
    private Long idUsuarioSeguidor;

}
