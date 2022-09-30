package br.com.miseenscene.miseenscene.dto;

import br.com.miseenscene.miseenscene.model.Comentario;
import br.com.miseenscene.miseenscene.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PublicacaoDTO {

    private Long idPublicacao;
    private String titulo;
    private String descricao;
    private LocalDateTime dhInclusao;
    private byte[] imagem;
    private Usuario usuario;
    private List<Comentario> comentarios;

}
