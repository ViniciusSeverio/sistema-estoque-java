import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		ArrayList<Produto> produtos = new ArrayList<>();
		
		int opcao = 0;
		
		while (opcao != 4) {
		
		System.out.println("\n====== MENU ======");
		System.out.println("1 - Cadastrar produto");
		System.out.println("2 - Listar produtos");
		System.out.println("3 - Movimentar estoque");
		System.out.println("4 - Sair");
		
		
		opcao = sc.nextInt();
		sc.nextLine();
		
			
		if (opcao == 1) {
			
			cadastroProduto(sc, produtos);
			
		}
		else if (opcao == 2) {
			
			listarProdutos(sc, produtos);
		}
		else if (opcao == 3) {
			 System.out.println("Movimentação de estoque em desenvolvimento.");
			
		}
		else if (opcao == 4) {
			System.out.println("Programa encerrado.");
			
		}
		else {
			System.out.println("Escolha Inválida.");
		}
	}
		sc.close();
	}
		

		public static void cadastroProduto(
				Scanner sc,
				ArrayList<Produto> produtos) {
			Produto produto = new Produto();
			
				
			double ValorTotalEstoque;
				
		System.out.println("Nome do produto:");
			produto.nome = sc.nextLine();
		System.out.println("Código do produto:");
			produto.codigo = sc.nextInt();
		System.out.println("Quantidade em estoque:");
			produto.quantidade = sc.nextInt();
		System.out.println("Preço:");
			produto.preco = sc.nextDouble();
			
			
			ValorTotalEstoque = produto.quantidade * produto.preco;
		
		if (produto.nome.isBlank()) {
			System.out.println("\n\nProduto inválido: nome não informado.");
			
		}
		else if (produto.codigo <= 0) {
			System.out.println("\n\nProduto inválido: código inválido.");
			
		}
		else if (produto.quantidade < 0) {
			System.out.println("\n\nProduto inválido: quantidade Inválida.");
			
		}
		else if (produto.preco <= 0) {
			System.out.println("\n\nProduto Inválido: preço inválido.");
			
		}
		else {
			produtos.add(produto);
			System.out.println("\n\n======= PRODUTO ======= \n\nNome: " + produto.nome + "\nCódigo: " + produto.codigo + "\nQuantidade: " + produto.quantidade + "\nPreço: " + produto.preco + "\nValor total em estoque: " + ValorTotalEstoque);
			
		}
		
	}
	
		public static void listarProdutos(
				Scanner sc,
				ArrayList<Produto> produtos) {
			
			int codigoEscolhido;
			
			System.out.println("Escolha um código:");
			codigoEscolhido = sc.nextInt();
			
			boolean produtoEncontrado = false;
			
			for (int i = 0; i < produtos.size(); i++) {
				
					if  (produtos.get(i).codigo == codigoEscolhido){
						System.out.println("\n\nProdutos:\nNome:" + produtos.get(i).nome);
						System.out.println("Código:" + produtos.get(i).codigo);
						System.out.println("Quantidade:" + produtos.get(i).quantidade);
						System.out.println("Preço:" + produtos.get(i).preco);
						produtoEncontrado = true;
						System.out.println("\nProduto encontrado!");
				
					}	
				}
			if (produtoEncontrado == false) {
				System.out.println("Produto não encontrado!");
				
			}
		}
			
			
		
		
		
		
			public static void movimentacaoEstoque(
					Scanner sc,
					Produto produto) {
		
		int movimentacao, QuantidadeEntrada, QuantidadeSaida;
		
		
		
		System.out.println("\n\nDigite o tipo de movimentação: \n1 - Entrada \n2 - Saída ");
			movimentacao = sc.nextInt();
			
			
		if (movimentacao == 1) {
			System.out.println("\nQuantidade de Entrada:");
				QuantidadeEntrada = sc.nextInt();
					if (QuantidadeEntrada <= 0) {
						System.out.println("\nQuantidade de entrada inválida.");
					}
					else {
						int estoqueAnterior = produto.quantidade;
						produto.quantidade = produto.quantidade + QuantidadeEntrada;
			System.out.println("\n\nEntrada realizada com sucesso. \nNovo estoque: " + produto.quantidade);
			System.out.println("\n\n======= MOVIMENTAÇÃO =======\nTipo:" + movimentacao + "\nQuantidade: " + QuantidadeEntrada + "\nEstoque anterior: " + estoqueAnterior + "\nEstoque atual: " + produto.quantidade);
			}
			
		}
					else if (movimentacao == 2) {
			System.out.println("\nQuantidade de Saída:");
				QuantidadeSaida = sc.nextInt();
					if (QuantidadeSaida > produto.quantidade || QuantidadeSaida <= 0) {
						System.out.println("\nSaída não realizada: estoque insuficiente. \nEstoque disponível: " + produto.quantidade);
					}
					else {
						int estoqueAnterior = produto.quantidade;
						produto.quantidade = produto.quantidade - QuantidadeSaida;
			System.out.println("\n\nSaída realizada com sucesso. \nNovo estoque: " + produto.quantidade);
			System.out.println("\n\n======= MOVIMENTAÇÃO =======\nTipo:" + movimentacao + "\nQuantidade: " + QuantidadeSaida + "\nEstoque anterior: " + estoqueAnterior + "\nEstoque atual: " + produto.quantidade);
			}
		}
					else {
			System.out.println("Tipo de movimentação inválido.");
		}
			
		}
	
	}

