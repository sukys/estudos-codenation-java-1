package br.com.codenation.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data()
@RequiredArgsConstructor()
@AllArgsConstructor()
@EqualsAndHashCode(callSuper = true, exclude = { "nome", "dataCriacao", "corUniformePrincipal", 
		"corUniformeSecundario", "id_capitao" })
public class Time extends BaseEntity {

	@NonNull
	private String nome;

	@NonNull
	private LocalDate dataCriacao;

	@NonNull
	private String corUniformePrincipal;

	@NonNull
	private String corUniformeSecundario;

	private Long id_capitao;

	public Time(@NonNull Long id, @NonNull String nome, @NonNull LocalDate dataCriacao,
			@NonNull String corUniformePrincipal, @NonNull String corUniformeSecundario) {
		this(nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		this.id = id;
	}

}
