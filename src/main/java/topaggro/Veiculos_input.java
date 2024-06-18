package topaggro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Veiculos_input {
	public void inserir (Veiculos cliente){
		//1: Definir o comando SQL
		String sql = "INSERT INTO veiculos(modelo, setor, id_dono) "
				+ "VALUES (?, ?, ?)";
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes
			ps.setString(1, cliente.getModelo());
			ps.setString(2, cliente.getSetor());
			ps.setInt(3, cliente.getId_dono());
        	//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public List<Veiculos> listar(){
		//1: Definir o comando SQL
		String sql = "SELECT * FROM veiculos";
		//2: Abrir uma conexão
		List<Veiculos> user = new ArrayList<>();
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
				String modelo = rs.getString("modelo");
				String setor = rs.getString("setor");
				int id_dono = rs.getInt("id_dono");
				String aux = String.format("id: %d, modelo: %s, setor: %s, id_dono: %d",codigo,modelo,setor,id_dono);
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
		String sql = "DELETE FROM veiculos WHERE id = ?; ";
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
	public void Atualizar(Veiculos cliente){
		//1: Definir o comando SQL
		String sql = "UPDATE veiculos SET modelo=?, setor=? WHERE id=?;";
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes			
			ps.setInt(3, cliente.getCodigo());
			ps.setString(1, cliente.getModelo());
			ps.setString(2, cliente.getSetor());
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
			}
	}
	public Veiculos mostraridd(int codigo){
		//1: Definir o comando SQL
		String sql = "SELECT * FROM veiculos WHERE id_dono = ?;";
		Veiculos cliente = new Veiculos();
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
			cliente.setModelo(rs.getString("producao"));
			cliente.setSetor(rs.getString("setor"));
			cliente.setId_dono(rs.getInt("id_dono"));
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
			}
		return cliente;
		}
		public Veiculos mostraridDono(int codigo){
			//1: Definir o comando SQL
			String sql = "SELECT * FROM veiculos WHERE id = ?;";
			Veiculos cliente = new Veiculos();
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
				cliente.setModelo(rs.getString("producao"));
				cliente.setSetor(rs.getString("setor"));
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
