package com.example.projetotdd.controller;

import static org.mockito.Answers.values;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.projetotdd.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.projetotdd.model.Produto;

@WebMvcTest
public class ProdutoControllerTest {
    // MockMvc

    // MocKito

    // Mock
    @Autowired
    private ProdutoController produtoController;

    @MockBean // ele escolhe o que vai devolver
    private ProdutoService produtoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // aqui será executado antesde qualquer caso de teste atraves da anotação
        // Beforeeach

        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    public void deve_retornar_status_200_ok_ao_chamar_metodo_obter_todos_os_produtos() throws Exception {
        // Arrange : preparação -> o que eu espero receber
        List<Produto> produtos = new ArrayList<Produto>();
        var requestBuilder = MockMvcRequestBuilders.get("/api/produtos");
        when(this.produtoService.obterTodosProdutos()).thenReturn(produtos);

        // act : ação
        this.mockMvc.perform(requestBuilder)

                // assert : confirmação
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deve_retornar_um_produtos_por_id() throws Exception {
        // Arrange : preparação -> o que eu espero receber
        Produto produto = new Produto();
        produto.setId(2l);
        produto.setNome("fernando");
        produto.setQuantidade(20);

        Optional<Produto> optProduto = Optional.of(produto);
        var requestBuilder = MockMvcRequestBuilders.get("/api/produtos/id");
        when(this.produtoService.obterPorId(2l)).thenReturn(optProduto);

        // act : ação
        this.mockMvc.perform(requestBuilder)

        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2l) );
        // assert : confirmação
                //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2l));
    }

    @Test
    public void deve_adicionar_um_produto() throws Exception {
        // Arrange : preparação -> o que eu espero receber
        Produto produto = new Produto();

        produto.setNome("fernando");
        produto.setQuantidade(20);

        // convertendo um produto em json
        String json = new ObjectMapper().writeValueAsString(produto);

        var requestBuilder = MockMvcRequestBuilders.post("/api/produtos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        produto.setId(1l);
        when(this.produtoService.adicionar(produto)).thenReturn(produto);

        // act : ação
        this.mockMvc.perform(requestBuilder)

                // assert : confirmação
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
