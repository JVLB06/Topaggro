package topaggro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Plantacao_input {
	public void inserir (Plantacao cliente){
		//1: Definir o comando SQL
		String sql = "INSERT INTO plantacao(plantas, solo, clima, id_dono) "
				+ "VALUES (?, ?, ?, ?)";
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes
			ps.setString(1, cliente.getPlantas());
			ps.setString(2, cliente.getSolo());
			ps.setString(3, cliente.getClima());
			ps.setInt(4, cliente.getId_dono());
        	//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public List<Plantacao> listar(){
		//1: Definir o comando SQL
		String sql = "SELECT * FROM plantacao";
		//2: Abrir uma conexão
		List<Plantacao> user = new ArrayList<>();
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
				String plantas = rs.getString("plantas");
				String solo = rs.getString("solo");
				String clima = rs.getString("clima");
				int id_dono = rs.getInt("id_dono");
				String aux = String.format("id: %d, plantas: %s, solo: %s, clima: %s, id_dono: %d",codigo,plantas,solo,clima,id_dono);
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
		String sql = "DELETE FROM plantacao WHERE id = ?; ";
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
	public void Atualizar(Plantacao cliente){
		//1: Definir o comando SQL
		String sql = "UPDATE plantacao SET plantas=?, solo=?, clima=? WHERE id=?;";
		//2: Abrir uma conexao
		Connectiondb factory = new Connectiondb();
		try (Connection c = factory.obtemConexao()){
			//3: Pre compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltantes			
			ps.setInt(4, cliente.getCodigo());
			ps.setString(1, cliente.getPlantas());
			ps.setString(2, cliente.getSolo());
			ps.setString(3, cliente.getClima());
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
			}
	}
	public Plantacao mostrarid(int codigo){
		//1: Definir o comando SQL
		String sql = "SELECT * FROM plantacao WHERE id_dono = ?;";
		Plantacao cliente = new Plantacao();
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
			cliente.setPlantas(rs.getString("planta"));
			cliente.setSolo(rs.getString("solo"));
			cliente.setClima(rs.getString("clima"));
			cliente.setId_dono(rs.getInt("id_dono"));
			//5: Executa o comando
			ps.execute();
		}
		catch (Exception e){
			e.printStackTrace();
			}
		return cliente;
		}
		public Plantacao mostraridDono(int codigo){
			//1: Definir o comando SQL
			String sql = "SELECT * FROM plantacao WHERE id = ?;";
			Plantacao cliente = new Plantacao();
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
				cliente.setPlantas(rs.getString("planta"));
				cliente.setSolo(rs.getString("solo"));
				cliente.setClima(rs.getString("clima"));
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