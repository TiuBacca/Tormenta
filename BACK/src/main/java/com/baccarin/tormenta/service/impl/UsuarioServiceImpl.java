package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Usuario;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.exception.RegistrosAssociadosException;
import com.baccarin.tormenta.repository.UsuarioRepository;
import com.baccarin.tormenta.service.PersonagemService;
import com.baccarin.tormenta.service.UsuarioService;
import com.baccarin.tormenta.util.Util;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;
import com.baccarin.tormenta.vo.usuario.UsuarioResponse;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final Util util;

	private final PersonagemService personagemService;
	private final UsuarioRepository usuarioRepository;

	@Override
	public List<UsuarioResponse> buscarListaUsuariosByFiltro(UsuarioRequest request) {

		StringBuilder sb = new StringBuilder();
		sb.append(
				" select new com.baccarin.tormenta.vo.usuario.UsuarioResponse (u.nome, u.email) FROM Usuario u where u.id > 0  ");

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			sb.append(" AND u.id = :id ");
		}

		if (Objects.nonNull(request.getNome()) && !request.getNome().isBlank()) {
			sb.append(" AND u.nome ilike :nome ");
		}

		if (Objects.nonNull(request.getEmail()) && !request.getEmail().isBlank()) {
			sb.append(" AND u.email ilike :email ");
		}

		TypedQuery<UsuarioResponse> query = util.getEntityManager().createQuery(sb.toString(), UsuarioResponse.class);

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			query.setParameter("id", request.getId());
		}

		if (Objects.nonNull(request.getNome()) && !request.getNome().isBlank()) {
			query.setParameter("nome", "%" + request.getNome() + "%");
		}

		if (Objects.nonNull(request.getEmail()) && !request.getEmail().isBlank()) {
			query.setParameter("email", "%" + request.getEmail() + "%");
		}

		return query.getResultList();
	}

	@Override
	public void removerUsuario(UsuarioRequest request) throws Exception {

		Usuario user = usuarioRepository.findById(request.getId())
				.orElseThrow(() -> new RegistroNaoEncontradoException("Não foi encontrado o usuário informado."));

		List<PersonagemResponse> personagensAssociados = personagemService
				.buscaListaPersonagemByFiltro(PersonagemRequest.builder().idUsuario(request.getId()).build());
		if (!personagensAssociados.isEmpty()) {
			throw new RegistrosAssociadosException(
					"Existem personagens associados a este usuário. Remover todos registros antes de remover o usuário.");
		}

		usuarioRepository.delete(user);

	}

	@Override
	public void salvarUsuario(UsuarioRequest request) {
		// TODO Auto-generated method stub

	}

}
