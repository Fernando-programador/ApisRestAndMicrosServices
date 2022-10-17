package com.example.projetotdd.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class ComissaoTest {

    @TestConfiguration
static class ComissaoConfig{

    @Bean
    public Comissao comissao() {
        return new Comissao();
    }
}

@Autowired
Comissao comissao;

    
    @Test
    public void deve_calculcar_100_reias_comissao_venda_1000_com_10_por_cento(){

        //arrange = preparação
         //Comissao comissao = new Comissao();
        Double valorVenda = 1000.00;
        Double valorComissao = 100.0;

        //act = ação
        Double valorCalculado = comissao.calcular(valorVenda);


        //assert = confirmação
        Assertions.assertEquals(valorComissao, valorCalculado);
    }

    @Test
    public void deve_calculcar_300_reias_comissao_venda_2000_com_10(){

        //arrange = preparação
        //Comissao comissao = new Comissao();
        Double valorVenda = 2000.00;
        Double valorComissao = 200.0;

        //act = ação
        Double valorCalculado = comissao.calcular(valorVenda);


        //assert = confirmação
        Assertions.assertEquals(valorComissao, valorCalculado);
    }
}
