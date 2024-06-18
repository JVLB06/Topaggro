package topaggro;

public class Veiculos {
    private String modelo, setor;
    private int id_dono, codigo;
    public Veiculos(String modelo, String setor, int id_dono, int codigo){
        super();
        this.codigo = codigo;
        this.modelo = modelo;
        this.setor = setor;
        this.id_dono = id_dono;
    }
    public Veiculos(){
        super();
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }
    public int getId_dono() {
        return id_dono;
    }
    public void setId_dono(int id_dono) {
        this.id_dono = id_dono;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}

