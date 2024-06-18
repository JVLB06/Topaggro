package topaggro;
public class Usuario {
	private String nome, cpf, email, telefone, senha;
	private float tamanho;
	private int codigo, tipo;
	private boolean valida_senha, cadastro_novo;
	public Usuario(int codigo, String nome, String cpf, String email, String senha, float tamanho,
			boolean valida_senha, boolean cadastro, int tipo) {
		super();
		this.tipo = tipo;
		this.codigo = codigo;
		this.cadastro_novo = cadastro;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.tamanho = tamanho;
		this.valida_senha = valida_senha;
	}
	public Usuario() {
		super();
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setCadastro(boolean cadastros) {
		this.cadastro_novo = cadastros;
	}
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void lesenha(String passe) {
		if (senha == passe) {
			this.valida_senha = true;
		}
		else {
			this.valida_senha = false;
		}
	}
	public boolean isValida_senha() {
		return valida_senha;
	}
	public void setValida_senha() {
		boolean valida = false;
		if (cadastro_novo == true) {
			valida = true;
		}
		this.valida_senha = valida;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getTamanho() {
		return tamanho;
	}
	public void setTamanho(float tamanho) {
		this.tamanho = tamanho;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
