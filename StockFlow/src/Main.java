import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList<>();

        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n====== MENU ======\n");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Movimentar estoque");
            System.out.println("4 - Sair");

            opcao = lerInteiro(sc, "Escolha: ");

            switch (opcao) {
                case 1: cadastroProduto(sc, produtos); break;
                case 2: listarProdutos(produtos); break;
                case 3: movimentacaoEstoque(sc, produtos); break;
                case 4: System.out.println("Programa encerrado."); break;
                default: System.out.println("Escolha inválida.");
            }
        }
        sc.close();
    }

    // ---------- Leitura segura ----------

    private static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números inteiros.");
            }
        }
    }

    private static double lerDouble(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número (ex.: 10.50).");
            }
        }
    }

    // ---------- Busca ----------

    private static Produto buscarProduto(int codigo, ArrayList<Produto> produtos) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    // ---------- Cadastro ----------

    public static void cadastroProduto(Scanner sc, ArrayList<Produto> produtos) {
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine().trim();
        int codigo = lerInteiro(sc, "Código do produto: ");
        int quantidade = lerInteiro(sc, "Quantidade em estoque: ");
        double preco = lerDouble(sc, "Preço: ");

        if (nome.isBlank()) {
            System.out.println("\nProduto inválido: nome não informado.");
        } else if (codigo <= 0) {
            System.out.println("\nProduto inválido: código inválido.");
        } else if (buscarProduto(codigo, produtos) != null) {
            System.out.println("\nProduto inválido: código já cadastrado.");
        } else if (quantidade < 0) {
            System.out.println("\nProduto inválido: quantidade inválida.");
        } else if (preco <= 0) {
            System.out.println("\nProduto inválido: preço inválido.");
        } else {
            Produto produto = new Produto(nome, codigo, quantidade, preco);
            produtos.add(produto);

            double valorTotal = produto.getQuantidade() * produto.getPreco();
            System.out.println("\n======= PRODUTO CADASTRADO =======");
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Código: " + produto.getCodigo());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.printf("Preço: %.2f%n", produto.getPreco());
            System.out.printf("Valor total em estoque: %.2f%n", valorTotal);
        }
    }

    // ---------- Listagem ----------

    public static void listarProdutos(ArrayList<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado.");
            return;
        }

        System.out.println("\n======= PRODUTOS =======");
        double totalGeral = 0;

        for (Produto produto : produtos) {
            double valorProduto = produto.getQuantidade() * produto.getPreco();
            totalGeral += valorProduto;

            System.out.println("\nNome: " + produto.getNome());
            System.out.println("Código: " + produto.getCodigo());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.printf("Preço: %.2f%n", produto.getPreco());
            System.out.printf("Valor em estoque: %.2f%n", valorProduto);
        }

        System.out.printf("%nValor total geral em estoque: %.2f%n", totalGeral);
    }

    // ---------- Movimentação ----------

    public static void movimentacaoEstoque(Scanner sc, ArrayList<Produto> produtos) {
        System.out.println("\nTipo de movimentação:\n1 - Entrada\n2 - Saída");
        int tipo = lerInteiro(sc, "Escolha: ");

        if (tipo != 1 && tipo != 2) {
            System.out.println("\nTipo de movimentação inválido.");
            return;
        }

        int codigo = lerInteiro(sc, "Código do produto: ");
        Produto produto = buscarProduto(codigo, produtos);

        if (produto == null) {
            System.out.println("\nProduto não encontrado.");
            return;
        }

        int estoqueAnterior = produto.getQuantidade();

        if (tipo == 1) {
            int quantidade = lerInteiro(sc, "Quantidade de entrada: ");

            if (quantidade <= 0) {
                System.out.println("\nQuantidade de entrada inválida.");
                return;
            }

            produto.entradaEstoque(quantidade);
            mostrarMovimentacao("Entrada", quantidade, estoqueAnterior, produto);

        } else {
            int quantidade = lerInteiro(sc, "Quantidade de saída: ");

            if (quantidade <= 0) {
                System.out.println("\nSaída não realizada.\nQuantidade inválida.");
                return;
            }

            if (produto.saidaEstoque(quantidade)) {
                mostrarMovimentacao("Saída", quantidade, estoqueAnterior, produto);
            } else {
                System.out.println("\nSaída não realizada.\nEstoque insuficiente.");
            }
        }
    }

    private static void mostrarMovimentacao(String tipo, int quantidade,
                                            int estoqueAnterior, Produto produto) {
        System.out.println("\n======= MOVIMENTAÇÃO =======");
        System.out.println("Tipo: " + tipo);
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Estoque anterior: " + estoqueAnterior);
        System.out.println("Estoque atual: " + produto.getQuantidade());
    }
}

