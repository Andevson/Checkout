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
            }else{
                limpar_pedido = true;
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
            lancarMensagem("E211");
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
            lancarMensagem("E211");
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
    public static boolean cadastrarProduto(String id, String codigo){
        try{
            if(validarId(id)){
                String novo_id = id;
                String novo_codigo = codigo.substring(0, 13);
                if(novo_id.length() > 0 && novo_codigo.length() == 13){
                    gravarProduto(new Produto(novo_id, novo_codigo));
                    return true;
                }else{
                    return false;
                }
            }else{
                lancarMensagem("E311");
                return false;
            }
        }catch(StringIndexOutOfBoundsException e){
            lancarMensagem("E312");
            return false;
        }
    }
    public static void finalizarPedido(Pedido pedido){
        if(pedido.getQuantidade() > 0){
            new InterfacePedido(pedido).setVisible(true);
        }else{
            lancarMensagem("A321");
        }
    }
    public static Pedido saida(Pedido pedido, String entrada){
        Pedido p = pedido;
        if(entrada == null || entrada == "" || entrada == "\n"){
            lancarMensagem("E331");
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
                    lancarMensagem("A212");
                }else{
                    lancarMensagem("E213");
                }
            }catch(IOException e1){
                lancarMensagem("D214");
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
            lancarMensagem("E341");
            return false;
        }else if(id.charAt(0) == ' '){
            lancarMensagem("E342");
            return false;
        }
        return true;
    }
    public static void lancarMensagem(String mensagem){
        switch(mensagem){
            case "D111":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao lançar mensagem", "Mensagem não identificada.").setVisible(true);
                break;
            case "E211":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro na base de dados", "Base de dados não encontrada.").setVisible(true);
                break;
            case "A212":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Base de dados criada", "Uma nova base de dados foi criada.").setVisible(true);
                break;
            case "E213":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar base de dados", "Não foi possível ler ou criar a base de dados.").setVisible(true);
                break;
            case "D214":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar base de dados", "Não foi possível estabelecer uma conexão com a nova base de dados.").setVisible(true);
                break;
            case "E221":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao abrir arquivo", "Imagem de fundo não encontrada.").setVisible(true);
                break;
            case "E311":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao cadastrar produto", "O ID inserido é inválido.").setVisible(true);
                break;
            case "E312":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao cadastrar produto", "O código inserido é inválido.").setVisible(true);
                break;
            case "A321":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao lançar pedido", "Não há nenhum produto no pedido.").setVisible(true);
                break;
            case "A322":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Checkout concluido", "Todos os produtos tiveram saída.").setVisible(true);
                break;
            case "E331":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro na entrada de código", "O código de barras é inválido.").setVisible(true);
                break;
            case "E341":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "ID não inserido", "O ID inserido é vazio ou inválido.").setVisible(true);
                break;
            case "E342":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "ID inválido", "Primeira letra não pode ser um espaço.").setVisible(true);
                break;
            default:
                lancarMensagem("E111");
        }
    }
    public static void main(String[] args){
        getBaseDeDados();
        new InterfaceApp().setVisible(true);
    }
}