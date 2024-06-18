package topaggro;
public class Plantacao {
    private String plantas, cuidados, solo, clima;
    private boolean var_clima;
    private int id_dono, codigo;
    public Plantacao(String plantas, boolean var_clima, String cuidados, String solo, String clima, int id_dono, int codigo) {
        this.codigo = codigo;
        this.plantas = plantas;
        this.var_clima = var_clima;
        this.cuidados = cuidados;
        this.solo = solo;
        this.clima = clima;
        this.id_dono = id_dono;
    }
    public Plantacao() {
        super();
    }
    public String getSolo() {
        return solo;
    }
    public void setSolo(String solo) {
        this.solo = solo;
    }
    public String getClima() {
        return clima;
    }
    public void setClima(String clima) {
        this.clima = clima;
    }
    public String getPlantas() {
        return plantas;
    }
    public void setPlantas(String plantas) {
        this.plantas = plantas;
    }
    public boolean getVar_clima() {
        return var_clima;
    }
    public void setVar_clima(boolean var_clima) {
        this.var_clima = var_clima;
    }
    public String getCuidados() {
        return cuidados;
    }
    public void setCuidados(String cuidados) {
        this.cuidados = cuidados;
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
