package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.entity.Jogador;
import br.com.codenation.entity.Time;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.util.CodenationUtil;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private final List<Time> TIMES = new ArrayList<>();
	private final List<Jogador> JOGADORES = new ArrayList<>();
	
	
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		if(CodenationUtil.listContainsId(TIMES, id)) { throw new IdentificadorUtilizadoException(); }
		TIMES.add(time);
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Jogador jogador = new Jogador(idTime, nome, dataNascimento, nivelHabilidade, salario);
		if(CodenationUtil.listContainsId(JOGADORES, id)) { throw new IdentificadorUtilizadoException(); }
		if(!CodenationUtil.listContainsId(TIMES, idTime)) { throw new TimeNaoEncontradoException();}
		JOGADORES.add(jogador);
	}

	public void definirCapitao(Long idJogador) {
		Jogador jogador = recuperarJogador(idJogador);
		Time time = recuperarTime(jogador.getIdTime());
		time.setId_capitao(jogador.getId());
		atualizarTime(time);
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		return recuperarTime(idTime).getId_capitao();
	}

	public String buscarNomeJogador(Long idJogador) {
		return recuperarJogador(idJogador).getNome();
	}

	public String buscarNomeTime(Long idTime) {
		return recuperarTime(idTime).getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		recuperarTime(idTime);
		return JOGADORES.stream()
				.filter(p->p.getIdTime() == idTime)
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		recuperarTime(idTime);
		return JOGADORES.stream()
				.filter(p->p.getIdTime() == idTime)
				.max(Comparator.comparing(Jogador::getNivelHabilidade)).get().getId();
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		recuperarTime(idTime);
		return JOGADORES.stream()
				.filter(p->p.getIdTime() == idTime)
				.min(Comparator.comparing(Jogador::getDataNascimento)).get().getId();
	}

	public List<Long> buscarTimes() {
		return TIMES.stream()
				.map(Time::getId)
				.collect(Collectors.toList());
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		recuperarTime(idTime);
		return JOGADORES.stream()
				.filter(p->p.getIdTime() == idTime)
				.max(Comparator.comparing(Jogador::getSalario)).get().getId();

	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return recuperarJogador(idJogador).getSalario();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		return JOGADORES.stream()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade))
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}
	
	
	/* Metodos auxiliares */
	
	/**
	 * Atualiza um Time na lista. 
	 * @param lista
	 * @param id
	 */
	private void atualizarTime(Time timeAtualizado) {
		int index = TIMES.indexOf(recuperarTime(timeAtualizado.getId()));
		TIMES.set(index, timeAtualizado);
	}
	
	/**
	 * Atualiza um jogador na lista
	 * @param jogadorAtualizado
	 */
	private void atualizarJogador(Jogador jogadorAtualizado) {
		int index = JOGADORES.indexOf(recuperarJogador(jogadorAtualizado.getId()));
		JOGADORES.set(index, jogadorAtualizado);
	}

	/**
	 * Recupera um time pelo seu id
	 * @param id
	 * @return
	 */
	private Time recuperarTime(Long id) {
		if(!CodenationUtil.listContainsId(TIMES, id)) {
			throw new TimeNaoEncontradoException();
		}
		return TIMES.stream().filter(t -> t.getId() == id).findFirst().get();
	}
	
	/**
	 * Recupera um jogador pelo seu id
	 * @param id
	 * @return
	 */
	private Jogador recuperarJogador(Long id) {
		if(!CodenationUtil.listContainsId(JOGADORES, id)) {
			throw new JogadorNaoEncontradoException();
		}
		return JOGADORES.stream().filter(j -> j.getId() == id).findFirst().get();
	}
	
	
	
}
