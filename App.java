import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void novoPedido(){
        Pedido pedido = new Pedido();
        new InterfaceNovoPedido(pedido).setVisible(true);
    }
    public static Pedido inserirProdutos(Pedido pedido, String entrada){
        String codigo_produto = "";
        Produto produto = null;
        Scanner leitura = new Scanner(entrada);
        do{
            try{
                codigo_produto = leitura.nextLine().substring(0, 13);
                if(codigo_produto == null || codigo_produto == "" || codigo_produto == "\n"){
                    codigo_produto = "\n";
                }else{
                    String id = obterIdProduto(codigo_produto);
                    if(id != "\n"){
                        produto = new Produto(id, codigo_produto);
                    }else{
                        codigo_produto = "";
                    }
                }
            }catch(Exception e){
                codigo_produto = "";
                produto = null;
                leitura.close();
                return pedido;
            }
            if(codigo_produto != ""){pedido.acrescentarProduto(produto);}
        }while(codigo_produto != "\n");
        leitura.close();
        return pedido;
    }
    public static String obterIdProduto(String codigo) throws IOException{
        boolean codigo_obtido = false;
        String id = "";
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null && !codigo_obtido) {
                String cod = "";
                cod = line.substring(0, 13);
                if(cod.equals(codigo)){
                    id = line.substring(14);
                    codigo_obtido = true;
                }
            }
        }
        return codigo_obtido ? id : "\n";
    }
    public static void main(String[] args){
        novoPedido();
    }
}