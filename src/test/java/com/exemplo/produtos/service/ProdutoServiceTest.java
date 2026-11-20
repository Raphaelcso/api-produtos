
package com.exemplo.produtos.service;

import com.exemplo.produtos.model.Produto;
import com.exemplo.produtos.repository.ProdutoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    // Testa o cadastro de um produto com sucesso.
    @Test
    void deveSalvarProduto() {
        Produto produto = new Produto(
                "Notebook",
                "Notebook Dell",
                new BigDecimal("3500.00"),
                10
        );

        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto resultado = produtoService.salvar(produto);

        assertEquals("Notebook", resultado.getNome());
        assertEquals(new BigDecimal("3500.00"), resultado.getPreco());
        verify(produtoRepository).save(produto);
    }

    // Testa a busca de um produto existente.
    @Test
    void deveBuscarProdutoPorId() {
        Produto produto = new Produto(
                "Mouse",
                "Mouse sem fio",
                new BigDecimal("100.00"),
                20
        );

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        Produto resultado = produtoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Mouse", resultado.getNome());
        verify(produtoRepository).findById(1L);
    }

    // Testa se a busca retorna 404 quando o produto não existe.
    @Test
    void deveLancarErroAoBuscarProdutoInexistente() {
        when(produtoRepository.findById(99L))
                .thenReturn(Optional.empty());

        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> produtoService.buscarPorId(99L)
        );

        assertEquals(HttpStatus.NOT_FOUND, excecao.getStatusCode());
        verify(produtoRepository).findById(99L);
    }

    // Testa se a atualização de um produto inexistente retorna erro.
    @Test
    void deveLancarErroAoAtualizarProdutoInexistente() {
        Produto produto = new Produto(
                "Teclado",
                "Teclado mecânico",
                new BigDecimal("250.00"),
                5
        );

        when(produtoRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> produtoService.atualizar(99L, produto)
        );

        verify(produtoRepository, never()).save(any(Produto.class));
    }
}
