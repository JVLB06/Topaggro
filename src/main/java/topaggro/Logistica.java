package topaggro;

public class Logistica {
    private String producao_nome, estoque;
    private int producao_qtd, id_log;
    private int id_dono;
    public Logistica(String producao_nome, String estoque, int producao_qtd, int id_log, int id_dono) {
        super();
        this.producao_nome = producao_nome;
        this.estoque = estoque;
        this.producao_qtd = producao_qtd;
        this.id_log = id_log;
        this.id_dono = id_dono;
    }
    public Logistica(){
        super();
    }
    public String getProducao_nome() {
        return producao_nome;
    }
    public void setProducao_nome(String producao_nome) {
        this.producao_nome = producao_nome;
    }
    public String getEstoque() {
        return estoque;
    }
    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }
    public int getProducao_qtd() {
        return producao_qtd;
    }
    public void setProducao_qtd(int producao_qtd) {
        this.producao_qtd = producao_qtd;
    }
    public int getId_log() {
        return id_log;
    }
    public void setId_log(int id_log) {
        this.id_log = id_log;
    }
    public int getId_dono() {
        return id_dono;
    }
    public void setId_dono(int id_dono) {
        this.id_dono = id_dono;
    }
    
}
