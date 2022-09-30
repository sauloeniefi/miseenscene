package br.com.miseenscene.miseenscene.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Publicacao publicacao;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idComentario;

    private String textoComentario;


}
