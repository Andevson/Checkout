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
        Scanner leitura = new Scanner(entrada);
        do{
            try{
                id = formatarEntrada(leitura.nextLine());
            }catch(NoSuchElementException e){
                leitura.close();
                if(limpar_pedido){pedido.limpar();}
                return pedido;
            }
            if(validarId(id)){
                Produto produto = getProduto(id, "");
                if(produto != null){
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
    public static void gravarProduto(Produto produto){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true));
            writer.append(produto.getCodigo());
            writer.append("\t");
            writer.append(produto.getId());
            writer.append("\n");
            writer.close();
        }catch(IOException e){
            lancarMensagem("E215");
            getBaseDeDados();
            return;
        }
    }
    public static void novoProduto(String id){
        new InterfaceNovoProduto(id).setVisible(true);
    }
    public static boolean cadastrarProduto(String id, String codigo){
        String produto_id = formatarEntrada(id);
        String produto_codigo = formatarEntrada(codigo);
        try{
            if(validarId(produto_id) && validarCodigo(produto_codigo)){
                gravarProduto(new Produto(produto_id, produto_codigo));
                return true;
            }else{
                lancarMensagem("A311");
                return false;
            }
        }catch(StringIndexOutOfBoundsException e){
            lancarMensagem("A312");
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
        String entrada_formatada = formatarEntrada(entrada);
        if(validarCodigo(entrada_formatada)){
            if(p.getQuantidade() > 0){
                p.saidaProduto(entrada_formatada);
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
                lancarMensagem("E214");
            }
        }
    }
    public static Produto getProduto(String id, String codigo){
        String produto_id = "";
        String produto_codigo = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                int n = 0;
                for(n = 0; n < line.length(); n++){
                    if(line.charAt(n) == '\t'){
                        produto_codigo = line.substring(0, n);
                        n++;
                        break;
                    }
                }
                produto_id = line.substring(n, line.length());
                if(validarId(produto_id) && validarCodigo(produto_codigo) && (produto_codigo.equals(codigo) || produto_id.equals(id))){
                    br.close();
                    return new Produto(produto_id, produto_codigo);
                }
            }
            br.close();
        }catch(FileNotFoundException e){
            lancarMensagem("E211");
            getBaseDeDados();
            return null;
        }catch(IOException e1){
            return null;
        }
        if(id.length() > 0){
            novoProduto(id);
        }else if(codigo.length() > 0){
            novoProduto(codigo);
        }
        return null;
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
        if(id == null || id == "" || id == "\n" || id.isEmpty()){
            lancarMensagem("A341");
            return false;
        }else if(id.contains(" ")){
            lancarMensagem("A342");
            return false;
        }
        return true;
    }
    public static boolean validarCodigo(String codigo){
        if(codigo == null || codigo == "" || codigo == "\n" || codigo.isEmpty()){
            lancarMensagem("A331");
            return false;
        }else if(codigo.contains(" ")){
            lancarMensagem("A332");
            return false;
        }
        return true;
    }
    public static String formatarEntrada(String entrada){
        String entrada_valida = "";
        for(int n = 0; n < entrada.length(); n++){
            if(entrada.charAt(n) == '\n' || entrada.charAt(n) == '\t'){
                entrada_valida = entrada.substring(0, n);
                break;
            }
            if(n == (entrada.length() - 1)){
                entrada_valida = entrada.substring(0, n + 1);
            }
        }
        return entrada_valida;
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
            case "E214":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar base de dados", "Não foi possível estabelecer uma conexão com a nova base de dados.").setVisible(true);
                break;
            case "E215":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao gravar produto", "Não foi possível armazenar dados no banco de dados.").setVisible(true);
                break;
            case "E221":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao abrir arquivo", "Imagem de fundo não encontrada.").setVisible(true);
                break;
            case "A311":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Não foi possível cadastrar o produto", "Os dados inseridos são inválidos.").setVisible(true);
                break;
            case "A312":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Não foi possível cadastrar o produto", "O código inserido é inválido.").setVisible(true);
                break;
            case "A321":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Pedido vazio", "Não há nenhum produto no pedido.").setVisible(true);
                break;
            case "A322":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Checkout concluido", "Todos os produtos tiveram saída.").setVisible(true);
                break;
            case "A331":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Código não inserido", "Nenhum código de barras foi inserido.").setVisible(true);
                break;
            case "A332":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Código inválido", "Código de barras não pode conter espaços.").setVisible(true);
                break;
            case "A341":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "ID não inserido", "Nenhum ID foi inserido.").setVisible(true);
                break;
            case "A342":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "ID inválido", "ID não pode conter espaços.").setVisible(true);
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