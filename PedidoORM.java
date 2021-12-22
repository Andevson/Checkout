public class PedidoORM {
    private String numero_pedido;
    private String quantidade;
    private String codigo_barras;
    PedidoORM(String numero_pedido, String quantidade, String codigo_barras){
        this.numero_pedido = numero_pedido;
        this.quantidade = quantidade;
        this.codigo_barras = codigo_barras;
    }
    public String getNumeroPedido(){
        return numero_pedido;
    }
    public String getQuantidade(){
        return quantidade;
    }
    public String getCodigoBarras(){
        return codigo_barras;
    }
}
