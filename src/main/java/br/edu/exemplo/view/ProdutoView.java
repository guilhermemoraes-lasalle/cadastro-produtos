package br.edu.exemplo.view;

import java.math.BigDecimal;
import java.util.Scanner;

import br.edu.exemplo.controller.ProdutoController;
import br.edu.exemplo.model.Produto;

public class ProdutoView {

    private final ProdutoController controller = new ProdutoController();
    private final Scanner sc = new Scanner(System.in);

    private String ler(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }

    private int inteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(ler(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um inteiro valido.");
            }
        }
    }

    private long longo(String mensagem) {
        while (true) {
            try {
                return Long.parseLong(ler(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um ID valido.");
            }
        }
    }

    private BigDecimal decimal(String mensagem) {
        while (true) {
            try {
                return new BigDecimal(ler(mensagem).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor valido.");
            }
        }
    }

    private void mostrar(Produto produto) {
        System.out.printf(
                "ID: %d | Nome: %s | Quantidade: %d | R$ %s%n",
                produto.getId(), produto.getNome(),
                produto.getQuantidade(), produto.getValorUnitario());
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n1-Cadastrar 2-Listar 3-Consultar 4-Alterar 5-Excluir 0-Sair");
            opcao = inteiro("Opcao: ");

            try {
                switch (opcao) {
                    case 1 -> {
                        controller.cadastrar(
                                ler("Nome: "),
                                inteiro("Quantidade: "),
                                decimal("Valor: "));
                        System.out.println("Produto cadastrado.");
                    }
                    case 2 -> {
                        var produtos = controller.listar();
                        if (produtos.isEmpty()) {
                            System.out.println("Nenhum produto cadastrado.");
                        } else {
                            produtos.forEach(this::mostrar);
                        }
                    }
                    case 3 -> {
                        Produto produto = controller.consultar(longo("ID: "));
                        if (produto == null) {
                            System.out.println("Nao encontrado.");
                        } else {
                            mostrar(produto);
                        }
                    }
                    case 4 -> {
                        long id = longo("ID: ");
                        Produto produto = controller.consultar(id);
                        if (produto == null) {
                            System.out.println("Nao encontrado.");
                            break;
                        }

                        mostrar(produto);
                        boolean alterado = controller.alterar(
                                id,
                                ler("Novo nome: "),
                                inteiro("Nova quantidade: "),
                                decimal("Novo valor: "));
                        System.out.println(alterado ? "Alterado." : "Nao encontrado.");
                    }
                    case 5 -> {
                        long id = longo("ID: ");
                        Produto produto = controller.consultar(id);
                        if (produto == null) {
                            System.out.println("Nao encontrado.");
                            break;
                        }

                        mostrar(produto);
                        if (ler("Confirma exclusao (S/N)? ").equalsIgnoreCase("S")) {
                            System.out.println(
                                    controller.excluir(id) ? "Excluido." : "Nao encontrado.");
                        } else {
                            System.out.println("Cancelado.");
                        }
                    }
                    case 0 -> System.out.println("Encerrando.");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }
}
