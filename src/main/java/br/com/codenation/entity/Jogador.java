package br.com.codenation.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Jogador extends BaseEntity {

	@NonNull
	private Long idTime;

	@NonNull
	private String nome;

	@NonNull
	private LocalDate dataNascimento;

	@NonNull
	private Integer nivelHabilidade; // * Nível de habilidade do jogador (0 a 100)

	@NonNull
	private BigDecimal salario;

	// Contrutor 
	
	public Jogador(@NonNull Long id, @NonNull Long idTime, @NonNull String nome, @NonNull LocalDate dataNascimento,
			@NonNull Integer nivelHabilidade, @NonNull BigDecimal salario) {
		this(idTime, nome, dataNascimento, nivelHabilidade, salario);
		this.idTime = idTime;

	}
	
	// Getters e setters
	
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
