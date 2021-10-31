public class Produto{
    private String id;
    private String codigo;
    Produto(String id, String codigo){
        this.id = id;
        this.codigo = codigo;
    }
    public String getId(){
        return id;
    }
    public String getCodigo(){
        return codigo;
    }
}