package br.com.strella.srv.empresa.adapter.input.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.strella.srv.empresa.adapter.dto.mapper.EmpresaMapper;
import br.com.strella.srv.empresa.adapter.input.rest.dto.EmpresaInputDTO;
import br.com.strella.srv.empresa.adapter.input.rest.dto.RootEmpresaDTO;
import br.com.strella.srv.empresa.port.input.IEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/strella-empresa")
public class EmpresaController implements SwaggerEmpresaController {
	@Autowired
	private final IEmpresa empresaUseCase;

	public EmpresaController(IEmpresa empresaUseCase) {
		this.empresaUseCase = empresaUseCase;
	}


	@PostMapping(value = "/", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<RootEmpresaDTO> cadastrarEmpresa(@RequestBody EmpresaInputDTO empresaInputDto) throws Exception {

		var empresa = EmpresaMapper.INSTANCE.empresaInputDTOToEmpresa(empresaInputDto);
		var response = EmpresaMapper.INSTANCE.empresaToEmpresaInputDTO(empresaUseCase.cadastrarEmpresa(empresa));
		var rootEmpresaDto = new RootEmpresaDTO(response, "200");

		return ResponseEntity.status(HttpStatus.CREATED).body(rootEmpresaDto);
	}


}
