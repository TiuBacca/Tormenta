package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Usuario;
import com.baccarin.tormenta.exception.RegistroDuplicadoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.exception.RegistrosAssociadosException;
import com.baccarin.tormenta.repository.UsuarioRepository;
import com.baccarin.tormenta.resource.PersonagemFiltro;
import com.baccarin.tormenta.service.PersonagemService;
import com.baccarin.tormenta.service.UsuarioService;
import com.baccarin.tormenta.util.Util;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;
import com.baccarin.tormenta.vo.usuario.UsuarioResponse;

import jakarta.persistence.Query;
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
				" select new com.baccarin.tormenta.vo.usuario.UsuarioResponse (u.nome) FROM Usuario u where u.id > 0  ");

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			sb.append(" AND u.id = :id ");
		}

		if (Objects.nonNull(request.getNome()) && !request.getNome().isBlank()) {
			sb.append(" AND u.nome ilike :nome ");
		}

		if (Objects.nonNull(request.getEmail()) && !request.getEmail().isBlank()) {
			sb.append(" AND u.email ilike :email ");
		}

		Query query = util.getEntityManager().createQuery(sb.toString());

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			query.setParameter("id", request.getId());
		}

		if (Objects.nonNull(request.getNome()) && !request.getNome().isBlank()) {
			query.setParameter("nome", "%" + request.getNome() + "%");
		}

		if (Objects.nonNull(request.getEmail()) && !request.getEmail().isBlank()) {
			query.setParameter("email", Util.criptografar(request.getEmail()));
		}

		List<UsuarioResponse> lista = query.getResultList();

		return lista;
	}

	@Override
	public void removerUsuario(UsuarioRequest request) throws Exception {
		validaExcluirUsuario(request);
		usuarioRepository.deleteById(request.getId());

	}

	@Override
	public void salvarUsuario(UsuarioRequest request) throws Exception {
		validarSalvarUsuario(request);
		usuarioRepository.save(new Usuario(request));
	}

	private void validarSalvarUsuario(UsuarioRequest request) throws Exception {

		if (Objects.nonNull(request.getId())) {
			usuarioRepository.findById(request.getId())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não encontrado."));
			if (Objects.nonNull(request.getEmail())) {
				throw new RegistroIncompletoException("Impossível atualizar o e-mail do usuário.");
			}

		} else {

			if (Objects.isNull(request.getNome()) || request.getNome().isBlank()) {
				throw new RegistroIncompletoException("Atributo nome faltando para salvar usuário.");
			} else {
				List<UsuarioResponse> usersNomeDuplicado = buscarListaUsuariosByFiltro(
						UsuarioRequest.builder().email(request.getEmail()).build());

				if (Objects.nonNull(usersNomeDuplicado) && !usersNomeDuplicado.isEmpty()) {
					throw new RegistroDuplicadoException("E-mail ja cadastrado.");
				}
			}

			if (Objects.isNull(request.getEmail()) || request.getEmail().isBlank()) {
				throw new RegistroIncompletoException("Atributo e-mail faltando para salvar usuário.");
			}

			if (Objects.isNull(request.getSenha()) || request.getSenha().isBlank()) {
				throw new RegistroIncompletoException("Atributo senha faltando para salvar usuário.");
			}
		}

	}

	private void validaExcluirUsuario(UsuarioRequest request) throws Exception {
		usuarioRepository.findById(request.getId())
				.orElseThrow(() -> new RegistroNaoEncontradoException("Não foi encontrado o usuário informado."));

		PersonagemFiltro filtro = new PersonagemFiltro();
		filtro.getUsuarios().add(UsuarioRequest.builder().id(request.getId()).build());
		List<PersonagemResponse> personagensAssociados = personagemService.buscaListaPersonagemByFiltro(filtro);
		if (!personagensAssociados.isEmpty()) {
			throw new RegistrosAssociadosException(
					"Existem personagens associados a este usuário. Remover todos registros antes de remover o usuário.");
		}
	}
}
