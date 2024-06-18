package topaggro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Input {
	public void inserir (Usuario cliente){
		//1: Definir o comando SQL
		String sql = "INSERT INTO cliente(nome, fone, email, cpf, tamanho, tipo, senha) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getTelefone());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getCpf());
			ps.setFloat(5, cliente.getTamanho());
			ps.setInt(6, cliente.getTipo());
			ps.setString(7, cliente.getSenha());
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public Usuario mostrarid(int codigo){
		//1: Definir o comando SQL
		String sql = "SELECT * FROM cliente WHERE id = ?;";
		Usuario cliente = new Usuario();
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes
			ps.setInt(1,  codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cliente.setCodigo(rs.getInt("id"));
			cliente.setNome(rs.getString("nome"));
			cliente.setTelefone(rs.getString("fone"));
			cliente.setEmail(rs.getString("email"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setTamanho(rs.getFloat("tamanho"));
			cliente.setTipo(rs.getInt("tipo"));
			cliente.setSenha(rs.getString("senha"));
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
			}
		return cliente;
		}
		public Usuario mostrarcpf(String cpf){
			//1: Definir o comando SQL
			String sql = "SELECT * FROM cliente WHERE cpf = ?;";
			Usuario cliente = new Usuario();
			//2: Abrir uma conexao
			Connectiondb factory = new Connectiondb();
			try (Connection c = factory.obtemConexao()){
				//3: Pre compila o comando
				PreparedStatement ps = c.prepareStatement(sql);
				//4: Preenche os dados faltantes
				ps.setString(1,  cpf);
				ResultSet rs = ps.executeQuery();
				rs.next();
				cliente.setCodigo(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setTelefone(rs.getString("fone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTamanho(rs.getFloat("tamanho"));
				cliente.setTipo(rs.getInt("tipo"));
				cliente.setSenha(rs.getString("senha"));
				//5: Executa o comando
				ps.execute();
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null,"Usuário não encontrado");
				cliente.setCodigo(-1);
				}
			return cliente;
			}
	public boolean Apagar(int codigo){
		//Exclusão de informações
		String sql = "DELETE FROM cliente WHERE id = ?; ";
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes
			ps.setInt(1,  codigo);
			ps.execute();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public void Atualizar(Usuario cliente){
		//1: Definir o comando SQL
		String sql = "UPDATE cliente SET nome=?, fone=?, email=?, cpf=?, tamanho=?, tipo=?, senha=? WHERE id=?;";
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes			
			ps.setInt(1, cliente.getCodigo());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getEmail());
			ps.setString(5, cliente.getCpf());
			ps.setFloat(6, cliente.getTamanho());
			ps.setInt(7, cliente.getTipo());
			ps.setString(8, cliente.getSenha());
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
			}
	}
	public List<Usuario> listar(){
		//1: Definir o comando SQL
		String sql = "SELECT * FROM cliente";
		//2: Abrir uma conexão
		List<Usuario> user = new ArrayList<>();
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pré compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Executa o comando e guarda
			//o resultado em um ResultSet
			ResultSet rs = ps.executeQuery();
			//5: itera sobre o resultado
			while (rs.next()){
				int codigo = rs.getInt("id");
				String nome = rs.getString("nome");
				String fone = rs.getString("fone");
				String email = rs.getString("email");
				String cpf = rs.getString("cpf");
				Float tamanho = rs.getFloat("tamanho");
				int tipo = rs.getInt("tipo");
				String senha = rs.getString("senha");
				String aux = String.format("id: %d, nome: %s, email: %s, fone: %s, cpf: %s, tamanho: %f, tipo: %d, senha: %s",codigo,nome,email,fone,cpf,tamanho,tipo,senha);
				JOptionPane.showMessageDialog (null, aux);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}
}
