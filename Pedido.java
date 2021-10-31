public class Pedido {
    private Produto[] produtos;
    private int quantidade;
    Pedido(){
        produtos = new Produto[100];
        quantidade = 0;
    }
    Pedido(Produto[] produtos, int quantidade){
        this.produtos = produtos;
        this.quantidade = quantidade;
    }
    public Produto[] getProdutos(){
        return produtos;
    }
    public int getQuantidade(){
        return quantidade;
    }
    public void acrescentarProduto(Produto produto){
        quantidade += 1;
        produtos[quantidade] = produto;
    }
}
