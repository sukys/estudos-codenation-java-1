package br.com.codenation.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor()
@EqualsAndHashCode(callSuper = true, exclude = { "idTime", "nome", "dataNascimento", "nivelHabilidade", "salario" })
public class Jogador extends BaseEntity {

	@NonNull
	private Long idTime;

	@NonNull
	private String nome;

	@NonNull
	private LocalDate dataNascimento;

	@NonNull
	private Integer nivelHabilidade;

	@NonNull
	private BigDecimal salario;

	public Jogador(@NonNull Long id, @NonNull Long idTime, @NonNull String nome, @NonNull LocalDate dataNascimento,
			@NonNull Integer nivelHabilidade, @NonNull BigDecimal salario) {
		this(idTime, nome, dataNascimento, nivelHabilidade, salario);
		this.id = id;

	}

	public void setNivelHabilidade(Integer nivelHabilidade) {
		if (nivelHabilidade == null) {
			nivelHabilidade = 0;
		} else if (nivelHabilidade < 0) {
			nivelHabilidade = 0;
		} else if (nivelHabilidade > 100) {
			nivelHabilidade = 100;
		}
		this.nivelHabilidade = nivelHabilidade;
	}

}
