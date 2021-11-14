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
import checkout_error.StringInvalida;
import checkout_error.StringVazia;

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
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao lançar mensagem", "Mensagem não identificada.");
                break;
            case "E211":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro na base de dados", "Base de dados não encontrada.");
                break;
            case "A212":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Base de dados criada", "Uma nova base de dados foi criada.");
                break;
            case "E213":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar base de dados", "Não foi possível ler ou criar a base de dados.");
                break;
            case "E214":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar base de dados", "Não foi possível estabelecer uma conexão com a nova base de dados.");
                break;
            case "E215":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao gravar produto", "Não foi possível armazenar dados no banco de dados.");
                break;
            case "E221":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao abrir arquivo", "Imagem de fundo não encontrada.");
                break;
            case "E231":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro nas configurações", "Arquivo de configurações não encontrado.");
                break;
            case "A232":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Configurações criadas", "Um novo arquivo de configurações foi criado.");
                break;
            case "E233":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar configurações", "Não foi possível ler ou criar as configurações.");
                break;
            case "E234":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao criar configurações", "Não foi possível estabelecer uma conexão com as novas configurações.");
                break;
            case "E235":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao carregar configurações", "As configurações são inválidas.");
                break;
            case "E241":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Erro ao carregar produto", "O produto carregado da base de dados é invalido.");
                break;
            case "A311":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Não foi possível cadastrar o produto", "Nenhum ID foi inserido.");
                break;
            case "A312":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Não foi possível cadastrar o produto", "ID não pode conter espaços.");
                break;
            case "A313":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Não foi possível cadastrar o produto", "Nenhum código foi inserido.");
                break;
            case "A314":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Não foi possível cadastrar o produto", "Código não pode conter espaços.");
                break;
            case "A315":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Não foi possível cadastrar o produto", "Nenhum fator foi inserido.");
                break;
            case "A316":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Não foi possível cadastrar o produto", "Fator não pode conter espaços.");
                break;
            case "A321":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Pedido vazio", "Não há nenhum produto no pedido.");
                break;
            case "A322":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Checkout concluido", "Todos os produtos tiveram saída.");
                break;
            case "A331":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Código não inserido", "Nenhum código de barras foi inserido.");
                break;
            case "A332":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Código inválido", "Código de barras não pode conter espaços.");
                break;
            case "F333":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Saída não efetuada", "O pedido atual está vazio.");
                break;
            case "A341":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "ID não inserido", "Nenhum ID foi inserido.");
                break;
            case "A342":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "ID inválido", "ID não pode conter espaços.");
                break;
            case "A343":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Nenhum produto inserido", "A entrada não possui produtos ou está faltando cabeçalho.");
                break;
            case "A344":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Quantidade não inserida", "Nenhuma quantidade foi inserida.");
                break;
            case "A345":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Quantidade inválida", "Quantidade não pode conter espaços.");
                break;
            case "A346":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Quantidade inválida", "Quantidade tem que ser múltipla do fator do produto.");
                break;
            case "A347":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Quantidade inválida", "Quantidade não pode ser nula ou negativa.");
                break;
            case "A351":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Configurações salvas", "As configurações foram armazenadas.");
                break;
            case "A352":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Configurações inválidas", "A ordem do ID não pode ser a mesma da quantidade.");
                break;
            case "A353":
                new InterfaceMensagem("(" + mensagem + ")" + " " + "Configurações criadas", "Novas configurações foram criadas.");
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
                validarId(id);
                Produto produto = getProduto(id, "");
                if(produto != null){
                    if(validarFator(quantidade, String.valueOf(produto.getFator()))){
                        pedido.acrescentarProduto(produto, Integer.parseInt(quantidade));
                    }else{
                        id = "";
                        limpar_pedido = true;
                    }
                }
            }while(leitura.hasNextLine());
        }catch(StringVazia e){
            abrirMensagem("A341");
            pedido.limpar();
            leitura.close();
            return pedido;
        }catch(StringInvalida e){
            abrirMensagem("A342");
            pedido.limpar();
            leitura.close();
            return pedido;
        }catch(NoSuchElementException e){
            abrirMensagem("A343");
            leitura.close();
            if(limpar_pedido){pedido.limpar();}
            return pedido;
        }catch(NullPointerException e){
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
        Produto produto;
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
                if(produto_codigo.equals(codigo) || produto_id.equals(id)){
                    produto = new Produto(produto_id, produto_codigo, Integer.parseInt(produto_fator_de_saida));
                    if(validarProduto(produto)){
                        br.close();
                        return produto;
                    }else{
                        abrirMensagem("E241");
                    }
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
            try{
                validarId(produto_id);
            }catch(StringVazia e){
                abrirMensagem("A311");
                return false;
            }catch(StringInvalida e){
                abrirMensagem("A312");
                return false;
            }
            try{
                validarCodigo(produto_codigo);
            }catch(StringVazia e){
                abrirMensagem("A313");
                return false;
            }catch(StringInvalida e){
                abrirMensagem("A314");
                return false;
            }
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
        }catch(StringIndexOutOfBoundsException e){
            abrirMensagem("A312");
            return false;
        }
        return true;
    } 
    public static Pedido removerProdutos(Pedido pedido, String entrada){
        Pedido p = pedido;
        String entrada_formatada = formatarEntrada(entrada);
        try{
            validarCodigo(entrada_formatada);
        }catch(StringVazia e){
            abrirMensagem("A331");
            return p;
        }catch(StringInvalida e){
            abrirMensagem("A332");
            return p;
        }
        if(p.getQuantidade() > 0){
            p.saidaProduto(entrada_formatada);
        }else{
            abrirMensagem("F333");
        }
        return p;
    }
    public static void validarId(String id) throws StringVazia, StringInvalida{
        if(id == null || id == "" || id == "\n" || id.isEmpty()){
            throw new StringVazia();
        }else if(id.contains(" ")){
            throw new StringInvalida();
        }
    }
    public static void validarCodigo(String codigo) throws StringVazia, StringInvalida{
        if(codigo == null || codigo == "" || codigo == "\n" || codigo.isEmpty()){
            throw new StringVazia();
        }else if(codigo.contains(" ")){
            throw new StringInvalida();
        }
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
    public static boolean validarFator(int fator_de_saida){
        if(fator_de_saida > 0){
            return true;
        }else{
            abrirMensagem("A347");
            return false;
        }
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
    public static boolean validarProduto(Produto produto){
        try{
            validarId(produto.getId());
            validarCodigo(produto.getCodigo());
            if(validarFator(produto.getFator())){
                return true;
            }else{
                return false;
            }
        }catch(StringVazia e){
            return false;
        }catch(StringInvalida e){
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