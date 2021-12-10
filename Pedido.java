import javax.swing.JTable;

public class Pedido {
    private String numero;
    private Produto[] produtos;
    private int[] produtos_quantidade;
    private int quantidade;
    private String codigo_cliente;
    Pedido(){
        produtos = new Produto[100];
        produtos_quantidade = new int[100];
        quantidade = 0;
    }
    Pedido(Produto[] produtos, int quantidade){
        this.produtos = produtos;
        this.quantidade = quantidade;
    }
    public String getNumero(){
        return numero;
    }
    public String getCodigoCliente(){
        return codigo_cliente;
    }
    public Produto[] getProdutos(){
        return produtos;
    }
    public int getQuantidade(){
        return quantidade;
    }
    private int[] getProdutosQuantidade(){
        return produtos_quantidade;
    }
    public int getQuantidadeAbsoluta(){
        int qty = 0;
        for(int n = 1; n <= quantidade; n++){
            qty += produtos_quantidade[n];
        }
        return qty;
    }
    public void acrescentarProduto(Produto produto, int produto_quantidade){
        if((produto_quantidade % produto.getFator()) == 0 && produto_quantidade > 0){
            quantidade += 1;
            produtos[quantidade] = produto;
            produtos_quantidade[quantidade] = produto_quantidade;
        }else{
            App.abrirMensagem("F323");
        }
    }
    public void setNumero(String numero){
        this.numero = numero;
    }
    public void setCodigoCliente(String codigo_cliente){
        this.codigo_cliente = codigo_cliente;
    }
    public void saidaProduto(String codigo){
        Produto[] produtos_atual = new Produto[100];
        int[] produtos_quantidade_atual = new int[100];
        int n = 1;
        int i = 1;
        while(n <= quantidade){
            if(!codigo.equals(produtos[n].getCodigo())){
                produtos_atual[i] = produtos[n];
                produtos_quantidade_atual[i] = produtos_quantidade[n];
                i++;
            }else{
                produtos_quantidade[n] -= produtos[n].getFator(); 
                codigo = "vazio";
                if(produtos_quantidade[n] > 0){
                    produtos_atual[i] = produtos[n];
                    produtos_quantidade_atual[i] = produtos_quantidade[n];
                    i++;
                }
            }
            n++;
        }
        produtos = produtos_atual;
        produtos_quantidade = produtos_quantidade_atual;
        quantidade = i - 1;
    }
    public JTable getTabelaProdutos(){
            Object[][] dados = new String[quantidade][3];
            String[] cabecalho = {"ID", "Código de barras", "quantidade"};
            for(int n = 0; n < quantidade; n++){
                dados[n][0] = produtos[n + 1].getId();
                dados[n][1] = produtos[n + 1].getCodigo();
                dados[n][2] = String.valueOf(getProdutosQuantidade()[n + 1]/ getProdutos()[n + 1].getFator());
            }
            return new JTable(dados, cabecalho);
    }
    public void limpar(){
        quantidade = 0;
        codigo_cliente = null;
    }
}
