import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class App {
    public static void novoPedido(){
        Pedido pedido = new Pedido();
        new InterfaceNovoPedido(pedido).setVisible(true);
    }
    public static Pedido inserirProdutos(Pedido pedido, String entrada){
        boolean limpar_pedido = false;
        String id = "";
        Produto produto = null;
        Scanner leitura = new Scanner(entrada);
        do{
            try{
                id = leitura.nextLine();
            }catch(NoSuchElementException e){
                leitura.close();
                if(limpar_pedido){pedido.limpar();}
                return pedido;
            }
            if(validarId(id)){
                String codigo = obterCodigoProduto(id);
                if(codigo != "\n"){
                    produto = new Produto(id, codigo);
                    pedido.acrescentarProduto(produto);
                }else{
                    id = "";
                    limpar_pedido = true;
                }
            }
        }while(id != "\n");
        if(limpar_pedido){pedido.limpar();}
        leitura.close();
        return pedido;
    }
    public static String obterIdProduto(String codigo){
        boolean codigo_obtido = false;
        String id = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = br.readLine()) != null && !codigo_obtido) {
                String cod = "";
                cod = line.substring(0, 13);
                if(cod.equals(codigo)){
                    id = line.substring(14);
                    codigo_obtido = true;
                }
            }
            br.close();
        }catch(FileNotFoundException e){
            new InterfaceMensagem("Erro na base de dados.", "Base de dados não encontrada!");
            getBaseDeDados();
            return "";
        }catch(IOException e1){
            return "";
        }
        return codigo_obtido ? id : "\n";
    }
    public static String obterCodigoProduto(String id){
        boolean codigo_obtido = false;
        String codigo = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = br.readLine()) != null && !codigo_obtido) {
                String data_id = line.substring(14);
                if(data_id.equals(id)){
                    codigo = line.substring(0, 13);
                    codigo_obtido = true;
                }
            }
            br.close();
        }catch(FileNotFoundException e){
            new InterfaceMensagem("Erro na base de dados.", "Base de dados não encontrada!");
            getBaseDeDados();
            return "";
        }catch(IOException e1){
            if(id.length() > 0 && !codigo_obtido){
                novoProduto(id);
            }
            return "";
        }
        if(id.length() > 0 && !codigo_obtido){
            novoProduto(id);
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
    public static void novoProduto(String id){
        new InterfaceNovoProduto(id).setVisible(true);
    }
    public static void cadastrarProduto(String id, String codigo){
        try{
            if(validarId(id)){
                String novo_id = id;
                String novo_codigo = codigo.substring(0, 13);
                if(novo_id.length() > 0 && novo_codigo.length() == 13){
                    gravarProduto(new Produto(novo_id, novo_codigo));
                }
            }else{
                new InterfaceMensagem("Dados inválidos", "O ID inserido é inválido!").setVisible(true);
            }
        }catch(StringIndexOutOfBoundsException e){
            new InterfaceMensagem("Dados inválidos", "O código inserido é inválido!").setVisible(true);
            return;
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
    public static void getBaseDeDados(){
        try{
            FileInputStream file = new FileInputStream("data.txt");
            // Base de dados carregada
            file.close();
        }catch(IOException e){
            // Base de dados não carregada
            try{
                File base_de_dados = new File("data.txt");
                base_de_dados.createNewFile();
                if(base_de_dados.exists()){
                    new InterfaceMensagem("Base de dados criada", "Uma nova base de dados foi criada.").setVisible(true);
                }else{
                    new InterfaceMensagem("Erro ao criar base de dados", "Não foi possível ler ou criar a base de dados.").setVisible(true);
                }
            }catch(IOException e1){
                new InterfaceMensagem("Erro ao criar base de dados", "Não foi possível estabelecer uma conexão com a nova base de dados.").setVisible(true);
            }
        }
    }
    public static void setIcone(JFrame tela){
        ImageIcon icone = new ImageIcon("app_icon.png");
        tela.setIconImage(icone.getImage());
    }
    public static void setIcone(JDialog tela){
        ImageIcon icone = new ImageIcon("app_icon.png");
        tela.setIconImage(icone.getImage());
    }
    public static boolean validarId(String id){
        if(id == null || id == "" || id == "\n" || id.isEmpty() || id.isBlank()){
            new InterfaceMensagem("ID não inserido", "Um dos produtos inseridos é inválido.").setVisible(true);
            return false;
        }else if(id.charAt(0) == ' '){
            new InterfaceMensagem("ID inválido", "Primeira letra não pode ser um espaço.").setVisible(true);
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        getBaseDeDados();
        new InterfaceApp().setVisible(true);
    }
}