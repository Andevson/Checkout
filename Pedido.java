import javax.swing.JTable;

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
        while(n <= quantidade){
            if(!codigo.equals(produtos[n].getCodigo())){
                produtos_atual[i] = produtos[n];
                i++;
            }else{
                codigo = "vazio";
            }
            n++;
        }
        produtos = produtos_atual;
        quantidade = i - 1;
    }
    public JTable getTabelaProdutos(){
            Object[][] dados = new String[quantidade][2];
            String[] cabecalho = {"ID", "Código de barras"};
            for(int n = 0; n < quantidade; n++){
                dados[n][0] = produtos[n + 1].getId();
                dados[n][1] = produtos[n + 1].getCodigo();
            }
            return new JTable(dados, cabecalho);
    }
}
