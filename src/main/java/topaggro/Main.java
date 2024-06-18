package topaggro;
//Importações do código
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
public class Main {
    //Void: estrutura funcional do código
    public static void main(String[] args) {
        //Declaração das variáveis e dos objetos globais do código
        Usuario user = new Usuario();
        Input servico = new Input();
        Plantacao planta = new Plantacao();
        Plantacao_input servicop = new Plantacao_input();
        Logistica log = new Logistica();
        Logistica_input servicol = new Logistica_input();
        Veiculos machine = new Veiculos();
        Veiculos_input servicom = new Veiculos_input();
        boolean loop1, continuar;
        continuar = true;
        loop1 = true;

        //Tela de Boas Vindas
        JOptionPane.showMessageDialog(null, "   Olá! Seja bem vindo ao sistema de gerenciamento agrícola Topaggro!");
        int q = JOptionPane.showConfirmDialog(null, "Já tem cadastro com a gente?");
        //Ciclo da tela de login
        while(loop1){
            if (q==JOptionPane.YES_OPTION){
                //Tela de login
                user = servico.mostrarcpf(JOptionPane.showInputDialog("Digite o cpf do usuário"));
                //Validação do login
                if (user.getCodigo()!=-1){
                    String senha = JOptionPane.showInputDialog("Olá, "+user.getNome()+"! Insira sua senha:");
                    if (senha==user.getSenha()){
                        loop1 = false;
                    }
                }
                if (user.getCodigo()==-1){
                    q = JOptionPane.YES_OPTION;
                }
                else{
                    break;
                }
            }
            if (q==JOptionPane.NO_OPTION){
                //Tela de cadastro
                JOptionPane.showMessageDialog(null,"Então vamos começar já o seu cadastro!");
                user.setTipo(1);
                user.setNome(JOptionPane.showInputDialog("Qual é o seu nome?"));
                user.setTelefone(JOptionPane.showInputDialog(user.getNome()+" insira seu número de telefone(somente números):"));
                user.setEmail(JOptionPane.showInputDialog(user.getNome()+" insira seu email:"));
                user.setCpf(JOptionPane.showInputDialog(user.getNome()+" insira seu cpf(somente números):"));
                user.setTamanho(Float.parseFloat(JOptionPane.showInputDialog(user.getNome()+" insira o tamanho da sua propriedade em m²:")));
                user.setSenha(JOptionPane.showInputDialog(user.getNome()+" insira uma senha para a sua conta:"));
                JOptionPane.showMessageDialog(null,"Conta criada com sucesso");
                //Inserção das informações na DB
                servico.inserir(user);
                //Redirecionamento para tela de login
                q = JOptionPane.YES_OPTION;
            }
            if (q==JOptionPane.CANCEL_OPTION){
                //Opção de saída do programa antes do cadastro
                loop1 = false;
                continuar = false;
            }
        }
        if (continuar==true){
            //Acesso validado no programa
            //Acesso do usuário padrão (agricultor)
            if (user.getTipo()==1){
                //Variável e texto de controle
                boolean volta = true;
                System.out.print("Entrou usuário");
                //Ciclo principal do programa
                while (volta){
                    //Display de seleção de ações
                    int selecione = Integer.parseInt(JOptionPane.showInputDialog(null, ("Olá"+user.getNome()+", o que vamos fazer?\n1-Cadastrar plantação;\n2-Cadastro de logistica;\n3-Cadastrar veículos;\n4-Conferir cadastros\n0-Sair.")));
                    switch (selecione){
                        case 1:
                            //Cadastro da Plantação
                            planta.setPlantas(JOptionPane.showInputDialog("Insira o nome do espécime"));
                            planta.setSolo(JOptionPane.showInputDialog("Qual é o tipo de solo? (ex: arenoso, úmido, etc)"));
                            planta.setClima(JOptionPane.showInputDialog("Como é o clima da região? (ex: seco, molhado, chuvoso, etc)"));
                            planta.setId_dono(user.getCodigo());
                            servicop.inserir(planta);
                            break;
                        case 2:
                            //Cadastro de Terras, vulgo logistica
                            log.setProducao_nome(JOptionPane.showInputDialog("Qual a produção em andamento?"));
                            log.setEstoque(JOptionPane.showInputDialog("Qual o estoque desta produção?"));
                            log.setProducao_qtd(Integer.parseInt(JOptionPane.showInputDialog("Qual a expectativa de produção com "+log.getProducao_nome()+"? (caso não saiba, informe 0 (zero))")));
                            log.setId_dono(user.getCodigo());
                            servicol.inserir(log);
                            break;
                        case 3:
                            //Cadastro de Veiculos e maquinário
                            machine.setModelo(JOptionPane.showInputDialog("Insira o modelo do maquinário selcionado:"));
                            machine.setSetor(JOptionPane.showInputDialog("Insira o setor de atuação de "+machine.getModelo()+":"));
                            machine.setId_dono(user.getCodigo());
                            servicom.inserir(machine);
                            break;
                        case 4:
                            //Opção alternativa para ver ou editar cadastros 
                            //Tela de seleção
                            UIManager.put("OptionPane.yesButtonText", "Ver");
                            UIManager.put("OptionPane.noButtonText", "Editar");
                            int sim = JOptionPane.showConfirmDialog(null, "Ver ou Editar?", "Cadastros",JOptionPane.YES_NO_OPTION);
                            if (sim == JOptionPane.YES_OPTION){
                                //Opção para ver cadastros
                                while (true){
                                    int ver = Integer.parseInt(JOptionPane.showInputDialog("Qual cadastro você quer ver?\n1-Plantação;\n2-Estoque;\n3-Maquinário\n0-Sair"));
                                    if (ver==1){
                                        //Ver plantação
                                        List<Plantacao> list_plantas = new ArrayList<>();
	    				                list_plantas = servicop.listar();
                					    for (int index=0;index<list_plantas.size();index++) {
			                			    System.out.println(list_plantas.get(index).toString());
				    	                }
                                    }
                                    if (ver==2){
                                        //Ver estoque
                                        List<Logistica> list_log = new ArrayList<>();
					                    list_log = servicol.listar();
                					    for (int index=0;index<list_log.size();index++) {
	    		            			    System.out.println(list_log.get(index).toString());
		    			                }
                                    }
                                    if (ver==3){
                                        //Ver maquinário
                                        List<Veiculos> list_machine = new ArrayList<>();
					                    list_machine = servicom.listar();
            					        for (int index=0;index<list_machine.size();index++) {
			            			        System.out.println(list_machine.get(index).toString());
    					                }
                                    }
                                    if (ver==0){
                                        //Sair
                                        break;
                                    }
                                }
                            }
                            if (sim == JOptionPane.NO_OPTION){
                                //Editar cadastros
                                //Opção editar ou remover cadastros
                                UIManager.put("OptionPane.yesButtonText", "Editar");
                                UIManager.put("OptionPane.noButtonText", "Remover");
                                int c = JOptionPane.showConfirmDialog(null, "Editar ou Remover?", "Topaggro", JOptionPane.YES_NO_OPTION);
                                UIManager.put("OptionPane.yesButtonText", "Sim");
                                UIManager.put("OptionPane.noButtonText", "Não");
                                if (c==JOptionPane.YES_OPTION){
                                    //Opção para editar os cadastros
                                    while (true){
                                        int edit = Integer.parseInt(JOptionPane.showInputDialog("Qual cadastro você quer editar?\n1-Plantação\n2-Logística\n3-Maquinário\n4-Cadastro do usuário\n0-Sair"));
                                        if (edit==1){
                                            //Plantação
                                            List<Plantacao> list_plantas = new ArrayList<>();
                                            String table_planta = "";
	    				                    list_plantas = servicop.listar();
                                            //Listagem dos itens
                					        for (int index=0;index<list_plantas.size();index++) {
			                			        table_planta = (index+table_planta+list_plantas.get(index).toString()+"\n");
				    	                    }
                                            int qual = Integer.parseInt(JOptionPane.showInputDialog(table_planta+"Qual item editar? (insira o número do id)"));
                                            if (qual-1<=list_plantas.size()){
                                                System.out.println("Cria objeto de edição");
                                                Plantacao atualize = servicop.mostraridDono(qual);
                                                System.out.println(atualize.getCodigo());
                                                if ((JOptionPane.showConfirmDialog(null, (atualize.getPlantas()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    atualize.setPlantas(JOptionPane.showInputDialog("Insira a nova planta"));
                                                }
                                                if ((JOptionPane.showConfirmDialog(null, (atualize.getSolo()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    atualize.setSolo(JOptionPane.showInputDialog("Insira o novo solo"));
                                                }
                                                if ((JOptionPane.showConfirmDialog(null, (atualize.getClima()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    atualize.setClima(JOptionPane.showInputDialog("Insira o novo clima"));
                                                }
                                                servicop.Atualizar(atualize);
                                            }
                                        }
                                        if (edit==2){
                                            //Logística
                                            List<Logistica> list_log = new ArrayList<>();
                                            String table_log = "";
	    				                    list_log = servicol.listar();
                                            //Listagem dos itens
                					        for (int index=0;index<list_log.size();index++) {
			                			        table_log = (index+table_log+list_log.get(index).toString()+"\n");
				    	                    }
                                            int qual = Integer.parseInt(JOptionPane.showInputDialog(table_log+"Qual item editar? (insira o número do id)"));
                                            if (qual-1<=list_log.size()){
                                                Logistica atualize = servicol.mostraridDono(qual);
                                                if ((JOptionPane.showConfirmDialog(null, (atualize.getProducao_nome()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    atualize.setProducao_nome(JOptionPane.showInputDialog("Insira o nome atualizado:"));
                                                }
                                                if ((JOptionPane.showConfirmDialog(null, (atualize.getEstoque()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    atualize.setEstoque(JOptionPane.showInputDialog("Insira a quantidade estocada atual:"));
                                                }
                                                if ((JOptionPane.showConfirmDialog(null, (atualize.getProducao_qtd()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    atualize.setProducao_qtd(Integer.parseInt(JOptionPane.showInputDialog("Insira o volume atualizado (em números):")));
                                                }
                                                servicol.Atualizar(atualize);
                                            }
                                        }
                                        if (edit==3){
                                            //Maquinário
                                            List<Veiculos> list_machine = new ArrayList<>();
                                            String table_machine = "";
	    				                    list_machine = servicom.listar();
                                            //Listagem dos itens
                					        for (int index=0;index<list_machine.size();index++) {
			                			        table_machine = (index+table_machine+list_machine.get(index).toString()+"\n");
				    	                    }
                                            int qual = Integer.parseInt(JOptionPane.showInputDialog(table_machine+"Qual item editar? (insira o número do id)"));
                                            if (qual-1<=list_machine.size()){
                                                Veiculos atualize = servicom.mostraridDono(qual);
                                                if ((JOptionPane.showConfirmDialog(null, (atualize.getModelo()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    atualize.setModelo(JOptionPane.showInputDialog("Insira o novo maquinário:"));
                                                }
                                                if ((JOptionPane.showConfirmDialog(null, (atualize.getSetor()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    atualize.setSetor(JOptionPane.showInputDialog("Insira o setor de atuação:"));
                                                }    
                                                servicom.Atualizar(atualize);  
                                            }  
                                        }
                                        if (edit==4){
                                            //Editar usuário
                                            UIManager.put("OptionPane.yesButtonText", "Atualizar");
                                            UIManager.put("OptionPane.noButtonText", "Remover");
                                            int update_user = JOptionPane.showConfirmDialog(null, "Atualizar ou excluir cadastro?", null, JOptionPane.YES_NO_OPTION);
                                            UIManager.put("OptionPane.yesButtonText", "Sim");
                                            UIManager.put("OptionPane.noButtonText", "Não");
                                            //Atualizar informações do usuário
                                            if (update_user==JOptionPane.YES_OPTION){
                                                //Email
                                                if ((JOptionPane.showConfirmDialog(null, ("Email: "+user.getEmail()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    user.setEmail(JOptionPane.showInputDialog("Insira o novo email"));
                                                }
                                                //Fone
                                                if ((JOptionPane.showConfirmDialog(null, ("Fone: "+user.getTelefone()+", deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    user.setTelefone(JOptionPane.showInputDialog("Insira o novo número de telefone (somente números):"));
                                                }
                                                //Tamanho
                                                if ((JOptionPane.showConfirmDialog(null, ("Área: "+user.getTamanho()+"m², deseja atualizar essa informação?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    user.setTamanho(Float.parseFloat(JOptionPane.showInputDialog("Insira o tamanho atualizado de sua propriedade em m²:")));
                                                }
                                                //Senha
                                                if ((JOptionPane.showConfirmDialog(null, ("Deseja alterar a sua senha?"), "Topaggro", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
                                                    //Validação para alteração de senha
                                                    if (JOptionPane.showInputDialog("Insira a antiga senha:").strip()==user.getSenha()){
                                                        JOptionPane.showMessageDialog(null, "Senha correta!");
                                                        user.setSenha(JOptionPane.showInputDialog("Insira a nova senha:"));
                                                    }
                                                    else{
                                                        //Senha incorreta, output
                                                        JOptionPane.showMessageDialog(null, "Senha incorreta, tente novamente mais tarde");
                                                    }
                                                }
                                                servico.Atualizar(user);
                                            }
                                            if (update_user==JOptionPane.NO_OPTION){
                                                //Exclusão de cadastro
                                                //Mensagem de tem certeza
                                                UIManager.put("OptionPane.yesButtonText", "Sim");
                                                UIManager.put("OptionPane.noButtonText", "Não");
                                                if (JOptionPane.showConfirmDialog(null, "Fica com a gente.\nTem certeza que quer excluir o seu cadastro?\nIsso apagará todas as informações que você tem com a gente", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                                                    //Exclusão de registros de plantação
                                                    System.out.println("Excluir Plantação");
                                                    List<Plantacao> exclusaop = servicop.listar();
                                                    for (int rep=0;rep<=exclusaop.size();rep++){
                                                        servicop.Apagar(exclusaop.get(rep).getCodigo());
                                                    }
                                                    //Exclusão de registros de logistica
                                                    System.out.println("Excluir Logística");
                                                    List<Logistica> exclusaol = servicol.listar();
                                                    for (int rep=0;rep<=exclusaol.size();rep++){
                                                        servicol.Apagar(exclusaol.get(rep).getId_log());
                                                    }
                                                    //Exclusão de registros de veiculos
                                                    System.out.println("Excluir Maquinário");
                                                    List<Veiculos> exclusaom = servicom.listar();
                                                    for (int rep=0;rep<=exclusaom.size();rep++){
                                                        servicom.Apagar(exclusaom.get(rep).getCodigo());
                                                    }
                                                    //Exclusão de registro do usuário
                                                    System.out.println("Excluir Usuário");
                                                    servico.Apagar(user.getCodigo());
                                                    //Mensagem de retorno
                                                    JOptionPane.showMessageDialog(null, "Conta excluída com sucesso.\nSentimos muito que esteja indo embora.\n      Até mais...");
                                                    //Fechar o programa 
                                                    volta = false;
                                                    break;
                                                }
                                            }
                                        }
                                        if (edit==0){
                                            //Sair
                                            break;
                                        }
                                    }
                                }
                                if (c==JOptionPane.NO_OPTION){
                                    //Opção para remover cadastros
                                    while (true){
                                        int remove = Integer.parseInt(JOptionPane.showInputDialog("De onde você deseja remover uma informação?\n1-Plantacao;\n2-Logistica\n3-Maquinário\n0-Sair."));
                                        if (remove == 1){
                                            //Remover de Plantacao
                                            List<Plantacao> list_plantas = new ArrayList<>();
                                            String table_planta = "";
	    				                    list_plantas = servicop.listar();
                                            //Listagem dos itens
                					        for (int index=0;index<list_plantas.size();index++) {
			                			        table_planta = (index+table_planta+list_plantas.get(index).toString()+"\n");
				    	                    }
                                            //Remoção e validação da remoção
                                            int remove_planta = Integer.parseInt(JOptionPane.showInputDialog("Insira a posição da informação na tabela"+table_planta));
                                            boolean apagou_planta = servicop.Apagar(remove_planta);
                                            if (apagou_planta==true){
                                                JOptionPane.showMessageDialog(null, "Item excluído");
                                            }
                                            if (apagou_planta==false){
                                                JOptionPane.showMessageDialog(null, "Item não excluído ou inexistente");
                                                }
                                        }
                                        if (remove==2){
                                            //Remover de Logistica
                                            List<Logistica> list_log = new ArrayList<>();
                                            String table_log = "";
	    				                    list_log = servicol.listar();
                                            //Listagem dos itens
                					        for (int index=0;index<list_log.size();index++) {
			                			        table_log = (index+table_log+list_log.get(index).toString()+"\n");
				    	                    }
                                            //Remoção e validação da remoção
                                            int remove_log = Integer.parseInt(JOptionPane.showInputDialog("Insira a posição da informação na tabela"+table_log));
                                            boolean apagou_log = servicol.Apagar(remove_log);
                                            if (apagou_log==true){
                                                JOptionPane.showMessageDialog(null, "Item excluído");
                                            }
                                            if (apagou_log==false){
                                                JOptionPane.showMessageDialog(null, "Item não excluído ou inexistente");
                                                }
                                        }
                                        if (remove==3){
                                            //Remover de Veículos
                                            List<Veiculos> list_machine = new ArrayList<>();
                                            String table_machine = "";
	    				                    list_machine = servicom.listar();
                                            //Listagem dos itens
                					        for (int index=0;index<list_machine.size();index++) {
			                			        table_machine = (index+table_machine+list_machine.get(index).toString()+"\n");
				    	                    }
                                            //Remoção e validação da remoção
                                            int remove_machine = Integer.parseInt(JOptionPane.showInputDialog("Insira a posição da informação na tabela"+table_machine));
                                            boolean apagou_machine = servicom.Apagar(remove_machine);
                                            if (apagou_machine==true){
                                                JOptionPane.showMessageDialog(null, "Item excluído");
                                            }
                                            if (apagou_machine==false){
                                                JOptionPane.showMessageDialog(null, "Item não excluído ou inexistente");
                                                }
                                        }
                                        if (remove==0){
                                            break;
                                        }
                                    }
                                }
                            }
                            break;
                        case 0:
                            //Sair
                            volta = false;
                            break;
                    }
                }
            }
            //Entrada de usuário administrador do sistema
            if (user.getTipo()==0){
                System.out.print("Entrou admin");
                //Última parte
            }
        }
    }
}