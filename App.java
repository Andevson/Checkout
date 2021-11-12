import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class App {
    public static void abrirCadastroDeProduto(String id){
        new InterfaceNovoProduto(id).setVisible(true);
    }
    public static void abrirConferencia(Pedido pedido){
        if(pedido.getQuantidade() > 0){
            new InterfacePedido(pedido).setVisible(true);
        }else{
            abrirMensagem("A321");
        }
    }
    public static void abrirConfiguracoes(){
        new InterfaceCfg().setVisible(true);
    }
    public static void abrirMensagem(String mensagem){
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
            case "E231":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro nas configurações", "Arquivo de configurações não encontrado.").setVisible(true);
                break;
            case "A232":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Configurações criadas", "Um novo arquivo de configurações foi criado.").setVisible(true);
                break;
            case "E233":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar configurações", "Não foi possível ler ou criar as configurações.").setVisible(true);
                break;
            case "E234":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar configurações", "Não foi possível estabelecer uma conexão com as novas configurações.").setVisible(true);
                break;
            case "E235":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao carregar configurações", "As configurações são inválidas.").setVisible(true);
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
            case "A343":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Nenhum produto inserido", "A entrada não possui produtos ou está faltando cabeçalho.").setVisible(true);
                break;
            case "A344":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Quantidade não inserida", "Nenhuma quantidade foi inserida.").setVisible(true);
                break;
            case "A345":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Quantidade inválida", "Quantidade não pode conter espaços.").setVisible(true);
                break;
            case "A346":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Quantidade inválida", "Quantidade tem que ser múltipla do fator do produto.").setVisible(true);
                break;
            case "A347":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Quantidade inválida", "Quantidade não pode ser nula ou negativa.").setVisible(true);
                break;
            case "A351":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Configurações salvas", "As configurações foram armazenadas.").setVisible(true);
                break;
            case "A352":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Configurações inválidas", "A ordem do ID não pode ser a mesma da quantidade.").setVisible(true);
                break;
            case "A353":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Configurações criadas", "Novas configurações foram criadas.").setVisible(true);
                break;
            default:
                abrirMensagem("E111");
        }
    }
    public static void abrirPedido(){
        Pedido pedido = new Pedido();
        new InterfaceNovoPedido(pedido).setVisible(true);
    }
    public static Pedido addProdutos(Pedido pedido, String entrada){
        boolean usar_cabecalho = Boolean.parseBoolean(getConfiguracoes()[0]);
        Byte ordem_id = Byte.parseByte(getConfiguracoes()[1]);
        Byte ordem_quantidade = Byte.parseByte(getConfiguracoes()[2]);
        boolean limpar_pedido = false;
        Scanner leitura = new Scanner(entrada);
        try{
            if(usar_cabecalho){
                leitura.nextLine();
            }
            String linha;
            do{
                String id = "";
                String quantidade = "";
                linha = leitura.nextLine();
                int i = 0;
                int n = 0;
                Byte coluna = 1;
                while(n < linha.length()){
                    for(n = 0; n < linha.length(); n++){
                        if(n == (linha.length() - 1)){
                            if(coluna == ordem_id){
                                id = linha.substring(i);
                            }else if(coluna == ordem_quantidade){
                                quantidade = linha.substring(i);
                            }
                        }else if(linha.charAt(n) == '\t'){
                            if(coluna == ordem_id){
                                id = linha.substring(i, n);
                            }else if(coluna == ordem_quantidade){
                                quantidade = linha.substring(i, n);
                            }
                            i = n + 1;
                            coluna++;
                        }
                    }
                }
                id = formatarEntrada(id);
                quantidade = formatarEntrada(quantidade);
                if(validarId(id)){
                    Produto produto = getProduto(id, "");
                    if(produto != null){
                        if(validarFator(quantidade, String.valueOf(produto.getFator()))){
                            pedido.acrescentarProduto(produto, Integer.parseInt(quantidade));
                        }else{
                            id = "";
                            limpar_pedido = true;
                        }
                    }
                }else{
                    limpar_pedido = true;
                }
            }while(leitura.hasNextLine());
        }catch(NoSuchElementException e){
            abrirMensagem("A343");
            leitura.close();
            if(limpar_pedido){pedido.limpar();}
            return pedido;
        }catch(NullPointerException e1){
            leitura.close();
            return pedido;
        }
        if(limpar_pedido){pedido.limpar();}
        leitura.close();
        return pedido;
    }
    public static void carregarBaseDeDados(){
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
                    abrirMensagem("A212");
                }else{
                    abrirMensagem("E213");
                }
            }catch(IOException e1){
                abrirMensagem("E214");
            }
        }
    }
    public static void carregarConfiguracoes(){
        try{
            FileInputStream file = new FileInputStream("config.cfg");
            // Configurações carregadas
            file.close();
        }catch(IOException e){
            // Configurações não carregadas
            abrirMensagem("E231");
            try{
                File base_de_dados = new File("config.cfg");
                base_de_dados.createNewFile();
                if(base_de_dados.exists()){
                    abrirMensagem("A232");
                }else{
                    abrirMensagem("E233");
                }
            }catch(IOException e1){
                abrirMensagem("E234");
            }
            setConfiguracoes(true, (byte)1, (byte)2);
        }
    }
    public static String[] getConfiguracoes(){
        boolean usar_cabecalho = true;
        byte ordem_id = 1;
        byte ordem_quantidade = 2;
        Boolean[] check_leitura = {false, false, false};
        String[] config = {"" + usar_cabecalho, "" + ordem_id, "" + ordem_quantidade};
        try{
            BufferedReader br = new BufferedReader(new FileReader("config.cfg"));
            String line;
            while ((line = br.readLine()) != null) {
                for(int n = 0; n < line.length(); n++){
                    if(line.charAt(n) == '='){
                        String propriedade = line.substring(0, n);
                        String valor = line.substring(n + 1);
                        if(propriedade.length() > 0 && valor.length() > 0){
                            switch (propriedade) {
                                case "USAR_CABECALHO":
                                    usar_cabecalho = Boolean.parseBoolean(valor);
                                    check_leitura[0] = true;
                                    break;
                                case "ORDEM_ID":
                                    ordem_id = Byte.parseByte(valor);
                                    check_leitura[1] = true;
                                    break;
                                case "ORDEM_QUANTIDADE":
                                    ordem_quantidade = Byte.parseByte(valor);
                                    check_leitura[2] = true;
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    }
                }
            }
            br.close();
        }catch(FileNotFoundException e){
            carregarConfiguracoes();
            return null;
        }catch(IOException e1){
            return null;
        }
        if(!check_leitura[0] || !check_leitura[1] || !check_leitura[2]){
            abrirMensagem("E235");
            setConfiguracoes(true, (byte)1, (byte)2);
        }else{
            config[0] = "" + usar_cabecalho;
            config[1] = "" + ordem_id;
            config[2] = "" + ordem_quantidade;
        }
        return config;
    }
    public static Produto getProduto(String id, String codigo){
        String produto_id = "";
        String produto_codigo = "";
        String produto_fator_de_saida = "";
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
                int i = n;
                for(i = n; i < line.length(); i++){
                    if(line.charAt(i) == '\t'){
                        produto_id = line.substring(n, i);
                        i++;
                        break;
                    }
                }
                produto_fator_de_saida = line.substring(i, line.length());
                if(validarId(produto_id) && validarCodigo(produto_codigo) && validarFator(produto_fator_de_saida) 
                && (produto_codigo.equals(codigo) || produto_id.equals(id))){
                    br.close();
                    return new Produto(produto_id, produto_codigo, Integer.parseInt(produto_fator_de_saida));
                }
            }
            br.close();
        }catch(FileNotFoundException e){
            abrirMensagem("E211");
            carregarBaseDeDados();
            return null;
        }catch(IOException e1){
            return null;
        }
        if(id.length() > 0){
            abrirCadastroDeProduto(id);
        }
        return null;
    }
    public static void setConfiguracoes(boolean usar_cabecalho, byte ordem_id, byte ordem_quantidade){
        if(ordem_id != ordem_quantidade){
            try{
                PrintWriter gravacao = new PrintWriter("config.cfg");
                gravacao.println("USAR_CABECALHO=" + usar_cabecalho);
                gravacao.println("ORDEM_ID=" + ordem_id);
                gravacao.println("ORDEM_QUANTIDADE=" + ordem_quantidade);
                gravacao.close();
            }catch(FileNotFoundException e){
                carregarConfiguracoes();
            }
            abrirMensagem("A351");
        }else{
            abrirMensagem("A352");
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
    public static boolean cadastrarProduto(String id, String codigo, String fator){
        String produto_id = formatarEntrada(id);
        String produto_codigo = formatarEntrada(codigo);
        int produto_fator = Integer.parseInt(formatarEntrada(fator));
        Produto produto = null;
        try{
            if(validarId(produto_id) && validarCodigo(produto_codigo)){
                produto = new Produto(produto_id, produto_codigo, produto_fator);
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true));
                    writer.append(produto.getCodigo());
                    writer.append("\t");
                    writer.append(produto.getId());
                    writer.append("\t");
                    writer.append(String.valueOf(produto.getFator()));
                    writer.append("\n");
                    writer.close();
                }catch(IOException e){
                    abrirMensagem("E215");
                    carregarBaseDeDados();
                    return false;
                }
            }else{
                abrirMensagem("A311");
                return false;
            }
        }catch(StringIndexOutOfBoundsException e){
            abrirMensagem("A312");
            return false;
        }
        return true;
    } 
    public static Pedido removerProdutos(Pedido pedido, String entrada){
        Pedido p = pedido;
        String entrada_formatada = formatarEntrada(entrada);
        if(validarCodigo(entrada_formatada)){
            if(p.getQuantidade() > 0){
                p.saidaProduto(entrada_formatada);
            }
        }
        return p;
    }
    public static boolean validarId(String id){
        if(id == null || id == "" || id == "\n" || id.isEmpty()){
            abrirMensagem("A341");
            return false;
        }else if(id.contains(" ")){
            abrirMensagem("A342");
            return false;
        }
        return true;
    }
    public static boolean validarCodigo(String codigo){
        if(codigo == null || codigo == "" || codigo == "\n" || codigo.isEmpty()){
            abrirMensagem("A331");
            return false;
        }else if(codigo.contains(" ")){
            abrirMensagem("A332");
            return false;
        }
        return true;
    }
    public static boolean validarFator(String fator_de_saida){
        if(fator_de_saida == null || fator_de_saida == "" || fator_de_saida == "\n" || fator_de_saida.isEmpty()){
            abrirMensagem("A344");
            return false;
        }else if(fator_de_saida.contains(" ")){
            abrirMensagem("A345");
            return false;
        }
        return true;
    }
    public static boolean validarFator(String fator_de_saida, String fator_produto){
        if(validarFator(fator_de_saida) && validarFator(fator_produto)){
            if(Integer.parseInt(fator_de_saida) > 0){
                if((Integer.parseInt(fator_de_saida) % Integer.parseInt(fator_produto)) == 0){
                    return true;
                }else{
                    abrirMensagem("A346");
                    return false;
                }
            }else{
                abrirMensagem("A347");
                return false;
            }
        }else{
            return false;
        }
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
    public static void main(String[] args){
        carregarConfiguracoes();
        carregarBaseDeDados();
        new InterfaceApp().setVisible(true);
    }
}