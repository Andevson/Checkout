public class Produto{
    private String id;
    private String codigo;
    private int fator_de_saida;
    Produto(String id, String codigo, int fator_de_saida){
        this.id = id;
        this.codigo = codigo;
        this.fator_de_saida = fator_de_saida;
    }
    public String getId(){
        return id;
    }
    public String getCodigo(){
        return codigo;
    }
    public int getFator(){
        return fator_de_saida;
    }
}