public class ProdutoORM {
    private String numero_pedido;
    private String codigo_produto;
    private int quantidade;
    ProdutoORM(String numero_pedido, String codigo_produto, int quantidade){
        this.numero_pedido = numero_pedido;
        this.codigo_produto = codigo_produto;
        this.quantidade = quantidade;
    }
    public String getNumeroPedido(){
        return numero_pedido;
    }
    public String getCodigoProduto(){
        return codigo_produto;
    }
    public int getQuantidade(){
        return quantidade;
    }
}
