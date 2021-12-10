public class PedidoORM {
    private String numero_pedido;
    private String quantidade;
    private String codigo_cliente;
    PedidoORM(String numero_pedido, String quantidade, String codigo_cliente){
        this.numero_pedido = numero_pedido;
        this.quantidade = quantidade;
        this.codigo_cliente = codigo_cliente;
    }
    public String getNumeroPedido(){
        return numero_pedido;
    }
    public String getQuantidade(){
        return quantidade;
    }
    public String getCodigoCliente(){
        return codigo_cliente;
    }
}
