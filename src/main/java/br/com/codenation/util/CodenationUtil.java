package br.com.codenation.util;

import java.util.Comparator;
import java.util.List;

import br.com.codenation.entity.BaseEntity;

public class CodenationUtil {

	/**
	 * Recupera o maior id da lista;
	 * 
	 * @param <T>
	 * @param lista
	 * @return
	 */
	public static <T extends BaseEntity> Long findMax(List<T> lista) {
		Long maxId = 0l;
		if (lista != null && !lista.isEmpty()) {
			maxId = lista.stream().max(Comparator.comparing(T::getId)).get().getId();
		}
		return maxId;
	}

	/**
	 * Verifica se uma lista ja contém um item com o id solicitado
	 * 
	 * @param <T>
	 * @param lista       lista para procurar o id
	 * @param idProcurado id Procurado.
	 * @return true se o id for encontrado.
	 */
	public static <T extends BaseEntity> boolean listContainsId(List<T> lista, Long idProcurado) {
		boolean contains = false;
		if (lista != null && !lista.isEmpty()) {
			contains = lista.stream().anyMatch(t -> t.getId() == idProcurado);
		}
		return contains;
	}

}
