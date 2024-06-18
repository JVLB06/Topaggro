package topaggro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Logistica_input {
	public void inserir (Logistica cliente){
		//1: Definir o comando SQL
		String sql = "INSERT INTO logistica(producao, estoque, producao_qtd, id_dono) "
				+ "VALUES (?, ?, ?, ?)";
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes
			ps.setString(1, cliente.getProducao_nome());
			ps.setString(2, cliente.getEstoque());
			ps.setInt(3, cliente.getProducao_qtd());
			ps.setInt(4, cliente.getId_dono());
        	//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public List<Logistica> listar(){
		//1: Definir o comando SQL
		String sql = "SELECT * FROM logistica";
		//2: Abrir uma conexão
		List<Logistica> user = new ArrayList<>();
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
				String produto = rs.getString("producao");
				String estoque = rs.getString("estoque");
				String quantidade = rs.getString("producao_qtd");
				int id_dono = rs.getInt("id_dono");
				String aux = String.format("id: %d, producao: %s, estoque: %s, producao_qtd: %s, id_dono: %d",codigo,produto,estoque,quantidade,id_dono);
				JOptionPane.showMessageDialog (null, aux);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}
	public boolean Apagar(int codigo){
		//Exclusão de informações
		String sql = "DELETE FROM logistica WHERE id = ?; ";
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
	public void Atualizar(Logistica cliente){
		//1: Definir o comando SQL
		String sql = "UPDATE logistica SET producao=?, estoque=?, producao_qtd=? WHERE id=?;";
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes			
			ps.setInt(4, cliente.getId_log());
			ps.setString(1, cliente.getProducao_nome());
			ps.setString(2, cliente.getEstoque());
			ps.setInt(3, cliente.getProducao_qtd());
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
			}
	}
	public Logistica mostrarid(int codigo){
		//1: Definir o comando SQL
		String sql = "SELECT * FROM logistica WHERE id_dono = ?;";
		Logistica cliente = new Logistica();
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes
			ps.setInt(1,  codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cliente.setId_log(rs.getInt("id"));
			cliente.setProducao_nome(rs.getString("producao"));
			cliente.setEstoque(rs.getString("estoque"));
			cliente.setProducao_qtd(rs.getInt("producao_qtd"));
			cliente.setId_dono(rs.getInt("id_dono"));
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
			}
		return cliente;
		}
		public Logistica mostraridDono(int codigo){
			//1: Definir o comando SQL
			String sql = "SELECT * FROM logistica WHERE id = ?;";
			Logistica cliente = new Logistica();
			//2: Abrir uma conexao
			Connectiondb factory = new Connectiondb();
			try (Connection c = factory.obtemConexao()){
				//3: Pre compila o comando
				PreparedStatement ps = c.prepareStatement(sql);
				//4: Preenche os dados faltantes
				ps.setInt(1,  codigo);
				ResultSet rs = ps.executeQuery();
				rs.next();
				cliente.setId_log(rs.getInt("id"));
				cliente.setProducao_nome(rs.getString("producao"));
				cliente.setEstoque(rs.getString("estoque"));
				cliente.setProducao_qtd(rs.getInt("producao_qtd"));
				cliente.setId_dono(rs.getInt("id_dono"));
				//5: Executa o comando
				ps.execute();
			}
			catch (Exception e){
				e.printStackTrace();
				}
			return cliente;
			}
}