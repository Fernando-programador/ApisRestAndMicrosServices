package com.example.projetotdd.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComissaoTest {
    
    @Test
    public void deve_calculcar_100_reias_comissao_venda_1000(){

        //arrange = preparação
        Comissao comissao = new Comissao();
        Double valorVenda = 1000.00;
        Double valorComissao = 0.0;

        //act = ação
        Double valorCalculado = comissao.calcular(valorVenda);


        //assert = confirmação
        Assertions.assertEquals(valorComissao, valorCalculado);
    }
}
