
package com.exemplo.produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;


@Entity

// Define o nome da tabela que será criada no PostgreSQL.
@Table(name = "produtos")
public class Produto {

    // Identifica o campo ID como chave primária da tabela.
    @Id

    // Define que o banco de dados irá gerar o ID automaticamente.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Impede que o nome seja nulo, vazio ou contenha apenas espaços.
    @NotBlank(message = "O nome é obrigatório")

    // Limita o nome a no máximo 100 caracteres.
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")

    // Define a coluna como obrigatória e com tamanho máximo de 100 caracteres.
    @Column(nullable = false, length = 100)
    private String nome;

    // Limita a descrição a no máximo 500 caracteres.
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")

    // Define o tamanho máximo da coluna no banco de dados.
    @Column(length = 500)
    private String descricao;

    // Impede que o preço seja nulo.
    @NotNull(message = "O preço é obrigatório")

    // Garante que o preço seja maior ou igual a 0.01.
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")

    // Permite até 10 dígitos inteiros e 2 casas decimais.
    @Digits(integer = 10, fraction = 2)

    // Define a coluna como obrigatória, com precisão de 12 dígitos e 2 casas decimais.
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    // Impede que a quantidade seja nula.
    @NotNull(message = "A quantidade é obrigatória")

    // Impede que a quantidade seja negativa.
    @Min(value = 0, message = "A quantidade não pode ser negativa")

    // Define a coluna como obrigatória no banco de dados.
    @Column(nullable = false)
    private Integer quantidade;

    // Construtor vazio exigido pelo JPA para criar objetos da entidade.
    public Produto() {
    }

    // Construtor que permite criar um produto informando seus dados.
    // O ID não é recebido porque será gerado automaticamente pelo banco.
    public Produto(String nome, String descricao, BigDecimal preco, Integer quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Retorna o ID do produto.
    public Long getId() {
        return id;
    }

    // Retorna o nome do produto.
    public String getNome() {
        return nome;
    }

    // Altera o nome do produto.
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna a descrição do produto.
    public String getDescricao() {
        return descricao;
    }

    // Altera a descrição do produto.
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Retorna o preço do produto.
    public BigDecimal getPreco() {
        return preco;
    }

    // Altera o preço do produto.
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    // Retorna a quantidade disponível em estoque.
    public Integer getQuantidade() {
        return quantidade;
    }

    // Altera a quantidade disponível em estoque.
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
