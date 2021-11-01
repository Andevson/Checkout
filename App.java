import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static void novoPedido(){
        Pedido pedido = new Pedido();
        new InterfaceNovoPedido(pedido).setVisible(true);
    }
    public static Pedido inserirProdutos(Pedido pedido, String entrada){
        String id = "";
        Produto produto = null;
        Scanner leitura = new Scanner(entrada);
        do{
            try{
                id = leitura.nextLine();
            }catch(NoSuchElementException e){
                leitura.close();
                return pedido;
            }
            if(id == null || id == "" || id == "\n"){
                id = "\n";
            }else{
                String codigo = obterCodigoProduto(id);
                if(codigo != "\n"){
                    produto = new Produto(id, codigo);
                }else{
                    id = "";
                }
            }
            if(id != ""){
                pedido.acrescentarProduto(produto);
                id = "";
            }
        }while(id != "\n");
        leitura.close();
        return pedido;
    }
    public static String obterIdProduto(String codigo){
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
        }catch(Exception e){
            return "";
        }
        return codigo_obtido ? id : "\n";
    }
    public static String obterCodigoProduto(String id){
        boolean codigo_obtido = false;
        String codigo = "";
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null && !codigo_obtido) {
                String data_id = line.substring(14);
                if(data_id.equals(id)){
                    codigo = line.substring(0, 13);
                    codigo_obtido = true;
                }
            }
        }catch(Exception e){
            if(id.length() > 0 && !codigo_obtido){
                novoProduto();
            }
            return "";
        }
        if(id.length() > 0 && !codigo_obtido){
            novoProduto();
        }
        return codigo_obtido ? codigo : "\n";
    }
    public static void gravarProduto(Produto produto){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true));
            writer.append(produto.getCodigo());
            writer.append(" ");
            writer.append(produto.getId());
            writer.append("\n");
            writer.close();
        }catch(IOException e){
            return;
        }
    }
    public static void novoProduto(){
        new InterfaceNovoProduto().setVisible(true);
    }
    public static void cadastrarProduto(String id, String codigo){
        try{
            String novo_id = id;
            String novo_codigo = codigo.substring(0, 13);
            if(novo_id.length() > 0 && novo_codigo.length() == 13){
                gravarProduto(new Produto(novo_id, novo_codigo));
            }
        }catch(StringIndexOutOfBoundsException e){
            new InterfaceMensagem("Os dados não são válidos!").setVisible(true);;
        }
    }
    public static void finalizarPedido(Pedido pedido){
        new InterfacePedido(pedido).setVisible(true);
    }
    public static Pedido saida(Pedido pedido, String entrada){
        Pedido p = pedido;
        if(entrada == null || entrada == "" || entrada == "\n"){
            System.out.println("Código inválido!");
        }else{
            String codigo = entrada.substring(0, 13);
            if(p.getQuantidade() > 0){
                p.saidaProduto(codigo);
            }
        }
        return p;
    }
    public static void main(String[] args){
        new InterfaceApp().setVisible(true);
    }
}