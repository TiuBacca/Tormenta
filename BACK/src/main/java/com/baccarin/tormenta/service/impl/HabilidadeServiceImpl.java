package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Habilidade;
import com.baccarin.tormenta.domain.Personagem;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistrosAssociadosException;
import com.baccarin.tormenta.repository.HabilidadeRepository;
import com.baccarin.tormenta.repository.PersonagemRepository;
import com.baccarin.tormenta.service.HabilidadeService;
import com.baccarin.tormenta.vo.habilidade.HabilidadeClasseResponse;
import com.baccarin.tormenta.vo.habilidade.HabilidadeRacaResponse;
import com.baccarin.tormenta.vo.habilidade.HabilidadeRequest;
import com.baccarin.tormenta.vo.habilidade.HabilidadeResponse;
import com.baccarin.tormenta.vo.item.ArmaduraResponse;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HabilidadeServiceImpl implements HabilidadeService {

	private final HabilidadeRepository habilidadeRepository;
	private final PersonagemRepository personagemRepository;

	@Override
	public void salvarHabilidade(HabilidadeRequest request) throws Exception {
		validaSalvar(request);
		Personagem personagem = personagemRepository.findById(request.getIdPersonagem()).get();
		Habilidade habilidade = new Habilidade(request);

		habilidade.setPersonagem(personagem);
		habilidadeRepository.save(habilidade);
	}

	@Override
	public void excluirHabilidade(HabilidadeRequest request) throws Exception {
		validaExcluir(request);
		habilidadeRepository.deleteById(request.getId());
	}

	@Override
	public List<HabilidadeResponse> buscarListaHabilidades() {
		return habilidadeRepository.buscarListaHabilidades();
	}

	private void validaSalvar(HabilidadeRequest request) throws Exception {
		if (Objects.isNull(request.getIdPersonagem())) {
			throw new RegistroIncompletoException("Atribudo id personagem faltando para salvar habilidade.");
		}
	}

	private void validaExcluir(HabilidadeRequest request) throws Exception {
		if (Objects.isNull(request.getId())) {
			throw new RegistroIncompletoException("Atributo id faltando para excluir habilidade.");
		} else {
			PersonagemResponse resp = habilidadeRepository.buscaPersonagensByHabilidadeId(request.getIdPersonagem());
			if (Objects.nonNull(resp)) {
				throw new RegistrosAssociadosException(
						"Impossível excluir uma habilidade com um personagem relacionado.");
			}
		}
	}

	@Override
	public List<HabilidadeClasseResponse> buscaListaHabilidadesClasse(PersonagemRequest request) throws Exception {

//		List<HabilidadeClasseResponse> armadurasResponse = armaduraRepository.findAll().stream().map(armadura -> {
//			ArmaduraResponse armaduraResponse = new ArmaduraResponse();
//			armaduraResponse.setId(armadura.getId());
//			armaduraResponse.setNome(armadura.getNome());
//			armaduraResponse.setDescricao(armadura.getDescricao());
//			armaduraResponse.setTipo(armadura.getTipoArmadura().getDescricao());
//			return armaduraResponse;
//		}).collect(Collectors.toList());
		
		return null;
	}

	@Override
	public List<HabilidadeRacaResponse> buscaListaHabilidadesRaca(PersonagemRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
