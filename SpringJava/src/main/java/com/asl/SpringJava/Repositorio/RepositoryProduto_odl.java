package com.asl.SpringJava.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.asl.SpringJava.Atributos.Produto;
import com.asl.SpringJava.MsgErro.ResourceMsg;


@Repository
public class RepositoryProduto_odl {
    private List<Produto> prod = new ArrayList<>();
    private Integer ultimoId = 0;

    /**
     * Método para retornar um produto do pacote Atributos Produto.java
     * @return retornar um produto 
     */
    public List<Produto> obterTodos(){
        return prod;
    }
    

    /**
     * Método  uqe irá retornar um produto quando for feita uma busca por ID
     * @param id o valor no banco de dados que será localizado
     * @return retorna o produto que foi localizado pelo ID
     */
    public Optional<Produto> obterPorId(Integer id){
        return prod.stream().filter(prod -> prod.getId() == id).findFirst();
    }


    /**
     * Método que irá acrescentar um produto 
     * @param produto este é o prod da Lista creida neste repositoryTeste
     * @return vais retornar o produto que foi adicionado
     */
    public Produto addProduto(Produto produto){
        ultimoId++; //incrementando mais um para ID

        produto.setId(ultimoId);;
        prod.add(produto);
        return produto;
    }

    /**
     * Método para delatar um produto ele é void pois não retorna nada, 
     * apenas vai deletar, irei colocar uma frase de deltar no service
     * @param id do produto que será deletado
     */
    public void deletar(Integer id){
        prod.removeIf(prod -> prod.getId() == id);
    }



    /**
     * Método que irá atualizar um produto mas antes deve achar o produto pelo ID,
     * logo após irá deletar o produto, e será inserido o novo produto 
     * no mesmo ID.
     * @param produto será atualizado
     * @return será retornado o produto atualizado
     */
    public Produto atualizar(Produto produto){
        //Primiero encontrar o id a ser atualizado
        Optional<Produto> prodAchado = obterPorId(produto.getId());
        if (prodAchado.isEmpty()){
            throw new ResourceMsg("Produto não encontrado");
        }
        // deletar o produto achado
        deletar(produto.getId());

        //inserir novo prodto
        addProduto(produto);
        produto.setId(ultimoId);
        prod.add(produto);
        return produto;

    }




}
