package br.edu.exemplo.controller;

import java.math.BigDecimal;
import java.util.List;

import br.edu.exemplo.dao.ProdutoDAO;
import br.edu.exemplo.model.Produto;

public class ProdutoController {

    private final ProdutoDAO dao = new ProdutoDAO();

    private void validar(String nome, Integer quantidade, BigDecimal valor) {
        if (nome == null || nome.isBlank() || nome.trim().length() > 120) {
            throw new IllegalArgumentException(
                    "Nome obrigatorio (maximo 120 caracteres).");
        }

        if (quantidade == null || quantidade < 0) {
            throw new IllegalArgumentException("Quantidade invalida.");
        }

        if (valor == null || valor.signum() <= 0 || valor.scale() > 2
                || valor.precision() - valor.scale() > 10) {
            throw new IllegalArgumentException("Valor unitario invalido.");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID invalido.");
        }
    }

    public void cadastrar(String nome, Integer quantidade, BigDecimal valor) {
        validar(nome, quantidade, valor);
        dao.salvar(new Produto(nome.trim(), quantidade, valor));
    }

    public List<Produto> listar() {
        return dao.listarTodos();
    }

    public Produto consultar(Long id) {
        validarId(id);
        return dao.buscarPorId(id);
    }

    public boolean alterar(Long id, String nome, Integer quantidade, BigDecimal valor) {
        validarId(id);
        validar(nome, quantidade, valor);

        Produto produto = dao.buscarPorId(id);
        if (produto == null) {
            return false;
        }

        produto.setNome(nome.trim());
        produto.setQuantidade(quantidade);
        produto.setValorUnitario(valor);
        dao.atualizar(produto);
        return true;
    }

    public boolean excluir(Long id) {
        validarId(id);
        return dao.excluir(id);
    }
}
