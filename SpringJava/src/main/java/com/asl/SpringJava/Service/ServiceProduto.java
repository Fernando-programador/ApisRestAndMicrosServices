package com.asl.SpringJava.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.asl.SpringJava.Atributos.Produto;
import com.asl.SpringJava.MsgErro.ResourceMsg;
import com.asl.SpringJava.Repositorio.RepositoryProduto;
import com.asl.SpringJava.Shared.ProdutoDTO;

@Service
public class ServiceProduto {

    @Autowired
    private RepositoryProduto repositoryProduto;


    /**
     * Método para retornar um produto do pacote Atributos Produto.java
     * 
     * @return retornar um produto
     */
    public List<ProdutoDTO> obterTodos() {
        //retorna uma lista de produtos model. do banco
        List<Produto> produtos = repositoryProduto.findAll();
        /*
         * criei uma dependencia mapper onde todo os produtos serão convertidos para o ProdutoDTO automaticamente
         */
        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class)).collect(Collectors.toList());
    }

    /**
     * Método uqe irá retornar um produto quando for feita uma busca por ID
     * 
     * @param id o valor no banco de dados que será localizado
     * @return retorna o produto que foi localizado pelo ID
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {
        /*
         * obtendo produto por id
         * criei um if caso não encontre o produto com uma messagem que criei que esta em MsgErro
         */
        Optional<Produto> produto = repositoryProduto.findById(id);
        if (produto.isEmpty()){
            throw new ResourceMsg("Produto com Id: " + id + "não Encontrado");
        }

        /*
         * estou convertendo um optional em um ProdutoDTO
         */
        ProdutoDTO produtodto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(produtodto);
    }

    /**
     * Método que irá acrescentar um produto
     * 
     * @param produto este é o prod da Lista creida neste repositoryTeste
     * @return vais retornar o produto que foi adicionado
     */
    public ProdutoDTO addProduto(ProdutoDTO produtoDto) {
        produtoDto.setId(null);

        //criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        //converter o ProdutoDto em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        //salvar o produto no banco de dados
        produto = repositoryProduto.save(produto);
        produtoDto.setId(produto.getId());

        //retornar o ProdutoDTO atualizado

        return produtoDto;
            }


    /**
     * Método para delatar um produto ele é void pois não retorna nada,
     * apenas vai deletar, irei colocar uma frase de deltar no controller
     * 
     * @param id do produto que será deletado
     */
    public void deletar(Integer id) {
        Optional<Produto> produto = repositoryProduto.findById(id);

        // verificar se o produto existe
        if(produto.isEmpty()){
            throw new ResourceMsg("Não foi possível deletar o produto com o Id " + id + " ...produto não existe.");
        }
        //Caso exista o produto agora é só deletar.
        repositoryProduto.deleteById(id);
    }

    /**
     * Método que irá atualizar um produto mas antes deve achar o produto pelo ID,
     * logo após irá deletar o produto, e será inserido o novo produto
     * no mesmo ID.
     * 
     * @param produto será atualizado
     * @return será retornado o produto atualizado
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
        
        //passar o id para o produtoDto
        produtoDto.setId(id);

        //criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //converter o produtoDto em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        //atualizar o produto no banco de dados
        repositoryProduto.save(produto);

        //retornar produtoDto atualozado
        return produtoDto;

    }

}
