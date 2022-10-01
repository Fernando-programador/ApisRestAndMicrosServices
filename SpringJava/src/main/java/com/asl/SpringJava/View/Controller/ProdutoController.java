package com.asl.SpringJava.View.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.asl.SpringJava.Service.ServiceProduto;
import com.asl.SpringJava.Shared.ProdutoDTO;
import com.asl.SpringJava.View.Model.ProdutoRequest;
import com.asl.SpringJava.View.Model.ProdutoResponse;




@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {


	@Autowired
	private ServiceProduto serviceProduto;

	@GetMapping
	public ResponseEntity<List<ProdutoResponse>> obterTodos() {
		List<ProdutoDTO> produtos = serviceProduto.obterTodos();
		
		ModelMapper mapper = new ModelMapper();

		List<ProdutoResponse> resposta  = produtos.stream()
		.map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class)).collect(Collectors.toList());

		return new ResponseEntity<>(resposta, HttpStatus.OK);
	}
	
		@GetMapping("/{id}")
		public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id) {
		//	try {
				//adquirir o produto do banco 
			Optional<ProdutoDTO> produtoDto = serviceProduto.obterPorId(id);
			//converter o produto que adquiri no banco por uma produtoResponse
			ProdutoResponse produto = new ModelMapper().map(produtoDto.get(), ProdutoResponse.class);

			return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
				
		//	} catch (Exception e) {
		//		return new ResponseEntity<>(HttpStatus.NO_CONTENT)
		//	}
			
		}

	@PostMapping
	public ResponseEntity<ProdutoResponse> addProduto(@RequestBody ProdutoRequest produtoRequisitado) {
		ModelMapper mapper  = new ModelMapper();

		ProdutoDTO produtoDto = mapper.map(produtoRequisitado, ProdutoDTO.class);

		produtoDto = serviceProduto.addProduto(produtoDto);
			
		return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id) {
		serviceProduto.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoRequsitado, @PathVariable Integer id) {

		ModelMapper mapper = new ModelMapper();
		
		ProdutoDTO produtoDto = mapper.map(produtoRequsitado, ProdutoDTO.class);

		produtoDto = serviceProduto.atualizar(id, produtoDto);
		
		return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.OK); 
	}
}
