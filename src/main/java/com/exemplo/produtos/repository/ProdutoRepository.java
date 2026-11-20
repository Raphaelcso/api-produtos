
package com.exemplo.produtos.repository;

import com.exemplo.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface responsável pelo acesso aos dados dos produtos.
// JpaRepository já disponibiliza operações de cadastro, consulta,
// atualização e exclusão sem precisarmos implementar os métodos.
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
