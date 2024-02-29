package br.com.strella.srv.empresa.adapter.input.rest;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.strella.srv.empresa.adapter.dto.mapper.UsuarioMapper;
import br.com.strella.srv.empresa.adapter.input.rest.dto.RootUsuarioDTO;
import br.com.strella.srv.empresa.adapter.input.rest.dto.UsuarioInputDTO;
import br.com.strella.srv.empresa.port.input.IUsuario;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/")
public class UsuarioController {

	@Autowired
	private final IUsuario usuarioUseCase;

	public UsuarioController(IUsuario usuarioUseCase) {
		this.usuarioUseCase = usuarioUseCase;
	}

	@PostMapping(value = "/strella-usuario", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<RootUsuarioDTO> cadastrar(@RequestBody UsuarioInputDTO usuarioInputDto) throws Exception {

		var usuario = UsuarioMapper.INSTANCE.usuarioInputDTOToUsuario(usuarioInputDto);
		var response = UsuarioMapper.INSTANCE.usuarioToUsuarioInputDTO(usuarioUseCase.cadastrarUsuario(usuario));
		var rootUsuarioDto = new RootUsuarioDTO(Arrays.asList(response), HttpStatus.CREATED);

		return ResponseEntity.status(HttpStatus.CREATED).body(rootUsuarioDto);
	}

	@GetMapping(value = "/strella-usuario", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<RootUsuarioDTO> listarPorParametros(
		@RequestParam(name = "nome", required = false) String nome,
		@RequestParam(name = "ultimoNome", required = false) String ultimoNome,
		@RequestParam(name = "email", required = false) String email,
		@RequestParam(name = "dataNascimento", required = false) LocalDate dataNascimento,
		@RequestParam(name = "idGoogle", required = false) String idGoogle,
		@RequestParam(name = "dataAtualizacao", required = false) LocalDate dataAtualizacao,
		@RequestParam(name = "idEmpresa", required = false) Long idEmpresa,
		@RequestParam(name = "page", required = false, defaultValue = "0") int page,
		@RequestParam(name = "size", required = false, defaultValue = "10") int size) {

		var in = new UsuarioInputDTO(nome, ultimoNome, email, dataNascimento, idGoogle, dataAtualizacao, idEmpresa);
		var filtros = UsuarioMapper.INSTANCE.usuarioInputDTOToUsuario(in);
		var retornoConsulta = usuarioUseCase.listarUsuariosViaFiltro(filtros, PageRequest.of(page, size));

		var response = new RootUsuarioDTO(UsuarioMapper.INSTANCE.listUsuarioToUsuarioInputDTO(retornoConsulta), OK);

		return ResponseEntity.status(OK).body(response);

	}
}