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
		
		System.out.println("\n\n====== MENU ======\n");
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
			
			listarProdutos(produtos);
		}
		else if (opcao == 3) {
			 movimentacaoEstoque(sc, produtos);
			
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
				
				
			double valorTotalEstoque;
				
		System.out.println("Nome do produto:");
			String nome = sc.nextLine();
		System.out.println("Código do produto:");
			int codigo = sc.nextInt();
		System.out.println("Quantidade em estoque:");
			int quantidade = sc.nextInt();
		System.out.println("Preço:");
			double preco = sc.nextDouble();
			
			Produto produto = new Produto(nome, codigo, quantidade, preco);
			
			valorTotalEstoque = produto.getQuantidade() * produto.getPreco();
		
		if (nome.isBlank()) {
			System.out.println("\n\nProduto inválido: nome não informado.");
			
		}
		else if (codigo <= 0) {
			System.out.println("\n\nProduto inválido: código inválido.");
			
		}
		else if (codigoExiste(codigo, produtos)) {
			System.out.println("\n\nProduto inválido: código já cadastrado.");
		
		}
		else if (quantidade < 0) {
			System.out.println("\n\nProduto inválido: quantidade Inválida.");
			
		}
		else if (preco <= 0) {
			System.out.println("\n\nProduto Inválido: preço inválido.");
			
		}
		else {
			produtos.add(produto);
			System.out.println("\n\n======= PRODUTO ======= \n\nNome: " + produto.getNome() + "\nCódigo: " + produto.getCodigo() + "\nQuantidade: " + produto.getQuantidade() + "\nPreço: " + produto.getPreco() + "\nValor total em estoque: " + valorTotalEstoque);
			
		}
		
	}
		public static boolean codigoExiste(
				int codigo,
				ArrayList<Produto> produtos) {
			
			for (Produto produto : produtos) {
				if(produto.getCodigo() == codigo) {
					return true;
				}
			}
			
			return false;
		}
		
	
		public static void listarProdutos(
				
				ArrayList<Produto> produtos) {
			
			if(produtos.isEmpty()) {
				System.out.println("\nNenhum produto cadastrado.");
				return;
			}
			
			for (int i = 0; i < produtos.size(); i++) {
				
						System.out.println("\n\nProdutos:\nNome: " + produtos.get(i).getNome());
						System.out.println("Código: " + produtos.get(i).getCodigo());
						System.out.println("Quantidade: " + produtos.get(i).getQuantidade());
						System.out.println("Preço: " + produtos.get(i).getPreco());
						
				}	
			}
		
			
			
		
		
		
		
			public static void movimentacaoEstoque(
					Scanner sc,
					ArrayList<Produto> produtos) {
		
		int movimentacao, quantidadeEntrada, quantidadeSaida, codigoEscolhido;
		
		
		
		System.out.println("\n\nDigite o tipo de movimentação: \n1 - Entrada \n2 - Saída ");
			movimentacao = sc.nextInt();
		System.out.println("\n\nDigite o código do produto:");
			codigoEscolhido = sc.nextInt();
			
		Produto produtoEncontrado = null;
		for(int i = 0; i < produtos.size(); i++) {
			if(produtos.get(i).getCodigo() == codigoEscolhido) {
				produtoEncontrado = produtos.get(i);
				break;
			}
		}
			if(produtoEncontrado == null) {
				System.out.println("\nProduto não encontrado.");
				return;
			}
		if (movimentacao == 1) {
			System.out.println("\nQuantidade de Entrada:");
				quantidadeEntrada = sc.nextInt();
					if (quantidadeEntrada <= 0) {
						System.out.println("\nQuantidade de entrada inválida.");
					}
					else {
						int estoqueAnterior = produtoEncontrado.getQuantidade();
						produtoEncontrado.entradaEstoque(quantidadeEntrada);
			System.out.println("\n\nEntrada realizada com sucesso. \nNovo estoque: " + produtoEncontrado.getQuantidade());
			System.out.println("\n\n======= MOVIMENTAÇÃO =======\nTipo:" + movimentacao + "\nQuantidade: " + quantidadeEntrada + "\nEstoque anterior: " + estoqueAnterior + "\nEstoque atual: " + produtoEncontrado.getQuantidade());
			}
			
		}
					else if (movimentacao == 2) {
			System.out.println("\nQuantidade de Saída:");
				quantidadeSaida = sc.nextInt();
				int estoqueAnterior = produtoEncontrado.getQuantidade();
				boolean saidaRealizada = produtoEncontrado.saidaEstoque(quantidadeSaida);
				
				
					if (saidaRealizada) {
						System.out.println("\n\nSaída realizada com sucesso. \nNovo estoque: " + produtoEncontrado.getQuantidade());
						System.out.println("\n\n======= MOVIMENTAÇÃO =======\nTipo:" + movimentacao 
								+ "\nQuantidade: " + quantidadeSaida 
								+ "\nEstoque anterior: " + estoqueAnterior 
								+ "\nEstoque atual: " + produtoEncontrado.getQuantidade());
					}
					else if (quantidadeSaida <= 0){
						System.out.println("\nSaída não realizada.\nQuantidade inválida.");
					}
					else {
						System.out.println("\nSaída não realizada.\nEstoque insuficiente.");
		
			}			
		}
	}
}	

