	
	public class Produto {
	
		private String nome;
		private int codigo;
		private int quantidade;
		private double preco;
		
		
	public Produto(String nome, int codigo, int quantidade, double preco) {
		this.nome = nome;
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.preco = preco;

	}

	public String getNome() {
		return nome;
	}

	public int getCodigo() {
		return codigo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public double getPreco() {
		return preco;
	}

	public void entradaEstoque(int quantidade) {
		this.quantidade = this.quantidade + quantidade;
	
	}
	
	public boolean saidaEstoque(int quantidade) {
		if (quantidade <= 0 || quantidade > this.quantidade) {
		return false;
		}
		else {
			this.quantidade = this.quantidade - quantidade;
			return true;
		}
			
		}
	}
	

	
