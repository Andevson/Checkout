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
    public void saidaProduto(String codigo){
        Produto[] produtos_atual = new Produto[100];
        int n = 1;
        int i = 1;
        while(n < quantidade){
            if(!codigo.equals(produtos[n].getCodigo())){
                produtos_atual[i] = produtos[n];
                i++;
            }else{
                codigo = "vazio";
            }
            n++;
        }
        System.out.println("Antes:\n");
        for(int m = 0; m < quantidade; m++){
            System.out.println("produto: " + produtos[m] + "\n");
        }
        produtos = produtos_atual;
        quantidade--;
        System.out.println("Depois:\n");
        for(int m = 0; m < quantidade; m++){
            System.out.println("produto: " + produtos[m] + "\n");
        }
    }
}
