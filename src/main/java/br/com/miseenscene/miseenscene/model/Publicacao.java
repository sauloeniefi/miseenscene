package br.com.miseenscene.miseenscene.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@Table(name = "PUBLICACAO")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPublicacao;

    private String titulo;

    private String descricao;

    private LocalDateTime dhInclusao;

    private byte[] imagem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "ID_USUARIO"))
    private Usuario usuario;

    @OneToMany
    private List<Comentario> comentarios = new ArrayList<>();
}
