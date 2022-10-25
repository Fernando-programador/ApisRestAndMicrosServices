package com.example.projetotdd.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProdutoTest {
    
    @Test
    public void deve_calcular_valor_total_produto(){
        //Arrange: preparação
        Produto produto = new Produto();
        produto.setQuantidade(10);
        produto.setDesconto(10.0);
        produto.setAcrescimo(0.0);
        produto.setValor(10.0);


        double resultadoEsperado = 90.0;

        //Act: Ação
        Double resultado = produto.calcularValorTotal();


        //Assert: Confirmação
        Assertions.assertEquals(resultadoEsperado, resultado);




}
}